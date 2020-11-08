package online.stringtek.toy.framework.toymybatis.pojo;

import java.util.Collection;
import java.util.List;

public interface SqlSession {
    <T> T selectOne(String id,Class<T> clazz,Object... args) throws Exception;
    <T> List<T> selectList(String id,Class<T> clazz, Object... args) throws Exception;
}
