package molicode.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import molicode.springframework.commands.CategoryCommand;
import molicode.springframework.commands.IngredientCommand;
import molicode.springframework.commands.NotesCommand;
import molicode.springframework.commands.RecipeCommand;
import molicode.springframework.domain.Difficulty;
import molicode.springframework.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecipeCommandToRecipeTest {

  private final CategoryCommandToCategory categoryCommandToCategory;

  private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

  private final IngredientCommandToIngredient ingredientCommandToIngredient;

  private final NotesCommandToNotes notesCommandToNotes;

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

  public RecipeCommandToRecipeTest() {
    this.categoryCommandToCategory = new CategoryCommandToCategory();
    this.unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
    this.notesCommandToNotes = new NotesCommandToNotes();
    this.ingredientCommandToIngredient = new IngredientCommandToIngredient(unitOfMeasureCommandToUnitOfMeasure);
  }

  @InjectMocks
  private RecipeCommandToRecipe converter;

  @BeforeEach
  public void setUp() throws Exception {
    converter = new RecipeCommandToRecipe(
        categoryCommandToCategory,
        ingredientCommandToIngredient,
        notesCommandToNotes);
  }

  @Test
  public void testNullObject() {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() {
    assertNotNull(converter.convert(new RecipeCommand()));
  }

  @Test
  public void convert() throws Exception {
    //given
    RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId(RECIPE_ID);
    recipeCommand.setCookTime(COOK_TIME);
    recipeCommand.setPrepTime(PREP_TIME);
    recipeCommand.setDescription(DESCRIPTION);
    recipeCommand.setDifficulty(DIFFICULTY);
    recipeCommand.setDirections(DIRECTIONS);
    recipeCommand.setServings(SERVINGS);
    recipeCommand.setSource(SOURCE);
    recipeCommand.setUrl(URL);

    NotesCommand notes = new NotesCommand();
    notes.setId(NOTES_ID);

    recipeCommand.setNotes(notes);

    CategoryCommand category = new CategoryCommand();
    category.setId(CAT_ID_1);

    CategoryCommand category2 = new CategoryCommand();
    category2.setId(CAT_ID2);

    recipeCommand.getCategories().add(category);
    recipeCommand.getCategories().add(category2);

    IngredientCommand ingredient = new IngredientCommand();
    ingredient.setId(INGRED_ID_1);

    IngredientCommand ingredient2 = new IngredientCommand();
    ingredient2.setId(INGRED_ID_2);

    recipeCommand.getIngredients().add(ingredient);
    recipeCommand.getIngredients().add(ingredient2);

    //when
    Recipe recipe = converter.convert(recipeCommand);

    assertNotNull(recipe);
    assertEquals(RECIPE_ID, recipe.getId());
    assertEquals(COOK_TIME, recipe.getCookTime());
    assertEquals(PREP_TIME, recipe.getPrepTime());
    assertEquals(DESCRIPTION, recipe.getDescription());
    assertEquals(DIFFICULTY, recipe.getDifficulty());
    assertEquals(DIRECTIONS, recipe.getDirections());
    assertEquals(SERVINGS, recipe.getServings());
    assertEquals(SOURCE, recipe.getSource());
    assertEquals(URL, recipe.getUrl());
    assertEquals(NOTES_ID, recipe.getNotes().getId());
    assertEquals(2, recipe.getCategories().size());
    assertEquals(2, recipe.getIngredients().size());
  }

}