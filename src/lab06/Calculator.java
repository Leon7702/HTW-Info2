package lab06;

public class Calculator
{
    private CalcEngine engine;
    private UserInterface gui;
    private HexaNotation gui2;

    /**
     * Create a new calculator and show it.
     */
    public Calculator()
    {
        engine = new CalcEngine();
        gui2 = new HexaNotation(engine);
    }

    /**
     * In case the window was closed, show it again.
     */
    public void show()
    {
        gui.setVisible(true);
    }
}

