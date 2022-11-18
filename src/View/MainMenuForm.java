package View;

import javax.swing.*;

public class MainMenuForm extends BaseForm
{
    public JButton loginButton = new JButton("Login");
    public JButton registerButton = new JButton("Register");
    public JButton viewButton = new JButton("View Data");
    public MainMenuForm()
    {
        super();
        this.setTitle("Main Menu");

        this.loginButton.addActionListener(e -> new LoginForm().setVisible(true));
        this.registerButton.addActionListener(e -> new RegisterForm().setVisible(true));
        this.viewButton.addActionListener(e -> new UserDataForm().setVisible(true));

        this.addMenu(this.loginButton);
        this.addMenu(this.registerButton);
        this.addMenu(this.viewButton);

        this.buildForm();
    }
}
