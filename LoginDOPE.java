import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;

public class LoginDOPE extends JPanel{

	private static JLabel password1, label;
	private static JTextField username;
	private static JButton button;
	private static JPasswordField Password;

    LoginDOPE(){
        
        //making panel
        setLayout(null);

        //making frame

        //username label constructor
        label = new JLabel("Username");
        label.setBounds(100, 8, 70, 20);
        add(label);

        //username textfield constructor
        username = new JTextField();
        username.setBounds(100, 27, 193, 28);
        add(username);

        // Password Label constructor
        password1 = new JLabel("Password");
        password1.setBounds(100, 55, 70, 20);
        add(password1);

        // Password TextField
        Password = new JPasswordField();
        Password.setBounds(100, 75, 193, 28);
        add(Password);

        //butn constructor
        button = new JButton("Login");
        button.setBounds(100, 110, 90, 25);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String Username = username.getText();
                String Password1 = Password.getText();

                if (Functions.loginn(Username, Password1)){
                    //JOptionPane.showMessageDialog(null, "Login Successful");
                    Main.transactionPanel.setVisible(false);
                    Main.checkoutPanel.setVisible(false);
                    Main.cardlayout.show(Main.cards, "managerPanel");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Username or Password mismatch ");
                }

                }
                catch(Exception x) {System.out.println(x.getMessage());}
                
            }
        });
        add(button);

    }

}

