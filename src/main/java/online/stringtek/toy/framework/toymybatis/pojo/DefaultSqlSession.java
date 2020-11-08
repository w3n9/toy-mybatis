package online.stringtek.toy.framework.toymybatis.pojo;

import java.lang.reflect.*;
import java.util.Collection;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;
    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        executor=new SimpleExecutor(configuration);
    }

    @Override
    public <T> T selectOne(String id,Class<T> clazz, Object... args) throws Exception {
        List<T> list = selectList(id, clazz, args);
        if(list.size()==1){
            return list.get(0);
        }else if(list.size()==0){
            return null;
        }
        throw new RuntimeException("返回结果大于1条");
    }

    @Override
    public <T> List<T> selectList(String id,Class<T> clazz, Object... args) throws Exception {
        return executor.query(id,args);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        Object proxyObject=Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class<?>[]{mapperClass},(proxy, method, args) -> {
            String typeName = mapperClass.getTypeName();
            String id=typeName+"."+method.getName();
            //当前只有select，没做其他判断
            MappedStatement mappedStatement = configuration.getMappedStatementMap().get(id);
            //处理select
            Type genericReturnType = method.getGenericReturnType();
            //TODO insert update delete
            if(genericReturnType instanceof ParameterizedType){
                //返回值中存在泛型
                ParameterizedType type=(ParameterizedType) genericReturnType;
                String className = type.getActualTypeArguments()[0].getTypeName();
                Class<?> clazz = Class.forName(className);
                return selectList(id,clazz,args);
            }else{
                String className = genericReturnType.getTypeName();
                Class<?> clazz = Class.forName(className);
                return selectOne(id,clazz,args);
            }
        });
        return (T)proxyObject;
    }

}
