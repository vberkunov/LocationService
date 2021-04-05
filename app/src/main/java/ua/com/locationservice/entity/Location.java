package ua.com.locationservice.entity;

import java.util.List;

public class Location {
    public int id;
    public String name;
    public double width;
    public double height;
    public Tag tag;

    // Constructor.
    public Location(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public Location(int id, String name, double width, double height, Tag tag) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Tag getTag() {
        return tag;
    }
}
