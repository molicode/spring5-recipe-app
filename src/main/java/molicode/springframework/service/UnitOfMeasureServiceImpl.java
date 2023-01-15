package molicode.springframework.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import molicode.springframework.commands.UnitOfMeasureCommand;
import molicode.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import molicode.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

  private final UnitOfMeasureRepository unitOfMeasureRepository;

  private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

  @Override
  public Set<UnitOfMeasureCommand> listAllUoms() {

    return StreamSupport.stream(unitOfMeasureRepository.findAll()
            .spliterator(), false)
        .map(unitOfMeasureToUnitOfMeasureCommand::convert)
        .collect(Collectors.toSet());
  }
}
