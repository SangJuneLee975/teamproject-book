package org.bookbook.mapper;

import org.bookbook.config.RootConfig;
import org.bookbook.domain.AuthVO;
import org.bookbook.domain.UserVO;
import org.bookbook.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j
public class MemberMapperTest {
	
	@Autowired
	private UserMapper mapper;

	@Test
	public void testRead() {
		UserVO member = mapper.read("test1");
		log.info(member);

		for(AuthVO auth : member.getAuthList()) {
			log.info(auth); //
		}
	}
}
	
