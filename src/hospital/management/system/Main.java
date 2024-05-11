package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    JPanel p1, p2;
    JButton button, addP, room, department, emp, pInfo, pDis, update, amb, search, logout;

    Main(){

        p1 = createPanel(5, 160, 1272, 670, 109, 164, 170);
        p2 = createPanel(5, 5, 1272, 150, 109, 164, 170);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/dr.png"));
        Image image = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(image);
        JLabel label = new JLabel(i2);
        label.setBounds(1050, 0, 250, 250);
        p2.add(label);



        // All The Buttons
        addP = createButton("Add New Patient", 30, 15);
        room = createButton("Room", 30, 58);
        department = createButton("Department", 30, 100);
        emp = createButton("All Employee Info", 270, 15);
        pInfo = createButton("Patient Info", 270, 58);
        pDis = createButton("Patient Discharge", 270, 100);
        update = createButton("Update Patient Details", 510, 15);
        amb = createButton("Hospital Ambulance", 510, 58);
        search = createButton("Search Room", 510, 100);
        logout = createButton("Logout", 750, 15);




        setSize(1950, 1090);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    private JPanel createPanel(int x,int y, int w, int h, int r, int g, int b){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, w, h);
        panel.setBackground(new Color(r, g, b));
        add(panel);
        return panel;
    }

    private JButton createButton(String text, int x, int y){
        button = new JButton(text);
        button.setBounds(x, y, 200, 30);
        button.setBackground(new Color(246, 215, 118));
        button.addActionListener(this);
        p2.add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addP){
            new New_Patient();
        } else if (e.getSource() == room) {
            new Room();
        } else if (e.getSource() == department) {
            new Department();
        } else if (e.getSource() == emp) {
            new Emp_Info();
        } else if (e.getSource() == pInfo) {
            //TODO
        } else if (e.getSource() == pDis) {
            //TODO
        } else if (e.getSource() == update) {
            //TODO
        } else if (e.getSource() == amb) {
            //TODO
        } else if (e.getSource() == search) {
            //TODO
        } else if (e.getSource() == logout) {
            //TODO
        }
    }

    public static void main(String[] args) {
        new Main();
    }

}
