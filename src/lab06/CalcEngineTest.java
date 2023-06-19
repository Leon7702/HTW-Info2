package lab06;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalcEngineTest {

    private CalcEngine calcEngine;
    private HexaNotation gui;

    @Before
    public void setUp(){
        calcEngine = new CalcEngine();
        gui = new HexaNotation(calcEngine);
    }

    @Test
    public void testPlus() {

        calcEngine.numberPressed(5);
        calcEngine.plus();
        calcEngine.numberPressed(3);
        calcEngine.equals();
        assertEquals(8, calcEngine.getDisplayValue());
    }

    @Test
    public void testMinus() {
        calcEngine.minus();
        calcEngine.numberPressed(10);
        calcEngine.minus();
        calcEngine.numberPressed(2);
        calcEngine.equals();
        assertEquals(-12, calcEngine.getDisplayValue());
    }

    @Test
    public void testMulti() {
        calcEngine.numberPressed(3);
        calcEngine.multi();
        calcEngine.numberPressed(3);
        calcEngine.equals();
        assertEquals(9, calcEngine.getDisplayValue());	}

    @Test
    public void testDiv() {
        calcEngine.numberPressed(10);
        calcEngine.div();
        calcEngine.numberPressed(2);
        calcEngine.equals();
        assertEquals(5, calcEngine.getDisplayValue());
    }

    @Test
    public void testClear() {
        calcEngine.numberPressed(7);
        calcEngine.clear();
        assertEquals(0, calcEngine.getDisplayValue());

    }


    @Test
    public void testHexa(){
        calcEngine.numberPressed(1);
        calcEngine.numberPressed(1);
        gui.redisplay();
        assertEquals("B", gui.convertToHex());
    }

    @Test
    public void HexMulti(){
        calcEngine.numberPressed(1);
        calcEngine.numberPressed(0);
        calcEngine.multi();
        calcEngine.numberPressed(1);
        calcEngine.numberPressed(1);
        calcEngine.equals();
        assertEquals("6E", gui.convertToHex());
    }

    public void hexPlus(){
        calcEngine.numberPressed(1);
        calcEngine.numberPressed(0);
        calcEngine.plus();
        calcEngine.numberPressed(1);
        calcEngine.numberPressed(2);
        calcEngine.equals();
        assertEquals("16", gui.convertToHex());
    }

    public void hexMinus(){
        calcEngine.numberPressed(2);
        calcEngine.numberPressed(5);
        calcEngine.minus();
        calcEngine.numberPressed(1);
        calcEngine.numberPressed(0);
        calcEngine.equals();
        assertEquals("B", gui.convertToHex());
    }
    @Test
    public void HexDiv(){
        calcEngine.numberPressed(1);
        calcEngine.numberPressed(0);
        calcEngine.numberPressed(0);
        calcEngine.div();
        calcEngine.numberPressed(1);
        calcEngine.numberPressed(0);
        calcEngine.equals();
        assertEquals("A", gui.convertToHex());
    }
}
