package HF21.beans;


import org.springframework.stereotype.Component;


@Component
public class OmikujiContent {
    private String wish;    // 愿望
    private String love;    // 恋爱
    private String study;   // 学业
    private String work;    // 工作
    private String health;  // 健康
    private String travel;  // 旅行

    public OmikujiContent(String wish, String love, String study, String work, String health, String travel) {
        this.wish = wish;
        this.love = love;
        this.study = study;
        this.work = work;
        this.health = health;
        this.travel = travel;
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

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }

    @Override
    public String toString() {
        return "OmikujiContent{" +
                "wish='" + wish + '\'' +
                ", love='" + love + '\'' +
                ", study='" + study + '\'' +
                ", work='" + work + '\'' +
                ", health='" + health + '\'' +
                ", travel='" + travel + '\'' +
                '}';
    }
}
