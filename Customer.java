import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector _rentals = new Vector(); //unsupported collection type: using Vector is not recommended, it is a legacy class and it is not thread-safe, we can use ArrayList instead
    
    public Customer (String name) { 
        _name = name;
    }
    
    public void addRental(Rental arg) { 
         _rentals.addElement(arg);
    }
    
    public String getName() {
        return _name;
    }
    
    public String statement() { 
        // long method: this method is too long and needs refactoring, 
        // also it violates the Single Responsibility Principle, 
        // also naming problem: the name of the method does not reflect what it does, also it has a lot of comments which indicates that it is doing too many things
    
        double      totalAmount          = 0;
        int         frequentRenterPoints = 0; //to get this we need to go through this function again and again which is persistant storage problem.
        Enumeration rentals              = _rentals.elements();
        String      result               = "Rental Record for " + getName() + "\n";
        
        while (rentals.hasMoreElements()) {
            
            double thisAmount = 0;
            Rental each       = (Rental) rentals.nextElement(); //each naming issue
            
            // determine amounts for each line
            switch (each.getMovie().getPriceCode()) { //switch case smell: 
                //missing default case: the switch statement does not have a default case, which can lead to unexpected behavior if an unknown price code is encountered.
                case Movie.REGULAR:
                    thisAmount += 2; //here 2 is dollar, we can call it basePrice 
                    if (each.getDaysRented() > 2) { //and here 2 is days, we can call it basePeriod , magic number 
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    //duplicate code 
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3) {
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    }
                    break;
            }
            
            // add frequent renter points
            frequentRenterPoints++;
            
            // add bonus for a two day new release rental
            //switch case issue here 
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && //message chaining , it should be in the rental class
                (each.getDaysRented() > 1)) {
                    frequentRenterPoints++;
            }
            
            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() +
                      "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        
        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) +
                  " frequent renter points";
        return result;
    }
}