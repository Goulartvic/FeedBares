package enterprises.tanheta.feedbares.model;

import java.util.ArrayList;
import java.util.List;

public class PubModel {

    private String name;

    private String id;

    private String address;

    private double rating;

    private String photoUrl;

    private String category;

    private List<Commentary> comments;

    public PubModel() {}

    public PubModel(String name, String address, double rating, String photoUrl, String category) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.photoUrl = photoUrl;
        this.category = category;
        this.comments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Commentary> getComments() {
        return comments;
    }

    public void setComments(List<Commentary> comments) {
        this.comments = comments;
    }
}
