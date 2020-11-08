package online.stringtek.toy.framework.toymybatis.pojo;

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

}
