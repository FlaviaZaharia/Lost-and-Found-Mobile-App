package com.app.lostandfound;

public class PostModel {
    private int image;
    private String title,category,description;

    PostModel(String description,String title,String category,int image) {
        this.description = description;
        this.title=title;
        this.category=category;
        this.image=image;
    }

    public int getImage() {
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
}
