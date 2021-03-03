import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.*;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Lab5
{
    public static void main (String[] args)
    {
        Connection con = null;

        try {
            Statement stmt0;
            Statement stmt;
            Statement stmt1;
            Statement stmt2;
            Statement stmt3;
            Statement stmt4;
            ResultSet rs0;
            ResultSet rs;
            ResultSet rs1;
            ResultSet rs2;
            ResultSet rs3;
            ResultSet rs4;

            // Register the JDBC driver for MySQL.
            Class.forName("com.mysql.jdbc.Driver");

            // Define URL of database server for
            // database named 'user' on the faure.
            String url =
                    "jdbc:mysql://faure/nikter?useTimezone=true&serverTimezone=UTC";

            // Get a connection to the database for a
            // user named 'user' with the password
            // 123456789.
            con = DriverManager.getConnection(
                    url, "nikter", "830717672");

            // Display URL and connection information
            System.out.println("URL: " + url);
            System.out.println("Connection: " + con);

            // Get a Statement object
            stmt0 = con.createStatement();
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();
            stmt3 = con.createStatement();
            stmt4 = con.createStatement();

            String value;

            System.out.println("Asking for value");

            // Get the value
            value = JOptionPane.showInputDialog ("Enter your member ID:", JOptionPane.OK_CANCEL_OPTION);
            //assert value is in Member.MemberID
            System.out.println("Got value:  " + value);

            try{
                JTextField field0 = new JTextField();
                rs = stmt.executeQuery("SELECT 1 FROM Member m WHERE m.MemberID="+value+"");
                if (rs.next() == false) {
                    //memberID does not exist in the database
                    if (JOptionPane.showConfirmDialog(null, "Would you like to enter a new Member ID?", null,
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                        String input = JOptionPane.showInputDialog(null, "Enter your new Member ID");
                        int i = stmt0.executeUpdate("INSERT INTO Member(MemberID, First_name, Last_name, Sex, DOB) VALUES("+input+",'','','','')");
                        JOptionPane.showMessageDialog(null,"New member ID added to database");
                    }else{
                        return;
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"welcome");
                }
                JOptionPane.showMessageDialog(null, "Find book to checkout");
                JTextField field1 = new JTextField();
                JTextField field2 = new JTextField();
                JTextField field3 = new JTextField();
                Object [] options = {
                        "Book Name",
                        "ISBN",
                        "Author"
                };
                int j = JOptionPane.showOptionDialog(null, "Choose a way to find your book", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (j == 0) {
                    String input1 = JOptionPane.showInputDialog(null, "Enter book title", JOptionPane.OK_CANCEL_OPTION);
                    rs1 = stmt1.executeQuery("SELECT b.Title FROM Book b WHERE b.Title= ' '+'%" + input1 + "%'");
                    System.out.println(field1.getText());
                    if (!rs1.next()){
                        JOptionPane.showMessageDialog(null,"Book does not exist in the database");
                    }else{
                        JOptionPane.showMessageDialog(null,"Book found");
                    }
                } else if (j == 1) {
                    String input = JOptionPane.showInputDialog(null, "Enter ISBN", JOptionPane.OK_CANCEL_OPTION);
                    rs2 = stmt2.executeQuery("SELECT b.Title FROM Book b WHERE b.ISBN = '" + input + "'");
                    if (!rs2.next()){
                        JOptionPane.showMessageDialog(null, "Book does not exist in the database");
                    }else{
                        JOptionPane.showMessageDialog(null,"Book found");
                        rs4 = stmt4.executeQuery("SELECT l.Library, l.Shelf FROM LocatedAt l WHERE l.ISBN = '" + input + "'");
                        String s = "";
                        ResultSetMetaData rsmd = rs4.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();
                        while (rs4.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) s.concat(",  ");
                                String columnValue = rs4.getString(i);
                                s.concat(columnValue + " " + rsmd.getColumnName(i));
                            }
                        }
                        JOptionPane.showMessageDialog(null,s);
                    }
                }else{
                    String input = JOptionPane.showInputDialog(null, "Enter Author", JOptionPane.OK_CANCEL_OPTION);
                    rs3 = stmt3.executeQuery("SELECT CONCAT(a.First_name, ' ', a.Last_name) FROM Author a WHERE CONCAT(a.First_name, ' ', a.Last_name)= ' ' + '%" + input + "%'");
                    if (!rs3.next()){
                        JOptionPane.showMessageDialog(null, "Author does not exist in the database");
                    }else{
                        JOptionPane.showMessageDialog(null, "Author found");
                    }
                }
            }catch(Exception e){
                System.out.print(e);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }



    }

} // end
