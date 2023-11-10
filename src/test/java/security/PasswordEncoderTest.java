package security;

import org.bookbook.config.RootConfig;
import org.bookbook.config.SecurityConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		
	RootConfig.class,
	SecurityConfig.class
})

@Log4j

public class PasswordEncoderTest {

	@Autowired
	private PasswordEncoder pwEncoder;
	
	

}
