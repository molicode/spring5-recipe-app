package molicode.springframework.service;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import molicode.springframework.commands.IngredientCommand;
import molicode.springframework.converters.IngredientToIngredientCommand;
import molicode.springframework.domain.Recipe;
import molicode.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

  private final IngredientToIngredientCommand ingredientToIngredientCommand;

  private final RecipeRepository recipeRepository;

  public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
    this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    this.recipeRepository = recipeRepository;
  }

  @Override
  public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

    Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

    if (!recipeOptional.isPresent()) {
      //todo impl error handling
      log.error("recipe id not found. Id: " + recipeId);
    }

    Recipe recipe = recipeOptional.get();

    Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
        .filter(ingredient -> ingredient.getId().equals(ingredientId))
        .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

    if (!ingredientCommandOptional.isPresent()) {
      //todo impl error handling
      log.error("Ingredient id not found: " + ingredientId);
    }

    return ingredientCommandOptional.get();
  }

}
