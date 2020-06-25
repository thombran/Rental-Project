package Project2;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RV extends CampSite {

    private int power;

    public RV() {
    }

    public RV(String guestName, GregorianCalendar checkIn, GregorianCalendar estimatedCheckOut, GregorianCalendar actualCheckOut, int power) {
        super(guestName, checkIn, estimatedCheckOut, actualCheckOut);
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public double getCost() {
        double cost = 0;
        if(actualCheckOut != null) {
            if (actualCheckOut.compareTo(estimatedCheckOut) < 0) {
                GregorianCalendar gTemp = (GregorianCalendar) actualCheckOut.clone();
                while (gTemp.after(checkIn)) {
                    cost += 20;
                    gTemp.add(Calendar.DATE, -1);
                }
                return cost;
            }
        }
        GregorianCalendar gTemp = (GregorianCalendar) estimatedCheckOut.clone();
        while (gTemp.after(checkIn)) {
            cost += 20;
            gTemp.add(Calendar.DATE, -1);  // add/sub a day from gTemp.
        }
        if (actualCheckOut != null) {
            GregorianCalendar temp = (GregorianCalendar) actualCheckOut.clone();
            while (temp.after(estimatedCheckOut)) {
                cost += 60;
                temp.add(Calendar.DATE, -1);
            }
        }
        return cost;
    }

    @Override
    public String toString() {
        return "RV{" +
                "power=" + power +
                super.toString() +
                '}';
    }
}