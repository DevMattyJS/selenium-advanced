package tests;

import base.TestBase;
import enumerators.SinType;
import models.Sin;
import org.junit.Test;
import pages.SinCityPage;
import pages.SpartaPage;

import java.util.List;

public class SinCityTest extends TestBase {
    @Test
    public void testNewSin() {

        SinCityPage sinCityPage = new SinCityPage();
        Sin spiderSin = new Sin("I killed a spider", "Matty", "I stomp on him");
        spiderSin.setTags(List.of(SinType.MURDER, SinType.BLACKMAIL, SinType.ROBBERY));

        sinCityPage.openPage();
        sinCityPage.fillSinDataAndConfess(spiderSin);
        sinCityPage.verifySinStatus(spiderSin);
        sinCityPage.verifySinDetail(spiderSin);
    }

    @Test
    public void testSinForgiveness() {

        SinCityPage sinCityPage = new SinCityPage();
        SpartaPage spartaPage = new SpartaPage();

        Sin mothSin = new Sin("I killed a moth", "Matko", "I slapped him with bare hands");
        mothSin.setTags(List.of(SinType.MURDER));

        sinCityPage.openPage();
        sinCityPage.fillSinDataAndConfess(mothSin);
        sinCityPage.verifySinStatus(mothSin);

        spartaPage.openPage();
        spartaPage.forgiveSin(mothSin);

        sinCityPage.openPage();
        sinCityPage.verifySinStatus(mothSin);

    }
}
