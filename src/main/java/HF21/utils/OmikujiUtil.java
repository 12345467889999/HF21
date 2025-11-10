package HF21.utils;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * おみくじ（签）随机抽取工具类
 */
public class OmikujiUtil {

    private OmikujiUtil() {}

    /** 签位枚举 */
    public enum Rank {
        DAIKICHI("大吉"),
        CHUKICHI("中吉"),
        SHOKICHI("小吉"),
        KICHI("吉"),
        SUEKICHI("末吉"),
        KYO("凶"),
        DAIKYO("大凶");

        private final String label;
        Rank(String label) {
            this.label = label;
        }
        public String getLabel() {
            return label;
        }
    }

    /**
     * 按权重随机抽取一个签位
     */
    public static Rank draw(Map<Rank, Integer> weights) {
        if (weights == null || weights.isEmpty()) {
            throw new IllegalArgumentException("weights不能为空");
        }

        long total = 0;
        for (Integer w : weights.values()) {
            if (w == null || w < 0) {
                throw new IllegalArgumentException("权重必须为非负整数");
            }
            total += w;
        }
        if (total <= 0) throw new IllegalArgumentException("权重总和不能为0");

        long r = nextLong(ThreadLocalRandom.current(), total);
        long sum = 0;
        for (Map.Entry<Rank, Integer> e : weights.entrySet()) {
            sum += e.getValue();
            if (r < sum) {
                return e.getKey();
            }
        }
        // 理论不会到这里
        return weights.keySet().iterator().next();
    }

    /** JDK8 生成 [0,bound) 的 long */
    private static long nextLong(Random random, long bound) {
        if (bound <= 0) throw new IllegalArgumentException("bound must be positive");
        long r = random.nextLong();
        long m = bound - 1;
        if ((bound & m) == 0L) {
            return r & m;
        }
        long u = r >>> 1;
        long x = u % bound;
        while (u + m - x < 0L) {
            u = random.nextLong() >>> 1;
            x = u % bound;
        }
        return x;
    }

    // ===================== 预设概率 =====================

    /** 游客友好：凶签极少 */
    public static Map<Rank, Integer> touristFriendly() {
        Map<Rank, Integer> m = new LinkedHashMap<>();
        m.put(Rank.DAIKICHI, 15);
        m.put(Rank.CHUKICHI, 25);
        m.put(Rank.SHOKICHI, 25);
        m.put(Rank.KICHI, 15);
        m.put(Rank.SUEKICHI, 15);
        m.put(Rank.KYO, 4);
        m.put(Rank.DAIKYO, 1);
        return m;
    }

    /** 经典均衡：约8%为凶 */
    public static Map<Rank, Integer> classicBalanced() {
        Map<Rank, Integer> m = new LinkedHashMap<>();
        m.put(Rank.DAIKICHI, 15);
        m.put(Rank.CHUKICHI, 22);
        m.put(Rank.SHOKICHI, 22);
        m.put(Rank.KICHI, 18);
        m.put(Rank.SUEKICHI, 15);
        m.put(Rank.KYO, 7);
        m.put(Rank.DAIKYO, 1);
        return m;
    }

    /** 浅草寺辣味：高凶率 */
    public static Map<Rank, Integer> asakusaSpicy() {
        Map<Rank, Integer> m = new LinkedHashMap<>();
        m.put(Rank.DAIKICHI, 10);
        m.put(Rank.CHUKICHI, 18);
        m.put(Rank.SHOKICHI, 18);
        m.put(Rank.KICHI, 14);
        m.put(Rank.SUEKICHI, 10);
        m.put(Rank.KYO, 28);
        m.put(Rank.DAIKYO, 2);
        return m;
    }
}
