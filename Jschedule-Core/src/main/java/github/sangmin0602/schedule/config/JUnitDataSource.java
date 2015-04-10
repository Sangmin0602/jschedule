package github.sangmin0602.schedule.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = { "github.sangmin0602.schedule.*" })
@Profile("junit")
@PropertySource("classpath:db/dbconn.properties")
public class JUnitDataSource {
	@Autowired
	private Environment env ;
	
	@Bean(destroyMethod="close")
	public DataSource dataSource() {
		System.out.println("YYYY");
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(env.getProperty("junit.driver"));
		ds.setUrl(env.getProperty("junit.url"));
		ds.setUsername(env.getProperty("junit.user"));
		ds.setPassword(env.getProperty("junit.pass"));
		
		return ds;
	}
}
