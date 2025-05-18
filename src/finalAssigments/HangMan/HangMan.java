package finalAssigments.HangMan;

import finalAssigments.HangMan.Controller.AccountController;
import finalAssigments.HangMan.Controller.MenuController;
import finalAssigments.HangMan.Model.DAO.UserDAO;
import finalAssigments.HangMan.View.*;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class HangMan {
    public static Font jaroFont;

    public static void main(String[] args) {
        try { // For creating and adding a font to my app I used chatgpt I have no knowledge of doing that and I just copied and pasted that.
            InputStream is = HangMan.class.getResourceAsStream("/finalAssigments/HangMan/resources/fonts/Jaro.ttf");
            if (is == null) {
                System.err.println("Font not found!");
                return;
            }

            jaroFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(28f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(jaroFont);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setUIFont(new Font("Chalkboard", Font.PLAIN, 14));

        MainFrame frame = new MainFrame();
        UserDAO userDAO = new UserDAO();
        MenuView menuView = new MenuView(null);
        SignInView signInView = new SignInView();
        RegisterView registerView = new RegisterView();


        new AccountController(userDAO, frame, menuView, signInView, registerView, null);
    }

    public static void setUIFont(Font font) {
        UIManager.put("Label.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("PasswordField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("List.font", font);
        UIManager.put("Table.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("TabbedPane.font", font);
        UIManager.put("TitledBorder.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("RadioButton.font", font);
    }
}
