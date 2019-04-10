package fi.haagahelia.course.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.Bookstore.domain.User;
import fi.haagahelia.course.Bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
    private UserRepository repository;
	
	@Test
    public void createNewUser() {
		
		User testu = new User("testu", "$2a$10$Hkalfpe8PygrqR3WacL.B.plDXQkHoeqgAVjnZ59p1B3pzYEehoES", "testu@bookstore.com", "USER");
		repository.save(testu);
		assertThat(testu.getId()).isNotNull();
	}
	
	@Test
    public void deleteUser() {
		
		User deleteu = new User("deleteu", "$2a$10$dg.BpwRAc8o4viUPDopmbuzYswkWTJhVGtiOt6HNKM8omanynQEdq", "deleteu@bookstore.com", "USER");
		repository.save(deleteu);
		long id = deleteu.getId();
		repository.delete(deleteu);
		
		
		assertThat(repository.findById(id)).isEmpty();
	}
	
	@Test
	public void findByUsernameShoudReturnUser() {
		User user = repository.findByUsername("admin");
		
		assertThat(user).isNotNull();
		
	}
}