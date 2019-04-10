package fi.haagahelia.course.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.Bookstore.domain.Category;
import fi.haagahelia.course.Bookstore.domain.CategoryRepository;

    @RunWith(SpringRunner.class)
	@DataJpaTest
	public class CategoryRepositoryTest {

		@Autowired
	    private CategoryRepository repository;
		
		@Test
	    public void createNewCategory() {
			
			Category category = new Category("Sisäinen laskentatoimi");
			repository.save(category);
			assertThat(category.getCategoryid()).isNotNull();
		}
		
		@Test
	    public void deleteCategory() {
			
			Category category = new Category("Sisäinen laskentatoimi");
			repository.save(category);
			long idc = category.getCategoryid();
			repository.delete(category);;
			assertThat(repository.findById(idc)).isEmpty();
		}
		
		@Test
		public void findByNameShoudReturnCategory() {
			List<Category> categories = repository.findByName("Kirjanpito");
			
			assertThat(categories).hasSize(1);
			
		}
	}
