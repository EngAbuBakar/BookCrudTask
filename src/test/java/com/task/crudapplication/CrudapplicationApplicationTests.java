package com.task.crudapplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrudapplicationApplicationTests {
	@Test
	void contextLoads() {
	}


//	private UserService userService;
//	@Mock
//	private UserRepository userRepository;
// @BeforeEach
//	void setUp(){
//	 this.userService=new UserService(this.userRepository);
//	// Optional<User> user = Optional.of(new User(302L, "Ahmed", "ahmed@gmail.com", 52, 152));
//	 Mockito.when(userRepository.findById(302L)).thenReturn(user);
//	}
//	@Test
//	public void testGetUserById() throws UserNotFound {
//		String name="Ahmed";
//		User userById=userService.getUserById(302L);
//		assertEquals(name,userById.getName());
//	}



}
