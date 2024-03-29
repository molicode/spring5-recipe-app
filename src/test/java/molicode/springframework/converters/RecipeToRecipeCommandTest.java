package molicode.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import molicode.springframework.commands.RecipeCommand;
import molicode.springframework.domain.Category;
import molicode.springframework.domain.Difficulty;
import molicode.springframework.domain.Ingredient;
import molicode.springframework.domain.Notes;
import molicode.springframework.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecipeToRecipeCommandTest {

  private final CategoryToCategoryCommand categoryToCategoryCommand;

  private final IngredientToIngredientCommand ingredientToIngredientCommand;

  private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

  private final NotesToNotesCommand notesToNotesCommand;

  public static final Long RECIPE_ID = 1L;

  public static final Integer COOK_TIME = Integer.valueOf("5");

  public static final Integer PREP_TIME = Integer.valueOf("7");

  public static final String DESCRIPTION = "My Recipe";

  public static final String DIRECTIONS = "Directions";

  public static final Difficulty DIFFICULTY = Difficulty.EASY;

  public static final Integer SERVINGS = Integer.valueOf("3");

  public static final String SOURCE = "Source";

  public static final String URL = "Some URL";

  public static final Long CAT_ID_1 = 1L;

  public static final Long CAT_ID2 = 2L;

  public static final Long INGRED_ID_1 = 3L;

  public static final Long INGRED_ID_2 = 4L;

  public static final Long NOTES_ID = 9L;

  @InjectMocks
  private RecipeToRecipeCommand converter;

  public RecipeToRecipeCommandTest() {
    this.categoryToCategoryCommand = new CategoryToCategoryCommand();
    this.unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    this.ingredientToIngredientCommand = new IngredientToIngredientCommand(unitOfMeasureToUnitOfMeasureCommand);
    this.notesToNotesCommand = new NotesToNotesCommand();

  }

  @BeforeEach
  public void setUp() throws Exception {
    converter = new RecipeToRecipeCommand(
        categoryToCategoryCommand,
        ingredientToIngredientCommand,
        notesToNotesCommand);
  }

  @Test
  public void testNullObject() {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() {
    assertNotNull(converter.convert(new Recipe()));
  }

  @Test
  public void convert() throws Exception {
    //given
    Recipe recipe = new Recipe();
    recipe.setId(RECIPE_ID);
    recipe.setCookTime(COOK_TIME);
    recipe.setPrepTime(PREP_TIME);
    recipe.setDescription(DESCRIPTION);
    recipe.setDifficulty(DIFFICULTY);
    recipe.setDirections(DIRECTIONS);
    recipe.setServings(SERVINGS);
    recipe.setSource(SOURCE);
    recipe.setUrl(URL);

    Notes notes = new Notes();
    notes.setId(NOTES_ID);

    recipe.setNotes(notes);

    Category category = new Category();
    category.setId(CAT_ID_1);

    Category category2 = new Category();
    category2.setId(CAT_ID2);

    recipe.getCategories().add(category);
    recipe.getCategories().add(category2);

    Ingredient ingredient = new Ingredient();
    ingredient.setId(INGRED_ID_1);

    Ingredient ingredient2 = new Ingredient();
    ingredient2.setId(INGRED_ID_2);

    recipe.getIngredients().add(ingredient);
    recipe.getIngredients().add(ingredient2);

    //when
    RecipeCommand command = converter.convert(recipe);

    //then
    assertNotNull(command);
    assertEquals(RECIPE_ID, command.getId());
    assertEquals(COOK_TIME, command.getCookTime());
    assertEquals(PREP_TIME, command.getPrepTime());
    assertEquals(DESCRIPTION, command.getDescription());
    assertEquals(DIFFICULTY, command.getDifficulty());
    assertEquals(DIRECTIONS, command.getDirections());
    assertEquals(SERVINGS, command.getServings());
    assertEquals(SOURCE, command.getSource());
    assertEquals(URL, command.getUrl());
    assertEquals(NOTES_ID, command.getNotes().getId());
    assertEquals(2, command.getCategories().size());
    assertEquals(2, command.getIngredients().size());

  }

}