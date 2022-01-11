package com.app.lostandfound;

public class PostModel {

    private String title,category,description,image;

    PostModel(String description,String title,String category, String image) {
        this.description = description;
        this.title=title;
        this.category=category;
        this.image=image;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
