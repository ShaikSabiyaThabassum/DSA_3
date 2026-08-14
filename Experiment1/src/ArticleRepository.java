import java.util.ArrayList;

public class ArticleRepository {

    private ArrayList<Article> articles;

    public ArticleRepository() {
        articles = new ArrayList<>();
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void displayArticles() {

        System.out.println("======================================");
        System.out.println("       TEXTHACK ARTICLE REPOSITORY");
        System.out.println("======================================");

        for (Article article : articles) {

            System.out.println("-------------------------------------------");
            System.out.println("Article ID : " + article.getArticleId());
            System.out.println("Title : " + article.getTitle());
            System.out.println("Word Count : " + article.getWordCount());
            System.out.println("Content :");
            System.out.println(article.getContent());
        }

        System.out.println("-------------------------------------------");

        System.out.println();
        System.out.println("Repository Statistics");
        System.out.println("----------------------");

        System.out.println("Total Articles Loaded : " + articles.size());

        int totalWords = 0;

        for (Article article : articles) {
            totalWords += article.getWordCount();
        }

        System.out.println("Total Words : " + totalWords);
    }
}