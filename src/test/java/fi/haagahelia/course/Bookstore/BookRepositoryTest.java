package fi.haagahelia.course.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.Bookstore.domain.Book;
import fi.haagahelia.course.Bookstore.domain.BookRepository;
import fi.haagahelia.course.Bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
    private BookRepository repository;
	
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = repository.findByTitle("Tilinpäätösanalyysi");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Juha-Pekka Kallunk");
		
	}
	
	@Test
    public void createNewBook() {
   
		Book book = new Book("Henkilökohtaisen tulon verotus", "Timo Räbinä, Matti Myrsky, Janne Myllymäki, Matti Myrsky", 2018, "978-952-341-011-1", 96.90, new Category("Henkilöverotus"));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    } 
	
	@Test
    public void deleteBook() {
		Book book = new Book("Henkilökohtaisen tulon verotus", "Timo Räbinä, Matti Myrsky, Janne Myllymäki, Matti Myrsky", 2018, "978-952-341-011-1", 96.90, new Category("Henkilöverotus"));
    	repository.save(book);
    	long idbook = book.getId();
    	repository.delete(book);
    	assertThat(repository.findById(idbook)).isEmpty();
    } 
}