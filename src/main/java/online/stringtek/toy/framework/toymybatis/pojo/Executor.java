package online.stringtek.toy.framework.toymybatis.pojo;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface Executor {
    <T> List<T> query(String id, Object... args) throws Exception;
}
