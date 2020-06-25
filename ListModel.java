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

public class ListModel extends AbstractTableModel {

    private ArrayList<CampSite> listCampSites;
    private ArrayList<CampSite> fileredListCampSites;

    private ScreenDisplay display = ScreenDisplay.CurrentParkStatus;

    private String[] columnNamesCurrentPark = {"Guest Name", "Est. Cost",
            "Check in Date", "EST. Check out Date ", "Max Power (watts)", "Num of Tenters"};

    private String[] columnNamesforCheckouts = {"Guest Name",
            "Check in Date", "ACTUAL Check out Date ", "Estimated Checkout", " Final Cost"};

    private String[] columnNamesExceedScreen = {"Guest Name", "Est. Cost",
            "Check in Date", "EST. Check out Date ",};

    private DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    public ListModel() {
        display = ScreenDisplay.CurrentParkStatus;
        listCampSites = new ArrayList<>();
        fileredListCampSites = new ArrayList<>();
        UpdateScreen();
        createList();
    }

    public void setDisplay(ScreenDisplay selected) {
        display = selected;
        UpdateScreen();
    }

    private void UpdateScreen() {
        switch (display) {
            case CurrentParkStatus:
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream()
                        .filter(n -> n.actualCheckOut == null).collect(Collectors.toList());

                // Note: This uses Lambda function
                Collections.sort(fileredListCampSites, (n1, n2) -> n1.getGuestName().compareTo(n2.guestName));
                break;

            case CheckOutGuest:
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream().
                        filter(n -> n.getActualCheckOut() != null).collect(Collectors.toList());

                // Note: This uses an anonymous class.
                Collections.sort(fileredListCampSites, new Comparator<CampSite>() {
                    @Override
                    public int compare(CampSite n1, CampSite n2) {
                        return n1.getGuestName().compareTo(n2.guestName);
                    }
                });

                break;

            case ExceedsCost:
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream()
                        .filter(n -> n.actualCheckOut == null)
                        .filter(n -> n.getClass() == RV.class || n.getClass() == Tent.class)
                        .filter(RV ->(RV.getCost() > 500) || RV.getClass() == Tent.class && RV.getCost() > 250)
                        .collect(Collectors.toList());
                        Collections.sort(fileredListCampSites, (n2, n1) -> Double.compare(n1.getCost(),n2.getCost()));

                break;
            default:
                throw new RuntimeException("upDate is in undefined state: " + display);
        }
        fireTableStructureChanged();
    }

    @Override
    public String getColumnName(int col) {
        switch (display) {
            case CurrentParkStatus:
                return columnNamesCurrentPark[col];
            case CheckOutGuest:
                return columnNamesforCheckouts[col];
            case ExceedsCost:
                return columnNamesExceedScreen[col];
            default:
                return columnNamesCurrentPark[col];
        }
    }

    @Override
    public int getColumnCount() {
        switch (display) {
            case CurrentParkStatus:
                return columnNamesCurrentPark.length;
            case CheckOutGuest:
                return columnNamesforCheckouts.length;
            case ExceedsCost:
                return columnNamesExceedScreen.length;
            default:
                return columnNamesCurrentPark.length;
        }
    }

    @Override
    public int getRowCount() {
        return fileredListCampSites.size();     // returns number of items in the arraylist
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (display) {
            case CurrentParkStatus:
                return currentParkScreen(row, col);
            case CheckOutGuest:
                return checkOutScreen(row, col);
            case ExceedsCost:
                return exceedsScreen(row, col);
            default:
                return currentParkScreen(row, col);
        }
    }

    private Object currentParkScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.get(row).getCost());

            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.getTime()));

            case 3:
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-";

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime()));

            case 4:
            case 5:
                if (fileredListCampSites.get(row) instanceof RV)
                    if (col == 4)
                        return (((RV) fileredListCampSites.get(row)).getPower());
                    else
                        return "";

                else {
                    if (col == 5)
                        return (((Tent) fileredListCampSites.get(row)).
                                getNumberOfTenters());
                    else
                        return "";
                }
            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }

    private Object checkOutScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (formatter.format(fileredListCampSites.get(row).checkIn.
                        getTime()));

            case 2:
                return (formatter.format(fileredListCampSites.get(row).actualCheckOut.
                        getTime()));

            case 3:
                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime()));

            case 4:
                return (fileredListCampSites.
                        get(row).getCost());

            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }

    private Object exceedsScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.get(row).getCost());

            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.getTime()));

            case 3:
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-";

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime()));


            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }


    public void add(CampSite a) {
        listCampSites.add(a);
        UpdateScreen();
    }

    public CampSite get(int i) {
        return fileredListCampSites.get(i);
    }

    public void upDate(int index, CampSite unit) {
        UpdateScreen();
    }

    public void saveDatabase(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            System.out.println(listCampSites.toString());
            os.writeObject(listCampSites);
            os.close();
        } catch (IOException ex) {
            throw new RuntimeException("Saving problem! " + display);
        }
    }

    public void loadDatabase(String filename) {
        listCampSites.clear();

        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream is = new ObjectInputStream(fis);

            listCampSites = (ArrayList<CampSite>) is.readObject();
            UpdateScreen();
            is.close();
        } catch (Exception ex) {
            throw new RuntimeException("Loading problem: " + display);
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
