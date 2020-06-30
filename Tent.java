package Project2;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**********************************************************************************
 * The Tent class contains functions to calculate costs, create Tent objects, dictates
 * the toString method, and includes getters and setters.
 *
 * @author Brandon and Dylan
 * @version 1.0.0 Summer 2020
 ************************************************************************************/
public class Tent extends CampSite {
    /** Integer used to count the number of tenters on campsite */
    private int numberOfTenters;


    /******************************************************************
     * Default constructor that sets up a Tent object with no parameters.
     *****************************************************************/
    public Tent() {
    }


    /******************************************************************
     * A constructor that accepts 5 parameters to represent a Tent object
     * @param guestName Name of the customer
     * @param checkIn The date of the check in
     * @param estimatedCheckOut Estimated date of check out
     * @param actualCheckOut Actual date customer checked out
     * @param numberOfTenters Amount of people on tent site
     ******************************************************************/
    public Tent(String guestName,
                GregorianCalendar checkIn,
                GregorianCalendar estimatedCheckOut,
                GregorianCalendar actualCheckOut,
                int numberOfTenters) {
        super(guestName, checkIn, estimatedCheckOut, actualCheckOut);
        this.numberOfTenters = numberOfTenters;
    }


    /******************************************************************
     * Getter method for tenters
     * @return Returns the number of tenters on site
     ******************************************************************/
    public int getNumberOfTenters() {
        return numberOfTenters;
    }


    /******************************************************************
     * Sets the Tent object with specified number of tenters
     * @param numberOfTenters number of tenters going to be on site
     * @return none
     ******************************************************************/
    public void setNumberOfTenters(int numberOfTenters) {
        this.numberOfTenters = numberOfTenters;
    }


    /******************************************************************
     * Gets the cost of the stay for the tenters
     * @return returns a double cost for the price of the stay
     *****************************************************************/
    @Override
    public double getCost() {
        double cost = 0;
        if(actualCheckOut != null) {
            if (actualCheckOut.compareTo(estimatedCheckOut) < 0) {
                GregorianCalendar gTemp = (GregorianCalendar) actualCheckOut.clone();
                while (gTemp.after(checkIn)) {
                    cost += 10; //10 dollars per day
                    gTemp.add(Calendar.DATE, -1);
                }
                return cost;
            }
        }

        GregorianCalendar gTemp = (GregorianCalendar) estimatedCheckOut.clone();
        while (gTemp.after(checkIn)) {
            cost += 10;
            gTemp.add(Calendar.DATE, -1);  // add/sub a day from gTemp.
        }
        if(actualCheckOut != null) {  //If the actual checkout date is not decided
            GregorianCalendar temp = (GregorianCalendar) actualCheckOut.clone(); //Cloned so actual date doesnt change
            while(temp.after(estimatedCheckOut)) {
                cost += 30; //20 extra per day plus the normal 10
                temp.add(Calendar.DATE, -1);
            }
        }

        return cost;
    }


    /******************************************************************
     * Overrides the default toString method to print out the tenters
     * information and goes into the super class CampSite for the
     * rest of the toString
     * @return Returns a string in the form of the aforementioned format
     *****************************************************************/
    @Override
    public String toString() {
        return "TentOnly{" +
                "numberOfTenters=" + numberOfTenters +
                super.toString() +
                '}';
    }

}
