package view;

import javax.swing.JOptionPane;

public class Dialogs {

	public static void showException(String message, Exception e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, message, "Exception Dialog", JOptionPane.ERROR_MESSAGE);
	}

}
