package molicode.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import molicode.springframework.commands.IngredientCommand;
import molicode.springframework.domain.Ingredient;
import molicode.springframework.domain.Recipe;
import molicode.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IngredientToIngredientCommandTest {

  private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

  public static final Recipe RECIPE = new Recipe();

  public static final BigDecimal AMOUNT = new BigDecimal("1");

  public static final String DESCRIPTION = "Cheeseburger";

  public static final Long UOM_ID = new Long(2L);

  public static final Long ID_VALUE = new Long(1L);

  @InjectMocks
  IngredientToIngredientCommand converter;

  public IngredientToIngredientCommandTest() {
    this.unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
  }

  @BeforeEach
  public void setUp() throws Exception {
    converter = new IngredientToIngredientCommand(unitOfMeasureToUnitOfMeasureCommand);
  }

  @Test
  public void testNullConvert() throws Exception {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() throws Exception {
    assertNotNull(converter.convert(new Ingredient()));
  }

  @Test
  public void testConvertNullUOM() throws Exception {
    //given
    Ingredient ingredient = new Ingredient();
    ingredient.setId(ID_VALUE);
    ingredient.setRecipe(RECIPE);
    ingredient.setAmount(AMOUNT);
    ingredient.setDescription(DESCRIPTION);
    ingredient.setUnitOfMeasure(null);
    //when
    IngredientCommand ingredientCommand = converter.convert(ingredient);
    //then
    assertNull(ingredientCommand.getUnitOfMeasure());
    assertEquals(ID_VALUE, ingredientCommand.getId());
    // assertEquals(RECIPE, ingredientCommand.get);
    assertEquals(AMOUNT, ingredientCommand.getAmount());
    assertEquals(DESCRIPTION, ingredientCommand.getDescription());
  }

  @Test
  public void testConvertWithUom() throws Exception {
    //given
    Ingredient ingredient = new Ingredient();
    ingredient.setId(ID_VALUE);
    ingredient.setRecipe(RECIPE);
    ingredient.setAmount(AMOUNT);
    ingredient.setDescription(DESCRIPTION);

    UnitOfMeasure uom = new UnitOfMeasure();
    uom.setId(UOM_ID);

    ingredient.setUnitOfMeasure(uom);
    //when
    IngredientCommand ingredientCommand = converter.convert(ingredient);
    //then
    assertEquals(ID_VALUE, ingredientCommand.getId());
    assertNotNull(ingredientCommand.getUnitOfMeasure());
    assertEquals(UOM_ID, ingredientCommand.getUnitOfMeasure().getId());
    // assertEquals(RECIPE, ingredientCommand.get);
    assertEquals(AMOUNT, ingredientCommand.getAmount());
    assertEquals(DESCRIPTION, ingredientCommand.getDescription());
  }
}