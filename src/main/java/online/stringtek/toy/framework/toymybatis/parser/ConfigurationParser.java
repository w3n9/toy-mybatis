package online.stringtek.toy.framework.toymybatis.parser;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import online.stringtek.toy.framework.toymybatis.handler.SQLHandler;
import online.stringtek.toy.framework.toymybatis.io.Resources;
import online.stringtek.toy.framework.toymybatis.pojo.BoundSQL;
import online.stringtek.toy.framework.toymybatis.pojo.Configuration;
import online.stringtek.toy.framework.toymybatis.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ConfigurationParser {
    public Configuration parse(InputStream in) throws DocumentException {
        Configuration configuration=new Configuration();
        Document document = new SAXReader().read(in);
        configuration.setDataSource(parseDataSource(document));
        configuration.setMappedStatementMap(parseMappedStatement(document));
        return configuration;
    }

    private Map<String, MappedStatement> parseMappedStatement(Document document) throws DocumentException {
        Map<String,MappedStatement> mappedStatementMap=new HashMap<>();
        Element rootElement=document.getRootElement();
        Element mappersElement = rootElement.element("mappers");
        List<Element> mapperElementList = mappersElement.elements();
        for (Element mapperElement : mapperElementList) {
            //读取每个mapper
            String resourcePath = mapperElement.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(resourcePath);
            mappedStatementMap.putAll(parseMapper(resourceAsStream));
        }
        return mappedStatementMap;
    }
    private Map<String,MappedStatement> parseMapper(InputStream in) throws DocumentException {
        Document document = new SAXReader().read(in);
        Element rootElement = document.getRootElement();
        Map<String,MappedStatement> mappedStatementMap=new HashMap<>();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> selectElementList = rootElement.elements("select");
        for (Element selectElement : selectElementList) {
            String id = selectElement.attributeValue("id");
            String parameterType = selectElement.attributeValue("parameterType");
            String resultType = selectElement.attributeValue("resultType");
            String sql = selectElement.getTextTrim();
            MappedStatement mappedStatement=new MappedStatement();
            mappedStatement.setId(namespace+"."+id);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setResultType(resultType);
            //TODO ${}的情况等
            mappedStatement.setBoundSQL(new SQLHandler().handle(sql,"#\\{(.+?)}"));
            mappedStatementMap.put(namespace+"."+id,mappedStatement);
        }
        //TODO delete update insert
        return mappedStatementMap;
    }

    /**
     * 从配置文件中读取数据源配置信息
     * @param document 配置文件
     * @return 数据源对象
     */
    private DataSource parseDataSource(Document document){
        Element root = document.getRootElement();
        //获取dataSource标签
        Element dataSourceElement = root.element("dataSource");
        List<Element> dataSourcePropertyElementList = dataSourceElement.elements("property");
        Properties properties=new Properties();
        for (Element propertyElement : dataSourcePropertyElementList) {
            properties.put(propertyElement.attributeValue("name"),propertyElement.attributeValue("value"));
        }
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        hikariDataSource.setUsername(properties.getProperty("username"));
        hikariDataSource.setPassword(properties.getProperty("password"));
        return hikariDataSource;
    }
}
