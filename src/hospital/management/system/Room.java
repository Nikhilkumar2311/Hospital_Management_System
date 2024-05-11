package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Room extends JFrame {

    JPanel panel;
    JTable table;

    Room(){

        panel = new JPanel();
        panel.setBounds(5,5,890,510);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/room.png"));
        Image image = imageIcon.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(600,150,200,200);
        panel.add(label);

        table = new JTable();
        table.setBounds(10,40,500,300);
        table.setBackground(new Color(90,156,163));
        panel.add(table);

        try{

            Conn c = new Conn();
            String q = "Select * from room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
        }

        createlabel("Room No", 12,15,80,20,"Tahoma", Font.BOLD, 14);
        createlabel("Availability", 135,15,80,20,"Tahoma", Font.BOLD, 14);
        createlabel("Price", 260,15,80,20,"Tahoma", Font.BOLD, 14);
        createlabel("Room Type", 385,15,80,20,"Tahoma", Font.BOLD, 14);

        JButton button = new JButton("Back");
        button.setBounds(200,400,120,30);
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
        setSize(900,600);
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
        new Room();
    }
}
