package br.com.marciofontes.calc.visao;

import java.awt.BorderLayout;
import javax.swing.*;

@SuppressWarnings("serial")
public class Calculadora extends JFrame {

	public Calculadora() {
		organizarLayout();
		setSize(232, 322);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void organizarLayout() {
		setLayout(new BorderLayout());
		add(new Display(), BorderLayout.NORTH);
		add(new Teclado(), BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Calculadora());
	}
}
