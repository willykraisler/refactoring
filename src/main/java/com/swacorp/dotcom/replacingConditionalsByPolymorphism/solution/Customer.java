package com.swacorp.dotcom.replacingConditionalsByPolymorphism.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Changing Temps by queries.
 */
public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer (String name){
        this.name = name;
    }
    public String getName (){
        return name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    //Unfortunately, Java 8 doesn't support to reuse a stream this an option to avoid
    // possible exception to call several times anyway It's a lazy traversal it doesn't matter
    private Supplier<Stream<Rental>> streamSupplierRentals = () -> rentals.stream();

    //Avoid to use temp variables as possible as you can replace by query methods
    //ASCII version
    public String statement() {
        //It introduced three bugs with the new change figure out which is it ?
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        result.append(printRentalsList());
        result.append("Amount owed is " + String.valueOf(getTotalCharge()) + "\n");
        result.append("You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points");
        return result.toString();
    }

    //ADVANTAGE -> By extracting the calculations I can create the htmlStatement
    //HTML version
    public String htmlStatement() {
        StringBuilder result = new StringBuilder("<h1>Rental record for <b>" + getName() + "</b></h1>\n");
        result.append(printRentalsList());
        result.append("<p>Amount owed is <b>" + String.valueOf(getTotalCharge()) + "</b></p>\n");
        result.append("<p>You earned <b>" + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points</b></p>");
        return result.toString();
    }

    private String printRentalsList() {
        return rentals.stream()
                .map(r -> appendRentalInfo(r))
                .reduce("",String::concat);
    }

    private double getTotalCharge() {
        return rentals.stream().parallel()
                .map(Rental::getCharge)
                .reduce(0.0,Double::sum);
    }

    private int getTotalFrequentRenterPoints() {
        return rentals.stream().parallel()
                .map(Rental::getFrequentRenterPoints)
                .reduce(0,Integer::sum);
    }

    private String appendRentalInfo(Rental r) {
        return "\t" + r.getMovie().getTitle() + "\t" + String.valueOf(r.getCharge()) + "\n";
    }

    public static void main(String[] args) {
        Customer customer = new Customer("Pancho");
        customer.addRental(new Rental(new Movie("Avenger", 1),4 ));
        System.out.println(customer.statement());
    }
}