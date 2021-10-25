package model.PersistanceTesting;

import model.EntryType;
import model.investment.ThirdPartyCaptchaSolversPurchaseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.JsonReaderForThirdPartySolvers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//json reader test for third part solver entries
public class JsonReaderForSolvesTest extends supportEntryTestJson{
    ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList;


    @BeforeEach
    public void SetUp(){
        thirdPartyCaptchaSolversPurchaseList = new ThirdPartyCaptchaSolversPurchaseList();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReaderForThirdPartySolvers jsonReaderForThirdPartySolvers = new JsonReaderForThirdPartySolvers
                ("./data/noSuchFile.json");
        try {
            thirdPartyCaptchaSolversPurchaseList = jsonReaderForThirdPartySolvers.read();
            fail();
        } catch (IOException e) {
            System.out.println("please enter a correct file directory ");
        }

    }

    @Test
    void testReaderEmptySolversFile() {
        JsonReaderForThirdPartySolvers jsonReaderForThirdPartySolvers = new JsonReaderForThirdPartySolvers
                ("./data/emptyThirdPartySolverListTest.json");
        try {
            thirdPartyCaptchaSolversPurchaseList = jsonReaderForThirdPartySolvers.read();
            assertEquals(0, thirdPartyCaptchaSolversPurchaseList.getLength());
            assertEquals(0, thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());
            assertEquals(EntryType.ThirdPartSolver, thirdPartyCaptchaSolversPurchaseList.getType());
        } catch (IOException e) {
            fail("no exception should been caught");
        }

    }

    @Test
    void testReaderGeneralSolversFile() {
        //read from current file
        JsonReaderForThirdPartySolvers jsonReaderForThirdPartySolvers = new JsonReaderForThirdPartySolvers
                ("./data/generalThirdPartySolverListTest.json");
        try {
            thirdPartyCaptchaSolversPurchaseList = jsonReaderForThirdPartySolvers.read();
            assertEquals(2, thirdPartyCaptchaSolversPurchaseList.getLength());
            assertEquals(27.98, thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());
            assertEquals(EntryType.ThirdPartSolver, thirdPartyCaptchaSolversPurchaseList.getType());
            checkSupportEntry( "CapMonster", 13.99, thirdPartyCaptchaSolversPurchaseList.get(0));
            checkSupportEntry("AntiCaptcha",13.99, thirdPartyCaptchaSolversPurchaseList.get(1));
        } catch (IOException e) {
            fail("no exception should been caught");
        }

    }
}
