public class Article {

    private int articleId;
    private String title;
    private String content;
    private int wordCount;

    public Article(int articleId, String title, String content) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.wordCount = countWords(content);
    }

    private int countWords(String content) {

        if (content == null || content.trim().isEmpty()) {
            return 0;
        }

        return content.trim().split("\\s+").length;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getWordCount() {
        return wordCount;
    }
}