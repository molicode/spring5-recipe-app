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
class UnitOfMeasureCommandToUnitOfMeasureTest {

  public static final String DESCRIPTION = "description";

  public static final Long LONG_VALUE = new Long(1L);

  @InjectMocks
  private UnitOfMeasureCommandToUnitOfMeasure converter;

  @Test
  public void testNullParamter() {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() {
    assertNotNull(converter.convert(new UnitOfMeasureCommand()));
  }

  @Test
  public void convert() throws Exception {
    //given
    UnitOfMeasureCommand command = new UnitOfMeasureCommand();
    command.setId(LONG_VALUE);
    command.setDescription(DESCRIPTION);

    //when
    UnitOfMeasure uom = converter.convert(command);

    //then
    assertNotNull(uom);
    assertEquals(LONG_VALUE, uom.getId());
    assertEquals(DESCRIPTION, uom.getDescription());

  }

}