package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author qishuo1
 * @create 2021/1/27 11:40
 */
public class MybatisTest {
    /**
     * 测试传统方法
     */
    public void test1() throws IOException {

        //读取配置文件,读成字节输入流,注意现在还没有解析
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        //读取配置文件,build方法是真正初始化
        //build()方法的作用
        //解析配置文件,将配置文件封装成Configuration对象,创建DefaultSqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

        //创建SqlSession对象执行sql,"statementId"就是对应MappedStatement的statementId
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Object> statementId = sqlSession.selectList("statementId");

    }

    /**
     * 使用mapper动态代理
     */
    public void test2() throws IOException {
        //读取配置文件,读成字节输入流,注意现在还没有解析
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        //读取配置文件,build方法是真正初始化
        //build()方法的作用
        //解析配置文件,将配置文件封装成Configuration对象,创建DefaultSqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession对象执行sql,"statementId"就是对应MappedStatement的statementId
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //使用JDK动态代理对mapper接口获取代理对象
        //扫描mapper.xml需要将扫描的接口进行加载需要在核心配置文件中配置
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.getUserByName("tom");
    }
}
