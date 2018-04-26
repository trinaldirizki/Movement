package movement.com.movement.model;

/**
 * Created by mobiltrakya on 21/04/2018.
 */

public class CharityAction {
    private String name;
    private String description;
    private int totalFund;
    private int currentFund;
    private int totalKm;
    private int currentKm;

    public CharityAction(String name, String description, int totalFund, int totalKm) {
        this.name = name;
        this.description = description;
        this.totalFund = totalFund;
        this.totalKm = totalKm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(int totalFund) {
        this.totalFund = totalFund;
    }

    public int getCurrentFund() {
        return currentFund;
    }

    public void setCurrentFund(int currentFund) {
        this.currentFund = currentFund;
    }

    public int getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(int totalKm) {
        this.totalKm = totalKm;
    }

    public int getCurrentKm() {
        return currentKm;
    }

    public void setCurrentKm(int currentKm) {
        this.currentKm = currentKm;
    }
}
