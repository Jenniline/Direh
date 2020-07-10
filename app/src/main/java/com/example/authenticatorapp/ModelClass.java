package com.example.authenticatorapp;

public class ModelClass {

        private String imageIcon;
        String title;
        String description;

    public ModelClass(String imageIcon, String title, String description) {
        this.imageIcon = imageIcon;
        this.title = title;
        this.description = description; //this is what he means by body
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
