import javax.swing.*;
/**
 * @author Natasha Phyle
 * This application is used to find the 20 most frequent words in the poem "The Raven" by Edgar Allan Poe.
 */
public class Main {
	
    
	/** 
	 * @param args
	 */
	public static void main(String[] args) 
    {
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run()
			{
				constructGUI();
			}
		});
	}
	
	private static void constructGUI() 
    {
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		MyFrame frame = new MyFrame();					
		frame.setVisible(true);						
	}
}
