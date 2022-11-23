package molicode.springframework.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import molicode.springframework.domain.Recipe;
import molicode.springframework.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

class IndexControllerTest {

  @Mock
  RecipeService recipeService;

  @Mock
  Model model;

  IndexController controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    controller = new IndexController(recipeService);
  }

  @Test
  void getIndexPage() {

    //given
    Set<Recipe> recipes = new HashSet<>();

    Recipe oneRecipe = new Recipe();
    oneRecipe.setId(1L);
    recipes.add(oneRecipe);

    Recipe twoRecipe = new Recipe();
    twoRecipe.setId(2L);
    recipes.add(twoRecipe);

    when(recipeService.getRecipes()).thenReturn(recipes);
    ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

    //when
    String viewName = controller.getIndexPage(model);

    //then
    assertEquals("index", viewName);
    verify(recipeService, times(1)).getRecipes();
    verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
    Set<Recipe> setInController = argumentCaptor.getValue();
    assertEquals(2, setInController.size());
  }
}