package github.sangmin0602.schedule;

import static org.junit.Assert.*;
import github.sangmin0602.schedule.dao.UserDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:schedule-core.xml"})
public class SpringBasedTestCase {
	@Autowired
	protected ApplicationContext ctx ;

	@Before
	public void setUp() throws Exception {
//		ctx = new ClassPathXmlApplicationContext("schedule-core.xml");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void dunny() {}

}
