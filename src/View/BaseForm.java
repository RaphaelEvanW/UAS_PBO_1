package View;

import View.Helper.SpringUtilities;

import javax.swing.*;
import java.awt.*;

public class BaseForm extends JDialog
{
    public JPanel mainBody = new JPanel();
    public int menuCount = 0;

    public void addMenu(Component a, Component b)
    {
        this.mainBody.add(a);
        this.mainBody.add(b);
        this.menuCount++;
    }

    public void addMenu(String text, Component c)
    {
        JLabel label = new JLabel(text, JLabel.TRAILING);
        label.setLabelFor(c);
        this.addMenu(label, c);
    }

    public void addMenu(Component c)
    {
        this.addMenu("", c);
    }

    public void buildForm()
    {
        SpringUtilities.makeCompactGrid(this.mainBody, this.menuCount, 2, 6, 6, 6, 6);
        this.pack();
    }

    public BaseForm()
    {
        super();
        this.setModal(true);
        this.mainBody.setLayout(new SpringLayout());
        this.add(this.mainBody);
    }
}
