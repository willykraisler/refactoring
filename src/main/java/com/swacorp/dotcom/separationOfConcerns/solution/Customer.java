package com.swacorp.dotcom.separationOfConcerns.solution;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Method decomposing
 */
public class Customer {

    private String name;
    private Vector rentals = new Vector();

    public Customer (String name){
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.addElement(arg);
    }

    public String getName (){
        return name;
    }

    public String statement() {

        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration enumRentals = rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while (enumRentals.hasMoreElements()) {

            Rental rental = (Rental) enumRentals.nextElement();

            //Add frequent renter points
            frequentRenterPoints ++;

            //Add bonus for a two day new release rental
            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            //Show figures for this rental
            result += rental.getMovie().getTitle() + "\t" + "\n";
            String.valueOf(rental.getCharge());
            totalAmount += rental.getCharge();
        }

        //Add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }


}
