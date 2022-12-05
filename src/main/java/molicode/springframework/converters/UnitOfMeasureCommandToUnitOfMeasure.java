package molicode.springframework.converters;

import lombok.Synchronized;
import molicode.springframework.commands.UnitOfMeasureCommand;
import molicode.springframework.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

  @Synchronized
  @Nullable
  @Override
  public UnitOfMeasure convert(UnitOfMeasureCommand source) {
    if (source == null) {
      return null;
    }

    final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
    unitOfMeasure.setId(source.getId());
    unitOfMeasure.setDescription(source.getDescription());
    return unitOfMeasure;
  }
}
