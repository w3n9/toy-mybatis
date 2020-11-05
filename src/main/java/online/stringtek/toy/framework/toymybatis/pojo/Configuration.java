package online.stringtek.toy.framework.toymybatis.pojo;

import lombok.Data;

import javax.sql.DataSource;
import java.util.Map;

@Data
public class Configuration {
    private DataSource dataSource;
    /**
     * key: namespace+"."+id
     * val: MappedStatement对象
     */
    private Map<String,MappedStatement> mappedStatementMap;
}
