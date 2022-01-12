package com.app.lostandfound;

public class PostClass {
    String category;
    String image;
    String description;
    String name;
    String phone;
    String location;
    String status;
    String email;

    public PostClass(String category, String image, String description, String name, String phone, String location, String status, String email) {
        this.category = category;
        this.image = image;
        this.description = description;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.status = status;
        this.email=email;
    }

    public String getCategory() {
        return category;
    }

    public String getEmail() {
        return email;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
