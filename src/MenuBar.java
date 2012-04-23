import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

public class MenuBar extends JMenuBar {
	final JFileChooser fc = new JFileChooser();
	static JRadioButtonMenuItem userChosenAction;

	public MenuBar() {

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("Üldine");
		JMenu editMenu = new JMenu("Alustaja");
		JMenu viewMenu = new JMenu("Abi");
		this.add(fileMenu);
		this.add(editMenu);
		this.add(viewMenu);

		JMenuItem newAction = new JMenuItem("Uus");
		JMenuItem saveAction = new JMenuItem("Salvesta");
		JMenuItem exitAction = new JMenuItem("Lahku");
		ButtonGroup group = new ButtonGroup();
		userChosenAction = new JRadioButtonMenuItem("Kasutaja");
		userChosenAction.setSelected(true);
		JRadioButtonMenuItem agentChosenAction = new JRadioButtonMenuItem("Agent");
		group.add(userChosenAction);
		group.add(agentChosenAction);
		JMenuItem manualAction = new JMenuItem("Juhend");

		newAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mainClass.motivationalSphere.MotivationalSphereToNull();
				mainClass.dialog.clearTextArea();
				mainClass.dialog.getTextField().setEditable(true);
				mainClass.dialog.firstSentence();
			}
		});
		
		exitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mainClass.dialog.getFrame().dispose();
				
			}
		});
		
		saveAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!isEmpty(mainClass.dialog.getTextAreaText())){
				int returnVal = fc.showSaveDialog(new FileChooserDemo());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						Writer output = new BufferedWriter(new FileWriter(file));
						output.write(mainClass.dialog.getTextAreaText());
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}}
		});
		
		manualAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				boolean isJPanelVisible = mainClass.dialog.isTextPanelVisible();
				mainClass.dialog.openManual();
				if(isJPanelVisible){
					mainClass.dialog.getJPanelSouth().setVisible(false);
					JPanel jPanel = new JPanel();
					JButton button = new JButton("Tagasi");
					jPanel.add(button);
					mainClass.dialog.getJPanelManual().add(jPanel, BorderLayout.SOUTH);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							mainClass.dialog.openTextArea();
						}
					});
				}
			}
		});

		fileMenu.add(newAction);
		fileMenu.add(saveAction);
		fileMenu.addSeparator();
		fileMenu.add(exitAction);
		editMenu.add(userChosenAction);
		editMenu.add(agentChosenAction);
		// editMenu.addSeparator();
		viewMenu.add(manualAction);
	}
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
}