import com.zzxx.dao.UserDao;
import com.zzxx.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyBatisTest {
    SqlSession session;
    InputStream in;
    UserDao userDao;

    @Before
    public void init() throws Exception {
        // 1.加载/读取Configuration配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.通过Configuration 构建一个 SqlSessionFactory 对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3.通过工厂 来创建 SqlSession 对象
        session = factory.openSession();
        // 4.通过SqlSession 来获得 UserDao接口的 代理对象
        userDao = session.getMapper(UserDao.class);
    }
    @After
    public void destroy() throws Exception {
        // 6.提交事务
        session.commit();
        // 7.释放资源
        session.close();
        in.close();
    }

    @Test
    public void testFindAll() throws Exception {
        // 5.查询所有
        List<User> list = userDao.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void testFindById() throws Exception {
        User user = userDao.findById(42);
        System.out.println(user);
    }

    @Test
    public void testFindTotalCount() throws Exception {
        int count = userDao.findTotalCount();
        System.out.println(count);
    }

    @Test
    public void testSaveUser() throws Exception {
        User user = new User();
        user.setAddress("河南");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setUsername("small black");
        System.out.println("插入之前:" + user);
        // 5.插入新用户
        userDao.saveUser(user);
        System.out.println("插入之后:" + user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(49);
        user.setAddress("杭州");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setUsername("small black");
        userDao.updateUser(user);
        System.out.println("插入之后:" + user);
    }

    @Test
    public void testDeleteById() throws Exception {
        userDao.deleteById(50);
    }

}
