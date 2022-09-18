public class NoteModel {

    public int noteID;
    public String title;
    public String body;

    NoteModel(){};

    NoteModel(int nID, String ttl, String txt){
        noteID = nID;
        title = ttl;
        body = txt;
    }

    @Override
    public String toString(){
        return "Note ID: " + noteID + ", Title: " + title + ", Body: " + body;
    }
}