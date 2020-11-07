package online.stringtek.toy.framework.toymybatis.handler;

import online.stringtek.toy.framework.toymybatis.handler.SQLHandler;
import online.stringtek.toy.framework.toymybatis.pojo.BoundSQL;
import org.junit.jupiter.api.Test;

class SQLHandlerTest {

    @Test
    void handler() {
        SQLHandler sqlHandler=new SQLHandler();
        BoundSQL boundSQL = sqlHandler.handle("select * from user where id=#{id} and name=#{name}", "#\\{(.+?)}");
        System.out.println(boundSQL.getSql());
        System.out.println("=================");
        for (String s : boundSQL.getParameterNameList()) {
            System.out.println(s);
        }
    }
}