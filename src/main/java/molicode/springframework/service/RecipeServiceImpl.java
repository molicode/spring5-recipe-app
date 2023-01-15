package molicode.springframework.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import molicode.springframework.commands.RecipeCommand;
import molicode.springframework.converters.RecipeCommandToRecipe;
import molicode.springframework.converters.RecipeToRecipeCommand;
import molicode.springframework.domain.Recipe;
import molicode.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;

  private final RecipeCommandToRecipe recipeCommandToRecipe;

  private final RecipeToRecipeCommand recipeToRecipeCommand;

  @Override
  public Set<Recipe> getRecipes() {
    log.debug("I'm in the service");

    Set<Recipe> recipeSet = new HashSet<>();
    recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
    return recipeSet;
  }

  @Override
  public Recipe findById(Long id) {

    Optional<Recipe> recipeOptional = recipeRepository.findById(id);

    if (!recipeOptional.isPresent()) {
      throw new RuntimeException("Recipe Not Found!");
    }

    return recipeOptional.get();
  }

  @Override
  @Transactional
  public RecipeCommand findCommandById(Long id) {
    return recipeToRecipeCommand.convert(findById(id));
  }

  @Override
  @Transactional
  public RecipeCommand saveRecipeCommand(RecipeCommand command) {
    Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

    Recipe savedRecipe = recipeRepository.save(detachedRecipe);
    log.debug("Saved RecipeId:" + savedRecipe.getId());
    return recipeToRecipeCommand.convert(savedRecipe);
  }

  @Override
  public void deleteById(Long id) {
    recipeRepository.deleteById(id);
  }

}
