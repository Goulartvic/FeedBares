package enterprises.tanheta.feedbares.model;

import java.util.ArrayList;
import java.util.List;

public class Pub {

    private String name;

    private String address;

    private double rating;

    private String photoUrl;

    private String category;

    private List<Commentary> comments;

    public Pub(String name, String address, double rating, String photoUrl, String category) {
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
}
