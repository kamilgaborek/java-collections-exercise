import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileOperatorTest {

    FileOperator fileOperatorTest;
    AppWorker appWorkerTest;

    public FileOperatorTest(){
        fileOperatorTest=new FileOperator();
        appWorkerTest=new AppWorker(fileOperatorTest);
    }

    @Test
    void saveStringToFile() {
        Assert.assertEquals(true,fileOperatorTest.saveStringToFile("test text","answerFileTest.txt"));
    }
}