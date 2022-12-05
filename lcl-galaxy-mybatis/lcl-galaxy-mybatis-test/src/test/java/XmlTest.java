
import com.lcl.mybatisnative.config.MyResources;
import com.lcl.mybatisnative.sqlsession.MySqlSession;
import com.lcl.mybatisnative.sqlsession.MySqlSessionFactorBuilder;
import com.lcl.mybatisnative.sqlsession.MySqlSessionFactory;
import com.lcl.mybatistest.mapper.UserDao;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

//@Slf4j
public class XmlTest {

//    private MySqlSessionFactory sqlSessionFactory;
//    @Before
//    public void init() throws Exception {
//        InputStream inputStream = MyResources.getResourceAsStream("SqlMapConfig.xml");
//        sqlSessionFactory = MySqlSessionFactorBuilder.build(inputStream);
//    }
//    @Test
//    public void testFindUserById() throws Exception {
//        MySqlSession sqlSession = sqlSessionFactory.openSession();
//        try{
//            UserDao userMapper = sqlSession.getMapper(UserDao.class);
//            UserDo user = userMapper.findUserById(2);
//            log.info("userDo====[{}]",user);
//        }finally {
//            sqlSession.close();
//        }
//    }
//    @Test
//    public void testFindUsersByName() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            UserDao userMapper = sqlSession.getMapper(UserDao.class);
//            List<UserDo> userDoList = userMapper.findUsersByName("lcl");
//            log.info("userDoList====[{}]",userDoList);
//        }finally {
//            sqlSession.close();
//        }
//    }
//
//
//    @Test
//    public void testInsertUser() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            UserDao userMapper = sqlSession.getMapper(UserDao.class);
//            UserDo userDo = new UserDo();
//            userDo.setUsername("qmm2");
//            userDo.setAddress("北京");
//            int row = userMapper.insertUser(userDo);
//            sqlSession.commit();
//            log.info("row====[{}]==========insertId====[{}]", row, userDo.getId());
//        }finally {
//            sqlSession.close();
//        }
//    }
//
//
//    @Test
//    public void testGetCount() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            UserDao userMapper = sqlSession.getMapper(UserDao.class);
//            int count = userMapper.getCount("qmm2");
//            log.info("count====[{}]", count);
//        }finally {
//            sqlSession.close();
//        }
//    }
//
//
//    @Test
//    public void testSelectAsAliase() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try{
//            UserDao userMapper = sqlSession.getMapper(UserDao.class);
//            UserDo user = userMapper.selectAsAliase(2);
//            log.info("userDo====[{}]",user);
//        }finally {
//            sqlSession.close();
//        }
//    }
//
//
//    @Test
//    public void testSelectUserOrderDtoByUserId() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try{
//            UserDao userMapper = sqlSession.getMapper(UserDao.class);
//            List<UserOrderDto> userOrderDto = userMapper.selectUserOrderDtoByUserName("qmm");
//            log.info("userOrderDto====[{}]",userOrderDto);
//        }finally {
//            sqlSession.close();
//        }
//    }
//
//    @Test
//    public void testSelectUserOrderDtoByUserId2() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try{
//            UserDao userMapper = sqlSession.getMapper(UserDao.class);
//            List<UserOrderDto> userOrderDto = userMapper.selectUserOrderDtoByUserName2("qmm");
//            log.info("userOrderDto====[{}]",userOrderDto);
//        }finally {
//            sqlSession.close();
//        }
//    }
//
//
//    @Test
//    public void testSelectUserOrderDtosByUserId() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try{
//            UserDao userMapper = sqlSession.getMapper(UserDao.class);
//            List<UserOrdersDto> userOrdersDtoList = userMapper.selectUserOrdersDtoByUserName("lcl");
//            log.info("userOrdersDtoList====[{}]",userOrdersDtoList);
//        }finally {
//            sqlSession.close();
//        }
//    }
//
//
//
//    @Test
//    public void testMybatisCache() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try{
//            UserDao userMapper = sqlSession.getMapper(UserDao.class);
//            UserDo user = userMapper.findUserById(2);
//            log.info("user====[{}]",user);
//            UserDo user2 = userMapper.findUserById(2);
//            log.info("user2====[{}]",user2);
//            UserDo user3 = new UserDo();
//            user3.setUsername("test");
//            userMapper.insertUser(user2);
//            UserDo user4 = userMapper.findUserById(2);
//            log.info("user4====[{}]",user4);
//        }finally {
//            sqlSession.close();
//        }
//    }
//
//    @Test
//    public void testMybatisCache2() throws Exception {
//        SqlSession sqlSession1 = sqlSessionFactory.openSession();
//        UserDao userMapper1 = sqlSession1.getMapper(UserDao.class);
//        SqlSession sqlSession2 = sqlSessionFactory.openSession();
//        UserDao userMapper2 = sqlSession2.getMapper(UserDao.class);
//        SqlSession sqlSession3 = sqlSessionFactory.openSession();
//        UserDao userMapper3 = sqlSession3.getMapper(UserDao.class);
//        SqlSession sqlSession4 = sqlSessionFactory.openSession();
//        UserDao userMapper4 = sqlSession4.getMapper(UserDao.class);
//        UserDo user1 = userMapper1.findUserById(2);
//        log.info("==========================================================");
//        log.info("user1====[{}]",user1);
//        sqlSession1.close();
//
//
//        UserDo user2 = userMapper2.findUserById(2);
//        log.info("==========================================================");
//        log.info("user2====[{}]",user2);
//        sqlSession2.close();
//
//        UserDo user3 = new UserDo();
//        user3.setUsername("test2");
//        userMapper3.insertUser(user3);
//        sqlSession3.commit();
//        sqlSession3.close();
//        log.info("==========================================================");
//        log.info("user3====[{}]",user3);
//
//        UserDo user4 = userMapper4.findUserById(2);
//        log.info("==========================================================");
//        log.info("user4====[{}]",user4);
//        sqlSession4.close();
//    }
//
//    @Test
//    public void testDynamic(){
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        UserDao mapper = sqlSession.getMapper(UserDao.class);
//        QueryVo queryVo = QueryVo.builder().sex("男").addressList(Arrays.asList("北京", "上海")).build();
//        List<UserDo> userList = mapper.findUserList(queryVo);
//        log.info("userList====[{}]",userList);
//
//        List<UserDo> userList1 = mapper.findUserList1(queryVo);
//        log.info("userList1====[{}]",userList1);
//
//        List<UserDo> userList2 = mapper.findUserList2(queryVo);
//        log.info("userList2====[{}]",userList2);
//        sqlSession.close();
//    }
}
