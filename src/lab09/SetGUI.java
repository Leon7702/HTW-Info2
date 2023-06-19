package lab09;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SetGUI extends UserInterface {

	private boolean saving = false;
	private boolean gettingSize = false;
	private boolean subtracting = false;
	private String lastCommand;

	
	public SetGUI(CalcEngine engine) {
		super(engine);
	}
    
	@Override
    protected void addButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);    
    }
	
	@Override
	protected void makeFrame() {
		frame = new JFrame(calc.getTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        display = new JTextField();
        contentPane.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
  
            
            super.addButton(buttonPanel, "7");
            super.addButton(buttonPanel, "8");         
            super.addButton(buttonPanel, "9");
            super.addButton(buttonPanel, "save to:");
            
            super.addButton(buttonPanel, "4");
            super.addButton(buttonPanel, "5");
            super.addButton(buttonPanel, "6");
            super.addButton(buttonPanel, "A");
            
            super.addButton(buttonPanel, "1");
            super.addButton(buttonPanel, "2");
            super.addButton(buttonPanel, "3");
            super.addButton(buttonPanel, "B");

            
            super.addButton(buttonPanel, "0");
            super.addButton(buttonPanel, ",");
            super.addButton(buttonPanel, "");
            super.addButton(buttonPanel, "clear");
            
            super.addButton(buttonPanel, "size");
            super.addButton(buttonPanel, "∩");
            super.addButton(buttonPanel, "⋃");
            super.addButton(buttonPanel, "/");

      
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        status = new JLabel(calc.getAuthor());
        contentPane.add(status, BorderLayout.SOUTH);

        frame.pack();
    }
	
	@Override
	public void actionPerformed(ActionEvent event)
    {
        String command = event.getActionCommand();

        if(command.equals("0") ||
           command.equals("1") ||
           command.equals("2") ||
           command.equals("3") ||
           command.equals("4") ||
           command.equals("5") ||
           command.equals("6") ||
           command.equals("7") ||
           command.equals("8") ||
           command.equals("9")) {
            String number = command;
            calc.buttonPressed(number);
        }
        
        else if(command.equals("save to:")) {
        	calc.saveSet(calc.getDisplayValue());
        	saving = true;
        }
        else if(command.equals(",")) {
        	calc.buttonPressed(",");
        }
        else if(command.equals("A")) {
        	if(saving) {
        		calc.saveTo("A");
        		saving = false;
        	}else if(gettingSize) {
        		calc.getSize("A");
        		gettingSize = false;
        	}else if(subtracting && lastCommand == "B") {
        		calc.getSubtraction("B", "A");
        		subtracting = false;
        	}
        	else {
        		calc.setDisplayValue(calc.getSetA().toString());
        		lastCommand = "A";
        	}
        }
        else if(command.equals("B")) {
        	if(saving) {
        		calc.saveTo("B");
        		saving = false;
        	}else if(gettingSize) {
        		calc.getSize("B");
        		gettingSize = false;
        	}else if(subtracting && lastCommand == "A") {
        		calc.getSubtraction("A", "B");
        		subtracting = false;
        	}
        	else {
        		calc.setDisplayValue(calc.getSetB().toString());
        		lastCommand = "B";
        	}       
        }
        else if(command.equals("size")) {
        	gettingSize = true;;
        }
        else if(command.equals("⋃")) {
        	calc.getUnion();
        }
        else if(command.equals("∩")) {
        	calc.getIntersection();
        }
        else if(command.equals("-")) {
        	calc.buttonPressed("-");
        }
        else if(command.equals("*")) {
        	calc.buttonPressed("*");
        }
        else if(command.equals("/")) {
        	subtracting = true;
        	calc.setDisplayValue("subtracting");
        }
        else if(command.equals("clear")) {
            calc.reset();
        }
        else if(command.equals("?")) {
            showInfo();
        } 
        
        redisplay();
    }
	
	@Override
    protected void redisplay() {		
          display.setText("" + calc.getDisplayValue());       
	}

}
    	
    
	


