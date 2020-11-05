package online.stringtek.toy.framework.toymybatis.io;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourcesTest {

    @Test
    void getResourceAsStream() {
        Resources.getResourceAsStream("sqlMapConfig.xml");
    }
}