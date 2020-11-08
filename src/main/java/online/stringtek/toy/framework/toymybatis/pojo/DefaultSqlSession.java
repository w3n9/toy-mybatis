package online.stringtek.toy.framework.toymybatis.pojo;

import java.util.Collection;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String id, Object... args) {

        return null;
    }

    @Override
    public <T> Collection<T> selectList(String id, Object... args) {
        return null;
    }
}
