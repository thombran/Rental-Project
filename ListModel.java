package Project2;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/******************************************************************
 * Program that helps camp ground owners to manage a inventory of campers
 * including tenters and rv users.
 *
 * @author Brandon and Dylan
 * @version Summer 2020
 *******************************************************************/
public class ListModel extends AbstractTableModel {
    /** Private ArrayList used to list the campsite objects */
    private ArrayList<CampSite> listCampSites;

    /** Private ArrayList used to filter Campsite objects */
    private ArrayList<CampSite> fileredListCampSites;

    /** Private Display variable to set a default ScreenDisplay to CurrentParkStatus */
    private ScreenDisplay display = ScreenDisplay.CurrentParkStatus;

    /** Private Array used to set the names of the columns for currentPark screen */
    private String[] columnNamesCurrentPark = {"Guest Name", "Est. Cost",
            "Check in Date", "EST. Check out Date ", "Max Power (watts)", "Num of Tenters"};

    /** Private Array used to set the names of the columns for the checkOuts screen */
    private String[] columnNamesforCheckouts = {"Guest Name",
            "Check in Date", "ACTUAL Check out Date ", "Estimated Checkout", " Final Cost"};

    /** Private Array used to set the names of the columns for the Exceeds screen */
    private String[] columnNamesExceedScreen = {"Guest Name", "Est. Cost",
            "Check in Date", "EST. Check out Date ",};

    /** SimpleDate formatter used to set the date to MM/dd/yyyy */
    private DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");


    /******************************************************************
     * Method used to bring up the display
     * @return Returns the default display CurrentParkStatus
     *******************************************************************/
    public ScreenDisplay getDisplay() {
        return display;
    }


    /******************************************************************
     * Method used to set the display to a default one
     * Create a new ArrayList called listCampSites
     * Create new ArrayList called fileredListCampSites
     * Update the screen to show the default display
     * Create basic list of tents and Rv objects to display
     *******************************************************************/
    public ListModel() {
        display = ScreenDisplay.CurrentParkStatus; //First screen user sees
        listCampSites = new ArrayList<>(); //Empty array list to add campsites to
        fileredListCampSites = new ArrayList<>(); //List after cost calculations and other factors
        UpdateScreen();
        createList(); //Creates list of default campsites
    }


    /******************************************************************
     * Method used to to set the display screen to selected parameter
     * @param selected Parameter used for what screen is desired
     * @return none
     *******************************************************************/
    public void setDisplay(ScreenDisplay selected) {
        display = selected;
        UpdateScreen();
    }


    /******************************************************************
     * Method used to switch the display screen to whatever one is selected
     * 3 different screens to show: CurrentParkStatus, CheckOutGuest and ExceedsCost
     * Each screen has different filtered list that is displayed
     * @throws RuntimeException if the current display is set to default
     * instead of CurrentParkStatus, CheckOutGuest or ExceedsCost
     * @return none
     *******************************************************************/
    private void UpdateScreen() {
        switch (display) {
            case CurrentParkStatus:
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream()
                        .filter(n -> n.actualCheckOut == null).collect(Collectors.toList()); //Have not checked out


                Collections.sort(fileredListCampSites, (n1, n2) -> n1.getGuestName().compareTo(n2.guestName));
                break; //Sorting the campsites alphabetically/numerically by guest name

            case CheckOutGuest:
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream().
                        filter(n -> n.getActualCheckOut() != null).collect(Collectors.toList()); //Have checked out

                // Note: This uses an anonymous class.
                Collections.sort(fileredListCampSites, new Comparator<CampSite>() {
                    @Override
                    public int compare(CampSite n1, CampSite n2) {
                        return n1.getGuestName().compareTo(n2.guestName);
                    }
                }); //New comparator to sort campsites alphabetically/numerically by guest name

                break;

            case ExceedsCost:
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream()
                        .filter(n -> n.actualCheckOut == null) //Campsite has not checked out
                        .filter(n -> n.getClass() == RV.class || n.getClass() == Tent.class) //Campsite is RV or Tent
                        .filter(RV ->(RV.getCost() > 500) || RV.getClass() == Tent.class && RV.getCost() > 250)
                        .collect(Collectors.toList()); //Total cost exceeds 500 for RVs or 250 for Tents
                Collections.sort(fileredListCampSites, (n2, n1) -> Double.compare(n1.getCost(),n2.getCost()));
                //Sorts the campsites on the current screen by total cost

                break;
            default:
                throw new RuntimeException("upDate is in undefined state: " + display); //If screen is set to default
        }
        fireTableStructureChanged();
    }


    /******************************************************************
     * Overrides current column displayed and replaces it with desired
     * integer parameter for column
     * @param col integer used to determine columns needed
     * @return String with the name col desired
     *******************************************************************/
    @Override
    public String getColumnName(int col) {
        switch (display) { //Current display
            case CurrentParkStatus:
                return columnNamesCurrentPark[col];
            case CheckOutGuest:
                return columnNamesforCheckouts[col];
            case ExceedsCost:
                return columnNamesExceedScreen[col];
            default: //If screen is set  to default
                return columnNamesCurrentPark[col];
        }
    }


    /******************************************************************
     * Method used to get the amount of columns needed for the screen display
     * @return int Returns the desired ArrayList.length for amount of columns
     * on the current screen
     *******************************************************************/
    @Override
    public int getColumnCount() {
        switch (display) { //Current display
            case CurrentParkStatus:
                return columnNamesCurrentPark.length;
            case CheckOutGuest:
                return columnNamesforCheckouts.length;
            case ExceedsCost:
                return columnNamesExceedScreen.length;
            default: //If screen is set to default
                return columnNamesCurrentPark.length;
        }
    }


    /******************************************************************
     * Overrides the other instance of getrowcount that was already in use
     * @return int number of items in the arrayList
     *******************************************************************/
    @Override
    public int getRowCount() {
        return fileredListCampSites.size();
    }


    /******************************************************************
     * Overrides the current screens row and columns with int parameters
     * for row and col
     * @param row integer used to determine rows
     * @param col integer used to determine columns
     * @return Returns the object with integer row and col inputted
     *******************************************************************/
    @Override
    public Object getValueAt(int row, int col) {
        switch (display) { //Current display
            case CurrentParkStatus:
                return currentParkScreen(row, col);
            case CheckOutGuest:
                return checkOutScreen(row, col);
            case ExceedsCost:
                return exceedsScreen(row, col);
            default: //If screen is set to default
                return currentParkScreen(row, col);
        }
    }


    /******************************************************************
     * Private method used to determine the amount of rows and columns
     * for the currentParkScreen
     * @param row integer parameter determining the amount of rows
     * @param col integer parameter determining the amount of columns
     * @throws RuntimeException if the given column number is greater than
     * 5 as there is only 6 columns on this screen
     * @return returns the currentParkScreen for the correct amount of
     * rows and columns determined by the parameters
     *******************************************************************/
    private Object currentParkScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName); //Returns sorted campsite in first column by name

            case 1:
                return (fileredListCampSites.get(row).getCost()); //Returns sorted campsite in second column by cost

            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.getTime())); //Date for this row

            case 3:
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-"; //If there is no estimated checkout program returns '-'

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime())); //Otherwise returns the estimated checkout date

            case 4:
            case 5:
                if (fileredListCampSites.get(row) instanceof RV)
                    if (col == 4)
                        return (((RV) fileredListCampSites.get(row)).getPower()); //If campsite is RV, power level ret.
                    else
                        return ""; //Otherwise if campsite is tent line is left blank

                else {
                    if (col == 5)
                        return (((Tent) fileredListCampSites.get(row)).
                                getNumberOfTenters()); //If campsite is Tent, number of tenters is returned
                    else
                        return ""; //Otherwise if campsite is RV line is left blank
                }
            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col); //If screen set to default
        }
    }


    /******************************************************************
     * Private method used to determine the amount of rows and columns
     * for the checkOutScreen
     * @param row integer parameter determining the amount of rows
     * @param col integer parameter determining the amount of columns
     * @throws RuntimeException if the given column number is greater than
     * 4 as there is only 5 columns on this screen
     * @return returns the checkOutScreen for the correct amount of
     * rows and columns determined by the parameters
     *******************************************************************/
    private Object checkOutScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (formatter.format(fileredListCampSites.get(row).checkIn. //Returns check in date for row
                        getTime()));

            case 2:
                return (formatter.format(fileredListCampSites.get(row).actualCheckOut. //Returns actual checkout for row
                        getTime()));

            case 3:
                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut. //Returns est. checkout for row
                        getTime()));

            case 4:
                return (fileredListCampSites.
                        get(row).getCost()); //Returns cost for this campsite in this row

            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col); //If screen set to default
        }
    }


    /******************************************************************
     * Private method used to determine the amount of rows and columns
     * for the exceedsScreen
     * @param row integer parameter determining the amount of rows
     * @throws RuntimeException if the given column is greater than 3 as
     * there is only 4 columns on this screen
     * @param col integer parameter determining the amount of columns
     * @return returns the exceedsScreen for the correct amount of
     * rows and columns determined by the parameters
     *******************************************************************/
    private Object exceedsScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName); //Returns guest name for this row

            case 1:
                return (fileredListCampSites.get(row).getCost()); //Returns cost for this row

            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.getTime())); //Returns check in for row

            case 3:
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-"; //If estimated checkout is not determined, returns a '-'

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime())); //Returns estimated checkout for this row


            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col); //If screen set to default
        }
    }


    /******************************************************************
     * Method used to add a new campsite to arraylist
     * Updates the Screen using the UpdateScreen method
     * @param a object to be added to the campsite arraylist
     * @return none
     *******************************************************************/
    public void add(CampSite a) {
        listCampSites.add(a);
        UpdateScreen();
    }


    /******************************************************************
     * Method to get campsite Arraylist  at desired integer
     * @param i integer to determine what arraylist to be selected
     * @return Returns an arraylist campsite at location i
     *******************************************************************/
    public CampSite get(int i) {
        return fileredListCampSites.get(i);
    }


    /******************************************************************
     * Method used to updatescreen with the integer index and specific
     * campsite object
     * @param index integer used for the index
     * @param unit Object used for choosing a Campsite
     * @return none
     *******************************************************************/
    public void upDate(int index, CampSite unit) {
        UpdateScreen();
    }


    /******************************************************************
     * Method used to save a list of campsites to be used at a later time
     * @param filename The name of the file to be saved for future use
     * @throws RuntimeException if there is a error saving the file
     * @return none
     *******************************************************************/
    public void saveDatabase(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            System.out.println(listCampSites.toString()); //Printing campsite information to file
            os.writeObject(listCampSites);
            os.close(); //Closing stream
        } catch (IOException ex) {
            throw new RuntimeException("Saving problem! " + display); //Something happens during save process
        }
    }


    /******************************************************************
     * This method is used to load a previously saved list of campsites
     * @param filename The name of the file that was saved at a earlier time
     * @throws RuntimeException    when there is a error loading filename
     * @return none
     *******************************************************************/
    public void loadDatabase(String filename) {
        listCampSites.clear(); //Clears any campsites loaded into the program

        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream is = new ObjectInputStream(fis);

            listCampSites = (ArrayList<CampSite>) is.readObject(); //Adds in campsites to ArrayList from file
            UpdateScreen();
            is.close(); //Closing stream
        } catch (Exception ex) {
            throw new RuntimeException("Loading problem: " + display); //Something happens during load process
        }
    }

    // This method is exempt from the coverage criteria
    public void createList() {
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        GregorianCalendar g6 = new GregorianCalendar();
        GregorianCalendar g7 = new GregorianCalendar();


        try {
            Date d1 = formatter.parse("12/20/2019");
            g1.setTime(d1);
            Date d2 = formatter.parse("6/22/2020");
            g2.setTime(d2);
            Date d3 = formatter.parse("7/20/2020");
            g3.setTime(d3);
            Date d4 = formatter.parse("7/25/2020");
            g4.setTime(d4);
            Date d5 = formatter.parse("1/21/2020");
            g5.setTime(d5);
            Date d6 = formatter.parse("3/29/2020");
            g6.setTime(d6);
            Date d7 = formatter.parse("7/26/2020");
            g7.setTime(d7);

            Tent tent1 = new Tent("T1", g1, g2, null, 4);
            Tent tent2 = new Tent("T2", g1, g5, null, 8);
            Tent tent3 = new Tent("T3", g3, g4, null, 8);
            Tent tent4 = new Tent("T4", g4, g7, null, 5);
            Tent tent5 = new Tent("T5", g3, g4, g7, 7);
            Tent tent6 = new Tent("T6", g3, g7, g4, 7);

            RV RV1 = new RV("RV1", g1, g2, null, 1000);
            RV RV2 = new RV("RV2",  g1, g5, null, 1000);
            RV RV3 = new RV("RV3", g3, g4, null, 2000);
            RV RV4 = new RV("RV4",  g4, g7, null, 2000);
            RV RV5 = new RV("RV5", g3, g4, g7, 1000);
            RV RV6 = new RV("RV6", g3, g7, g4, 1000);

            add(tent1);
            add(tent2);
            add(tent3);
            add(tent4);
            add(tent5);
            add(tent6);

            add(RV1);
            add(RV2);
            add(RV3);
            add(RV4);
            add(RV5);
            add(RV6);

        } catch (ParseException e) {
            throw new RuntimeException("Error in testing, creation of list");
        }
    }
}
