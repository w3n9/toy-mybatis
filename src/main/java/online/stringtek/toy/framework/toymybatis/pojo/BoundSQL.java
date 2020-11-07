package online.stringtek.toy.framework.toymybatis.pojo;

import lombok.Data;

import java.util.List;

@Data
public class BoundSQL {
    private String sql;
    private List<String> parameterNameList;
}
