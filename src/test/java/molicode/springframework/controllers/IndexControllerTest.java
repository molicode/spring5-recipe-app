package molicode.springframework.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import molicode.springframework.domain.Recipe;
import molicode.springframework.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

  @Mock
  private RecipeService recipeService;

  @Mock
  private Model model;

  private MockMvc mockMvc;

  @InjectMocks
  private IndexController controller;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void testMockMVC() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("index"));
  }

  @Test
  public void getIndexPage() throws Exception {
    //given
    Set<Recipe> recipes = new HashSet<>();
    recipes.add(new Recipe());

    Recipe recipe = new Recipe();
    recipe.setId(1L);

    recipes.add(recipe);

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