

//****************************************
//
//			CIS 611	
//			Spring 2018		
//			
//			Minh Le and Andy Carlson
//
//			PP04
//
//			Regex
//			
//			April 16, 2018
//	
//			Saved in LeCarlsonPP04.zip
//
//****************************************

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class UserGUI extends JPanel {
	
	  private JButton scrapeButton;
	  private JButton closeButton;
	  private JLabel label;
	  private JPanel textPanel;
	  static JTextArea textarea;
	  private JScrollPane jp;
	  
	  // add more UI components as needed
	  private Scraper scraper;
	
	
	  private String url;
	  
	  public UserGUI() throws IOException {
		 
		  // uses the url provided in the document
		 Scraper scraper = new Scraper(url);

	    initGUI();
	    doTheLayout();

	    scrapeButton.addActionListener( new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            try {
					scrape();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            }
	    });
	    
	    closeButton.addActionListener( new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            close();
	            }
	    });
	  } // end of constructor
	  
	  // Creates and initialize the GUI objects
	  private void initGUI(){
		  scrapeButton = new JButton("Scrape NFL Page");
		  closeButton = new JButton("Close");
		  label = new JLabel("Output");
		  
		  textPanel = new JPanel();
		  textarea = new JTextArea();
		  
		  textarea = new JTextArea(20,110);
		  textarea.setEditable(false);
		  textarea.setLineWrap(false);
		  textarea.setWrapStyleWord(false);
		  textarea.setFont(new Font("monospaced", Font.PLAIN, 12));
		  jp = new JScrollPane(textarea);
		  jp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		  jp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		  textPanel.add(jp);
		  
	  }// end of creating objects method
	  
	  // Layouts the UI components as shown in the project document
	  private void doTheLayout(){
		   JPanel top = new JPanel();
		   JPanel center = new JPanel();
		   JPanel bottom = new JPanel();
		   setLayout( new BorderLayout());
		   add(top, "North");
		   add(center, "Center");
		   add(bottom, "South");
		  		  
		  top.add(scrapeButton);
		  center.add(textPanel);
		  bottom.add(closeButton);

	  }// end of Layout method

	 
	// Uses the Scraper object reference to return and display the data as shown in the project document 
	 void scrape() throws IOException{
		 scraper = new Scraper(url);
		 scraper.parseData(null);
	 }// end of scrape action event method
	  
	 
	  void close(){
	      System.exit(0);
	  }// end of close action event method


	public static void main(String[] args) throws IOException {
	   JFrame f = new JFrame("NFL Stats");
	   f.setLocationRelativeTo(null);
	   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   Container contentPane = f.getContentPane();
	   contentPane.add( new UserGUI());
	   
	   f.pack();
	   f.setVisible(true);
	}// end of main method

	public static JTextArea getTextarea() {
		return textarea;
	}

	public static void setTextarea(JTextArea textarea) {
		UserGUI.textarea = textarea;
	}

	
	
}// end of class
