package HF21.beans;


import org.springframework.stereotype.Component;

@Component
public class OmikujiLuckyItem {

    private String luckyPlace;

    private String nagoyaFood;

    public OmikujiLuckyItem(String luckyPlace, String nagoyaFood) {
        this.luckyPlace = luckyPlace;
        this.nagoyaFood = nagoyaFood;
    }

    public OmikujiLuckyItem() {
    }

    public String getLuckyPlace() {
        return luckyPlace;
    }

    public void setLuckyPlace(String luckyPlace) {
        this.luckyPlace = luckyPlace;
    }

    public String getNagoyaFood() {
        return nagoyaFood;
    }

    public void setNagoyaFood(String nagoyaFood) {
        this.nagoyaFood = nagoyaFood;
    }

    @Override
    public String toString() {
        return "OmikujiLuckyItem{" +
                "luckyPlace='" + luckyPlace + '\'' +
                ", nagoyaFood='" + nagoyaFood + '\'' +
                '}';
    }
}
