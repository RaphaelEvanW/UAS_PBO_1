package View;

import Controller.DatabaseManager;
import Model.User;

import javax.swing.*;
import java.sql.SQLException;

public class RegisterForm extends BaseActionForm
{
    private JTextField nameField = new JTextField();
    private JTextField emailField = new JTextField();
    private JTextField genderField = new JTextField();
    private JTextField passwordField = new JPasswordField();
    private JSpinner followersField = new JSpinner();
    private JComboBox<String> categoryChooser = new JComboBox<>();

    public RegisterForm()
    {
        super();

        this.setTitle("Register");
        this.continueButton.setText("Register");

        this.addMenu("Name", this.nameField);
        this.addMenu("Email", this.emailField);
        this.addMenu("Password", this.passwordField);
        this.addMenu("Gender", this.genderField);
        this.addMenu("Category", this.categoryChooser);
        this.addMenu("Followers", this.followersField);

        try
        {
            var categories = DatabaseManager.getInstance().getUserCategories("CategoryUser", "categoryId");
            for (var category : categories)
            {
                this.categoryChooser.addItem(category.getName());
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

        this.continueButton.addActionListener(e ->
        {
            try
            {
                var users = DatabaseManager.getInstance().getUsers("User", "userId");
                boolean exists = false;
                for (var user : users)
                {
                    System.out.println(this.nameField.getText());
                    System.out.println(user.getName());
                    if (user.getName().equals(this.nameField.getText()) || user.getEmail().equals(this.emailField.getText()))
                    {
                        exists = true;
                        break;
                    }
                }
                if (this.nameField.getText().isEmpty() || this.emailField.getText().isEmpty() || this.passwordField.getText().isEmpty() || this.genderField.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Register failed! Data is empty!", "Register", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (exists)
                {
                    JOptionPane.showMessageDialog(null, "Register failed! Data exists!", "Register", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    var user = new User(0,
                            this.categoryChooser.getSelectedIndex(),
                            this.nameField.getText(),
                            this.emailField.getText(),
                            this.passwordField.getText(),
                            this.genderField.getText(),
                            (Integer) this.followersField.getValue());
                    DatabaseManager.getInstance().insert("user", user.getData());
                    JOptionPane.showMessageDialog(null, "Register success!", "Register", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();

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
