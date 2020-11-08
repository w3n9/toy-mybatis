package online.stringtek.toy.framework.toymybatis.pojo;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleExecutor implements Executor {
    public Configuration configuration;
    public SimpleExecutor(Configuration configuration){
        this.configuration=configuration;
    }
    @Override
    public <T> List<T> query(String id,Object... args) throws Exception {
        Connection connection=configuration.getDataSource().getConnection();
        MappedStatement statement = configuration.getMappedStatementMap().get(id);
        BoundSQL boundSQL = statement.getBoundSQL();
        String parameterType = statement.getParameterType();
        String resultType = statement.getResultType();
        PreparedStatement preparedStatement = connection.prepareStatement(boundSQL.getSql());
        if(args.length>0)
            handleParameter(preparedStatement,args[0],parameterType,boundSQL.getParameterNameList());
        ResultSet resultSet = preparedStatement.executeQuery();
        return handleResultSet(resultSet,resultType);
    }
    public void handleParameter(PreparedStatement preparedStatement,Object parameter,String parameterType,List<String> parameterNameList) throws Exception {
        if(parameterType!=null){
            if(!parameter.getClass().getName().equals(parameterType)){
                throw new RuntimeException("参数类型错误");
            }
            Class<?> parameterClass = parameter.getClass();
            //设置参数
            for(int i=0;i<parameterNameList.size();i++){
                String parameterName = parameterNameList.get(i);
                Field declaredField = parameterClass.getDeclaredField(parameterName);
                declaredField.setAccessible(true);
                Object val = declaredField.get(parameter);
                preparedStatement.setObject(i+1,val);
            }
        }
    }
    public <T> List<T> handleResultSet(ResultSet resultSet,String resultType) throws Exception {
        ResultSetMetaData metaData = resultSet.getMetaData();
        Class<?> resultClass = getClass().getClassLoader().loadClass(resultType);
        List<T> resultList=new ArrayList<>();
        while(resultSet.next()){
            T result = (T)resultClass.newInstance();
            for(int i=1;i<=metaData.getColumnCount();i++){
                Object val = resultSet.getObject(i);
                Field declaredField = resultClass.getDeclaredField(metaData.getColumnName(i));
                declaredField.setAccessible(true);
                declaredField.set(result,val);
            }
            resultList.add(result);
        }
        return resultList;
    }
}
