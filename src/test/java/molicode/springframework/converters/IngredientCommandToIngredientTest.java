package molicode.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import molicode.springframework.commands.IngredientCommand;
import molicode.springframework.commands.UnitOfMeasureCommand;
import molicode.springframework.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IngredientCommandToIngredientTest {

  private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

  public static final BigDecimal AMOUNT = new BigDecimal("1");

  public static final String DESCRIPTION = "Cheeseburger";

  public static final Long ID_VALUE = new Long(1L);

  public static final Long UOM_ID = new Long(2L);

  @InjectMocks
  private IngredientCommandToIngredient converter;

  public IngredientCommandToIngredientTest() {
    this.unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
  }

  @BeforeEach
  public void setUp() throws Exception {
    converter = new IngredientCommandToIngredient(unitOfMeasureCommandToUnitOfMeasure);
  }

  @Test
  public void testNullObject() {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() {
    assertNotNull(converter.convert(new IngredientCommand()));
  }

  @Test
  public void convert() throws Exception {
    //given
    IngredientCommand command = new IngredientCommand();
    command.setId(ID_VALUE);
    command.setAmount(AMOUNT);
    command.setDescription(DESCRIPTION);
    UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
    unitOfMeasureCommand.setId(UOM_ID);
    command.setUnitOfMeasure(unitOfMeasureCommand);

    //when
    Ingredient ingredient = converter.convert(command);

    //then
    assertNotNull(ingredient);
    assertNotNull(ingredient.getUnitOfMeasure());
    assertEquals(ID_VALUE, ingredient.getId());
    assertEquals(AMOUNT, ingredient.getAmount());
    assertEquals(DESCRIPTION, ingredient.getDescription());
    assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
  }

  @Test
  public void convertWithNullUOM() {
    //given
    IngredientCommand command = new IngredientCommand();
    command.setId(ID_VALUE);
    command.setAmount(AMOUNT);
    command.setDescription(DESCRIPTION);

    //when
    Ingredient ingredient = converter.convert(command);

    //then
    assertNotNull(ingredient);
    assertNull(ingredient.getUnitOfMeasure());
    assertEquals(ID_VALUE, ingredient.getId());
    assertEquals(AMOUNT, ingredient.getAmount());
    assertEquals(DESCRIPTION, ingredient.getDescription());
  }

}