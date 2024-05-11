package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Search_Room extends JFrame implements ActionListener {

    JPanel panel;
    JLabel label;
    Choice choice;
    JTable table;
    JButton button, search, back;

    Search_Room(){

        panel = new JPanel();
        panel.setBounds(5,5, 690, 490);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        createlabel("Search For Room", 250,11,186,31,"Tahoma", Font.BOLD, 20);

        createlabel("Status", 70,70,80,20,"Tahoma", Font.BOLD, 14);

        choice = new Choice();
        choice.setBounds(170, 70,120,20);
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        table = new JTable();
        table.setBounds(10, 187, 700, 210);
        table.setBackground(new Color(90, 156, 163));
        panel.add(table);

        try{

            Conn c = new Conn();
            String q = "Select * from Room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e){
            e.printStackTrace();
        }

        createlabel("Room No.", 10,160,150,20,"Tahoma", Font.BOLD, 14);
        createlabel("Availability", 185,160,150,20,"Tahoma", Font.BOLD, 14);
        createlabel("Price", 360,160,150,20,"Tahoma", Font.BOLD, 14);
        createlabel("Room Type", 535,160,150,20,"Tahoma", Font.BOLD, 14);

        // Buttons

        search = createButton("SEARCH", 160,420,120,25, Color.BLACK, Color.WHITE);
        back = createButton("BACK", 360,420,120,25, Color.BLACK, Color.WHITE);



        setUndecorated(true);
        setSize(700,500);
        setLayout(null);
        setLocation(250,200);
        setVisible(true);

    }
    private JLabel createlabel(String text, int x, int y, int w, int h, String fontName, int fontStyle, int fontSize){
        label = new JLabel(text);
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
        if (e.getSource() == search) {
            String q = "select * from Room where Availability = '"+choice.getSelectedItem()+"'";
            try {

                Conn c = new Conn();
                ResultSet resultSet = c.statement.executeQuery(q);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));

            } catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Search_Room();
    }

}
