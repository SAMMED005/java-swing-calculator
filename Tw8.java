import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class Tw8 {
    private final JFrame frame;
    private final JTextField textField;
    private double num1, num2, result;
    private String operator = "";

    public Tw8() {
        // Create the main frame
        frame = new JFrame("Simple Calculator");
        frame.setSize(300, 300);
        frame.setLocation(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the text field for displaying the result
        textField = new JTextField();
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 3);
        textField.setBorder(border);
        textField.setEditable(false);
        Font font = new Font("Cambria", Font.BOLD, 20); // Font name, style, and size textField.setFont(font);
        textField.setFont(font);
        textField.setPreferredSize(new Dimension(20, 40)); // Set preferred size (width, height)
        frame.add(textField, BorderLayout.NORTH);

        // Create the panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        // Create buttons and add action listeners
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            font = new Font("Cambria", Font.BOLD, 20); // Font name, style, and size textField.setFont(font);
            button.setFont(font);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Inner class for handling button clicks
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                // If a number is clicked, append it to the text field
                textField.setText(textField.getText() + command);
            } else if (command.equals("C")) {
                // Clear the text field
                textField.setText("");
                num1 = num2 = result = 0;
                operator = "";
            } else if (command.equals("=")) {
                // Calculate the result
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case "+" -> result = num1 + num2;
                    case "-" -> result = num1 - num2;
                    case "*" -> result = num1 * num2;
                    case "/" -> result = num1 / num2;
                }

                
                textField.setText(String.valueOf(result));
                operator = ""; // Reset operator
            } else {
                // Store the first number and operator
                if (!operator.isEmpty()) {
                    return; // Prevent multiple operators
                }
                num1 = Double.parseDouble(textField.getText());
                operator = command; // Set operator
                textField.setText(""); // Clear text field for the next number
            }
        }
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
        new Tw8(); // Create the calculator GUI
    }
}
