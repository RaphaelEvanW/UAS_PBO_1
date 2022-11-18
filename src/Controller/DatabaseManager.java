package Controller;

import Model.CategoryUser;
import Model.User;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseManager
{
    private Connection conn;
    private static final DatabaseManager instance = new DatabaseManager("jdbc:mysql://localhost:3307/db_uas_1121053", "root", "");
    public static DatabaseManager getInstance() { return  DatabaseManager.instance; }
    private DatabaseManager(String url, String username, String password)
    {
        try
        {
            this.conn = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException exception)
        {
            JOptionPane.showMessageDialog(
                    null,
                    "Unexpected error:  " + exception.getMessage(),
                    "Could not connect to SQL server",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void insert(String tableName, HashMap<String, String> data) throws SQLException
    {
        StringBuilder vars = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for (var set : data.entrySet())
        {
            if (vars.length() > 0) { vars.append(", "); }
            vars.append(String.format("`%s`", set.getKey()));
            if (values.length() > 0) { values.append(", "); }
            values.append(String.format("'%s'", set.getValue()));
        }

        this.conn.createStatement().executeUpdate(String.format("INSERT INTO `%s` (%s) VALUES (%s);", tableName, vars, values));
    }

    public String getLoadQuery(String tableName, String primaryKeyName) throws IndexOutOfBoundsException
    {
        return String.format("SELECT * FROM `%s` ORDER BY `%s`;", tableName, primaryKeyName);
    }

    public ArrayList<CategoryUser> getUserCategories(String tableName, String primaryKeyName) throws SQLException
    {
        ArrayList<CategoryUser> results = new ArrayList<>();
        ResultSet rs = this.conn.createStatement().executeQuery(getLoadQuery(tableName, primaryKeyName));
        while (rs.next())
        {
            results.add(new CategoryUser(rs));
        }
        return results;
    }

    public ArrayList<User> getUsers(String tableName, String primaryKeyName) throws SQLException
    {
        ArrayList<User> results = new ArrayList<>();
        ResultSet rs = this.conn.createStatement().executeQuery(getLoadQuery(tableName, primaryKeyName));
        while (rs.next())
        {
            results.add(new User(rs));
        }
        return results;
    }
}
