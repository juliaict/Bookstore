package fi.haagahelia.course.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.Bookstore.domain.Book;
import fi.haagahelia.course.Bookstore.domain.BookRepository;
import fi.haagahelia.course.Bookstore.domain.Category;
import fi.haagahelia.course.Bookstore.domain.CategoryRepository;
import fi.haagahelia.course.Bookstore.domain.User;
import fi.haagahelia.course.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			
			log.info("save a couple of books");
			crepository.save(new Category("Kirjanpito"));
			crepository.save(new Category("Tilintarkastus"));
			crepository.save(new Category("Verotus"));
			
			repository.save(new Book("Tilinpäätösanalyysi", "Juha-Pekka Kallunk", 2014, "978-952-1-42195-2", 58.70, crepository.findByName("Kirjanpito").get(0)));
			repository.save(new Book("Yritystutkimuksen tilinpäätösanat", "Yritystutkimus ry", 2017, "978-952-4-95427-3", 27, crepository.findByName("Tilintarkastus").get(0)));
			repository.save(new Book("Yritystutkimuksen tilinpäätösana", "Yritystutkimus ry", 2017, "978-952-4-95427-3", 27, null));
			
			//Luo käyttäjiä: admin/adminadmin user/useruser
			
			User user1 = new User("user", "$2a$10$Hkalfpe8PygrqR3WacL.B.plDXQkHoeqgAVjnZ59p1B3pzYEehoES", "user@bookstore.com", "USER");
			User user2 = new User("admin", "$2a$10$dg.BpwRAc8o4viUPDopmbuzYswkWTJhVGtiOt6HNKM8omanynQEdq", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
										
						
			
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
