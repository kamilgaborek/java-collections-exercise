import java.io.IOException;

public class AppMain {
    public static void main(String[] args) throws IOException {

        AppWorker zw=new AppWorker(new FileOperator());
        zw.getMapOfWords("mockFile.txt");
        zw.saveMapToFile("answerFile.txt");
        System.out.println(zw.mapToString());

    }

}
