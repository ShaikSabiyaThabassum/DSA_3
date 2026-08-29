import java.util.*;

public class SequenceAlignmentBitmaskDP {

    // -----------------------------------------
    // SEQUENCE ALIGNMENT
    // -----------------------------------------

    public static int sequenceAlignment(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();

        int gap = 2;
        int mismatch = 1;
        int match = 0;

        int[][] dp = new int[m + 1][n + 1];

        // Initialize first row
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j * gap;
        }

        // Initialize first column
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i * gap;
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                int cost;

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    cost = match;
                } else {
                    cost = mismatch;
                }

                int diagonal = dp[i - 1][j - 1] + cost;
                int delete = dp[i - 1][j] + gap;
                int insert = dp[i][j - 1] + gap;

                dp[i][j] = Math.min(diagonal,
                        Math.min(delete, insert));
            }
        }

        return dp[m][n];
    }


    // -----------------------------------------
    // BITMASK DP - TRAVELLING SALESMAN PROBLEM
    // -----------------------------------------

    static int[][] dp;
    static int[][] distance;
    static int n;

    static int tsp(int mask, int pos) {

        // All cities visited
        if (mask == (1 << n) - 1) {
            return distance[pos][0];
        }

        // Already calculated
        if (dp[mask][pos] != -1) {
            return dp[mask][pos];
        }

        int answer = Integer.MAX_VALUE;

        // Visit an unvisited city
        for (int city = 0; city < n; city++) {

            if ((mask & (1 << city)) == 0) {

                int newCost = distance[pos][city]
                        + tsp(mask | (1 << city), city);

                answer = Math.min(answer, newCost);
            }
        }

        return dp[mask][pos] = answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // -----------------------------------------
        // SEQUENCE ALIGNMENT
        // -----------------------------------------

        System.out.println("====================================");
        System.out.println("       SEQUENCE ALIGNMENT");
        System.out.println("====================================");

        System.out.print("Enter first sequence: ");
        String s1 = sc.nextLine();

        System.out.print("Enter second sequence: ");
        String s2 = sc.nextLine();

        int alignmentCost = sequenceAlignment(s1, s2);

        System.out.println("\nMinimum Alignment Cost = "
                + alignmentCost);


        // -----------------------------------------
        // BITMASK DP
        // -----------------------------------------

        System.out.println("\n====================================");
        System.out.println("      BITMASK DP - TSP");
        System.out.println("====================================");

        System.out.print("Enter number of cities (max 10): ");
        n = sc.nextInt();

        distance = new int[n][n];

        System.out.println("\nEnter distance matrix:");

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                distance[i][j] = sc.nextInt();
            }
        }

        // DP table
        dp = new int[1 << n][n];

        for (int i = 0; i < (1 << n); i++) {
            Arrays.fill(dp[i], -1);
        }

        // Start from city 0
        int minimumCost = tsp(1, 0);

        System.out.println("\nMinimum TSP Cost = "
                + minimumCost);

        sc.close();
    }
}
