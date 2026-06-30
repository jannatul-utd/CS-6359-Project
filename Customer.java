import java.util.ArrayList;
import java.util.List;

public class Customer {

    // Renamed: _name -> name (camelCase, no underscore prefix)
    // Data type replaced: Vector -> List<Rental> / ArrayList<Rental>
    private String       name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    // Renamed parameter: arg -> rental
    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    // Extracted method: sums charges across all rentals
    private double calculateTotalCharge() {
        double total = 0;
        for (Rental rental : rentals) {
            total += rental.calculateCharge();
        }
        return total;
    }

    // Extracted method: sums frequent renter points across all rentals
    private int calculateTotalFrequentRenterPoints() {
        int points = 0;
        for (Rental rental : rentals) {
            points += rental.getFrequentRenterPoints();
        }
        return points;
    }

    // Extracted method: builds one detail line per rental for the text statement
    private String buildRentalLines() {
        StringBuilder lines = new StringBuilder();
        for (Rental rental : rentals) {
            lines.append("\t")
                 .append(rental.getMovie().getTitle())
                 .append("\t")
                 .append(rental.calculateCharge())
                 .append("\n");
        }
        return lines.toString();
    }

    // Generates a plain-text rental statement for this customer.
    public String statement() {
        String result = "Rental Record for " + getName() + "\n";
        result += buildRentalLines();
        result += "Amount owed is " + calculateTotalCharge() + "\n";
        result += "You earned " + calculateTotalFrequentRenterPoints() + " frequent renter points";
        return result;
    }

    // New method (HW2): generates the same statement in XML format.
    public String xmlStatement() {
        StringBuilder xml = new StringBuilder();
        xml.append("<statement>\n");
        xml.append("  <name>").append(getName()).append("</name>\n");
        for (Rental rental : rentals) {
            xml.append("  <rental>\n");
            xml.append("    <movie>").append(rental.getMovie().getTitle()).append("</movie>\n");
            xml.append("    <daysRented>").append(rental.getDaysRented()).append("</daysRented>\n");
            xml.append("    <charge>").append(rental.calculateCharge()).append("</charge>\n");
            xml.append("  </rental>\n");
        }
        xml.append("  <totalAmount>").append(calculateTotalCharge()).append("</totalAmount>\n");
        xml.append("  <frequentRenterPoints>")
           .append(calculateTotalFrequentRenterPoints())
           .append("</frequentRenterPoints>\n");
        xml.append("</statement>");
        return xml.toString();
    }
}
