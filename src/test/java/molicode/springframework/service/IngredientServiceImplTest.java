package molicode.springframework.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import molicode.springframework.commands.IngredientCommand;
import molicode.springframework.converters.IngredientCommandToIngredient;
import molicode.springframework.converters.IngredientToIngredientCommand;
import molicode.springframework.converters.UnitOfMeasureCommandToUnitOfMeasure;
import molicode.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import molicode.springframework.domain.Ingredient;
import molicode.springframework.domain.Recipe;
import molicode.springframework.repositories.RecipeRepository;
import molicode.springframework.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

  private final IngredientToIngredientCommand ingredientToIngredientCommand;

  private final IngredientCommandToIngredient ingredientCommandToIngredient;

  @Mock
  RecipeRepository recipeRepository;

  @Mock
  UnitOfMeasureRepository unitOfMeasureRepository;

  private IngredientService ingredientService;

  public IngredientServiceImplTest() {
    this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
  }

  @BeforeEach
  public void setUp() throws Exception {
    ingredientService = new IngredientServiceImpl(
        ingredientToIngredientCommand,
        ingredientCommandToIngredient,
        unitOfMeasureRepository,
        recipeRepository);
  }

  @Test
  public void findByRecipeIdAndId() {
  }

  @Test
  public void findByRecipeIdAndReceipeIdHappyPath() {
    //given
    Recipe recipe = new Recipe();
    recipe.setId(1L);

    Ingredient ingredient1 = new Ingredient();
    ingredient1.setId(1L);

    Ingredient ingredient2 = new Ingredient();
    ingredient2.setId(1L);

    Ingredient ingredient3 = new Ingredient();
    ingredient3.setId(3L);

    recipe.addIngredient(ingredient1);
    recipe.addIngredient(ingredient2);
    recipe.addIngredient(ingredient3);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    //then
    IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

    //when
    assertEquals(Long.valueOf(3L), ingredientCommand.getId());
    assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
    verify(recipeRepository, times(1)).findById(anyLong());
  }

  @Test
  public void testSaveRecipeCommand() {
    //given
    IngredientCommand command = new IngredientCommand();
    command.setId(3L);
    command.setRecipeId(2L);

    Optional<Recipe> recipeOptional = Optional.of(new Recipe());

    Recipe savedRecipe = new Recipe();
    savedRecipe.addIngredient(new Ingredient());
    savedRecipe.getIngredients().iterator().next().setId(3L);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
    when(recipeRepository.save(any())).thenReturn(savedRecipe);

    //when
    IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

    //then
    assertEquals(Long.valueOf(3L), savedCommand.getId());
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, times(1)).save(any(Recipe.class));

  }

  @Test
  public void testDeleteById() {
    //given
    Recipe recipe = new Recipe();
    Ingredient ingredient = new Ingredient();
    ingredient.setId(3L);
    recipe.addIngredient(ingredient);
    ingredient.setRecipe(recipe);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    //when
    ingredientService.deleteById(1L, 3L);

    //then
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, times(1)).save(any(Recipe.class));
  }


}