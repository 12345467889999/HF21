package HF21.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomPoolUtils {

    private static final Random RANDOM = new Random();

    // -------------------------------
    // 名古屋ラッキースポット池
    // -------------------------------
    private static final List<String> LUCKY_PLACES =  Arrays.asList(
            "名古屋駅",
            "栄駅",
            "金山駅",
            "伏見駅",
            "大須観音",
            "上前津駅",
            "今池駅",
            "名古屋城",
            "大須商店街",
            "名古屋港水族館",
            "東山動植物園",
            "オアシス21",
            "名古屋テレビ塔",
            "レゴランド・ジャパン",
            "名古屋市科学館",
            "徳川園",
            "熱田神宮",
            "久屋大通公園",
            "ミッドランドスクエア",
            "JRゲートタワー",
            "名駅エリア",
            "サカエ地下街"
    );

    // -------------------------------
    // 名古屋めし池
    // -------------------------------
    private static final List<String> NAGOYA_FOODS = Arrays.asList(
            "味噌カツ",
            "手羽先",
            "ひつまぶし",
            "味噌煮込みうどん",
            "台湾ラーメン",
            "小倉トースト",
            "天むす",
            "きしめん",
            "あんかけスパ",
            "ういろう",
            "どて煮",
            "シロノワール",
            "えびふりゃー",
            "赤味噌おでん",
            "名古屋コーチン親子丼",
            "名古屋コーチンラーメン",
            "カレーうどん",
            "鰻丼",
            "名古屋ハンバーグ"
    );

    // -------------------------------
    // ランダム取得メソッド
    // -------------------------------

    /** ラッキースポットをランダムに返す */
    public static String getRandomLuckyPlace() {
        return LUCKY_PLACES.get(RANDOM.nextInt(LUCKY_PLACES.size()));
    }

    /** 名古屋めしをランダムに返す */
    public static String getRandomNagoyaFood() {
        return NAGOYA_FOODS.get(RANDOM.nextInt(NAGOYA_FOODS.size()));
    }
}
