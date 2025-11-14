package HF21.beans;


import org.springframework.stereotype.Component;


@Component
public class OmikujiContent {
    private String wish;    // 愿望
    private String love;    // 恋爱
    private String study;   // 学业
    private String business;    // 工作
    private String health;  // 健康

    public OmikujiContent(String wish, String love, String study, String business, String health) {
        this.wish = wish;
        this.love = love;
        this.study = study;
        this.business = business;
        this.health = health;
    }

    public OmikujiContent() {
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "OmikujiContent{" +
                "wish='" + wish + '\'' +
                ", love='" + love + '\'' +
                ", study='" + study + '\'' +
                ", business='" + business + '\'' +
                ", health='" + health + '\'' +
                '}';
    }
}
