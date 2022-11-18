import View.MainMenuForm;

public class Main
{
    public static void main(String[] args)
    {
        new Thread(() -> new MainMenuForm().setVisible(true)).start();
    }
}
