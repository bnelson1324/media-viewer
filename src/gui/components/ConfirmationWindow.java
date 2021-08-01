package gui.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ConfirmationWindow extends JFrame {

	/* Popup window offering 1 or 2 choices: yes or no */
	
	// whether the user chose "yes" or "no", false until a choice is selected
	public boolean choice;
	
	// runnable run when the user chooses "yes" or "no"
	private Runnable onChoice;
	
	// components
	private JLabel lblDesc;
	private JPanel pnlButtons, pnlYes, pnlNo;
	private JButton btnYes, btnNo;
	
	// two buttons
	public ConfirmationWindow(String title, String description, String yesText, String noText) {
		super(title);
		setup(description);
		addYesButton(yesText, BorderLayout.WEST);
		addNoButton(noText, BorderLayout.EAST);
		
		this.setSize(480, 180);
		
		// centers window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) ((screenSize.width-this.getSize().width)*0.5), (int) ((screenSize.height-this.getSize().height)*0.25));
		
		this.setVisible(true);
	}
	
	// one button (choice is true)
	public ConfirmationWindow(String title, String description, String yesText) {
		super(title);
		setup(description);
		addYesButton(yesText, BorderLayout.CENTER);	
		
		this.setSize(480, 180);
		
		// centers window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) ((screenSize.width-this.getSize().width)*0.5), (int) ((screenSize.height-this.getSize().height)*0.25));
		
		this.setVisible(true);
	}
	
	public void setOnChoice(Runnable r) {
		this.onChoice = r;
	}
	
	// misc setup, sets up jframe and creates body of confirmation window (description and jpanel holding buttons)
	private void setup(String description) {
		this.choice = false;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		lblDesc = new JLabel(description);
		lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblDesc, BorderLayout.CENTER);
		lblDesc.setBorder(new EmptyBorder(10, 20, 10, 20));
		
		pnlButtons = new JPanel();
		getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		pnlButtons.setLayout(new BorderLayout(0, 0));
	}
	
	private void addYesButton(String yesText, String btnLayout) {
		pnlYes = new JPanel();
		pnlButtons.add(pnlYes, btnLayout);
		pnlYes.setBorder(new EmptyBorder(10, 50, 10, 10));
		btnYes = new JButton(yesText);
		pnlYes.add(btnYes, BorderLayout.CENTER);
		
		btnYes.addActionListener((e) -> {
			choice = true;
			if(onChoice != null) {
				onChoice.run();
			}
			ConfirmationWindow.this.setVisible(false);
			ConfirmationWindow.this.dispose();
		});
	}
	
	private void addNoButton(String noText, String btnLayout) {
		pnlNo = new JPanel();
		pnlButtons.add(pnlNo, BorderLayout.EAST);
		pnlNo.setBorder(new EmptyBorder(10, 10, 10, 50));
		btnNo = new JButton(noText);
		pnlNo.add(btnNo, btnLayout);
		
		btnNo.addActionListener((e) -> {
			choice = false;
			if(onChoice != null) {
				onChoice.run();
			}
			ConfirmationWindow.this.setVisible(false);
			ConfirmationWindow.this.dispose();
		});
	}
	
	
}
