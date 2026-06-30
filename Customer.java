
import java.util.List;
import java.util.ArrayList;

public class Customer {

    private String name;
    private List<Rental> rentals; 
    
    public Customer (String name) {
        this.name = name;
        this.rentals = new ArrayList<> ();
    }
    
    public void addRental(Rental rentals) {
        this.rentals.add(rentals);
    }
    
    public String getName() {
        return name;
    }
    public List<Rental> getRentals(){
        return rentals;
    }
    // public String statement() {
    
    //     double      totalAmount          = 0;
    //     int         frequentRenterPoints = 0;
    //     Enumeration rentals              = _rentals.elements();
    //     String      result               = "Rental Record for " + getName() + "\n";
        
    // }
    public String xmlStatement(){
        return new XmlStatement().generate(this);
    }
    public double getTotalCharge() {
    double total = 0;
    for (Rental rental : rentals){
        total += rental.getCharge();
    }
    return total;
}

public int getTotalFrequentRenterPoints(){
    int total = 0;
    for (Rental rental : rentals){
        total += rental.getFrequentRenterPoints();
    }
    return total;
}



}


