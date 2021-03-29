package ua.com.locationservice.entity;

import java.util.List;

public class Location {
    public int id;
    public String name;
    private List<User> users;
    private Tag tag;

    // Constructor.
    public Location(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public Location(int id, String name, List<User> users, Tag tag) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public Tag getTag() {
        return tag;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
