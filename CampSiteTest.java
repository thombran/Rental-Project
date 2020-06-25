package Project2;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CampSiteTest {
    // Testing the basic RV() function no parameters
    @Test
    public void testRV(){
        CampSite d = new RV();
    }
    // Testing the RV function with correct parameters
    @Test
    public void testRV1(){
        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020,00,01);
        estCheckOut.set(2020,01,30);
        actCheckOut.set(2020,01,01);

        CampSite d = new RV("Test Name",checkIn, estCheckOut,actCheckOut,1500);
    }

    // Testing correct getCost method under with the RV Object
    @Test
    public void testRVCost(){

        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020,00,01);
        estCheckOut.set(2020,01,30);
        actCheckOut.set(2020,01,01);

        CampSite d = new RV("Test Name",checkIn, estCheckOut,actCheckOut,1500);
        assertTrue(d.getCost() == 620.0);
    }
    // Testing incorrect getCost method under with the RV Object
    @Test
    public void testRVCost2(){

        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020,00,01);
        estCheckOut.set(2020,01,30);
        actCheckOut.set(2020,01,01);

        CampSite d = new RV("Test Name",checkIn, estCheckOut,actCheckOut,1500);
        assertFalse(d.getCost() == 600.0);


    }

    // Testing the RV toString function
    @Test
    public void testRVString(){
        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020,00,01);
        estCheckOut.set(2020,01,30);
        actCheckOut.set(2020,01,01);

        CampSite d = new RV("Test Name",checkIn, estCheckOut,actCheckOut,1500);
        d.toString();
    }




    // Testing the basic Tent() function no parameters
    @Test
    public void testCamp(){
        CampSite d = new Tent();
    }
    // Testing the Tent function with correct parameters
    @Test
    public void testTent1(){
        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020,00,01);
        estCheckOut.set(2020,01,30);
        actCheckOut.set(2020,01,01);

        CampSite d = new Tent("Test Name",checkIn, estCheckOut,actCheckOut,2);
    }

    // Testing correct getCost method under with the Tent Object
    @Test
    public void testTentCost(){

        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020,00,01);
        estCheckOut.set(2020,01,30);
        actCheckOut.set(2020,01,01);

        CampSite d = new Tent("Test Name",checkIn, estCheckOut,actCheckOut,2);
        assertTrue(d.getCost() == 310.0);
    }
    // Testing incorrect getCost method under with the Tent Object
    @Test
    public void testTentCost2(){

        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020,00,01);
        estCheckOut.set(2020,01,30);
        actCheckOut.set(2020,01,01);

        CampSite d = new Tent("Test Name",checkIn, estCheckOut,actCheckOut,2);
        assertFalse(d.getCost() == 500.0);
    }

    // Testing the RV toString function
    @Test
    public void testTenttoString(){
        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020,00,01);
        estCheckOut.set(2020,01,30);
        actCheckOut.set(2020,01,01);

        CampSite d = new Tent("Test Name",checkIn, estCheckOut,actCheckOut,2);
        d.toString();
    }



    @Test
    public void testListModel(){
        new ListModel();
    }

    @Test
    public void testDisplay(){
        ScreenDisplay display = ScreenDisplay.CurrentParkStatus;
    }

    @Test
    public void testgetColumnName(){
        String d = "";



    }

    @Test
    public void testScreenUpdate() {
        ListModel list = new ListModel();
    }

}

