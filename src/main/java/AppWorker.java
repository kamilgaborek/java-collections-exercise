import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppWorker {

    private FileOperator fileOperator;
    private Map<Character, TreeSet<String>> mapOfAnswer;

    public AppWorker(FileOperator fileOperator){
        this.fileOperator = fileOperator;
    }


    //główna problematyka która mogła by byc odpowiedzią na to zadanie
    public Map<Character, TreeSet<String>> getMapOfWords(String fileName){

        BufferedReader bufferedReader= fileOperator.getFileReader(fileName);
        mapOfAnswer=new TreeMap<Character, TreeSet<String>>();
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcherSingleWord=null;
        String line=null;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                matcherSingleWord = pattern.matcher(line);

                while (matcherSingleWord.find()) {
                    String matchedWord = matcherSingleWord.group().toLowerCase();
                    for (char singleChar : matchedWord.toCharArray()) {
                        if (mapOfAnswer.containsKey(singleChar)) {
                            mapOfAnswer.get(singleChar).add(matchedWord);
                        } else {
                            Set<String> setOfWords = new TreeSet<String>();
                            setOfWords.add(matchedWord);
                            mapOfAnswer.put(singleChar, (TreeSet<String>) setOfWords);
                        }
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Can not read from file:" +fileName);
        }
        finally {
            try{
                bufferedReader.close();
            } catch(IOException e){
                System.out.println("Trouble with connection closing");
            }
        }
        return mapOfAnswer;
    }

    public boolean saveMapToFile(String fileName){
        return fileOperator.saveStringToFile(mapToString(),fileName);
    }

    public String mapToString(){
        StringBuilder stringBuilder = new StringBuilder("");
        for (Map.Entry<Character, TreeSet<String>> entry : mapOfAnswer.entrySet()) {
            stringBuilder.append(entry.getKey() + ": ");
            TreeSet<String> tmpSet=entry.getValue();
            Iterator iterator= tmpSet.iterator();
            while(iterator.hasNext()){
                stringBuilder.append(iterator.next());
                if(iterator.hasNext()){
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public FileOperator getFileOperator() {
        return fileOperator;
    }

    public void setFileOperator(FileOperator fileOperator) {
        this.fileOperator = fileOperator;
    }

    public Map<Character, TreeSet<String>> getMapOfAnswer() {
        return mapOfAnswer;
    }

    public void setMapOfAnswer(Map<Character, TreeSet<String>> mapOfAnswer) {
        this.mapOfAnswer = mapOfAnswer;
    }
}
