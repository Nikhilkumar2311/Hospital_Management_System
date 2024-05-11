package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Patient_Discharge extends JFrame implements ActionListener {

    JPanel panel;
    JLabel Rno, INTime, OUTTime;
    JButton button, Discharge, Back, Check;
    Choice choice;

    Patient_Discharge(){

        panel = new JPanel();
        panel.setBounds(5,5, 790, 390);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/discharge.png"));
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(550, 100, 200, 200);
        panel.add(label);

        createlabel("CHECK-OUT", 100,20,150,20,"Tahoma", Font.BOLD, 20);
        createlabel("Customer Id", 30,80,150,20,"Tahoma", Font.BOLD, 14);

        choice = new Choice();
        choice.setBounds(200, 80, 150, 25);
        panel.add(choice);

        try{

            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("Select * from patient_info");
            while (resultSet.next()){
                choice.add(resultSet.getString("Number"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        createlabel("Room Number", 30,130,150,20,"Tahoma", Font.BOLD, 14);
        Rno = createlabel("", 200,130,150,20,"Tahoma", Font.BOLD, 14);

        createlabel("In Time", 30,180,150,20,"Tahoma", Font.BOLD, 14);
        INTime = createlabel("", 200,180,250,20,"Tahoma", Font.BOLD, 14);

        createlabel("Out Time", 30,230,150,20,"Tahoma", Font.BOLD, 14);

        Date date = new Date();
        OUTTime = createlabel(""+date, 200,230,250,20,"Tahoma", Font.BOLD, 14);

        Discharge = createButton("DISCHARGE", 30, 300, 120, 30, Color.BLACK, Color.WHITE);
        Check = createButton("CHECK", 170, 300, 120, 30, Color.BLACK, Color.WHITE);
        Back = createButton("BACK", 300, 300, 120, 30, Color.BLACK, Color.WHITE);



        setUndecorated(true);
        setSize(800, 400);
        setLayout(null);
        setLocation(250, 250);
        setVisible(true);
    }

    private JLabel createlabel(String text, int x, int y, int w, int h, String fontName, int fontStyle, int fontSize){
        JLabel label = new JLabel(text);
        label.setBounds(x,y,w,h);
        label.setFont(new Font(fontName, fontStyle, fontSize));
        panel.add(label);
        return label;
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
        Conn c = new Conn();
        if (e.getSource() == Discharge) {
            try {

                c.statement.executeUpdate("delete from patient_info where Number = '"+choice.getSelectedItem()+"'");
                c.statement.executeUpdate("update Room set Availability = 'Available' where Room_no = '"+Rno.getText()+"'");
                JOptionPane.showMessageDialog(null, "Patient Discharged");
                setVisible(false);

            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == Check) {
            try{

                ResultSet resultSet = c.statement.executeQuery("select * from patient_info where Number = '"+choice.getSelectedItem()+"'");
                while (resultSet.next()) {
                    Rno.setText(resultSet.getString("Room_Number"));
                    INTime.setText(resultSet.getString("Time"));
                }

            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource() == Back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Patient_Discharge();
    }


}
