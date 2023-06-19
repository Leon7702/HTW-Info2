package lab06;

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
            int number = Integer.parseInt(command);
            calc.numberPressed(number);
        }
        else if(command.equals("A")) {
            calc.numberPressed(10);
        }
        else if(command.equals("B")) {
            calc.numberPressed(11);
        }
        else if(command.equals("C")) {
            calc.numberPressed(12);
        }
        else if(command.equals("D")) {
            calc.numberPressed(13);
        }
        else if(command.equals("E")) {
            calc.numberPressed(14);
        }
        else if(command.equals("F")) {
            calc.numberPressed(15);
        }
        else if(command.equals("+")) {
            calc.plus();
        }
        else if(command.equals("-")) {
            calc.minus();
        }
        else if(command.equals("*")) {
            calc.multi();
        }
        else if(command.equals("/")) {
            calc.div();
        }
        else if(command.equals("=")) {
            calc.equals();
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

    protected String convertToHex() {

        String s = Integer.toHexString((int)calc.getDisplayValue()).toUpperCase();
        return s;
    }

    @Override
    protected void redisplay()
    {
        if(checkbox.isSelected()) {
            display.setText(convertToHex());

        } else {
            display.setText("" + calc.getDisplayValue());

        }

    }

}
