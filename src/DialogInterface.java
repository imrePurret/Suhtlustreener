import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class DialogInterface implements ActionListener {

	private JTextField textField = new JTextField(50);
	private JTextArea textArea = new JTextArea(15, 50);
	private JTextField textField2 = new JTextField(15);
	private JTextPane textPane = new JTextPane();
	private List<Answer> argumentList;
	private final static String newline = "\n";
	private JFrame frame;
	private JPanel jPanel = new JPanel();
	private JPanel jPanelManual = new JPanel();
	private JMenuBar menu = new MenuBar();
	private String userName;
	private JPanel jPanelSouth;
	private JLabel label;
	private int starter;
	private String tacticName;
	private String[] tactics = {"veenmine", "ahvatlemine", "ahvardamine"};

	public DialogInterface() {
		frame = new JFrame("Suhtlustreener");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 620);
		frame.setLocation(200, 200);
		frame.add(textPanelManual());
		frame.pack();
		Dimension dim = new Dimension(650, 420);
		frame.setMinimumSize(dim);
		Dimension dim2 = new Dimension(600, 420);
		frame.setMaximumSize(dim2);
		frame.setVisible(true);
		jPanel.setVisible(false);
	}

	private Component textPanel() {
		jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());

		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		menu.setVisible(true);
		jPanel.add(menu, BorderLayout.NORTH);
		jPanel.add(scrollPane, BorderLayout.CENTER);
		JPanel jPanelTextAreaSouth = new JPanel();
		JButton sendButton = new JButton("Saada");

		textField.addActionListener(this);
		sendButton.addActionListener(this);

		jPanelTextAreaSouth.add(textField);
		jPanelTextAreaSouth.add(sendButton);

		jPanel.add(jPanelTextAreaSouth, BorderLayout.SOUTH);
		jPanel.setVisible(true);
		return jPanel;
	}

	private Component textPanelManual() {
		jPanelManual = new JPanel();
		jPanelManual.setLayout(new BorderLayout());

		textPane.setContentType("text/html");
		textPane.setEditable(false);
		textPane.setBackground(new Color(238, 238, 224));
		textPane.setBorder(null);
		Dimension dim = new Dimension(600, 420);
		textPane.setMaximumSize(dim);
		writeManual(textPane);
		JScrollPane scrollPane = new JScrollPane(textPane);
		menu.setVisible(true);

		jPanelManual.add(menu, BorderLayout.NORTH);
		jPanelManual.add(scrollPane, BorderLayout.CENTER);
		jPanelSouth = new JPanel();
		JButton sendButton = new JButton("Edasi");
		label = new JLabel("Sisesta oma kasutajanimi");
		textField2.setDocument(new JTextFieldLimit(15));

		textField2.addActionListener(this);
		sendButton.addActionListener(this);

		jPanelSouth.add(label);
		jPanelSouth.add(textField2);
		jPanelSouth.add(sendButton);

		jPanelManual.add(jPanelSouth, BorderLayout.SOUTH);
		jPanelManual.setVisible(true);
		return jPanelManual;
	}

	private void writeManual(JTextPane textPane2) {
		String text = "<html><b><font size=6>Tutvustus</font></b><br>"
				+ "&#8;Tegemist on dialoogisüsteemiga, kus saab arvutiga dialoogi pidada. Dialoogi eesmärgiks on aidata<br>"
				+ "kasutaja argumenteerimisvõimet parandada. Dialoog toimub eesti keeles. Dialoogi alustaja eesmärgiks<br>"
				+ "on veenda teist osapoolt taimetoitlaseks hakkama, kasutades selleks erinevaid argumente, mis võiksid<br>"
				+ "suurendada teise osapoole huvi tegevuse vastu. Näiteks suurendada tegevuse meeldivust, rõhuda karistusele<br>"
				+ "või ressursside puudumisele.<br>"
				+ "<br><b><font size=6>Kasutusjuhend</font></b><br>"
				+ "<ol><li>Esmalt tuleks valida kasutaja, selleks on menüüribal valik \"Alustaja\".</li>"
				+ "<li>Teisena tuleks sisestada kasutajanimi. Välja täitmine on kohustuslik.</li>"
				+ "<li>Uue dialoogi alustamiseks on menüüribal valik \"Üldine\", mille all on alavalik \"Uus\". Uut dialoogi <br>"
				+ "alustab hetkel \"Alustaja\" valikus aktiivne osapool. </li>"
				+ "<li>Dialoogi salvestamiseks on menüüribal valik \"Üldine\", mille all on alavalik \"Salvesta\".</li>"
				+ "</ol></html>";
		textPane2.setText(text);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (jPanelManual.isVisible() && !isEmpty(textField2.getText())) {
			frame.add(textPanel(), 1);
			jPanelManual.setVisible(false);
			userName = textField2.getText();
			frame.addNotify();
			if(MenuBar.userChosenAction.isSelected()){
				firstSentence();
				starter = 0;
			}
			else{
				firstSentenceAgent();
				starter = 1;
			}
			MenuBar.userChosenAction.setText(textField2.getText());
		} else if (!isEmpty(textField.getText())) {
			String text = textField.getText();
			textArea.append(userName + " : " + text + newline);
			textField.setText("");
			textArea.setCaretPosition(textArea.getDocument().getLength());
			try {
				Thread.yield();
				Thread.sleep(500);
			} catch (InterruptedException exep) {
				exep.printStackTrace();
			}
			if(starter==0){
				new ConversationAgent().scales(text);				
			}
			if(starter==1){
				new ConversationAgent().tactic(text);				
			}
		}
	}

	public void firstSentenceAgent() {
		Random generator = new Random();
		int r = generator.nextInt(tactics.length);
		this.setTacticName(tactics[r]);
		setArgumentList(tacticName);
		String text = "Kas sa oleksid nõus taimetoitlaseks hakkama? ";
		textArea.append("Agent : " + text + newline);
		//new ConversationAgent().tactic(text);
		
	}

	public void firstSentence() {
		String text = "Kas sa oleksid nõus taimetoitlaseks hakkama?";
		textArea.append(userName + " : " + text + newline);
		new ConversationAgent().scales(text);
	}

	public void openManual() {
		jPanel.setVisible(false);
		jPanelManual.setVisible(true);
	}

	public void openTextArea() {
		jPanel.setVisible(true);
		jPanelManual.setVisible(false);
	}

	public boolean isTextPanelVisible() {
		return jPanel.isVisible();
	}

	public JPanel getJPanelSouth() {
		return jPanelSouth;
	}

	public JPanel getJPanelManual() {
		return jPanelManual;
	}

	public void addTextToTextArea(String enteredText) {
		textArea.append("Agent : " + enteredText + newline);
	}

	public void clearTextArea() {
		textArea.setText("");
	}

	public String getTextAreaText() {
		return textArea.getText();
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public List<Answer> getArgumentList() {
		return argumentList;
	}

	public void setArgumentList(String argumentList) {
		this.argumentList = new AnswerReader(argumentList).getAnswerList();
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public int getStarter() {
		return starter;
	}

	public void setStarter(int starter) {
		this.starter = starter;
	}
	
	public String getTacticName() {
		return tacticName;
	}

	public void setTacticName(String tacticName) {
		this.tacticName = tacticName;
	}

}
