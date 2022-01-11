package com.app.lostandfound;

public class PostClass {
    String category;
    String image;
    String description;
    String name;
    String phone;

    public PostClass(String category, String image, String description, String name, String phone) {
        this.category = category;
        this.image = image;
        this.description = description;
        this.name = name;
        this.phone = phone;
    }

    public String getCategory() {
        return category;
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
}
