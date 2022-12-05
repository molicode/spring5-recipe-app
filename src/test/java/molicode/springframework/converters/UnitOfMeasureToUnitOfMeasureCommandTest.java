package molicode.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import molicode.springframework.commands.UnitOfMeasureCommand;
import molicode.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

  public static final String DESCRIPTION = "description";

  public static final Long LONG_VALUE = new Long(1L);

  UnitOfMeasureToUnitOfMeasureCommand converter;

  @BeforeEach
  public void setUp() throws Exception {
    converter = new UnitOfMeasureToUnitOfMeasureCommand();
  }

  @Test
  public void testNullObjectConvert() throws Exception {
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