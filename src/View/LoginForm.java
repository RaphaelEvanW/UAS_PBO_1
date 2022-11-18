package View;

import Controller.DatabaseManager;

import javax.swing.*;
import java.sql.SQLException;

public class LoginForm extends BaseActionForm
{
    private JTextField usernameField = new JTextField();
    private JTextField passwordField = new JPasswordField();
    private JLabel imageIcon = new JLabel(new ImageIcon("logo.png"));

    public LoginForm()
    {
        super();

        this.setTitle("Login");
        this.continueButton.setText("Login");

        this.addMenu(this.imageIcon);
        this.addMenu("Username", this.usernameField);
        this.addMenu("Password", this.passwordField);

        this.continueButton.addActionListener(e ->
        {
            try
            {
                var users = DatabaseManager.getInstance().getUsers("User", "userId");
                boolean login = false;
                for (var user : users)
                {
                    if (user.getName().equals(this.usernameField.getText()) && user.getPassword().equals(this.passwordField.getText()))
                    {
                        login = true;
                    }
                }
                String temp = login ? "success!" : "failed!";
                JOptionPane.showMessageDialog(null, "Login " + temp, "Login", JOptionPane.INFORMATION_MESSAGE);
                if (login)
                {
                    new UserDataForm().setVisible(true);
                }
            }
            catch (SQLException exception)
            {
                JOptionPane.showMessageDialog(
                        null,
                        "Unexpected error:  " + exception.getMessage(),
                        "Could not connect to SQL server",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        this.buildForm();
    }
}
