package movement.com.movement.model;

import android.graphics.drawable.Drawable;

import movement.com.movement.R;

/**
 * Created by mobiltrakya on 21/04/2018.
 */

public class Charity {
    private String name;
    private int thumbnailId;

    private Charity(String name, int imageResourceId){
        this.name = name;
        this.thumbnailId = imageResourceId;
    }

    public static final Charity[] charities = {
            new Charity("Laznas Dewan Da'wah", R.drawable.c1),
            new Charity("Aksi Cepat Tanggap", R.drawable.c2),
            new Charity("Yayasan Kanker Indonesia", R.drawable.c3),
            new Charity("Yayasan Kesehatan Perempuan", R.drawable.c4),
            new Charity("Peduli Anak Foundation", R.drawable.c5),
            new Charity("RZ", R.drawable.c6),
            new Charity("Yayasan Difabel Mandiri", R.drawable.c7),
            new Charity("Yayasan Katarak Peduli", R.drawable.c8),
            new Charity("WeCare.id", R.drawable.c9),
            new Charity("WALHI", R.drawable.c10),
            new Charity("Dompet Dhuafa", R.drawable.c11),
            new Charity("Yayasan Jantung Indonesia", R.drawable.c12)
    };


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
