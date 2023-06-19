package lab09;

import java.util.HashSet;
import java.util.Set;

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
    private HashSet<String> setA;
    private HashSet<String> setB;
    private HashSet<String> tmp;


  
    /**
     * Create a CalcEngine.
     */
    public CalcEngine()
    {
        clear();
        sb = new StringBuilder();
        setA = new HashSet<>();
        setB = new HashSet<>();
        tmp = new HashSet<>();
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
    
    public void saveSet(String c) {
    	String[] elements = c.split(",");
    	
    	for (int i = 0; i < elements.length; i++) {
    		tmp.add(elements[i]);
    	}	
    }
    
    public void saveTo(String c) {
    	if(c.equals("A")) setA = tmp;
    	if(c.equals("B")) setB = tmp;
    	tmp = new HashSet<>();
    	clear();
    }
    
    public void getSize(String c) {
    	if(c.equals("A")) {
    		displayValue = "Size of A = " + setA.size();
    	}
    	if(c.equals("B")) {
    		displayValue = "Size of B = " + setB.size();
    	}
    }
    

    
    public void getUnion() {
  
    	tmp.addAll(setA);
    	
    	tmp.addAll(setB);
    	displayValue = "Union of A and B: " + tmp.toString();
    	tmp = new HashSet<>();
    }

    
    public void getIntersection() {
    	  
    	tmp.addAll(setB);
    	tmp.retainAll(setA);
    	displayValue = "Intersection of A and B: " + tmp.toString();
    	tmp = new HashSet<>();

    	}
    
    public void getSubtraction(String original, String subtraction) {
    	
    	if(original.equals("A")) {
        	tmp.addAll(setA);
        	tmp.removeAll(setB);
    	}
    	
    	if(original.equals("B")) {
        	tmp.addAll(setB);
        	tmp.removeAll(setA);

    	}
    	
    	displayValue = "Subtracting " + subtraction + " from " + original + ": " + tmp.toString();
    	
    	tmp = new HashSet<>();
    	
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
    
    public void reset() {
        setA = new HashSet<>();
        setB = new HashSet<>();
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

	public HashSet<String> getSetA() {
		return setA;
	}

	public void HashSet(HashSet<String> setA) {
		this.setA = setA;
	}

	public HashSet<String> getSetB() {
		return setB;
	}

	public void setSetB(HashSet<String> setB) {
		this.setB = setB;
	}



}
