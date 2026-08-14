public class QueryProcessor {

    private ArticleRepository repository;

    public QueryProcessor(ArticleRepository repository) {
        this.repository = repository;
    }

    public void search(String keyword) {

        Article[] articles = repository.getArticles();

        boolean found = false;

        System.out.println();
        System.out.println("Matching Articles");
        System.out.println("----------------------------------------");

        for (Article article : articles) {

            String title = article.getTitle();
            String content = article.getContent();

            if (title.toLowerCase().contains(keyword.toLowerCase())
                    || content.toLowerCase().contains(keyword.toLowerCase())) {

                found = true;

                System.out.println("Article ID : " + article.getArticleId());
                System.out.println("Title : " + article.getTitle());
                System.out.println("Word Count : " + article.getWordCount());
                System.out.println("Content :");
                System.out.println(article.getContent());
                System.out.println("----------------------------------------");
            }
        }

        if (!found) {
            System.out.println("No matching articles found.");
        }
    }
}