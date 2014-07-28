package org.apcs.zeriksen.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apcs.zeriksen.binary.Secret;

public class GUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField picToDecode;
	private JTextField PicToEncode;
	private JTextField saveLoc;
	private JTextField MessageToEncode;
	private JTextArea textArea;
	private JFileChooser fc = new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnPictureToBe_1 = new JButton("Picture to be Encoded");
		btnPictureToBe_1.setBounds(249, 22, 180, 29);
		contentPane.add(btnPictureToBe_1);
		btnPictureToBe_1.addActionListener(this);

		saveLoc = new JTextField();
		saveLoc.setBounds(21, 55, 207, 28);
		contentPane.add(saveLoc);
		saveLoc.setColumns(10);

		PicToEncode = new JTextField();
		PicToEncode.setBounds(21, 21, 207, 28);
		contentPane.add(PicToEncode);
		PicToEncode.setColumns(10);

		JButton btnSaveLocation = new JButton("Save Location");
		btnSaveLocation.setBounds(249, 56, 180, 29);
		contentPane.add(btnSaveLocation);
		btnSaveLocation.addActionListener(this);

		JButton btnEncode = new JButton("Encode");
		btnEncode.setBounds(21, 113, 89, 29);
		contentPane.add(btnEncode);
		btnEncode.addActionListener(this);

		MessageToEncode = new JTextField();
		MessageToEncode.setBounds(122, 95, 307, 62);
		contentPane.add(MessageToEncode);
		MessageToEncode.setColumns(10);

		JButton btnDecode = new JButton("Decode");
		btnDecode.setBounds(21, 209, 91, 29);
		contentPane.add(btnDecode);
		btnDecode.addActionListener(this);

		picToDecode = new JTextField();
		picToDecode.setBounds(21, 169, 207, 28);
		contentPane.add(picToDecode);
		picToDecode.setColumns(10);

		JButton btnPictureToBe = new JButton("Picture to be Decoded");
		btnPictureToBe.setBounds(249, 169, 182, 29);
		contentPane.add(btnPictureToBe);
		btnPictureToBe.addActionListener(this);
		
		textArea = new JTextArea();
		textArea.setBounds(21, 241, 408, 107);
		textArea.setEditable(false);
		contentPane.add(textArea);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.getText() == "Encode") {
			Secret s = new Secret(MessageToEncode.getText());
			s.encode(PicToEncode.getText(), saveLoc.getText());
		} else if (button.getText() == "Decode") {
			textArea.setText(Secret.decode(picToDecode.getText()));
		} else if (button.getText() == "Picture to be Decoded") {
			int returnVar = fc.showOpenDialog(picToDecode);
			if (returnVar == JFileChooser.APPROVE_OPTION) {
				picToDecode.setText(fc.getSelectedFile().getPath());
			}
		} else if (button.getText() == "Save Location") {
			int returnVar = fc.showOpenDialog(picToDecode);
			if (returnVar == JFileChooser.APPROVE_OPTION) {
				saveLoc.setText(fc.getSelectedFile().getPath());
			}
		} else if (button.getText() == "Picture to be Encoded") {
			int returnVar = fc.showOpenDialog(picToDecode);
			if (returnVar == JFileChooser.APPROVE_OPTION) {
				PicToEncode.setText(fc.getSelectedFile().getPath());
			}
		}
	}
}
