package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Patient_Update extends JFrame implements ActionListener {

    JPanel panel;
    Choice choice;
    JTextField textField, roomNo, inTime, amountP, pending;
    JButton button, check, update, back;

    Patient_Update(){

        panel = new JPanel();
        panel.setBounds(5,5, 925, 452);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(600, 100, 250, 250);
        panel.add(label);

        createlabel("Update Patient Details", 124,11,260,25,"Tahoma", Font.BOLD, 20);

        createlabel("Number :", 25,88,100,14,"Tahoma", Font.PLAIN, 14);
        choice = new Choice();
        choice.setBounds(248,85, 140, 25);
        panel.add(choice);

        try {

            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("Select * from patient_info");
            while (resultSet.next()) {
                choice.add(resultSet.getString("Number"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        createlabel("Room Number :", 25,129,100,14,"Tahoma", Font.PLAIN, 14);
        roomNo = createField(248,129,140,20);

        createlabel("In-Time :", 25,174,100,14,"Tahoma", Font.PLAIN, 14);
        inTime = createField(248,174,140,20);

        createlabel("Amount Paid :", 25,216,100,14,"Tahoma", Font.PLAIN, 14);
        amountP = createField(248,216,140,20);

        createlabel("Pending Amount :", 25,261,130,16,"Tahoma", Font.PLAIN, 14);
        pending = createField(248,261,140,20);

        // Buttons
        update = createButton("UPDATE", 56,378,100,25, Color.BLACK, Color.WHITE);
        check = createButton("CHECK", 200,378,100,25, Color.BLACK, Color.WHITE);
        back = createButton("BACK", 344,378,100,25, Color.BLACK, Color.WHITE);

        setSize(950, 500);
        setLayout(null);
        setLocation(250, 220);
        setVisible(true);

    }
    private JLabel createlabel(String text, int x, int y, int w, int h, String fontName, int fontStyle, int fontSize){
        JLabel label = new JLabel(text);
        label.setBounds(x,y,w,h);
        label.setFont(new Font(fontName, fontStyle, fontSize));
        panel.add(label);
        return label;
    }
    private JTextField createField(int x, int y, int w, int h){
        textField = new JTextField();
        textField.setBounds(x, y, w, h);
        panel.add(textField);
        return textField;
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
        if(e.getSource() == update){
            try {

                String q = choice.getSelectedItem();
                String room = roomNo.getText();
                String time = inTime.getText();
                String amount = amountP.getText();
                String oldRoom = getPreviousRoomNumber(q);
                c.statement.executeUpdate("update patient_info set Room_Number = '"+room+"', Time = '"+time+"', Deposite = '"+amount+"' where Number = '"+q+"'");
                c.statement.executeUpdate("update Room set Availability = 'Available' where Room_no = '"+oldRoom+"'");
                c.statement.executeUpdate("update Room set Availability = 'Occupied' where Room_no = '"+room+"'");
                JOptionPane.showMessageDialog(null, "Updated Successfully");
                setVisible(false);

            } catch (Exception E) {
                E.printStackTrace();
            }

        } else if(e.getSource() == check){
            String id = choice.getSelectedItem();
            String q = "Select * from patient_info where Number = '"+id+"'";
            try{

                ResultSet resultSet = c.statement.executeQuery(q);
                while (resultSet.next()) {
                    roomNo.setText(resultSet.getString("Room_Number"));
                    inTime.setText(resultSet.getString("Time"));
                    amountP.setText(resultSet.getString("Deposite"));
                }

                ResultSet resultSet1 = c.statement.executeQuery("select * from Room where Room_no = '"+roomNo.getText()+"'");
                while (resultSet1.next()) {
                    String price = resultSet1.getString("Price");
                    int amountPaid = Integer.parseInt(price) - Integer.parseInt(amountP.getText());
                    pending.setText(""+amountPaid);
                }

            }catch (Exception E){
                E.printStackTrace();
            }

        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    private String getPreviousRoomNumber(String patientNumber) {
        Conn c = new Conn();
        String query = "Select Room_Number from patient_info where Number = '"+patientNumber+"'";
        try {
            ResultSet resultSet = c.statement.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getString("Room_Number");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        new Patient_Update();
    }


}
