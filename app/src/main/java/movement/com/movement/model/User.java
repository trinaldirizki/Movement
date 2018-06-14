package movement.com.movement.model;

/**
 * Created by isma-ilou on 14.06.2018.
 */

public class User {
    private String uid;
    private String name;
    private String email;
    private String photoUrl;
    private int totalDonation;
    private int totalDistance;
    private int totalMovement;

    public User() {
    }

    public User(String uid, String name, String email, String photoUrl, int totalDonation, int totalDistance, int totalMovement) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.photoUrl = photoUrl;
        this.totalDonation = totalDonation;
        this.totalDistance = totalDistance;
        this.totalMovement = totalMovement;
    }

    public String getUid() {
        return uid;
    }

    public void setId(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getTotalDonation() {
        return totalDonation;
    }

    public void setTotalDonation(int totalDonation) {
        this.totalDonation = totalDonation;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getTotalMovement() {
        return totalMovement;
    }

    public void setTotalMovement(int totalMovement) {
        this.totalMovement = totalMovement;
    }
}
