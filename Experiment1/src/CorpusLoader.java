import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CorpusLoader {

    private ArticleRepository repository;

    public CorpusLoader(ArticleRepository repository) {
        this.repository = repository;
    }

    public void loadCorpus(String folderPath) {

        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Corpus folder not found!");
            return;
        }

        File[] files = folder.listFiles();

        if (files == null) {
            System.out.println("No files found in Corpus folder.");
            return;
        }

        int articleId = 101;

        for (File file : files) {

            if (file.isFile() && file.getName().endsWith(".txt")) {

                try {

                    String content = Files.readString(file.toPath());

                    String[] lines = content.split("\\R", 2);

                    String title = lines[0].trim();

                    String articleContent = "";

                    if (lines.length > 1) {
                        articleContent = lines[1].trim();
                    }

                    Article article =
                            new Article(articleId, title, articleContent);

                    repository.addArticle(article);

                    articleId++;

                } catch (IOException e) {

                    System.out.println(
                        "Error reading file: " + file.getName()
                    );
                }
            }
        }
    }
}
