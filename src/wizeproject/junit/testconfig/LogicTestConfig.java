package wizeproject.junit.testconfig;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:wizeproject/config/spring/context-common.xml", "classpath:wizeproject/config/spring/context-datasource.xml",
		"classpath:wizeproject/config/spring/context-mapper.xml" })
public class LogicTestConfig {

}
