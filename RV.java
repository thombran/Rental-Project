package Project2;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**********************************************************************************
 * The RV class contains functions to calculate costs, create RV objects, dictates
 * the toString method, and includes getters and setters.
 *
 * @author Brandon and Dylan
 * @version 1.0.0 Summer 2020
 ************************************************************************************/
public class RV extends CampSite {
    /** Integer power for the amount of power the RV object will use */
    private int power;


    /******************************************************************
     * Default constructor that sets up a RV object with no parameters.
     *****************************************************************/
    public RV() {
    }


    /******************************************************************
     *  A constructor that accepts 5 parameters to represent a RV object
     * @param guestName Name of the customer
     * @param checkIn The date of the check in
     * @param estimatedCheckOut Estimated date of check out
     * @param actualCheckOut Actual date customer checked out
     * @param power Amount of power to be used by RV
     *****************************************************************/
    public RV(String guestName, GregorianCalendar checkIn, GregorianCalendar estimatedCheckOut, GregorianCalendar actualCheckOut, int power) {
        super(guestName, checkIn, estimatedCheckOut, actualCheckOut);
        this.power = power;
    }


    /******************************************************************
     * Getter method used to get the amount of power
     * @return power Returns the amount of power the RV campsite will be
     * using
     ******************************************************************/
    public int getPower() {
        return power;
    }


    /******************************************************************
     * Sets the RV object with specified number power to be used
     * @param power the number of power the RV will be using
     * @return none
     ******************************************************************/
    public void setPower(int power) {
        this.power = power;
    }


    /******************************************************************
     * Gets the cost of the stay for the RV campsite
     * @return returns a double cost for the price of the stay
     *****************************************************************/
    @Override
    public double getCost() {
        double cost = 0;
        if(actualCheckOut != null) {
            if (actualCheckOut.compareTo(estimatedCheckOut) < 0) {
                GregorianCalendar gTemp = (GregorianCalendar) actualCheckOut.clone();
                while (gTemp.after(checkIn)) {
                    cost += 20; //20 dollars per day
                    gTemp.add(Calendar.DATE, -1); //Brings actual checkout closer to check in by 1 day
                }

                return cost;
            }
        }

        GregorianCalendar gTemp = (GregorianCalendar) estimatedCheckOut.clone(); //Copy so real date doesn't change
        while (gTemp.after(checkIn)) {
            cost += 20; //20 dollars per day
            gTemp.add(Calendar.DATE, -1);  // add/sub a day from gTemp.
        }
        if (actualCheckOut != null) {
            GregorianCalendar temp = (GregorianCalendar) actualCheckOut.clone();
            while (temp.after(estimatedCheckOut)) {
                cost += 60; //40 extra per day, plus the normal 20 per day
                temp.add(Calendar.DATE, -1);
            }
        }
        return cost;
    }


    /***********************************************************************
     * Overrides the default toString method to print out the RV information
     * and goes into the super class CampSite for the rest of the toString
     * @return Returns a string in the form of the aforementioned format
     ***********************************************************************/
    @Override
    public String toString() {
        return "RV{" +
                "power=" + power +
                super.toString() +
                '}';
    }
}
