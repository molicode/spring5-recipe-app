package molicode.springframework.service;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import molicode.springframework.commands.IngredientCommand;
import molicode.springframework.converters.IngredientCommandToIngredient;
import molicode.springframework.converters.IngredientToIngredientCommand;
import molicode.springframework.domain.Ingredient;
import molicode.springframework.domain.Recipe;
import molicode.springframework.repositories.RecipeRepository;
import molicode.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

  private final IngredientToIngredientCommand ingredientToIngredientCommand;

  private final IngredientCommandToIngredient ingredientCommandToIngredient;

  private final UnitOfMeasureRepository unitOfMeasureRepository;

  private final RecipeRepository recipeRepository;

  public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
      IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository,
      UnitOfMeasureRepository unitOfMeasureRepository) {
    this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
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

  @Override
  @Transactional
  public IngredientCommand saveIngredientCommand(IngredientCommand command) {
    Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

    if (!recipeOptional.isPresent()) {

      //todo toss error if not found!
      log.error("Recipe not found for id: " + command.getRecipeId());
      return new IngredientCommand();
    } else {
      Recipe recipe = recipeOptional.get();

      Optional<Ingredient> ingredientOptional = recipe
          .getIngredients()
          .stream()
          .filter(ingredient -> ingredient.getId().equals(command.getId()))
          .findFirst();

      if (ingredientOptional.isPresent()) {
        Ingredient ingredientFound = ingredientOptional.get();
        ingredientFound.setDescription(command.getDescription());
        ingredientFound.setAmount(command.getAmount());
        ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
            .findById(command.getUnitOfMeasure().getId())
            .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
      } else {
        //add new Ingredient
        recipe.addIngredient(ingredientCommandToIngredient.convert(command));
      }

      Recipe savedRecipe = recipeRepository.save(recipe);

      //to do check for fail
      return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
          .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
          .findFirst()
          .get());
    }

  }

}
