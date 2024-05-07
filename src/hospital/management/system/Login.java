package hospital.management.system;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    JTextField textField;
    JPasswordField passwordField;
    JButton button;

    Login(){

        createLabel("Username", 40, 20, 100, 30, "Tahoma", 16, Font.BOLD, Color.BLACK);
        createLabel("Password", 40, 70, 100, 30, "Tahoma", 16, Font.BOLD, Color.BLACK);

        createField(150, 20, 150, 30, "Tahoma", 15, Font.PLAIN, 255, 179, 0);
        createpassField(150, 70, 150, 30, "Tahoma", 15, Font.PLAIN, 255, 179, 0);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(350, -30, 400, 300);
        add(label);

        createButton("Login", 40, 140, 120, 30, "serif", 15, Font.BOLD, Color.BLACK, Color.WHITE);
        createButton("Cancel", 180, 140, 120, 30, "serif", 15, Font.BOLD, Color.BLACK, Color.WHITE);





        getContentPane().setBackground(new Color(109, 164,170));
        setSize(750, 300);
        setLocation(280, 200);
        setLayout(null);
        setVisible(true);


    }

    private JLabel createLabel(String text, int x, int y, int width, int height, String fontName, int fontSize, int fontStyle, Color color) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font(fontName, fontStyle, fontSize));
        label.setForeground(color);
        add(label);
        return label;
    }
    private JTextField createField(int x, int y, int w, int h, String fontName, int fontSize, int fontStyle, int r, int g, int b){
        textField = new JTextField();
        textField.setBounds(x, y, w, h);
        textField.setFont(new Font(fontName, fontStyle, fontSize));
        textField.setBackground(new Color(r, g, b));
        add(textField);
        return textField;
    }
    private JPasswordField createpassField(int x, int y, int w, int h, String fontName, int fontSize, int fontStyle, int r, int g, int b){
        passwordField = new JPasswordField();
        passwordField.setBounds(x, y, w, h);
        passwordField.setFont(new Font(fontName, fontStyle, fontSize));
        passwordField.setBackground(new Color(r, g, b));
        add(passwordField);
        return passwordField;
    }

    private JButton createButton(String text, int x, int y, int w, int h, String fontName, int fontSize, int fontStyle, Color c1, Color c2){
        button = new JButton(text);
        button.setBounds(x, y, w, h);
        button.setFont(new Font(fontName, fontStyle, fontSize));
        button.setBackground(c1);
        button.setForeground(c2);
        add(button);
        return button;
    }

    public static void main(String[] args) {
        new Login();
    }
}