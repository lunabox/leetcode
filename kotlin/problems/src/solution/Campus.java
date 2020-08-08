package solution;

import java.util.Arrays;
import java.util.Scanner;

public class Campus {
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        System.out.println(primeNumberCount(nums));
    }

    private static int primeNumberCount(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            if (num >= 2) {
                if (num % 2 == 0) {
                    ans += num / 2;
                } else {
                    if (num > 3) {
                        ans += num / 2;
                    } else {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[i] = scanner.nextInt();
            }
            System.out.println(minValue(nums));
        }
    }

    private static int minValue(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        boolean[] show = new boolean[nums.length];
        Arrays.fill(show, true);
        return minValueDfs(nums, show, 0, sum);
    }

    private static int minValueDfs(int[] nums, boolean[] show, int index, int sum) {
        if (sum < 0) {
            return -1;
        }
        if (sum == 0) {
            int s = 0;
            for (int i = 0; i < show.length; i++) {
                if (!show[i]) {
                    s += nums[i];
                }
            }
            return s;
        }
        show[index] = false;
        int result1 = minValueDfs(nums, show, index + 1, sum - nums[index]);
        show[index] = true;
        int result2 = minValueDfs(nums, show, index + 1, sum - nums[index]);
        if (result1 < 0 && result2 < 0) {
            return -1;
        }
        return Math.min(result1 + nums[index], result2);
    }
}
