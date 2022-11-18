package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class User
{
    int id, idCategory, followers;
    String name, email, password, gender;

    public User(int id, int idCategory, String name, String email, String password, String gender, int followers)
    {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.idCategory = idCategory;
        this.followers = followers;
    }

    public HashMap<String, String> getData()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", this.name);
        map.put("userPassword", this.password);
        map.put("userEmail", this.email);
        map.put("userGender", this.gender);
        map.put("categoryId", String.valueOf(this.idCategory));
        map.put("userFollowers", String.valueOf(this.followers));
        return map;
    }

    public User(ResultSet rs) throws SQLException
    {
        this.id = rs.getInt("userId");
        this.name = rs.getString("userName");
        this.password = rs.getString("userPassword");
        this.email = rs.getString("userEmail");
        this.gender = rs.getString("userGender");
        this.idCategory = rs.getInt("categoryId");
        this.followers = rs.getInt("userFollowers");
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getIdCategory()
    {
        return idCategory;
    }

    public void setIdCategory(int idCategory)
    {
        this.idCategory = idCategory;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPhoto()
    {
        return gender;
    }

    public void setPhoto(String photo)
    {
        this.gender = photo;
    }

    public int getFollowers() { return followers; }

    public void setFollowers(int followers) { this.followers = followers; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }
}
