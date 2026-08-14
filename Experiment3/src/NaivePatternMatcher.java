public class NaivePatternMatcher {

    public static int[] search(String text, String pattern) {

        int[] positions = new int[text.length()];
        int count = 0;

        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {

            int j = 0;

            while (j < m && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }

            if (j == m) {
                positions[count] = i;
                count++;
            }
        }

        int[] result = new int[count];

        for (int i = 0; i < count; i++) {
            result[i] = positions[i];
        }

        return result;
    }
}
