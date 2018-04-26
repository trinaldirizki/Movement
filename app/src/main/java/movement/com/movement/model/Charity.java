package movement.com.movement.model;

import android.graphics.drawable.Drawable;

import movement.com.movement.R;

/**
 * Created by mobiltrakya on 21/04/2018.
 */

public class Charity {
    private String name;
    private int thumbnailId;
    private CharityAction charityAction;

    private Charity(String name, int imageResourceId){
        this.name = name;
        this.thumbnailId = imageResourceId;
    }

    public Charity(String name, int thumbnailId, CharityAction charityAction) {
        this.name = name;
        this.thumbnailId = thumbnailId;
        this.charityAction = charityAction;
    }

    public static final Charity[] charities = {
            new Charity("Laznas Dewan Da'wah", R.drawable.ca1),
            new Charity("Aksi Cepat Tanggap", R.drawable.ca2),
            new Charity("Yayasan Kanker Indonesia", R.drawable.ca3),
            new Charity("Yayasan Kesehatan Perempuan", R.drawable.ca4),
            new Charity("Peduli Anak Foundation", R.drawable.ca5),
            new Charity("RZ", R.drawable.ca6),
            new Charity("Yayasan Difabel Mandiri", R.drawable.ca7),
            new Charity("Yayasan Katarak Peduli", R.drawable.ca8),
            new Charity("WeCare.id", R.drawable.ca9),
            new Charity("WALHI", R.drawable.ca10),
            new Charity("Dompet Dhuafa", R.drawable.ca11),
            new Charity("Yayasan Jantung Indonesia", R.drawable.ca12)
    };

    public CharityAction getCharityAction() {
        return charityAction;
    }

    public void setCharityAction(CharityAction charityAction) {
        this.charityAction = charityAction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(int thumbnailId) {
        this.thumbnailId = thumbnailId;
    }
}
