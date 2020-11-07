package online.stringtek.toy.framework.toymybatis.factory;

import online.stringtek.toy.framework.toymybatis.io.Resources;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class SqlSessionFactoryBuilderTest {
    @Test
    void parse() throws DocumentException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        new SqlSessionFactoryBuilder().build(resourceAsStream);
    }
}