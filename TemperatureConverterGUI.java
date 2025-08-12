import javax.swing.*;
import  java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame {
    private JTextField tempField;
    private JComboBox<String> unitBox;
    private JLabel resultLabel;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new FlowLayout());

        add(new JLabel("Enter Temperature:"));
        tempField = new JTextField(10);
        add(tempField);

        add(new JLabel("Select Unit:"));
        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitBox = new JComboBox<>(units);
        add(unitBox);

        JButton convertBtn = new JButton("Convert");
        add(convertBtn);

        resultLabel = new JLabel(" ");
        add(resultLabel);

        convertBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
    }
    private void convertTemperature() {
        try {
            double temp = Double.parseDouble(tempField.getText());
            String unit = (String) unitBox.getSelectedItem();
            String result = "";

            if (unit.equals("Celsius")) {
                double f = (temp*9/5)+32;
                double k = temp + 273.15;
                result = String.format("Fahrenheit:%.2f°F | Kelvin:%.2fK", f,k);
            } else if (unit.equals("Fahrenheit")) {
                double c = (temp-32)*5/9;
                double k = c +273.15;
                result = String.format("Celsius:%.2f°C | Kelvin:%.2fK", c, k);
            } else if (unit.equals("Kelvin")) {
                double c = temp -273.15;
                double f = (c*9/5)+32;
                result = String.format("Celsius:%.2f°C | Fahrenheit:%.2f°F",c, f);
            }
            resultLabel.setText(result);
        } catch (NumberFormatException ex){
            resultLabel.setText("Invalid input! Please enter a number.");
        }
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            new TemperatureConverterGUI().setVisible(true);
        });
    }
}