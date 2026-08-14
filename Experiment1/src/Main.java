public class Main {

    public static void main(String[] args) {

        ArticleRepository repository =
                new ArticleRepository();

        CorpusLoader loader =
                new CorpusLoader(repository);

        loader.loadCorpus("Corpus");

        repository.displayArticles();
    }
}