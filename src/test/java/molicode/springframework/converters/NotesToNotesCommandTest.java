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
class NotesToNotesCommandTest {

  public static final Long ID_VALUE = new Long(1L);

  public static final String RECIPE_NOTES = "Notes";

 @InjectMocks
 private NotesToNotesCommand converter;

  @Test
  public void convert() throws Exception {
    //given
    Notes notes = new Notes();
    notes.setId(ID_VALUE);
    notes.setRecipeNotes(RECIPE_NOTES);

    //when
    NotesCommand notesCommand = converter.convert(notes);

    //then
    assertEquals(ID_VALUE, notesCommand.getId());
    assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
  }

  @Test
  public void testNull() throws Exception {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() throws Exception {
    assertNotNull(converter.convert(new Notes()));
  }
}