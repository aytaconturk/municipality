package com.example.userregistration;

public class Model {
    private String id;
    private String title;
    private String description;
    private String category;
    private String town;
    private String district;
    private String street;
    private String address;
    private String imageURL;
    private boolean isProblemSolved;
    private String voteNumber;
    private String time;



    public Model(String id, String category, String title, String town, String district,  String street,  String address, String description, String imageURL, String time) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.town = town;
        this.district = district;
        this.street = street;
        this.address = address;
        this.imageURL = imageURL;
        this.time = time;
        this.isProblemSolved = false;
        this.voteNumber = "1";
    }

    public Model(String id, String category, String title, String town, String district,  String street,  String address, String description, String imageURL, String time, String vote) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.town = town;
        this.district = district;
        this.street = street;
        this.address = address;
        this.imageURL = imageURL;
        this.time = time;
        this.isProblemSolved = false;
        this.voteNumber = vote;
    }

    public Model(String category, String title, String town, String district, String description) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.town = town;
        this.district = district;
        isProblemSolved = false;
        voteNumber = "1";
    }

    public Model(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isProblemSolved() {
        return isProblemSolved;
    }

    public void setProblemSolved(boolean problemSolved) {
        isProblemSolved = problemSolved;
    }

    public String getVoteNumber() {
        return voteNumber;
    }

    public void setVoteNumber(String voteNumber) {
        this.voteNumber = voteNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", town='" + town + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", address='" + address + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", isProblemSolved=" + isProblemSolved +
                ", voteNumber=" + voteNumber +
                ", time='" + time + '\'' +
                '}';
    }
}
