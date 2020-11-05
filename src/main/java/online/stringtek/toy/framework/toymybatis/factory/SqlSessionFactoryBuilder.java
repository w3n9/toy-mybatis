package online.stringtek.toy.framework.toymybatis.factory;

import online.stringtek.toy.framework.toymybatis.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.xml.parsers.SAXParser;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream in){
        //使用dom4j解析配置文件保存到Configuration对象中
        return null;
    }
    private Configuration parse(InputStream in) throws DocumentException {
        Document document = new SAXReader().read(in);
        Element root = document.getRootElement();
        return null;
    }
}
