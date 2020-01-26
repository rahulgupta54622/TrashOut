package com.gupta54622.rahul.trashout;

public class WasteBin {
    
    private double height;
    private String color;
    private String longitude;
    private String latitude;
    private String description;
    private String capacity;
    private String uploadDate;
    private String imageUrl;
    private String reference;
    private double currentWasteLevel;


    public WasteBin(double height, String color, String longitude, String latitude, String description, String capacity, String uploadDate, String imageUrl, String reference, double currentWasteLevel) {
        this.height = height;
        this.color = color;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.capacity = capacity;
        this.uploadDate = uploadDate;
        this.imageUrl = imageUrl;
        this.reference = reference;
        this.currentWasteLevel = currentWasteLevel;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getCurrentWasteLevel() {
        return currentWasteLevel;
    }

    public void setCurrentWasteLevel(double currentWasteLevel) {
        this.currentWasteLevel = currentWasteLevel;
    }
}
