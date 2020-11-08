package online.stringtek.toy.framework.toymybatis.pojo;


import lombok.Data;

@Data
public class MappedStatement {
    private String id;
    private String resultType;
    private String parameterType;
    private BoundSQL boundSQL;
}
