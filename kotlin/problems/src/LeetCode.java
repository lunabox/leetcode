import java.math.BigInteger;
import java.util.*;

public class LeetCode {

    public void test() {
        int[][] x = new int[2][];

    }
    public int[] rank(int[] a, int[] b) {
        Arrays.sort(a);

        // 存储B中数据的位置
        Map<Integer, List<Integer>> map = new HashMap<>(b.length);
        for (int i = 0; i < b.length; i++) {
            List<Integer> list = map.get(b[i]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(b[i], list);
            }
            list.add(i);
        }
        Arrays.sort(b);

        int[] result = new int[a.length];

        int left = 0;
        int right = b.length - 1;

        for (int value : a) {
            int selectB;
            // 如果A的最小比B的最小小，则A的最小对B的最大
            // 如果A的最小比B的最小大，则A的最小对B的最小
            if (value > b[left]) {
                selectB = b[left++];
            } else {
                selectB = b[right--];
            }
            // 移除掉选中的B
            List<Integer> list = map.get(selectB);
            Integer index = list.remove(0);
            result[index] = value;
        }

        return result;
    }


    public int maxScore(int[] places) {
        int result = 0;
        int current = places[0];
        int i = 0;
        for (int j = 1; j < places.length; j++) {
            result = Math.max(result, current + places[j] + i - j);
            if (places[j] >= current + i - j) {
                current = places[j];
                i = j;
            }
        }
        return result;
    }

    public void calcBigInt() {
        BigInteger n = new BigInteger("50000000000");
//        BigInteger n = new BigInteger("50");
        BigInteger sum = BigInteger.valueOf(0);
        for (BigInteger i = BigInteger.valueOf(1); i.compareTo(n) <= 0; i = i.add(BigInteger.valueOf(1L))) {
            BigInteger temp = i.multiply(BigInteger.valueOf(2)).subtract(BigInteger.valueOf(1));
            BigInteger item = temp.multiply(temp);
            sum = sum.add(item);
        }
        System.out.println(sum.toString());
    }
}
