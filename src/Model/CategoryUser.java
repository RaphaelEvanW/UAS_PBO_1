package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CategoryUser
{
    int id;
    String name;

    public CategoryUser(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public CategoryUser(ResultSet rs) throws SQLException
    {
        this.id = rs.getInt("categoryId");
        this.name = rs.getString("categoryName");
    }

    public HashMap<String, String> getData()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("categoryName", this.name);
        return map;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
