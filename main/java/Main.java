import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class Main extends JFrame implements ActionListener {
    static Frame frame;
    static JTextField result;
    static String a = "", b = "", operation = "";
    public static void main(String[] args) {
        Main listener = new Main();
        frame = new JFrame("Calculator");
        result = new JTextField(16);

        ArrayList<JButton> buttons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JButton jTempButton = new JButton(Integer.toString(i));
            jTempButton.addActionListener(listener);
            buttons.add(jTempButton);
        }

        List<String> operations = Arrays.asList("+","-","/","*","c", "=");

        JPanel panel = new JPanel();
        buttons.forEach(panel::add);
        operations.forEach(it -> {
            JButton jTempButton = new JButton(it);
            jTempButton.addActionListener(listener);
            panel.add(jTempButton);
        });

        GridLayout calculatorLayout = new GridLayout(4,4);
        panel.setLayout(calculatorLayout);

        JPanel mainPanel = new JPanel();
        mainPanel.add(result);
        mainPanel.add(panel);

        frame.add(mainPanel);
        frame.setSize(260, 200);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.charAt(0) >= '0' && actionCommand.charAt(0) <= '9') {
            if (operation.equals("")) {
                a = a + actionCommand;
            } else {
                b = b + actionCommand;
            }
            result.setText(a + operation + b);
        } else if (actionCommand.charAt(0) == 'c') {
            a = operation = b = "";
            result.setText(a + operation + b);
        } else if (actionCommand.charAt(0) == '=') {
            int eqResult = switch (operation) {
                case "+" -> Integer.parseInt(a) + Integer.parseInt(b);
                case "-" -> Integer.parseInt(a) - Integer.parseInt(b);
                case "*" -> Integer.parseInt(a) * Integer.parseInt(b);
                default -> Integer.parseInt(a) / Integer.parseInt(b);
            };
            a = String.valueOf(eqResult);
            result.setText(a);
            operation = b = "";
        } else {
            if (operation.equals("")) {
                operation = actionCommand;
            }
            result.setText(a + operation + b);
        }
    }
}
