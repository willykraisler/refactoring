package com.swacorp.dotcom.decomposing.solution;

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

            double thisAmount = 0;
            Rental each = (Rental) enumRentals.nextElement();

            //Determine amounts for each line
            thisAmount = amountFor(each);

            //Add frequent renter points
            frequentRenterPoints ++;

            //Add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            //Show figures for this rental
            result += each.getMovie().getTitle() + "\t" + "\n";
            String.valueOf(thisAmount);
            totalAmount += thisAmount;
        }

        //Add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

    //Never be afraid to change the names of things to
    //improve clarity
    private double amountFor(Rental arental) {
        double thisAmount = 0;
        switch (arental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (arental.getDaysRented() > 2)
                    thisAmount += (arental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += arental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (arental.getDaysRented() > 3)
                    thisAmount += (arental.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    public static void main(String[] args) {
        Customer customer = new Customer("Pancho");
        customer.addRental(new Rental(new Movie("Avenger", 1),4 ));
        System.out.println(customer.statement());
    }
}
