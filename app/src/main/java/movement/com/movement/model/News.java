package movement.com.movement.model;

/**
 * Created by isma-ilou on 11.06.2018.
 */

public class News {

    private String uid;
    private String content;
    private String date;
    private String imageUrl;

    public News(){

    }

    public News(String uid, String content, String date, String imageUrl) {
        this.content = content;
        this.date = date;
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
