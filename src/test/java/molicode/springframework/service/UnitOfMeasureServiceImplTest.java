package molicode.springframework.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import molicode.springframework.commands.UnitOfMeasureCommand;
import molicode.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import molicode.springframework.domain.UnitOfMeasure;
import molicode.springframework.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UnitOfMeasureServiceImplTest {

  private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

  private UnitOfMeasureService service;

  @Mock
  UnitOfMeasureRepository unitOfMeasureRepository;

  public UnitOfMeasureServiceImplTest() {
    this.unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
  }

  @BeforeEach
  public void setUp() throws Exception {
    service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
  }

  @Test
  public void listAllUoms() {
    //given
    Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
    UnitOfMeasure uom1 = new UnitOfMeasure();
    uom1.setId(1L);
    unitOfMeasures.add(uom1);

    UnitOfMeasure uom2 = new UnitOfMeasure();
    uom2.setId(2L);
    unitOfMeasures.add(uom2);

    when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

    //when
    Set<UnitOfMeasureCommand> commands = service.listAllUoms();

    //then
    assertEquals(2, commands.size());
    verify(unitOfMeasureRepository, times(1)).findAll();
  }

}