import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class AppWorkerTest {

    private AppWorker appWorkerTest;
    private  Map<Character, TreeSet<String>> mapOfAnswer;

    public AppWorkerTest(){
        appWorkerTest =new AppWorker(new FileOperator());
        //String tmpString="a b c abc";

        mapOfAnswer=new TreeMap<Character, TreeSet<String>>();

        Set<String> setOfWordsA = new TreeSet<String>();
        setOfWordsA.add("a");
        setOfWordsA.add("abc");
        mapOfAnswer.put('a', (TreeSet<String>) setOfWordsA);

        Set<String> setOfWordsB = new TreeSet<String>();
        setOfWordsB.add("b");
        setOfWordsB.add("abc");
        mapOfAnswer.put('b', (TreeSet<String>) setOfWordsB);

        Set<String> setOfWordsC = new TreeSet<String>();
        setOfWordsC.add("c");
        setOfWordsC.add("abc");
        mapOfAnswer.put('c', (TreeSet<String>) setOfWordsC);

        appWorkerTest.setMapOfAnswer(mapOfAnswer);
    }


    @Test
    public void getMapOfWordsTest() throws IOException {

        Assert.assertEquals(mapOfAnswer, appWorkerTest.getMapOfWords("mockFileTest.txt"));
    }

    @Test
    public void saveMapToFileTest(){
        Assert.assertTrue(appWorkerTest.saveMapToFile("answerFileTest.txt"));
    }

    @Test
    public void mapToStringTest() {
        String expectedString="a: a, abc\nb: abc, b\nc: abc, c\n";
        Assert.assertEquals(expectedString,appWorkerTest.mapToString());
    }
}