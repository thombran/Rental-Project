package Project2;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Tent extends CampSite {

    private int numberOfTenters;

    public Tent() {
    }

    public Tent(String guestName,
                GregorianCalendar checkIn,
                GregorianCalendar estimatedCheckOut,
                GregorianCalendar actualCheckOut,
                int numberOfTenters) {
        super(guestName, checkIn, estimatedCheckOut, actualCheckOut);
        this.numberOfTenters = numberOfTenters;
    }

    public int getNumberOfTenters() {
        return numberOfTenters;
    }

    public void setNumberOfTenters(int numberOfTenters) {
        this.numberOfTenters = numberOfTenters;
    }

    @Override
    public double getCost() {
        double cost = 0;
        if(actualCheckOut != null) {
            if (actualCheckOut.compareTo(estimatedCheckOut) < 0) {
                GregorianCalendar gTemp = (GregorianCalendar) actualCheckOut.clone();
                while (gTemp.after(checkIn)) {
                    cost += 10;
                    gTemp.add(Calendar.DATE, -1);
                }
                return cost;
            }
        }
        // makes a copy... why is that important?
        GregorianCalendar gTemp = (GregorianCalendar) estimatedCheckOut.clone();
        while (gTemp.after(checkIn)) {
            cost += 10;
            gTemp.add(Calendar.DATE, -1);  // add/sub a day from gTemp.
       }
        if(actualCheckOut != null) {
            GregorianCalendar temp = (GregorianCalendar) actualCheckOut.clone();
            while(temp.after(estimatedCheckOut)) {
                cost += 30;
                temp.add(Calendar.DATE, -1);
            }
        }

        return cost;
    }

    @Override
    public String toString() {
        return "TentOnly{" +
                "numberOfTenters=" + numberOfTenters +
                super.toString() +
                '}';
    }

}
