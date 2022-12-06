package molicode.springframework.service;

import java.util.Set;

import molicode.springframework.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

  Set<UnitOfMeasureCommand> listAllUoms();

}
