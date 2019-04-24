package com.swacorp.dotcom.replacingConditionalsByPolymorphism.toDo;

public class Rental {

    public final static int ONE = 1;
    public final static int TWO = 2;
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }
    public int getDaysRented() {
        return daysRented;
    }
    public Movie getMovie() {
        return movie;
    }

    //The first part of this problem is that switch statement.
    //It is a bad idea to do a switch based on an attribute of another object.
    // If you must use a switch statement, it should be on your own data, not on someone else's.
    //TODO Delegate the price calculation to the class that it has the attribute to calculate.
    // Price calculation has kind of contract figure out how to change it !
    public double getCharge() {
        double thisAmount = 0;
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (getDaysRented() > 2)
                    thisAmount += (getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (getDaysRented() > 3)
                    thisAmount += (getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    //TODO move to the natural place thinking in objects
    public int getFrequentRenterPoints() {
        if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) && getDaysRented() > 1) {
            return TWO;
        }else{
            return ONE;
        }
    }
}
