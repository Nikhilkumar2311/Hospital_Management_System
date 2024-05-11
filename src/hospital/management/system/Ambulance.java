package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Ambulance extends JFrame {

    JPanel panel;
    JTable table;

    Ambulance(){
        panel = new JPanel();
        panel.setBounds(5,5,790,510);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);


        table = new JTable();
        table.setBounds(10,100,700,200);
        table.setBackground(new Color(90,156,163));
        panel.add(table);

        try{

            Conn c = new Conn();
            String q = "Select * from Ambulance";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
        }

        createlabel("Name", 10,65,200,20,"Tahoma", Font.BOLD, 14);
        createlabel("Gender", 150,65,200,20,"Tahoma", Font.BOLD, 14);
        createlabel("Car Name", 290,65,200,20,"Tahoma", Font.BOLD, 14);
        createlabel("Availability", 430,65,200,20,"Tahoma", Font.BOLD, 14);
        createlabel("Location", 570,65,200,20,"Tahoma", Font.BOLD, 14);

        JButton button = new JButton("Back");
        button.setBounds(500,400,120,30);
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
        setSize(800,600);
        setLayout(null);
        setLocation(250,200);
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
        new Ambulance();
    }
}
