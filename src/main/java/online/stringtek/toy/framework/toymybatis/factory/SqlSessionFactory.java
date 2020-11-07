package online.stringtek.toy.framework.toymybatis.factory;

import online.stringtek.toy.framework.toymybatis.pojo.Configuration;
import online.stringtek.toy.framework.toymybatis.pojo.SqlSession;

public interface SqlSessionFactory {
    SqlSession openSession();
}
