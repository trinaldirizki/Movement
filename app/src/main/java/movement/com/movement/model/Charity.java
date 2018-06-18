package movement.com.movement.model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import movement.com.movement.R;

/**
 * Created by mobiltrakya on 21/04/2018.
 */

public class Charity implements Parcelable{
    private String uid;
    private String name;
    private String imageUrl;
    private String detail;
    private Movement movement;

    public Charity() {
    }

    public Charity(String uid, String name, String imageUrl, String detail, Movement movement) {
        this.uid = uid;
        this.name = name;
        this.imageUrl = imageUrl;
        this.detail = detail;
        this.movement = movement;
    }

    protected Charity(Parcel in) {
        uid = in.readString();
        name = in.readString();
        imageUrl = in.readString();
        detail = in.readString();
        movement = in.readParcelable(Movement.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeString(detail);
        dest.writeParcelable(movement, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Charity> CREATOR = new Creator<Charity>() {
        @Override
        public Charity createFromParcel(Parcel in) {
            return new Charity(in);
        }

        @Override
        public Charity[] newArray(int size) {
            return new Charity[size];
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    //    public static final Charity[] charities = {
//            new Charity("Laznas Dewan Da'wah", R.drawable.ca1),
//            new Charity("Aksi Cepat Tanggap", R.drawable.ca2),
//            new Charity("Yayasan Kanker Indonesia", R.drawable.ca3),
//            new Charity("Yayasan Kesehatan Perempuan", R.drawable.ca4),
//            new Charity("Peduli Anak Foundation", R.drawable.ca5),
//            new Charity("RZ", R.drawable.ca6),
//            new Charity("Yayasan Difabel Mandiri", R.drawable.ca7),
//            new Charity("Yayasan Katarak Peduli", R.drawable.ca8),
//            new Charity("WeCare.id", R.drawable.ca9),
//            new Charity("WALHI", R.drawable.ca10),
//            new Charity("Dompet Dhuafa", R.drawable.ca11),
//            new Charity("Yayasan Jantung Indonesia", R.drawable.ca12)
//    };

}
