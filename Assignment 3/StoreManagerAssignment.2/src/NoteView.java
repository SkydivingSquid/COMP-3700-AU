import javax.swing.*;
import java.awt.*;

public class NoteView extends JFrame {

    public JTextField noteIDText = new JTextField(30);
    public JTextField titleText = new JTextField(30);
    public JTextField bodyText = new JTextField(30);

    public JButton loadButton = new JButton("Download Note");
    public JButton saveButton = new JButton("Upload Note");
    public JButton deleteButton = new JButton("Remove Note");

    public NoteView(){

        this.setTitle("Note View");
        this.setSize(new Dimension(600,300));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout


        JPanel line1 = new JPanel();
        line1.add(new JLabel("Note ID"));
        line1.add(noteIDText);
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel();
        line2.add(new JLabel("Title"));
        line2.add(titleText);
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel();
        line3.add(new JLabel("Body"));
        line3.add(bodyText);
        this.getContentPane().add(line3);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        this.getContentPane().add(buttonPanel);

    }
}