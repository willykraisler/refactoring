package com.swacorp.dotcom.separationOfConcerns.solution;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Method decomposing
 */
public class Customer {

    private String name;
    private Vector<Rental> rentals = new Vector();

    public Customer (String name){
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.addElement(arg);
    }

    public String getName (){
        return name;
    }

    //Avoid casting as you can change it by generics
    public String statement() {

        double totalAmount = 0;
        int frequentRenterPoints = 0;

        Enumeration<Rental> enumRentals = rentals.elements();

        String result = "Rental Record for " + getName() + "\n";

        while (enumRentals.hasMoreElements()) {

            Rental rental = enumRentals.nextElement();

            frequentRenterPoints += rental.getFrequentRenterPoints();

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

    public static void main(String[] args) {
        Customer customer = new Customer("Pancho");
        customer.addRental(new Rental(new Movie("Avenger", 1),4 ));
        System.out.println(customer.statement());
    }
}
