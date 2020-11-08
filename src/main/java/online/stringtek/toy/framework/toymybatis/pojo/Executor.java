package online.stringtek.toy.framework.toymybatis.pojo;

import java.sql.SQLException;
import java.util.Collection;

public interface Executor {
    <T> Collection<T> query(String id, Object... args) throws Exception;
}
