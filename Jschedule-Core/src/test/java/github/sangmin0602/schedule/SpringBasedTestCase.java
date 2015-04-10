package github.sangmin0602.schedule;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import testutils.db.DbTestHelper;
import testutils.db.MySqlQueryParser;
import testutils.db.SqlParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:schedule-core.xml"})
@ActiveProfiles("junit")
public class SpringBasedTestCase {
	@Autowired
	protected ApplicationContext ctx ;

	protected static DbTestHelper helper ;

	private static SqlParser mysqlParser;

	private static Connection conn;
	
	private static InputStream cachedInputStream ;
	
	private static String QUERY_DDL = "ddl-20150410-test.sql";
	private static String QUERY_TEST_DML = "dml-test.sql";
	
	@BeforeClass
	public static void setUpClass() throws BeansException, Exception {
		helper = new DbTestHelper();
		mysqlParser = new MySqlQueryParser();
		
		/* caching query file data to memory */
		InputStream queryFileStream = DbTestHelper.class
				.getClassLoader()
				.getResourceAsStream(QUERY_TEST_DML);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024*512);
		BufferedInputStream bis = new BufferedInputStream(queryFileStream);
		int b ;
		while ( (b = bis.read()) >= 0 ) {
			bos.write(b);
		}
		cachedInputStream = new ByteArrayInputStream(bos.toByteArray());
		cachedInputStream.mark(0);

		String url = "jdbc:mysql://localhost:3306/test_scheduledb";
		String user = "root";
		String password = "123456";
		conn = DriverManager.getConnection(url, user, password);
	}
	
	@Before
	public void setUp() throws Exception {
		SqlSessionFactory factory = ctx.getBean("sqlSessionFactory", DefaultSqlSessionFactory.class);
		
		cachedInputStream.reset();
		helper.resetDB(conn, cachedInputStream, mysqlParser);
//		ctx = new ClassPathXmlApplicationContext("schedule-core.xml");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void dunny() {}

}
