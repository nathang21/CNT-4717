/* Name: Nathan Guenther
 Course: CNT 4714 – Fall 2015
 Assignment title: Program 1 – Event-driven Programming
 Date: Sunday September 13, 2015
*/
package assignment.pkg1;
    
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;


// Clasified the GUI that the user interacts with
public class Display {
	
        // Initialize Base GUI and functionality
	private static Book sBook;
	private static int sCurrentOrderNumber;
	private static JFrame sDisplay;
	private static JPanel sPanel;
	private static SpringLayout sLayout;
	private static StoreLogic sStoreLogic;
    
        // Initalize User Input Fields
	private static JTextField sNumItemsTF;
	private static JTextField sIDTF;
	private static JTextField sQuantityTF;
	private static JTextField sItemInfoTF;
	private static JTextField sSubtotalTF;
	
        // Initalize GUI Text
	private static JLabel sNumItemsL;
	private static JLabel sIDL;
	private static JLabel sQuantityL;
	private static JLabel sItemInfoL;
	private static JLabel sSubtotalL;
	
        // Initalize Action Butons
	private static JButton sProcessItem;
	private static JButton sConfirmItem;
	private static JButton sViewOrder;
	private static JButton sFinishOrder;
	private static JButton sNewOrder;
	private static JButton sExit;
	

	//Initialize JFrame and creates a new thread for each new window.
	//Sets current order number to 1 for placement.
	public static void main(String[] args) 
	{
            new Display().StartThread();
            sCurrentOrderNumber = 1;
            InitWindow();
	}
   

	//Defines the Store window frame and calls button,label, and textfield initializers.
	//After initializing, uses SpringLayout for placement and enables visibility.
	private static void InitWindow()
	{
            sDisplay = new JFrame("Ball'in Books");
            sPanel  = new JPanel();
            sPanel.setBackground(Color.GREEN);

            sPanel.setSize(700, 220);
            sDisplay.setSize(700, 220);

            // Prepare Content
            InitButtons();
            InitLabels();
            InitText();
            InitPlacement();

            // Render GUI
            sDisplay.add(sPanel);
            sDisplay.setVisible(true);
    }
    
	
	
	//Initializes Jlabels which are placed into panel within the store.
	private static void InitLabels()
	{
        
            // Prepare Content
            sNumItemsL = new JLabel("Enter the number of items for this order:");
            sIDL = new JLabel("Enter Book ID for item #"+String.valueOf(sCurrentOrderNumber)+":");
            sQuantityL = new JLabel("Enter quantity for item #"+String.valueOf(sCurrentOrderNumber)+":");
            sItemInfoL = new JLabel("Item #"+String.valueOf(sCurrentOrderNumber)+" info:");
            sSubtotalL = new JLabel("Order subtotal for "+String.valueOf(sCurrentOrderNumber-1)+" item(s):");
            

            // Render Labels
            sPanel.add(sNumItemsL);
            sPanel.add(sIDL);
            sPanel.add(sQuantityL);
            sPanel.add(sItemInfoL);
            sPanel.add(sSubtotalL);
    }
    
	
	
	
	//Initializes the text fields and creates listeners to them.
	//sIDTF represents the book ID text field.
	private static void InitText()
	{
            // Prepare
            sNumItemsTF = new JTextField(13);
            sIDTF = new JTextField(13);
            sQuantityTF = new JTextField(13);
            sItemInfoTF = new JTextField(40);
            sSubtotalTF = new JTextField(13);

            sIDTF.addKeyListener(new KeyListener()
    	{

                @Override
                public void keyPressed(KeyEvent arg0) 
                {
                }

                @Override
                public void keyReleased(KeyEvent arg0) 
                {
                }

                @Override
                public void keyTyped(KeyEvent arg0)
                {
                        if((arg0.getKeyCode() == KeyEvent.VK_ENTER) && !sIDTF.getText().isEmpty())
                        {
                                sBook = sStoreLogic.lookupBook(Integer.valueOf(sIDTF.getText()));

                                if(!sQuantityTF.getText().isEmpty())
                                        sStoreLogic.showBookInfo(sBook, Integer.valueOf(sQuantityTF.getText()));
                                else
                                        sStoreLogic.showBookInfo(sBook, 1);

                                AutoComplete(sBook);
                        }

                }});
    	
    	sIDTF.addFocusListener(new FocusListener()
    	{
			@Override
			public void focusGained(FocusEvent e) {
			
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				if(!sIDTF.getText().isEmpty())
				{
					sBook = sStoreLogic.lookupBook(Integer.valueOf(sIDTF.getText()));
					
					if(!sQuantityTF.getText().isEmpty())
						sStoreLogic.showBookInfo(sBook, Integer.valueOf(sQuantityTF.getText()));
					else
						sStoreLogic.showBookInfo(sBook, 1);
					
					AutoComplete(sBook);
				}
			}
    		
    	});
    	
    	sQuantityTF.addFocusListener(new FocusListener()
    	{

			@Override
			public void focusGained(FocusEvent arg0) 
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent arg0) 
			{
				if(!sQuantityTF.getText().isEmpty() && !sIDTF.getText().isEmpty()){
//					sBook = sStoreLogic.FindBook(Integer.valueOf(sIDTF.getText()),Integer.valueOf(sQuantityTF.getText()));
					sBook = sStoreLogic.lookupBook(Integer.valueOf(sIDTF.getText()));

					sStoreLogic.showBookInfo(sBook, Integer.valueOf(sQuantityTF.getText()));
					
					AutoComplete(sBook);
				}
				
			}
		});
    	
    	sQuantityTF.addKeyListener(new KeyListener()
    	{

			@Override
			public void keyPressed(KeyEvent arg0)
			{
			}

			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				if(Character.isDigit(arg0.getKeyChar()))
				{
					sBook = sStoreLogic.lookupBook(Integer.valueOf(sIDTF.getText()));
					sStoreLogic.showBookInfo(sBook, Integer.valueOf(sQuantityTF.getText()));
					
					AutoComplete(sBook);
				}
			}
			
			@Override
			public void keyTyped(KeyEvent arg0) 
			{
			}});
    	
    	
    	// disables ability to modify data in the Subtotal and ItemInfo text fields.
    	sItemInfoTF.setEnabled(false);
    	sSubtotalTF.setEditable(false);
    	
    	sItemInfoTF.setBackground(Color.gray);
    	
        // Render Fields
        sPanel.add(sNumItemsTF);
        sPanel.add(sIDTF);
        sPanel.add(sQuantityTF);
        sPanel.add(sItemInfoTF);
        sPanel.add(sSubtotalTF);
    }
    
	
	// Initializes the Buttons for Panel and creates listeners for when the customer clicks on them.
	private static void InitButtons()
	{
            // Prepare
            sProcessItem = new JButton("Process Item #"+String.valueOf(sCurrentOrderNumber)+"");
            sConfirmItem = new JButton("Confirm Item #"+String.valueOf(sCurrentOrderNumber)+"");
            sViewOrder = new JButton("View Order");
            sFinishOrder = new JButton("Finish Order");
            sNewOrder = new JButton("New Order");
            sExit = new JButton("Exit");

            sProcessItem.setEnabled(false);

            sProcessItem.addActionListener(new ActionListener()
            {
                            @Override
                            public void actionPerformed(ActionEvent arg0) 
                            {
                                    sStoreLogic.processOrder(sBook, Integer.valueOf(sQuantityTF.getText()), Integer.valueOf(sNumItemsTF.getText()));
                                    if(sCurrentOrderNumber < Integer.valueOf(sNumItemsTF.getText()))
                                    {
                                            sNumItemsTF.setEditable(false);
                                            sNumItemsTF.setEditable(false);
                                            sConfirmItem.setEnabled(true);
                                            sProcessItem.setEnabled(false);

                                            sIDTF.setText("");
                                            sQuantityTF.setText("");
                                            sCurrentOrderNumber++;
                                            RedrawLabels();
                                    }

                                    else
                                    {
                                            sConfirmItem.setEnabled(false);
                                            sProcessItem.setEnabled(false);
                                    }

                            }
                    });


            sConfirmItem.addActionListener(new ActionListener()
            {
                            @Override
                            public void actionPerformed(ActionEvent arg0) 
                            {
                                    JOptionPane.showMessageDialog(sPanel,"Item #"+String.valueOf(sCurrentOrderNumber)+" accepted");
                                    sNumItemsTF.setEditable(false);
                                    sProcessItem.setEnabled(true);
                                    sConfirmItem.setEnabled(false);
                            }
                    });


            sViewOrder.addActionListener(new ActionListener()
            {
                            @Override
                            public void actionPerformed(ActionEvent arg0) 
                            {
                                    JOptionPane.showMessageDialog(sPanel, sStoreLogic.reviewOrder());
                            }
                    });


            sFinishOrder.addActionListener(new ActionListener()
            {
                            @Override
                            public void actionPerformed(ActionEvent arg0) 
                            {
                                    JOptionPane.showMessageDialog(sPanel, sStoreLogic.showInvoice());
                                    sStoreLogic.prepInvoice();
                                    NewOrderClick();
                            }
                    });

            sNewOrder.addActionListener(new ActionListener()
            {
                            @Override
                            public void actionPerformed(ActionEvent arg0)
                            {
                                    NewOrderClick();
                            }
                    });

            sExit.addActionListener(new ActionListener()
            {
                            @Override
                            public void actionPerformed(ActionEvent arg0) 
                            {
                                    sDisplay.dispose();
                            }
                    });

            // Render
            sPanel.add(sProcessItem);
            sPanel.add(sConfirmItem);
            sPanel.add(sViewOrder);
            sPanel.add(sFinishOrder);
            sPanel.add(sNewOrder);
            sPanel.add(sExit);
    }
	
	
	// Places constraints on the buttons, labels, and text fields within the panel using SpringLayout
	// Pushes text fields and labels to the right of window.
	private static void InitPlacement(){
            sLayout = new SpringLayout();
            sLayout.putConstraint(SpringLayout.NORTH,sNumItemsL,  10, SpringLayout.NORTH,sPanel);
            sLayout.putConstraint(SpringLayout.NORTH,sNumItemsTF, 10, SpringLayout.NORTH,sPanel);
            sLayout.putConstraint(SpringLayout.WEST, sNumItemsL, 261, SpringLayout.WEST, sPanel);
            sLayout.putConstraint(SpringLayout.WEST, sNumItemsTF, 10, SpringLayout.EAST, sNumItemsL);

            sLayout.putConstraint(SpringLayout.NORTH,sIDL, 5, SpringLayout.SOUTH,sNumItemsL);
            sLayout.putConstraint(SpringLayout.WEST, sIDL, 350, SpringLayout.WEST, sPanel);
            sLayout.putConstraint(SpringLayout.NORTH, sIDTF, 5, SpringLayout.SOUTH, sNumItemsTF);
            sLayout.putConstraint(SpringLayout.WEST, sIDTF, 5, SpringLayout.EAST, sIDL);

            sLayout.putConstraint(SpringLayout.NORTH,sQuantityL, 10, SpringLayout.SOUTH,sIDL);
            sLayout.putConstraint(SpringLayout.WEST, sQuantityL, 348, SpringLayout.WEST, sPanel);
            sLayout.putConstraint(SpringLayout.NORTH, sQuantityTF, 5, SpringLayout.SOUTH, sIDTF);
            sLayout.putConstraint(SpringLayout.WEST, sQuantityTF, 5, SpringLayout.EAST, sQuantityL);

            sLayout.putConstraint(SpringLayout.NORTH,sItemInfoL, 10, SpringLayout.SOUTH,sQuantityL);
            sLayout.putConstraint(SpringLayout.WEST, sItemInfoL, 130, SpringLayout.WEST, sPanel);
            sLayout.putConstraint(SpringLayout.NORTH, sItemInfoTF, 5, SpringLayout.SOUTH, sQuantityTF);
            sLayout.putConstraint(SpringLayout.WEST, sItemInfoTF, 5, SpringLayout.EAST, sItemInfoL);

            sLayout.putConstraint(SpringLayout.NORTH,sSubtotalL, 10, SpringLayout.SOUTH,sItemInfoL);
            sLayout.putConstraint(SpringLayout.WEST, sSubtotalL, 338, SpringLayout.WEST, sPanel);
            sLayout.putConstraint(SpringLayout.NORTH, sSubtotalTF, 5, SpringLayout.SOUTH, sItemInfoTF);
            sLayout.putConstraint(SpringLayout.WEST, sSubtotalTF, 5, SpringLayout.EAST, sSubtotalL);

            sLayout.putConstraint(SpringLayout.NORTH, sProcessItem, 20, SpringLayout.SOUTH, sSubtotalL);
            sLayout.putConstraint(SpringLayout.NORTH, sConfirmItem, 20, SpringLayout.SOUTH, sSubtotalL);
            sLayout.putConstraint(SpringLayout.NORTH, sViewOrder, 20, SpringLayout.SOUTH, sSubtotalL);
            sLayout.putConstraint(SpringLayout.NORTH, sFinishOrder, 20, SpringLayout.SOUTH, sSubtotalL);
            sLayout.putConstraint(SpringLayout.NORTH, sNewOrder, 20, SpringLayout.SOUTH, sSubtotalL);
            sLayout.putConstraint(SpringLayout.NORTH, sExit, 20, SpringLayout.SOUTH, sSubtotalL);

            sLayout.putConstraint(SpringLayout.WEST, sProcessItem, 10, SpringLayout.WEST, sPanel);
            sLayout.putConstraint(SpringLayout.WEST, sConfirmItem, 10, SpringLayout.EAST, sProcessItem);
            sLayout.putConstraint(SpringLayout.WEST, sViewOrder, 10, SpringLayout.EAST, sConfirmItem);
            sLayout.putConstraint(SpringLayout.WEST, sFinishOrder, 10, SpringLayout.EAST, sViewOrder);
            sLayout.putConstraint(SpringLayout.WEST, sNewOrder, 10, SpringLayout.EAST, sFinishOrder);
            sLayout.putConstraint(SpringLayout.WEST, sExit, 10, SpringLayout.EAST, sNewOrder);

            sPanel.setLayout(sLayout);
    }
	

	 // Sets the Info, Subtotal, and Quantity text fields.
	private static void AutoComplete(Book mBook){
            sItemInfoTF.setText(mBook.getbInfo());
            if(!sQuantityTF.getText().isEmpty())
                    sSubtotalTF.setText(String.valueOf(sStoreLogic.calcSubTotal(mBook, Integer.valueOf(sQuantityTF.getText()))));
            else{
                    sQuantityTF.setText("1");
                    sSubtotalTF.setText(String.valueOf(sStoreLogic.calcSubTotal(mBook,1)));
            }
    }
	
	//Resets the order # based on the current order number.
	private static void RedrawLabels()
	{
            sProcessItem.setText("Process Item #"+String.valueOf(sCurrentOrderNumber)+"");
            sConfirmItem.setText("Confirm Item #"+String.valueOf(sCurrentOrderNumber)+"");
            sIDL.setText("Enter Book ID for item #"+String.valueOf(sCurrentOrderNumber)+":");
            sQuantityL.setText("Enter quantity for item #"+String.valueOf(sCurrentOrderNumber)+":");
            sItemInfoL.setText("Item #"+String.valueOf(sCurrentOrderNumber)+" info:");
            sSubtotalL.setText("Order subtotal for "+String.valueOf(sCurrentOrderNumber-1)+" item(s):");
	}
	
	 
	//Clears the text fields, reinitializes the StoreLogic object, and redraws the labels.
	private static void NewOrderClick(){
            new Display().StartThread();
            sCurrentOrderNumber = 1;

            sIDTF.setText("");
                    sQuantityTF.setText("");
                    sItemInfoTF.setText("");
            sSubtotalTF.setText("");
                    sNumItemsTF.setText("");

            RedrawLabels();

            sNumItemsTF.setEditable(true);
            sConfirmItem.setEnabled(true);
            sProcessItem.setEnabled(false);
    }
	
	 //Defines a new class to execute the initialization of Store Action on its own thread.
	class NewThread extends Thread{
		@Override
		public void run() {
			sStoreLogic = new StoreLogic();
		}
	}
	
	//Starts Thread.
	private void StartThread(){
		new NewThread().start();
	}
}