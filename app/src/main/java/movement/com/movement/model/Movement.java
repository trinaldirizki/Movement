package movement.com.movement.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mobiltrakya on 21/04/2018.
 */

public class Movement implements Parcelable {
    private String uid;
    private String name;
    private String detail;
    private int targetDonation;
    private int currentDonation;
    private int targetDistance;
    private int currentDistance;
    private String imageUrl;

    public Movement() {
    }

    public Movement(String uid, String name, String detail, int targetDonation, int currentDonation, int targetDistance, int currentDistance, String imageUrl) {
        this.uid = uid;
        this.name = name;
        this.detail = detail;
        this.targetDonation = targetDonation;
        this.currentDonation = currentDonation;
        this.targetDistance = targetDistance;
        this.currentDistance = currentDistance;
        this.imageUrl = imageUrl;
    }

    protected Movement(Parcel in) {
        uid = in.readString();
        name = in.readString();
        detail = in.readString();
        targetDonation = in.readInt();
        currentDonation = in.readInt();
        targetDistance = in.readInt();
        currentDistance = in.readInt();
        imageUrl = in.readString();
    }

    public static final Creator<Movement> CREATOR = new Creator<Movement>() {
        @Override
        public Movement createFromParcel(Parcel in) {
            return new Movement(in);
        }

        @Override
        public Movement[] newArray(int size) {
            return new Movement[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(name);
        dest.writeString(detail);
        dest.writeInt(targetDonation);
        dest.writeInt(currentDonation);
        dest.writeInt(targetDistance);
        dest.writeInt(currentDistance);
        dest.writeString(imageUrl);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getTargetDonation() {
        return targetDonation;
    }

    public void setTargetDonation(int targetDonation) {
        this.targetDonation = targetDonation;
    }

    public int getCurrentDonation() {
        return currentDonation;
    }

    public void setCurrentDonation(int currentDonation) {
        this.currentDonation = currentDonation;
    }

    public int getTargetDistance() {
        return targetDistance;
    }

    public void setTargetDistance(int targetDistance) {
        this.targetDistance = targetDistance;
    }

    public int getCurrentDistance() {
        return currentDistance;
    }

    public void setCurrentDistance(int currentDistance) {
        this.currentDistance = currentDistance;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
