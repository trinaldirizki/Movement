package movement.com.movement.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by isma-ilou on 17.06.2018.
 */

public class Sponsor implements Parcelable {
    private String uid;
    private String name;
    private String logoUrl;
    private String adUrl;
    private String adDetail;

    public Sponsor() {
    }

    public Sponsor(String uid, String name, String logoUrl, String adUrl, String adDetail) {
        this.uid = uid;
        this.name = name;
        this.logoUrl = logoUrl;
        this.adUrl = adUrl;
        this.adDetail = adDetail;
    }

    protected Sponsor(Parcel in) {
        uid = in.readString();
        name = in.readString();
        logoUrl = in.readString();
        adUrl = in.readString();
        adDetail = in.readString();
    }

    public static final Creator<Sponsor> CREATOR = new Creator<Sponsor>() {
        @Override
        public Sponsor createFromParcel(Parcel in) {
            return new Sponsor(in);
        }

        @Override
        public Sponsor[] newArray(int size) {
            return new Sponsor[size];
        }
    };

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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getAdDetail() {
        return adDetail;
    }

    public void setAdDetail(String adDetail) {
        this.adDetail = adDetail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(name);
        dest.writeString(logoUrl);
        dest.writeString(adUrl);
        dest.writeString(adDetail);
    }
}
