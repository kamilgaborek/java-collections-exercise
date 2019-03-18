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

    public Map<Character, TreeSet<String>> getMapOfWords(String fileName) throws IOException {

        BufferedReader bufferedReader= fileOperator.getFileReader(fileName);
        mapOfAnswer=new TreeMap<Character, TreeSet<String>>();
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m1=null;
        String line=null;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                m1 = p.matcher(line);

                while (m1.find()) {
                    String matchedWord = m1.group().toLowerCase();
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
            bufferedReader.close();
        }


        return mapOfAnswer;
    }

    public void saveMapToFile(String fileName){
        fileOperator.saveStringToFile(mapToString(),fileName);
    }

    public String mapToString(){
        String stringOfMap="";
        for (Map.Entry<Character, TreeSet<String>> entry : mapOfAnswer.entrySet()) {
            stringOfMap+=entry.getKey() + ": ";
            TreeSet<String> tmpSet=entry.getValue();
            Iterator iterator= tmpSet.iterator();
            while(iterator.hasNext()){
                stringOfMap+=iterator.next();
                if(iterator.hasNext()){
                    stringOfMap+=", ";
                }
            }
            stringOfMap+="\n";
        }
        return stringOfMap;
    }


}
