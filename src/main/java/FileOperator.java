import java.io.*;

public class FileOperator {


    public FileOperator(){
    }

    public BufferedReader getFileReader(String filePath){
        BufferedReader bufferedReader=null;

        try {
            FileReader fileReader = new FileReader(filePath);
            bufferedReader= new BufferedReader(fileReader);
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file: " +filePath);
        }
        catch(IOException ex) {
            System.out.println("Error caused by file: " +filePath);
        }

        return bufferedReader;
    }
    public boolean saveStringToFile(String text,String fileName){
        String fileNameToSave = fileName;

        try {
            FileWriter fileWriter = new FileWriter(fileNameToSave);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(text);
            bufferedWriter.close();
            return true;
        }
        catch(IOException ex) {
            System.out.println("Can not write to file:"+fileNameToSave);
            return false;
        }
    }
}
