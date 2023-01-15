package molicode.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import molicode.springframework.commands.UnitOfMeasureCommand;
import molicode.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UnitOfMeasureToUnitOfMeasureCommandTest {

  public static final String DESCRIPTION = "description";

  public static final Long LONG_VALUE = new Long(1L);

  @InjectMocks
  private UnitOfMeasureToUnitOfMeasureCommand converter;

  @Test
  public void testNullObjectConvert() {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObj() throws Exception {
    assertNotNull(converter.convert(new UnitOfMeasure()));
  }

  @Test
  public void convert() throws Exception {
    //given
    UnitOfMeasure uom = new UnitOfMeasure();
    uom.setId(LONG_VALUE);
    uom.setDescription(DESCRIPTION);
    //when
    UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(uom);

    //then
    assertEquals(LONG_VALUE, unitOfMeasureCommand.getId());
    assertEquals(DESCRIPTION, unitOfMeasureCommand.getDescription());
  }

}