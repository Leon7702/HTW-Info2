package lab08;


/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  David J. Barnes and Michael Kolling 
 * @version 2008.03.30
 */
public class CalcEngine
{

    // The current value (to be) shown in the display.
    private String displayValue;
    private StringBuilder sb;
  
    /**
     * Create a CalcEngine.
     */
    public CalcEngine()
    {
        clear();
        sb = new StringBuilder();
    }

    /**
     * @return The value that should currently be displayed
     * on the calculator display.
     */
    public String getDisplayValue()
    {
        return displayValue;
    }
    
    public void setDisplayValue(String s) {
    	displayValue = s;
    }

    
    public void buttonPressed(String c) {
    	sb.append(c);
    	displayValue = sb.toString();
    }
    
    public boolean isOperator(char c) {
    	return (c == '+' || c == '-' || c == '*' || c == '/');
    }
    
    public boolean checkOperators(){
    	
    	boolean check = true;
    	for (int i = 0; i < displayValue.length() - 1; i++) {
        	
        	char current = displayValue.charAt(i);
        	char next = displayValue.charAt(i+1);
        	
        	if (isOperator(current) && isOperator(next)) {
        		check = false;
        	}
    	}
    	
    	if (displayValue.charAt(0) == '*' || displayValue.charAt(0) == '/') check = false;
    	return check;
    }
    
    public void equals(String calc) throws OverflowException, UnderflowException, NonSenseException {
  
        if(checkOperators()) {
        	String pfx = Postfix.infixToPostfix(calc);
            double result = Postfix.evaluate(pfx);
            displayValue = ""+result;
        } else {
        	System.out.print("Wrong operator input.");
        }
    	

    }

    /**
     * The 'C' (clear) button was pressed.
     * Reset everything to a starting state.
     */
    public void clear()
    {
        displayValue = "";
        sb = new StringBuilder();
    }

    /**
     * @return The title of this calculation engine.
     */
    public String getTitle()
    {
        return "Java Calculator";
    }

    /**
     * @return The author of this engine.
     */
    public String getAuthor()
    {
        return "Asli and Leon";
    }

    /**
     * @return The version number of this engine.
     */
    public String getVersion()
    {
       return "Version 2.0";
    }



}
