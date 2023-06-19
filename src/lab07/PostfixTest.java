package lab07;

import static org.junit.Assert.*;

import org.junit.Test;

public class PostfixTest {

    private static final double DELTA = 1e-10; // Delta value for comparing double values

    @Test
    public void testEvaluate() throws OverflowException, UnderflowException {
        assertEquals(Postfix.evaluate("12*3+"), 5.0, DELTA);
    }
    @Test
    public void testEvaluate2() throws OverflowException, UnderflowException {
        assertEquals(Postfix.evaluate("123*+"), 7.0, DELTA);
    }
    @Test
    public void testEvaluate3() throws OverflowException, UnderflowException {
        assertEquals(Postfix.evaluate("12+34^-"), -78.0, DELTA);
    }
    @Test
    public void testEvaluate4() throws OverflowException, UnderflowException {
        assertEquals(Postfix.evaluate("12^34*-"), -11.0, DELTA);
    }
    @Test
    public void testEvaluate5() throws OverflowException, UnderflowException {
        assertEquals(Postfix.evaluate("123*+45^-6+"), -1011.0, DELTA);
    }
    @Test
    public void testEvaluate6() throws OverflowException, UnderflowException {
        assertEquals(Postfix.evaluate("12+3*456-^+"), 9.25, DELTA);
    }
    @Test
    public void testEvaluate7() throws OverflowException, UnderflowException {
        assertEquals(Postfix.evaluate("12+34/+5+678+*+"), 98.75, DELTA);
    }
    @Test
    public void testEvaluate8() throws OverflowException, UnderflowException {
        assertEquals(Postfix.evaluate("91-2-32*-1-"), -1.0, DELTA);
    }

}
