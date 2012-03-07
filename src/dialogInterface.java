import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class dialogInterface implements ActionListener {
	
	private JTextField textField = new JTextField(60);
	private JTextArea textArea = new JTextArea(20,60);
	private final static String newline = "\n";

	public dialogInterface() {
		JFrame frame = new JFrame("Suhtlustreener");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,400);
		frame.setLocation(300,300);
		frame.add(textPanel());
		frame.pack();
		frame.setVisible(true);
	}

	private Component textPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
 
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
 
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
 
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL; 
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel.add(scrollPane, gridBagConstraints);
        
        textField.addActionListener(this);
        jPanel.add(textField, gridBagConstraints);
        return jPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        String text = textField.getText();
        textArea.append(text + newline);
        
        textField.setText("");
        textArea.setCaretPosition(textArea.getDocument().getLength());
		
	}
	
	public void addTextToTextArea(String enteredText){
		textArea.append(enteredText + newline);
	}

}
