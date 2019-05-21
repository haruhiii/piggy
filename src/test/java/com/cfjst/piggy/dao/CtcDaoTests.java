package com.cfjst.piggy.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.cfjst.piggy.bean.CTC;
import com.cfjst.piggy.bean.Teacher;
import com.cfjst.piggy.util.SqlUtil;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 教师数据库接口测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest

public class CtcDaoTests {

    SqlSession sqlSession;
    CtcDao dao;

    @Before
    public void init() {
        sqlSession = new SqlUtil().openSession();
        dao = sqlSession.getMapper(CtcDao.class);
    }

    @After
    public void done() {
        sqlSession.close();
    }

    @Test
    public void findByIdTest() {
        // 查找测试

        List<CTC> ctcs = dao.findAllCourse();
		for(CTC ctc:ctcs){
			System.out.println(ctc);
		}

    }

;

}
