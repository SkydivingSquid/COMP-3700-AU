public interface DataAccess {
    void connect();

    void saveNote(NoteModel note);

    void deleteNote(NoteModel note);

    NoteModel loadNote(int noteID);

    SearchModel searchNotes(String keyword);
}