package com.cfjst.piggy.util;

import java.io.IOException;
import java.io.InputStream;

import com.cfjst.piggy.dao.UserDao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * SqlSession工具类 方便调用代理接口的查询语句
 */
public class SqlUtil {

    private InputStream res = null;
    private SqlSessionFactory sessionFactory = null;
    private SqlSession sqlSession = null;

    
    public SqlUtil() {
		//读取配置文件流
		try {
            res = Resources.getResourceAsStream("config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
		//获取SqlSession工厂方法
        sessionFactory = new SqlSessionFactoryBuilder().build(res);
	}

    /**
     * 打开sqlSession
     * @return SqlSession
     */
	public SqlSession openSession() {
        
		// //生成SqlSession
        sqlSession = sessionFactory.openSession(true);
		return sqlSession;
    }

    /**
     * 关闭sqlSession
     */

}
