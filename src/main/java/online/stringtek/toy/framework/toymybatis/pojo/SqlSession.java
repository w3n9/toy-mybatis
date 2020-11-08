package online.stringtek.toy.framework.toymybatis.pojo;

import java.util.Collection;

public interface SqlSession {
    <T> T selectOne(String id,Object... args);
    <T> Collection<T> selectList(String id,Object... args);
}
