package info.ebtd.test;

import org.junit.Assert;
import org.junit.Test;

public class TestSuit {

    @Test
    public void createTest(){
        int a = 5;
        int b = 5;

        Assert.assertEquals(a,b);

    }
}
