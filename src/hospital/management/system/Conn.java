package hospital.management.system;

import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {

    Connection connection;
    Statement statement;

    public Conn(){
        try{

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "mysql");
            statement = connection.createStatement();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
