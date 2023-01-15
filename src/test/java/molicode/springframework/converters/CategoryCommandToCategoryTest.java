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
class CategoryCommandToCategoryTest {

  public static final Long ID_VALUE = new Long(1L);

  public static final String DESCRIPTION = "description";

  @InjectMocks
  private CategoryCommandToCategory converter;

  @Test
  public void testNullObject() {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() {
    assertNotNull(converter.convert(new CategoryCommand()));
  }

  @Test
  public void convert() throws Exception {
    //given
    CategoryCommand categoryCommand = new CategoryCommand();
    categoryCommand.setId(ID_VALUE);
    categoryCommand.setDescription(DESCRIPTION);

    //when
    Category category = converter.convert(categoryCommand);

    //then
    assertEquals(ID_VALUE, category.getId());
    assertEquals(DESCRIPTION, category.getDescription());
  }

}