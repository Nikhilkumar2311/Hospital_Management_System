package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Patient_Info extends JFrame {

    JPanel panel;
    JTable table;

    Patient_Info(){
        panel = new JPanel();
        panel.setBounds(5,5,1100,590);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        table = new JTable();
        table.setBounds(10,80,1000,200);
        table.setBackground(new Color(90,156,163));
        panel.add(table);

        try{

            Conn c = new Conn();
            String q = "Select * from patient_info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
        }

        createlabel("ID", 10,45,200,20,"Tahoma", Font.BOLD, 14);
        createlabel("Number", 135,45,200,20,"Tahoma", Font.BOLD, 14);
        createlabel("Name", 260,45,200,20,"Tahoma", Font.BOLD, 14);
        createlabel("Gender", 385,45,200,20,"Tahoma", Font.BOLD, 14);
        createlabel("Disease", 510,45,200,20,"Tahoma", Font.BOLD, 14);
        createlabel("Room No.", 635,45,200,20,"Tahoma", Font.BOLD, 14);
        createlabel("Time", 760,45,200,20,"Tahoma", Font.BOLD, 14);
        createlabel("Deposite", 885,45,200,20,"Tahoma", Font.BOLD, 14);

        JButton button = new JButton("Back");
        button.setBounds(900,420,120,30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(1100,600);
        setLayout(null);
        setLocation(100,200);
        setVisible(true);

    }

    private JLabel createlabel(String text, int x, int y, int w, int h, String fontName, int fontStyle, int fontSize){
        JLabel label = new JLabel(text);
        label.setBounds(x,y,w,h);
        label.setFont(new Font(fontName, fontStyle, fontSize));
        panel.add(label);
        return label;
    }

    public static void main(String[] args) {
        new Patient_Info();
    }
}
