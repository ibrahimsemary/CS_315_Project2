import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ErrorHandling {

   public static void main(String argv[]) {
      String errorType = "Access Denied";
      String errorExplanation = "Incorrect Username and/or Password. Try again.";
      createErrorMessage(errorType, errorExplanation);
   }

   private static void createErrorMessage(String errorType, String errorExplanation) {

      JOptionPane.showMessageDialog(null, errorExplanation, 
      errorType, JOptionPane.ERROR_MESSAGE);
   }
}