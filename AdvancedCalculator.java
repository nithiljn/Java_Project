package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvancedCalculator extends JFrame implements ActionListener {
	private JTextField display;
	private JButton[] numButtons = new JButton[10];
	private JButton addButton, subButton, mulButton, divButton, decButton, equButton, clrButton, delButton;
	private double num1 = 0, num2 = 0, result = 0;
	private char operator;

	public AdvancedCalculator() {
		setTitle("Advanced Calculator");
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		display = new JTextField();
		display.setFont(new Font("Arial", Font.PLAIN, 24));
		display.setEditable(false);
		display.setHorizontalAlignment(JTextField.RIGHT);
		add(display, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));
		buttonPanel.setBackground(Color.DARK_GRAY);

		for (int i = 0; i < 10; i++) {
			numButtons[i] = new JButton(String.valueOf(i));
			numButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
			numButtons[i].setBackground(Color.LIGHT_GRAY);
			numButtons[i].setForeground(Color.BLACK);
			numButtons[i].addActionListener(this);
		}

		addButton = createButton("+", Color.ORANGE);
		subButton = createButton("-", Color.ORANGE);
		mulButton = createButton("*", Color.ORANGE);
		divButton = createButton("/", Color.ORANGE);
		decButton = createButton(".", Color.LIGHT_GRAY);
		equButton = createButton("=", Color.GREEN);
		clrButton = createButton("C", Color.RED);
		delButton = createButton("DEL", Color.YELLOW);

		buttonPanel.add(numButtons[1]);
		buttonPanel.add(numButtons[2]);
		buttonPanel.add(numButtons[3]);
		buttonPanel.add(addButton);

		buttonPanel.add(numButtons[4]);
		buttonPanel.add(numButtons[5]);
		buttonPanel.add(numButtons[6]);
		buttonPanel.add(subButton);

		buttonPanel.add(numButtons[7]);
		buttonPanel.add(numButtons[8]);
		buttonPanel.add(numButtons[9]);
		buttonPanel.add(mulButton);

		buttonPanel.add(decButton);
		buttonPanel.add(numButtons[0]);
		buttonPanel.add(equButton);
		buttonPanel.add(divButton);

		buttonPanel.add(clrButton);
		buttonPanel.add(delButton);

		add(buttonPanel, BorderLayout.CENTER);

		setVisible(true);
	}

	private JButton createButton(String text, Color color) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 20));
		button.setBackground(color);
		button.setForeground(Color.BLACK);
		button.addActionListener(this);
		return button;
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 10; i++) {
			if (e.getSource() == numButtons[i]) {
				display.setText(display.getText().concat(String.valueOf(i)));
			}
		}

		if (e.getSource() == decButton) {
			if (!display.getText().contains(".")) {
				display.setText(display.getText().concat("."));
			}
		}

		if (e.getSource() == addButton || e.getSource() == subButton || e.getSource() == mulButton
				|| e.getSource() == divButton) {
			if (!display.getText().isEmpty()) {
				num1 = Double.parseDouble(display.getText());
				operator = e.getActionCommand().charAt(0);
				display.setText("");
			}
		}

		if (e.getSource() == equButton) {
			if (!display.getText().isEmpty()) {
				num2 = Double.parseDouble(display.getText());
				switch (operator) {
				case '+':
					result = num1 + num2;
					break;
				case '-':
					result = num1 - num2;
					break;
				case '*':
					result = num1 * num2;
					break;
				case '/':
					result = num2 != 0 ? num1 / num2 : Double.NaN;
					break;
				}
				display.setText(String.valueOf(result));
				num1 = result;
			}
		}

		if (e.getSource() == clrButton) {
			display.setText("");
		}

		if (e.getSource() == delButton) {
			String text = display.getText();
			display.setText(text.length() > 0 ? text.substring(0, text.length() - 1) : "");
		}
	}

	public static void main(String[] args) {
		new AdvancedCalculator();
	}
}
