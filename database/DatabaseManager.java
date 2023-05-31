package com.example.bookstore.database;

import com.example.bookstore.mainclasses.CreditCard;
import com.example.bookstore.mainclasses.Reader;
import com.example.bookstore.utilities.DataBaseManipulationType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static Connection connection;

    public static boolean verifyAdmin(String id, String password) {
        try {
            connection = ConnectionEstablisher.establishConnection();
            String query = "SELECT* FROM admins WHERE id='" + id + "' AND password='" + password + "'";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet r = statement.executeQuery(query);
            boolean valid = false;
            while (r.next()) {
                valid = true;
                break;
            }
            return valid;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static Reader verifyUser(String username, String password) {
        String query = "";
        try {
            connection = ConnectionEstablisher.establishConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            query = "select* from users WHERE" +
                    " username= '" + username + "' AND password= '" + password + "'";
            ResultSet r = statement.executeQuery(query);
            System.out.println(query);
            r.last();
            if (r.getRow() == 1) {
                r.first();
                Reader a = new Reader(
                        r.getInt("id"),
                        r.getString("username"),
                        r.getString("email"),
                        r.getString("password"),
                        r.getString("address"),
                        r.getString("phonenumber"),
                        new CreditCard(r.getDouble("money")),
                        r.getString("library"));
                connection.close();
                return a;
            }
        } catch (SQLException e) {
            System.out.println(query);
            e.printStackTrace();
        }
        return null;
    }

    public static int getUsersCount() {
        try {
            connection = ConnectionEstablisher.establishConnection();
            String query = "SELECT* FROM users";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet r = statement.executeQuery(query);
            if (r != null) {
                r.last();
                try {
                    return r.getInt("id")+1;
                }catch (Exception e){
                    return 0;
                }

            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    //true if avalible
    public static boolean checkUser(String username, String email, String phonenumber, DataBaseManipulationType a) {
        try {
            connection = ConnectionEstablisher.establishConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String query = "select* from users WHERE"
                    + " username= '" + username
                    + "' OR email= '" + email
                    + "' OR phonenumber='" + phonenumber
                    + "'";
            ResultSet r = statement.executeQuery(query);
            System.out.println(query);
            r.last();
            if (a == DataBaseManipulationType.NEWUSER) {
                if (r.getRow() > 0)
                    return false;
            } else if (a == DataBaseManipulationType.UPDATEUSER){
                if (r.getRow() > 1)
                    return false;
            }
            statement.close();
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void addUser(Reader user) {
        Connection connection = null;
        try {
            connection = ConnectionEstablisher.establishConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String query = "INSERT INTO `users` (`id`, `username`, `email`, `password`," +
                    " `address`, `money`, `phonenumber`, `library`)" +
                    " VALUES ('"
                    + getUsersCount()
                    + "','" + user.getUserName()
                    + "','" + user.getMail()
                    + "','" + user.getPassword()
                    + "','" + user.getAddress()
                    + "','" + user.getMoney()
                    + "','" + user.getPhoneNumber()
                    + "','" + user.getLibrarystring() + "')";
            statement.execute(query);
            System.out.println(query);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateUser(Reader user) {

        String query = "UPDATE users SET username = '" + user.getUserName()
                + "', email =         '" + user.getMail()
                + "', phonenumber =   '" + user.getPhoneNumber()
                + "', address =       '" + user.getAddress()
                + "', password =      '" + user.getPassword()
                + "', money =         '" + user.getMoney()
                + "', library =       '" + user.getLibrarystring()
                + "' where id=         " + user.getId();

        try {
            connection = ConnectionEstablisher.establishConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.execute(query);
            System.out.println(query);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void CreateTables(){

        String tablebooks= " create table books(id int not null, name varchar(100) not null, " +
                "description varchar(1500) not null,count int not null" +
                ",cost int not null,coverurl varchar(1500) not null, downloadurl varchar(1500) not null" +
                " , hasdiscount boolean, deleted boolean, discount int " +
                ", constraint pk1 primary key (id))";



        String tableuser= " create table users(id int not null, username varchar(100) not null, " +
                "email varchar(100) not null,password varchar(100) not null,phonenumber varchar(100) not null,library varchar(100) not null" +
                ",address varchar(100) not null,money int  not null" +

                ", constraint pk1 primary key (id))";
        
        String tableadmin= " create table admins(id int not null, password varchar(100) not null " +
                ", constraint pk1 primary key (id))";
        try {
            connection = ConnectionEstablisher.establishConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.execute(tableadmin);
            statement.execute(tableuser);
            statement.execute(tablebooks);
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
