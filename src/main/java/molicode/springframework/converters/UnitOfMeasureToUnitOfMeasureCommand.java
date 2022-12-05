package molicode.springframework.converters;

import lombok.Synchronized;
import molicode.springframework.commands.UnitOfMeasureCommand;
import molicode.springframework.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

  @Synchronized
  @Nullable
  @Override
  public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {

    if (unitOfMeasure != null) {
      final UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
      unitOfMeasureCommand.setId(unitOfMeasure.getId());
      unitOfMeasureCommand.setDescription(unitOfMeasure.getDescription());
      return unitOfMeasureCommand;
    }
    return null;
  }
}
