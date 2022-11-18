package View;

import javax.swing.*;

public class BaseActionForm extends BaseForm
{
    public JButton continueButton = new JButton("Continue");
    public JButton backButton = new JButton("Back");

    public BaseActionForm()
    {
        super();
    }

    @Override
    public void buildForm()
    {
        this.backButton.addActionListener(e -> this.dispose());
        this.addMenu(backButton, continueButton);
        super.buildForm();
    }
}
