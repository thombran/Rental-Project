package Project2;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class CampSiteTest {





    // Testing the basic new CampSite RV() function no parameters
    @Test
    public void testCampRV(){
        CampSite d = new RV();
    }
    // Testing the Campsite RV function with correct parameters
    @Test
    public void testCampRV1(){
        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020, Calendar.JANUARY,1);
        estCheckOut.set(2020, Calendar.FEBRUARY,30);
        actCheckOut.set(2020, Calendar.FEBRUARY,1);

        CampSite d = new RV("Test Name",checkIn, estCheckOut,actCheckOut,1500);
    }

    // Testing correct getCost method under with the Campsite Object
    @Test
    public void testCampRVCost(){

        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020, Calendar.JANUARY,1);
        estCheckOut.set(2020, Calendar.FEBRUARY,30);
        actCheckOut.set(2020, Calendar.FEBRUARY,1);

        CampSite d = new RV("Test Name",checkIn, estCheckOut,actCheckOut,1500);
        assertEquals(620.0, d.getCost(), 0.0);
    }
    // Testing incorrect RV getCost method  with the Campsite Object
    @Test
    public void testCampRVCost2(){

        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020, Calendar.JANUARY,1);
        estCheckOut.set(2020, Calendar.FEBRUARY,30);
        actCheckOut.set(2020, Calendar.FEBRUARY, 1);


        CampSite d = new RV("Test Name",checkIn, estCheckOut,actCheckOut,1500);
        assertNotEquals(600.0, d.getCost(), 0.0);

    }

    // Testing the CampSite RV toString function
    @Test
    public void testCampRVtoString(){
        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020, Calendar.JANUARY, 1);
        estCheckOut.set(2020, Calendar.FEBRUARY,30);
        actCheckOut.set(2020, Calendar.FEBRUARY, 1);

        CampSite d = new RV("Test Name",checkIn, estCheckOut,actCheckOut,1500);
        d.toString();
    }

    @Test
    public void testRV(){
        RV d = new RV();
    }
    // Test rv getPower method for correct number.
    @Test
    public void testRVgetPower(){
        RV d = new RV();
        d.setPower(1000);
        assertEquals(d.getPower(),1000);
    }

    @Test
    public void testRVCostExceeds() {
        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020, Calendar.FEBRUARY,5);
        estCheckOut.set(2020, Calendar.FEBRUARY,10);
        actCheckOut.set(2020, Calendar.FEBRUARY,12);

        CampSite d = new RV("Test Name",checkIn, estCheckOut,actCheckOut,1500);
        assertEquals(220, d.getCost(), 0.0);
    }

    @Test
    public void testRVCostExceedsLeapYear() {
        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020, Calendar.FEBRUARY,10);
        estCheckOut.set(2020, Calendar.FEBRUARY,28);
        actCheckOut.set(2020, Calendar.FEBRUARY,29);

        CampSite d = new RV("Test Name",checkIn, estCheckOut,actCheckOut,1500);
        assertEquals(420, d.getCost(), 0.0);
    }



    // Testing the basic Tent() function no parameters
    @Test
    public void testCampTent(){
        CampSite d = new Tent();
    }
    // Testing the Tent function with correct parameters
    @Test
    public void testCamptTent1(){
        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020, Calendar.JANUARY,1);
        estCheckOut.set(2020, Calendar.FEBRUARY,30);
        actCheckOut.set(2020, Calendar.FEBRUARY,1);

        CampSite d = new Tent("Test Name",checkIn, estCheckOut,actCheckOut,2);
    }

    // Testing correct getCost method under with the Tent Object
    @Test
    public void testCamptTentCost(){

        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020, Calendar.JANUARY,1);
        estCheckOut.set(2020, Calendar.FEBRUARY,30);
        actCheckOut.set(2020, Calendar.FEBRUARY,1);

        CampSite d = new Tent("Test Name",checkIn, estCheckOut,actCheckOut,2);
        assertEquals(310.0, d.getCost(), 0.0);
    }
    // Testing incorrect getCost method under with the Tent Object
    @Test
    public void testCampTentCost2(){

        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020, Calendar.JANUARY,1);
        estCheckOut.set(2020, Calendar.FEBRUARY,30);
        actCheckOut.set(2020, Calendar.FEBRUARY,1);

        CampSite d = new Tent("Test Name",checkIn, estCheckOut,actCheckOut,2);
        assertNotEquals(500.0, d.getCost(), 0.0);
    }

    // Testing the tent toString function
    @Test
    public void testCamptTenttoString(){
        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020, Calendar.JANUARY,1);
        estCheckOut.set(2020, Calendar.FEBRUARY,30);
        actCheckOut.set(2020, Calendar.FEBRUARY,1);

        CampSite d = new Tent("Test Name",checkIn, estCheckOut,actCheckOut,2);
        d.toString();
    }

    @Test
    public void testTent(){
        Tent d = new Tent();
    }
    // Test rv getPower method for correct number.
    @Test
    public void testTentgetTenters(){
        Tent d = new Tent();
        d.setNumberOfTenters(5);
        assertEquals(d.getNumberOfTenters(),5);
    }

    @Test
    public void testCampTentExceeds() {
        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020, Calendar.AUGUST,20);
        estCheckOut.set(2020, Calendar.AUGUST,25);
        actCheckOut.set(2020, Calendar.AUGUST,26);

        CampSite d = new Tent("Test Name", checkIn, estCheckOut,actCheckOut,4);
        assertEquals(80, d.getCost(), 0.0);
    }

    @Test
    public void testCampTentExceedsLeapYear() {
        GregorianCalendar checkIn = new GregorianCalendar();
        GregorianCalendar estCheckOut = new GregorianCalendar();
        GregorianCalendar actCheckOut = new GregorianCalendar();
        checkIn.set(2020, Calendar.FEBRUARY,10);
        estCheckOut.set(2020, Calendar.FEBRUARY,27);
        actCheckOut.set(2020, Calendar.FEBRUARY,29);

        CampSite d = new Tent("Test Name", checkIn, estCheckOut,actCheckOut,4);
        assertEquals(230, d.getCost(), 0.0);
    }
}
