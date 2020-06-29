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



    @Test
    public void testListModel(){
        ListModel l = new ListModel();
        assertSame(l.getDisplay(), ScreenDisplay.CurrentParkStatus); //TODO What else/how do we test the rest of this constructor?
    }

    @Test
    public void testDisplay(){
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        assertSame(l.getDisplay(), ScreenDisplay.CurrentParkStatus);
    }

    @Test
    public void testUpdateScreenCheckoutGuest() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CheckOutGuest);
        assertSame(l.getDisplay(), ScreenDisplay.CheckOutGuest);
        assertSame("RV5", l.getValueAt(0, 0));
    }

    @Test
    public void testUpdateScreenExceedsCost() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.ExceedsCost);
        assertSame(l.getDisplay(), ScreenDisplay.ExceedsCost);
        assertSame("RV1", l.getValueAt(0, 0));
        assertEquals(3700.0,l.getValueAt(0,1));
    }

    @Test
    public void testGetColumnNameCurrentParkStatus() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        assertSame("Guest Name", l.getColumnName(0));
        assertSame("Est. Cost", l.getColumnName(1));
        assertSame("Check in Date", l.getColumnName(2));
        assertSame("EST. Check out Date ", l.getColumnName(3));
        assertSame("Max Power (watts)", l.getColumnName(4));
        assertSame("Num of Tenters", l.getColumnName(5));
    }

    @Test
    public void testGetColumnNameCheckOutGuest() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CheckOutGuest);
        assertSame("Guest Name", l.getColumnName(0));
        assertSame("Check in Date", l.getColumnName(1));
        assertSame("ACTUAL Check out Date ", l.getColumnName(2));
        assertSame("Estimated Checkout", l.getColumnName(3));
        assertSame(" Final Cost", l.getColumnName(4));
    }

    @Test
    public void testGetColumnNameExceedsCost() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.ExceedsCost);
        assertSame("Guest Name", l.getColumnName(0));
        assertSame("Est. Cost", l.getColumnName(1));
        assertSame("Check in Date", l.getColumnName(2));
        assertSame("EST. Check out Date ", l.getColumnName(3));
    }

    @Test
    public void testGetColumnNameDefaultCase() {
        ListModel l = new ListModel();
        try {
            l.setDisplay(ScreenDisplay.Default);
        }
        catch (RuntimeException e) {
            Assert.assertEquals(l.getColumnName(1), "Est. Cost");
        }
    }

    @Test
    public void testGetColumnCountCurrentParkStatus() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        assertEquals(6, l.getColumnCount());
    }

    @Test
    public void testGetColumnCountCheckOutGuest() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CheckOutGuest);
        assertEquals(5, l.getColumnCount());
    }

    @Test
    public void testGetColumnCountExceedsCost() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.ExceedsCost);
        assertEquals(4, l.getColumnCount());
    }

    @Test
    public void testGetColumnCountDefaultCase() {
        ListModel l = new ListModel();
        try {
            l.setDisplay(ScreenDisplay.Default);
        }
        catch (RuntimeException e) {
            Assert.assertEquals(6,l.getColumnCount());
        }
    }

    @Test
    public void testGetRowCount() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        Assert.assertEquals(8, l.getRowCount());
        l.setDisplay(ScreenDisplay.CheckOutGuest);
        Assert.assertEquals(4, l.getRowCount());
    }

    @Test
    public void testGetValueAtCurrentParkStatus(){
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        Assert.assertEquals("12/20/2019", l.getValueAt(0,2));
    }

    @Test
    public void testGetValueAtDefaultCase() {
        ListModel l = new ListModel();
        try {
            l.setDisplay(ScreenDisplay.Default);
        }
        catch (RuntimeException e) {
            Assert.assertEquals(l.getValueAt(0, 0), "RV1");
        }
    }

    @Test
    public void testCurrentParkScreenCase0() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        assertSame("RV2", l.getValueAt(1, 0));
    }

    @Test
    public void testCurrentParkScreenCase1() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        Assert.assertEquals(50.0, l.getValueAt(6,1));
    }

    @Test
    public void testCurrentParkScreenCase3NullValue() throws ParseException {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        l.get(0).estimatedCheckOut = null;
        assertSame("-", l.getValueAt(0, 3));
    }

    @Test
    public void testCurrentParkScreenCase3() throws ParseException {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        Assert.assertEquals("06/22/2020", l.getValueAt(0, 3));
    }

    @Test
    public void testCurrentParkScreenCase4RV() throws ParseException {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        Assert.assertEquals(1000,l.getValueAt(0,4));
    }

    @Test
    public void testCurrentParkScreenCase5RV() throws ParseException {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        Assert.assertEquals("",l.getValueAt(0,5));
    }

    @Test
    public void testCurrentParkScreenCase4Tent() throws ParseException {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        Assert.assertEquals(4,l.getValueAt(4,5));
    }

    @Test
    public void testCurrentParkScreenCase5Tent() throws ParseException {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        Assert.assertEquals("",l.getValueAt(4,4));
    }

    @Test (expected = RuntimeException.class)
    public void testCurrentParkScreenOutOfBounds() throws ParseException {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        l.getValueAt(10,10);
    }

    @Test
    public void testCheckOutScreenCase0() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CheckOutGuest);
        Assert.assertEquals("RV5",l.getValueAt(0,0));
    }
    @Test
    public void testCheckOutScreenCase1() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CheckOutGuest);
        Assert.assertEquals("07/20/2020",l.getValueAt(0,1));
    }
    @Test
    public void testCheckOutScreenCase2() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CheckOutGuest);
        Assert.assertEquals("07/26/2020",l.getValueAt(0,2));
    }
    @Test
    public void testCheckOutScreenCase3() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CheckOutGuest);
        Assert.assertEquals("07/25/2020",l.getValueAt(0,3));
    }
    @Test
    public void testCheckOutScreenCase4() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CheckOutGuest);
        Assert.assertEquals(160.0,l.getValueAt(0,4));
    }
    @Test (expected = RuntimeException.class)
    public void testCheckOutScreenCaseDefault() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.CheckOutGuest);
        Assert.assertEquals("RV5",l.getValueAt(0,6));
    }

    @Test
    public void testExceedsScreenCase0() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.ExceedsCost);
        Assert.assertEquals("RV1",l.getValueAt(0,0));
    }

    @Test
    public void testExceedsScreenCase1() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.ExceedsCost);
        Assert.assertEquals(1850.0,l.getValueAt(1,1));
    }

    @Test
    public void testExceedsScreenCase2() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.ExceedsCost);
        Assert.assertEquals("12/20/2019",l.getValueAt(0,2));
    }

    @Test
    public void testExceedsScreenCase3() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.ExceedsCost);
        Assert.assertEquals("06/22/2020",l.getValueAt(0,3));
    }

    @Test
    public void testExceedsScreenCase3Null() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.ExceedsCost);
        l.get(0).estimatedCheckOut = null;
        Assert.assertEquals("-",l.getValueAt(0,3));
    }

    @Test (expected = RuntimeException.class)
    public void testExceedsScreenCaseDefault() {
        ListModel l = new ListModel();
        l.setDisplay(ScreenDisplay.ExceedsCost);
        Assert.assertEquals("RV1",l.getValueAt(0,5));
    }

    @Test
    public void testUpDateAcessor() throws ParseException { //TODO What is this method even doing? Test still needs work
        ListModel l = new ListModel();
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date d1 = formatter.parse("12/20/2019");
        g1.setTime(d1);
        Date d2 = formatter.parse("6/22/2020");
        g2.setTime(d2);
        RV r = new RV("Test RV",g1,g2,g2,1500);
        l.upDate(0,r);
        Assert.assertEquals("Test RV",l.getValueAt(0,0));
    }

    @Test
    public void testSaveAndLoadDatabase() throws ParseException {
        ListModel l = new ListModel();
        ListModel l1 = new ListModel();
        l.setDisplay(ScreenDisplay.CheckOutGuest);
        l1.setDisplay(ScreenDisplay.CheckOutGuest);
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date d1 = formatter.parse("12/20/2019");
        g1.setTime(d1);
        Date d2 = formatter.parse("6/22/2020");
        g2.setTime(d2);
        RV r = new RV("Test RV",g1,g2,g2,1500);
        l.add(r);
        l.saveDatabase("Test DB");
        l1.loadDatabase("Test DB");
        Assert.assertEquals(l.getValueAt(4, 0), l1.getValueAt(4, 0));
    }

    @Test (expected = RuntimeException.class)
    public void testSaveDatabaseException() {
        ListModel l = new ListModel();
        l.saveDatabase("!@()&$(#*$");
    }

    @Test (expected = RuntimeException.class)
    public void testLoadDatabaseException() {
        ListModel l = new ListModel();
        l.saveDatabase("Test");
        l.loadDatabase(")#(@!#&*(#");
    }
}
