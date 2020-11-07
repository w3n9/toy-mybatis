package online.stringtek.toy.framework.toymybatis.handler;

import online.stringtek.toy.framework.toymybatis.pojo.BoundSQL;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLHandler {

    public BoundSQL handle(String sql,String pattern){
        BoundSQL boundSQL=new BoundSQL();
        Pattern regex=Pattern.compile(pattern);
        Matcher matcher = regex.matcher(sql);
        List<String> parameterNameList=new ArrayList<>();
        while(matcher.find()){
            String parameterName = matcher.group(1);
            parameterNameList.add(parameterName.trim());
        }
        boundSQL.setSql(sql.replaceAll(pattern," ? "));
        boundSQL.setParameterNameList(parameterNameList);
        return boundSQL;
    }
}
