package tests;

import base.TestBase;
import enumerators.SinType;
import models.Sin;
import org.junit.Test;
import pages.SinCityPage;

import java.util.List;

public class SinCityTest extends TestBase {
    @Test
    public void testNewSin() {

        SinCityPage sinCityPage = new SinCityPage();
        Sin spiderSin = new Sin("I killed a spider", "Matty", "I stomp on him");

        sinCityPage.openPage();
        spiderSin.setTags(List.of(SinType.MURDER, SinType.BLACKMAIL, SinType.ROBBERY));
        sinCityPage.fillSinDataAndConfess(spiderSin, spiderSin.getTags());
        sinCityPage.verifySinStatus(spiderSin, "pending");
        sinCityPage.verifySinDetail(spiderSin);
    }
}
