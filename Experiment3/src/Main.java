import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        File folder = new File("Corpus");

        File[] files = folder.listFiles();

        if (files == null) {
            System.out.println("Corpus folder not found!");
            return;
        }

        Article[] articles = new Article[files.length];

        int articleCount = 0;
        int articleId = 101;

        try {

            for (File file : files) {

                if (file.isFile() && file.getName().endsWith(".txt")) {

                    String text = Files.readString(file.toPath());

                    String[] lines = text.split("\\R", 2);

                    String title = lines[0].trim();

                    String content = "";

                    if (lines.length > 1) {
                        content = lines[1].trim();
                    }

                    articles[articleCount] =
                            new Article(articleId, title, content);

                    articleCount++;
                    articleId++;
                }
            }

        } catch (IOException e) {

            System.out.println("Error reading corpus files.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("=====================================");
        System.out.println("       TEXTHACK PATTERN SEARCH");
        System.out.println("=====================================");

        System.out.print("Enter keyword to search : ");

        String pattern = scanner.nextLine();

        System.out.println();
        System.out.println("=====================================");
        System.out.println("       NAIVE PATTERN MATCHING");
        System.out.println("=====================================");

        for (int i = 0; i < articleCount; i++) {

            Article article = articles[i];

            int[] positions =
                    NaivePatternMatcher.search(
                            article.getContent(),
                            pattern
                    );

            if (positions.length > 0) {

                System.out.println();
                System.out.println("Article ID : " + article.getArticleId());
                System.out.println("Title     : " + article.getTitle());

                for (int position : positions) {
                    System.out.println(
                            "Pattern found at position : " + position
                    );
                }

                System.out.println(
                        "Total occurrences : " + positions.length
                );
            }
        }

        System.out.println();
        System.out.println("=====================================");
        System.out.println("       KMP PATTERN MATCHING");
        System.out.println("=====================================");

        for (int i = 0; i < articleCount; i++) {

            Article article = articles[i];

            int[] positions =
                    KMPMatcher.search(
                            article.getContent(),
                            pattern
                    );

            if (positions.length > 0) {

                System.out.println();
                System.out.println("Article ID : " + article.getArticleId());
                System.out.println("Title     : " + article.getTitle());

                for (int position : positions) {
                    System.out.println(
                            "Pattern found at position : " + position
                    );
                }

                System.out.println(
                        "Total occurrences : " + positions.length
                );
            }
        }

        scanner.close();
    }
}