import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private static final JFrame frame = new JFrame();
    private static final JPanel contentPane = new JPanel(new BorderLayout());
    private static final JPanel top = new JPanel(new GridBagLayout());
    private static final JLabel titleLabel = new JLabel("Temperature Convertor");
    private static final JPanel mainPanel = new JPanel(new GridLayout(2, 3));

    private static final JTextField celField = new JTextField(5);
    private static final JTextField farField = new JTextField(5);
    private static final JTextField kelField = new JTextField(5);

    private static final JLabel celLabel = new JLabel("Celsius (°C)");
    private static final JLabel farLabel = new JLabel("Fahrenheit (F)");
    private static final JLabel kelLabel = new JLabel("Kelvin (K)");

    private static final JButton convertButton = new JButton("Convert");
    private static final JButton clearButton = new JButton("Clear");


    public GUI() {
        initializeFrame();
        initializeContentPane();
        initializeTitle();
        initializeMain();
        initializeBottom();
    }

    private void initializeFrame() {
        frame.setTitle("Temperature Convertor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initializeContentPane() {
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(contentPane);
    }

    private void initializeTitle() {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        top.setPreferredSize(new Dimension(100, 100));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        top.add(titleLabel, gbc);

        contentPane.add(top, BorderLayout.NORTH);
    }

    private void initializeMain() {

        Font font = new Font("Arial", Font.PLAIN, 24);
        celLabel.setFont(font);
        farLabel.setFont(font);
        kelLabel.setFont(font);
        celField.setFont(font);
        farField.setFont(font);
        kelField.setFont(font);

        JPanel celPanel = new JPanel(new FlowLayout());
        celPanel.add(celLabel);
        celPanel.add(celField);
        celField.setHorizontalAlignment(JTextField.CENTER);


        JPanel farPanel = new JPanel(new FlowLayout());
        farPanel.add(farLabel);
        farPanel.add(farField);
        farField.setHorizontalAlignment(JTextField.CENTER);

        JPanel kelPanel = new JPanel(new FlowLayout());
        kelPanel.add(kelLabel);
        kelPanel.add(kelField);
        kelField.setHorizontalAlignment(JTextField.CENTER);


        mainPanel.add(celPanel);
        mainPanel.add(farPanel);
        mainPanel.add(kelPanel);

        contentPane.add(mainPanel, BorderLayout.CENTER);

    }

    private void initializeBottom() {
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(convertButton);
        bottomPanel.add(clearButton);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperatures();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void convertTemperatures() {
        try {
            if (!celField.getText().isEmpty()) {
                double celsius = Double.parseDouble(celField.getText());
                farField.setText(String.format("%.2f", celsius * 9 / 5 + 32));
                kelField.setText(String.format("%.2f", celsius + 273.15));
            } else if (!farField.getText().isEmpty()) {
                double fahrenheit = Double.parseDouble(farField.getText());
                celField.setText(String.format("%.2f", (fahrenheit - 32) * 5 / 9));
                kelField.setText(String.format("%.2f", (fahrenheit - 32) * 5 / 9 + 273.15));
            } else if (!kelField.getText().isEmpty()) {
                double kelvin = Double.parseDouble(kelField.getText());
                celField.setText(String.format("%.2f", kelvin - 273.15));
                farField.setText(String.format("%.2f", (kelvin - 273.15) * 9 / 5 + 32));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a number.");
        }
    }

    private void clearFields() {
        celField.setText("");
        farField.setText("");
        kelField.setText("");
    }

}
