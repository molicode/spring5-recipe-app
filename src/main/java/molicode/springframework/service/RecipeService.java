package molicode.springframework.service;

import java.util.Set;

import molicode.springframework.commands.RecipeCommand;
import molicode.springframework.domain.Recipe;

public interface RecipeService {

  Set<Recipe> getRecipes();

  Recipe findById(Long id);

  RecipeCommand findCommandById(Long l);

  RecipeCommand saveRecipeCommand(RecipeCommand command);

}
