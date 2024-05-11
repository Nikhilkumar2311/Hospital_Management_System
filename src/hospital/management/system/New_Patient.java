package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class New_Patient extends JFrame implements ActionListener {

    JComboBox comboBox;
    JTextField textNumber, textName, textDisease, textDeposite, textField;
    JRadioButton male, female, other, radioButton;
    Choice c1;
    JLabel date;
    JButton Add, Back, button;
    JPanel panel;


    New_Patient(){

        panel = new JPanel();
        panel.setBounds(5, 5, 840, 510);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/patient.png"));
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(550, 150, 200, 200);
        panel.add(label);

        createLabel("NEW PATIENT FORM", 118, 11, 260, 53, "Tahoma", 16, Font.BOLD, Color.BLACK);
        createLabel("ID : ", 35, 76, 200, 14, "Tahoma", 14, Font.BOLD, Color.WHITE);

        comboBox = new JComboBox(new String[] {"Aadhar Card", "Voter Id", "Driving License"});
        comboBox.setBounds(271, 73, 150, 20);
        comboBox.setBackground(new Color(3, 45, 48));
        comboBox.setForeground(Color.WHITE);
        comboBox.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(comboBox);

        createLabel("Number : ", 35, 111, 200, 14, "Tahoma", 14, Font.BOLD, Color.WHITE);
        textNumber = createField(271, 111, 150, 20);

        createLabel("Name : ", 35, 151, 200, 14, "Tahoma", 14, Font.BOLD, Color.WHITE);
        textName = createField(271, 151, 150, 20);

        // Radio Button
        createLabel("Gender : ", 35, 191, 200, 14, "Tahoma", 14, Font.BOLD, Color.WHITE);

        male = createradio("Male", 271, 191, 80, 15, "Tahoma", 14, Font.BOLD, Color.WHITE,109, 164, 170);
        female = createradio("Female", 350, 191, 80, 15, "Tahoma", 14, Font.BOLD, Color.WHITE,109, 164, 170);
        other = createradio("Others", 440, 191, 80, 15, "Tahoma", 14, Font.BOLD, Color.WHITE,109, 164, 170);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(male);
        buttonGroup.add(female);
        buttonGroup.add(other);

        createLabel("Disease : ", 35, 231, 200, 14, "Tahoma", 14, Font.BOLD, Color.WHITE);
        textDisease = createField(271, 231, 150, 20);

        createLabel("Room : ", 35, 274, 200, 14, "Tahoma", 14, Font.BOLD, Color.WHITE);

        c1 = new Choice();
        try{
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from Room where Availability = 'Available'");
            while(resultSet.next()){
                c1.add(resultSet.getString("Room_no"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        c1.setBounds(271, 274, 150, 20);
        c1.setFont(new Font("Tahoma", Font.BOLD, 14));
        c1.setForeground(Color.WHITE);
        c1.setBackground(new Color(3,45,48));
        panel.add(c1);

        createLabel("Time : ", 35, 316, 200, 14, "Tahoma", 14, Font.BOLD, Color.WHITE);
        Date date1 = new Date();
        date = new JLabel("" + date1);
        date.setBounds(271,316,250,18);
        date.setForeground(Color.WHITE);
        date.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(date);

        createLabel("Deposite : ", 35, 359, 200, 18, "Tahoma", 14, Font.BOLD, Color.WHITE);
        textDeposite = createField(271, 359, 150, 20);

        // Buttons
        Add = createButton("ADD", 100, 430, 120, 30, Color.BLACK, Color.WHITE);
        Back = createButton("BACK", 260, 430, 120, 30, Color.BLACK, Color.WHITE);

        setUndecorated(true);
        setSize(850, 550);
        setLayout(null);
        setLocation(250, 200);
        setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y, int width, int height, String fontName, int fontSize, int fontStyle, Color color) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font(fontName, fontStyle, fontSize));
        label.setForeground(color);
        panel.add(label);
        return label;
    }

    private JTextField createField(int x, int y, int w, int h){
        textField = new JTextField();
        textField.setBounds(x, y, w, h);
        panel.add(textField);
        return textField;
    }
    private JRadioButton createradio(String text, int x, int y, int w, int h, String fontName, int fontSize, int fontStyle, Color color, int r, int g, int b){
        radioButton = new JRadioButton(text);
        radioButton.setFont(new Font(fontName, fontStyle, fontSize));
        radioButton.setForeground(color);
        radioButton.setBackground(new Color(r, g, b));
        radioButton.setBounds(x, y, w, h);
        panel.add(radioButton);
        return radioButton;
    }

    private JButton createButton(String text, int x, int y, int w, int h, Color c1, Color c2){
        button = new JButton(text);
        button.setBounds(x, y, w, h);
        button.setBackground(c1);
        button.setForeground(c2);
        button.addActionListener(this);
        panel.add(button);
        return button;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Add){
            Conn c = new Conn();
            String radioBTN = null;
            if(male.isSelected()){
                radioBTN = "Male";
            } else if (female.isSelected()) {
                radioBTN = "Female";
            }else if(other.isSelected()) {
                radioBTN = "Others";
            }
            String id = (String)comboBox.getSelectedItem();
            String no = textNumber.getText();
            String name = textName.getText();
            String gender = radioBTN;
            String disease = textDisease.getText();
            String room = c1.getSelectedItem();
            String date2 = date.getText();
            String deposite = textDeposite.getText();


            try{

                String q = "insert into patient_info values('"+id+"', '"+no+"', '"+name+"', '"+gender+"', '"+disease+"', '"+room+"', '"+date2+"', '"+deposite+"')";
                String q1 = "update Room set Availability = 'Occupied' where Room_no = "+room;
                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);
                JOptionPane.showMessageDialog(null, "Added Successfully");
                setVisible(false);

            }catch (Exception E){
                E.printStackTrace();
            }

        }else if(e.getSource() == Back) {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new New_Patient();
    }

}
