package molicode.springframework.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import molicode.springframework.commands.RecipeCommand;
import molicode.springframework.converters.RecipeCommandToRecipe;
import molicode.springframework.converters.RecipeToRecipeCommand;
import molicode.springframework.domain.Recipe;
import molicode.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

  @Mock
  private RecipeRepository recipeRepository;

  @Mock
  private RecipeToRecipeCommand recipeToRecipeCommand;

  @Mock
  private RecipeCommandToRecipe recipeCommandToRecipe;

  private RecipeServiceImpl recipeService;

  @BeforeEach
  public void setUp() throws Exception {
    recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
  }

  @Test
  public void getRecipeByIdTest() {
    Recipe recipe = new Recipe();
    recipe.setId(1L);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    Recipe recipeReturned = recipeService.findById(1L);

    assertNotNull("Null recipe returned", recipeReturned);
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, never()).findAll();
  }

  @Test
  public void getRecipeCoomandByIdTest() {
    Recipe recipe = new Recipe();
    recipe.setId(1L);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId(1L);

    when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

    RecipeCommand commandById = recipeService.findCommandById(1L);

    assertNotNull("Null recipe returned", commandById);
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, never()).findAll();
  }

  @Test
  public void getRecipesTest() {

    Recipe recipe = new Recipe();
    HashSet receipesData = new HashSet();
    receipesData.add(recipe);

    when(recipeRepository.findAll()).thenReturn(receipesData);

    Set<Recipe> recipes = recipeService.getRecipes();

    assertEquals(recipes.size(), 1);
    verify(recipeRepository, times(1)).findAll();
    verify(recipeRepository, never()).findById(anyLong());
  }

  @Test
  public void testDeleteById() {

    //given
    Long idToDelete = Long.valueOf(2L);

    //when
    recipeService.deleteById(idToDelete);

    //no 'when', since method has not void return type

    //then
    verify(recipeRepository, times(1)).deleteById(anyLong());
  }

}