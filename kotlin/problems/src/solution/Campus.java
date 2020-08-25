package solution;

import java.util.*;

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

    public int string2Int(String str) {
        int ans = 0;
        boolean isNeg = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (i == 0 && ch == '-') {
                isNeg = true;
                continue;
            } else if (i == 0 && ch == '+') {
                isNeg = false;
                continue;
            }

            ans *= 10;
            if (ch >= '0' && ch <= '9') {
                ans += (ch - '0');
            } else {
                // 失败返回0
                return 0;
            }
        }
        if (isNeg) {
            return -ans;
        } else {
            return ans;
        }
    }

    public int findKth(int[] a, int k) {
        return findKth(a, 0, a.length - 1, k);
    }

    public int findKth(int[] a, int low, int high, int k) {
        int part = partition(a, low, high);

        if (k == part - low + 1) {
            return a[part];
        } else if (k > part - low + 1) {
            return findKth(a, part + 1, high, k - part + low - 1);
        } else {
            return findKth(a, low, part - 1, k);
        }
    }

    public int partition(int[] a, int low, int high) {
        int key = a[low];
        while (low < high) {
            while (low < high && a[high] <= key) {
                high--;
                a[low] = a[high];
            }
            while (low < high && a[low] >= key) {
                low++;
                a[high] = a[low];
            }
            a[low] = key;
            return low;
        }
        return low;
    }

    public int third(int[] n) {
        int max = n[0];
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;
        for (int i = 1; i < n.length; i++) {
            int a = n[i];
            if (a > max) {
                third = second;
                second = max;
                max = a;
            } else if (a > second) {
                third = second;
                second = a;
            } else if (a > third) {
                third = a;
            }
        }
        return third;
    }

    private static void isNewRevertNumber(int n) {
        int edge = n / 4;
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= edge; i++) {
            String a = String.valueOf(i);
            String b = String.valueOf(i * 4);
            if (a.length() == b.length()) {
                // 长度一样的情况下判断逆序
                boolean isSame = true;
                int len = a.length();
                for (int j = 0; j < len; j++) {
                    if (a.charAt(j) != b.charAt(len - j - 1)) {
                        isSame = false;
                        break;
                    }
                }
                if (isSame) {
                    ans.add(a + " " + b);
                }
            }
        }
        System.out.println(ans.size());
        for (String line : ans) {
            System.out.println(line);
        }
    }

    public static void main3(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            Map<String, String> map = new HashMap<>();
            int count = 0;
            String start = null;
            String end = null;
            for (int i = 0; i < n; i++) {
                String[] places = new String[]{scanner.next(), scanner.next()};
                map.put(places[0], places[1]);
                if (start == null) {
                    start = places[0];
                }
                if (end == null) {
                    end = places[1];
                }
                if (map.containsKey(end)) {
                    end = map.get(end);
                }
                if (end.equals(start)) {
                    count++;
                    map.clear();
                    start = null;
                    end = null;
                }
            }
            System.out.println(count);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] u = new int[m + 1];
        int[] v = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (a > b) {
                u[i] = b;
                v[i] = a;
            } else {
                u[i] = a;
                v[i] = b;
            }
        }
        order(n, m, u, v);
    }

    private static void order(int n, int m, int[] u, int[] v) {
        boolean[] visited = new boolean[n + 1];
        LinkedList<Integer> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }
            List<Integer> line = new ArrayList<>();

            queue.add(i);

            while (!queue.isEmpty()) {
                int s = queue.poll();
                if (visited[s]) {
                    continue;
                }
                visited[s] = true;
                line.add(s);

                for (int j = 0; j < u.length; j++) {
                    if (u[j] == s && !visited[v[j]]) {
                        queue.add(v[j]);
                    }
                }
            }
            if (line.size() != 0) {
                ans.add(line);
            }
        }

        System.out.println(ans.size());
        for (List<Integer> line : ans) {
            for (int i = 0; i < line.size(); i++) {
                System.out.printf("%d", line.get(i));
                if (i == line.size() - 1) {
                    System.out.printf("\n");
                } else {
                    System.out.printf(" ");
                }
            }
        }
    }
}
