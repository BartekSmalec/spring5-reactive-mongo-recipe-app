package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    private static final String CATEGORY_DESCRIPTION = "test";

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();

    }

    @Test
    public void testFindByDescription() {
        Category category = new Category();
        category.setDescription(CATEGORY_DESCRIPTION);

        categoryReactiveRepository.save(category).block();

        Category foundCategory = categoryReactiveRepository.findByDescription(CATEGORY_DESCRIPTION).block();

        assertEquals(CATEGORY_DESCRIPTION, foundCategory.getDescription());
    }

    @Test
    public void testSaveCategory() {
        Category category = new Category();
        category.setId("id");

        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), count);
    }
}