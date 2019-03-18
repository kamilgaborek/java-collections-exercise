import java.io.IOException;

public class AppMain {
    public static void main(String[] args) throws IOException {

        AppWorker appWorker=new AppWorker(new FileOperator());
        appWorker.getMapOfWords("mockFile.txt");
        appWorker.saveMapToFile("answerFile.txt");
        System.out.println(appWorker.mapToString());

    }

}
