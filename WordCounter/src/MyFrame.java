import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame 
{
	public JButton beginButton;
    JTextArea output;
	
    String word, description, prompt;
    Integer frequency;

    public MyFrame()
    {
        super();
        init();
    }

    private void init()
    {
        //Initialize variables
        beginButton = new JButton("Begin");
        description = "This program will calculate how often words appear in the poem\t \"The Raven\" by Edgar Allan Poe.";
        prompt = "To proceed, please press begin.";

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Word Frequency Calculator");
		this.setLayout(new GridLayout(5, 1));

        // Frame Contents

        this.add(new JLabel(description));
        this.add (new JLabel(prompt));

        // Begin Button
        beginButton.addActionListener(new MyActionListener(this));
        this.getContentPane().add(beginButton);

        // Results
        output = new JTextArea(20,1);
        this.add(output);

        JScrollPane scrollPane = new JScrollPane(output);
        this.add(scrollPane);

        // Set up frame position/size
        int frameWidth = 600;
        int frameHeight = 400;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds( 
				(int) (screenSize.getWidth() / 2) - frameWidth, 
				(int) (screenSize.getHeight() / 2) - frameHeight, 
				frameWidth, 
				frameHeight); 

		this.setVisible(true);
    }
}

class MyActionListener implements ActionListener
{
    MyFrame fr;

    public MyActionListener(MyFrame frame)
    {
        fr = frame;
    }
	
	public void actionPerformed(ActionEvent e) 
    {
        String file = "WordCounter\\TheRavenByEdgarAllanPoe.html";
        String[] wordArray;
        wordArray = WordCounter.begin(file);
 
        for (int i = 0; i < wordArray.length; i++){
            fr.output.append(wordArray[i] + "\n");
        }

	}
}

