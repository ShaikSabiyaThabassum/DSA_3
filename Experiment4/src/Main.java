
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=====================================");
        System.out.println("       TEXTHACK RABIN-KARP SEARCH");
        System.out.println("=====================================");

        System.out.print("\nEnter keyword to search : ");
        String pattern = sc.nextLine();

        String corpusPath = "Experiment4/Corpus";

        Article[] articles = new Article[3];

        try {

            // Read article 1
            String content1 = Files.readString(
                    Path.of(corpusPath, "a1.txt")
            );

            // Read article 2
            String content2 = Files.readString(
                    Path.of(corpusPath, "a2.txt")
            );

            // Read article 3
            String content3 = Files.readString(
                    Path.of(corpusPath, "a3.txt")
            );

            // Create Article objects
            articles[0] = new Article(
                    101,
                    "Data Structures",
                    content1
            );

            articles[1] = new Article(
                    102,
                    "Algorithms",
                    content2
            );

            articles[2] = new Article(
                    103,
                    "Computer Science",
                    content3
            );

            System.out.println("\n=====================================");
            System.out.println("       RABIN-KARP PATTERN SEARCH");
            System.out.println("=====================================");

            boolean foundAnywhere = false;

            // Search in every article
            for (Article article : articles) {

                List<Integer> positions =
                        RabinKarp.search(
                                article.getContent(),
                                pattern
                        );

                if (!positions.isEmpty()) {

                    foundAnywhere = true;

                    System.out.println("\nArticle ID : "
                            + article.getArticleId());

                    System.out.println("Title     : "
                            + article.getTitle());

                    for (int position : positions) {

                        System.out.println(
                                "Pattern found at position : "
                                + position
                        );
                    }

                    System.out.println(
                            "Total occurrences : "
                            + positions.size()
                    );

                    System.out.println(
                            "----------------------------------------"
                    );
                }
            }

            if (!foundAnywhere) {

                System.out.println(
                        "\nPattern not found in any article."
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "Error reading corpus files."
            );

            System.out.println(
                    "Make sure the Corpus folder exists."
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }

        sc.close();
    }
}