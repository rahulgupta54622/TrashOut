package com.gupta54622.rahul.trashout;

public class Trash {

    private String uploadDescription;
    private String uploadDate;
    private String imageUrl;
    private String reference;
    private double latitude;
    private double longitude;


    public Trash() {
    }

    public Trash(String uploadDescription, String uploadDate, String imageUrl, String reference, double latitude, double longitude) {
        this.uploadDescription = uploadDescription;
        this.uploadDate = uploadDate;
        this.imageUrl = imageUrl;
        this.reference = reference;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getUploadDescription() {
        return uploadDescription;
    }

    public void setUploadDescription(String uploadDescription) {
        this.uploadDescription = uploadDescription;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
