import javax.swing.*;

public class Main {

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
