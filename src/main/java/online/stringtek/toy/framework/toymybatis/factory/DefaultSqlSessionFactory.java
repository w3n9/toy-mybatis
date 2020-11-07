package online.stringtek.toy.framework.toymybatis.factory;

import online.stringtek.toy.framework.toymybatis.pojo.Configuration;
import online.stringtek.toy.framework.toymybatis.pojo.SqlSession;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration=configuration;
    }
    @Override
    public SqlSession openSession() {
        return null;
    }
}
