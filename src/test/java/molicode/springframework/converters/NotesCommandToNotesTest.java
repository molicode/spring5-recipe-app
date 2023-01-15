package molicode.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import molicode.springframework.commands.NotesCommand;
import molicode.springframework.domain.Notes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotesCommandToNotesTest {

  public static final Long ID_VALUE = new Long(1L);

  public static final String RECIPE_NOTES = "Notes";

  @InjectMocks
  private NotesCommandToNotes converter;

  @Test
  public void testNullParameter() {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() {
    assertNotNull(converter.convert(new NotesCommand()));
  }

  @Test
  public void convert() throws Exception {
    //given
    NotesCommand notesCommand = new NotesCommand();
    notesCommand.setId(ID_VALUE);
    notesCommand.setRecipeNotes(RECIPE_NOTES);

    //when
    Notes notes = converter.convert(notesCommand);

    //then
    assertNotNull(notes);
    assertEquals(ID_VALUE, notes.getId());
    assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
  }

}