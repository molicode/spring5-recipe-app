package molicode.springframework.converters;

import lombok.Synchronized;
import molicode.springframework.commands.NotesCommand;
import molicode.springframework.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

  @Synchronized
  @Nullable
  @Override
  public NotesCommand convert(Notes source) {
    if (source == null) {
      return null;
    }

    final NotesCommand notesCommand = new NotesCommand();
    notesCommand.setId(source.getId());
    notesCommand.setRecipeNotes(source.getRecipeNotes());
    return notesCommand;
  }

}
