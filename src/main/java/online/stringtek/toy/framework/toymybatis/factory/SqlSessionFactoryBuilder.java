package online.stringtek.toy.framework.toymybatis.factory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import online.stringtek.toy.framework.toymybatis.parser.ConfigurationParser;
import online.stringtek.toy.framework.toymybatis.pojo.Configuration;
import online.stringtek.toy.framework.toymybatis.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import javax.xml.parsers.SAXParser;
import java.io.InputStream;
import java.util.*;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream in) throws DocumentException {
        return new DefaultSqlSessionFactory(new ConfigurationParser().parse(in));
    }

}
