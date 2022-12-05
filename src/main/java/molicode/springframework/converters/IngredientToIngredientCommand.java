package molicode.springframework.converters;

import lombok.Synchronized;
import molicode.springframework.commands.IngredientCommand;
import molicode.springframework.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

  private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

  public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
    this.uomConverter = uomConverter;
  }

  @Synchronized
  @Nullable
  @Override
  public IngredientCommand convert(Ingredient ingredient) {
    if (ingredient == null) {
      return null;
    }

    IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setId(ingredient.getId());
    ingredientCommand.setAmount(ingredient.getAmount());
    ingredientCommand.setDescription(ingredient.getDescription());
    ingredientCommand.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitOfMeasure()));
    return ingredientCommand;
  }

}

