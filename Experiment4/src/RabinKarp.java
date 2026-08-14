import java.util.ArrayList;
import java.util.List;

public class RabinKarp {

    private static final int PRIME = 101;

    public static List<Integer> search(String text, String pattern) {

        List<Integer> positions = new ArrayList<>();

        if (text == null || pattern == null) {
            return positions;
        }

        if (pattern.length() == 0 || pattern.length() > text.length()) {
            return positions;
        }

        text = text.toLowerCase();
        pattern = pattern.toLowerCase();

        int m = pattern.length();
        int n = text.length();

        int patternHash = 0;
        int textHash = 0;
        int h = 1;

        // Calculate h = pow(256, m-1) % PRIME
        for (int i = 0; i < m - 1; i++) {
            h = (h * 256) % PRIME;
        }

        // Calculate initial hash values
        for (int i = 0; i < m; i++) {
            patternHash =
                    (256 * patternHash + pattern.charAt(i)) % PRIME;

            textHash =
                    (256 * textHash + text.charAt(i)) % PRIME;
        }

        // Slide the pattern over the text
        for (int i = 0; i <= n - m; i++) {

            // If hash values match, compare characters
            if (patternHash == textHash) {

                boolean match = true;

                for (int j = 0; j < m; j++) {

                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    positions.add(i);
                }
            }

            // Calculate hash for next window
            if (i < n - m) {

                textHash =
                        (256 * (textHash
                        - text.charAt(i) * h)
                        + text.charAt(i + m)) % PRIME;

                if (textHash < 0) {
                    textHash = textHash + PRIME;
                }
            }
        }

        return positions;
    }
}
