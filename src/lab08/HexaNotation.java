package lab08;

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

public class HexaNotation extends UserInterface {

	private Map<String, JButton> buttonMap;
	private JCheckBox checkbox;
	private String hexString;


	public HexaNotation(CalcEngine engine) {
		super(engine);
	}

    protected void addCheckBox(Container panel, String text)
    {
        JCheckBox box = new JCheckBox(text);
        checkbox = box;
        box.addActionListener(this);
        panel.add(box);
        box.setSelected(true);
    }
    
	@Override
    protected void addButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);    
        buttonMap.put(buttonText, button);
    }
	
	@Override
	protected void makeFrame() {
		frame = new JFrame(calc.getTitle());
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        display = new JTextField();
        contentPane.add(display, BorderLayout.NORTH);

        buttonMap = new HashMap<>();
        JPanel buttonPanel = new JPanel(new GridLayout(6, 4));
            addButton(buttonPanel, "C");
            addButton(buttonPanel, "D");
            addButton(buttonPanel, "E");
            addButton(buttonPanel, "F");
            
            super.addButton(buttonPanel, "8");
            super. addButton(buttonPanel, "9");
            addButton(buttonPanel, "A");
            addButton(buttonPanel, "B");
            
            super.addButton(buttonPanel, "4");
            super.addButton(buttonPanel, "5");
            super.addButton(buttonPanel, "6");
            super.addButton(buttonPanel, "7");
            
            super.addButton(buttonPanel, "0");
            super.addButton(buttonPanel, "1");
            super.addButton(buttonPanel, "2");
            super.addButton(buttonPanel, "3");
            
            super.addButton(buttonPanel, "+");
            super.addButton(buttonPanel, "-");
            super.addButton(buttonPanel, "*");
            super.addButton(buttonPanel, "/");
            
            super.addButton(buttonPanel, "=");
            super.addButton(buttonPanel, "c");
            super.addButton(buttonPanel, "?");
            addCheckBox(buttonPanel, "toHEX");
      
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
        else if(command.equals("A")) {
            calc.buttonPressed("A");
        }
        else if(command.equals("B")) {
            calc.buttonPressed("B");
        }
        else if(command.equals("C")) {
            calc.buttonPressed("C");
        }
        else if(command.equals("D")) {
            calc.buttonPressed("D");
        }
        else if(command.equals("E")) {
            calc.buttonPressed("E");
        }
        else if(command.equals("F")) {
            calc.buttonPressed("F");
        }
        else if(command.equals("+")) {
        	calc.buttonPressed("+");
        }
        else if(command.equals("-")) {
        	calc.buttonPressed("-");
        }
        else if(command.equals("*")) {
        	calc.buttonPressed("*");
        }
        else if(command.equals("/")) {
        	calc.buttonPressed("/");
        }
        else if(command.equals("=")) {
            
        	if(checkbox.isSelected()) {
        		try {
        			convertToHex();
					calc.equals(hexString);
					String resultString = calc.getDisplayValue();
					double resultDouble = Double.parseDouble(resultString);
					String resultHex =  Integer.toHexString((int)resultDouble);
					calc.setDisplayValue("" + resultHex.toUpperCase());
				} catch (OverflowException e) {
					e.printStackTrace();
				} catch (UnderflowException e) {
					e.printStackTrace();
				} catch (NonSenseException e) {
					e.printStackTrace();
				}
        	} else {
        	
	        	try {
					calc.equals(calc.getDisplayValue());
				} catch (OverflowException e) {
					e.printStackTrace();
				} catch (UnderflowException e) {
					e.printStackTrace();
				} catch (NonSenseException e) {
					e.printStackTrace();
				}
        	}
        }
        else if(command.equals("c")) {
            calc.clear();
        }
        else if(command.equals("?")) {
            showInfo();
        }
        else if(command.equals("toHEX")) {
        	if(!checkbox.isSelected()) {
        		buttonMap.get("A").setEnabled(false);
        		buttonMap.get("B").setEnabled(false);
        		buttonMap.get("C").setEnabled(false);
        		buttonMap.get("D").setEnabled(false);
        		buttonMap.get("E").setEnabled(false);
        		buttonMap.get("F").setEnabled(false);


        	}
        	if(checkbox.isSelected()) {
        		buttonMap.get("A").setEnabled(true);
        		buttonMap.get("B").setEnabled(true);
        		buttonMap.get("C").setEnabled(true);
        		buttonMap.get("D").setEnabled(true);
        		buttonMap.get("E").setEnabled(true);
        		buttonMap.get("F").setEnabled(true);

        	}

  
        	calc.clear();
        }
        // else unknown command.

        redisplay();
    }
	
	protected void convertToHex() {
		
		hexString = calc.getDisplayValue();
				
		if(calc.getDisplayValue().contains("A")) {
			hexString = hexString.replace("A", "(5+5)");
		}
		if(calc.getDisplayValue().contains("B")) {
			hexString = hexString.replace("B", "(5+6)");
		}
		if(calc.getDisplayValue().contains("C")) {
			hexString = hexString.replace("C", "(5+7)");
		}
		if(calc.getDisplayValue().contains("D")) {
			hexString = hexString.replace("D", "(5+8)");
		}
		if(calc.getDisplayValue().contains("E")) {
			hexString = hexString.replace("E", "(5+9)");
		}
		if(calc.getDisplayValue().contains("F")) {
			hexString = hexString.replace("F", "(6+9)");
		}
		
	}

	@Override
    protected void redisplay() {
		
          display.setText("" + calc.getDisplayValue());

        
	}

}
    	
    
	


