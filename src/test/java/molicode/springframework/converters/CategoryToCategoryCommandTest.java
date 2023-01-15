package molicode.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import molicode.springframework.commands.CategoryCommand;
import molicode.springframework.domain.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryToCategoryCommandTest {

  public static final Long ID_VALUE = new Long(1L);

  public static final String DESCRIPTION = "descript";

  @InjectMocks
  CategoryToCategoryCommand converter;

  @Test
  public void testNullObject() throws Exception {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() throws Exception {
    assertNotNull(converter.convert(new Category()));
  }

  @Test
  public void convert() throws Exception {
    //given
    Category category = new Category();
    category.setId(ID_VALUE);
    category.setDescription(DESCRIPTION);

    //when
    CategoryCommand categoryCommand = converter.convert(category);

    //then
    assertEquals(ID_VALUE, categoryCommand.getId());
    assertEquals(DESCRIPTION, categoryCommand.getDescription());

  }

}