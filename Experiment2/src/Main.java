import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArticleRepository repository =
                new ArticleRepository();

        CorpusLoader loader =
                new CorpusLoader(repository);

        loader.loadCorpus("Corpus");

        QueryProcessor processor =
                new QueryProcessor(repository);

        Scanner scanner = new Scanner(System.in);

        System.out.println("=====================================");
        System.out.println("       TEXTHACK QUERY PROCESSOR");
        System.out.println("=====================================");

        System.out.print("Enter keyword to search : ");

        String keyword = scanner.nextLine();

        processor.search(keyword);

        scanner.close();
    }
}