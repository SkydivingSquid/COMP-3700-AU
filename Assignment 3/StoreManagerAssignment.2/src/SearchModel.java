import java.util.*;
public class SearchModel {

    List<NoteModel> matchedNotes;

    SearchModel(){
        matchedNotes = new ArrayList<NoteModel>();
    }

    @Override
    public String toString(){
        String ret = "";
        for(NoteModel note : matchedNotes){
            ret = ret + "Note ID: " + note.noteID + "\nTitle: " + note.title + "\nBody: " + note.body + "\n\n";
        }
        return ret;
    }

    public void add(NoteModel note){
        matchedNotes.add(note);
    }
}