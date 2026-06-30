// Rental now has behavior, not just data.
// calculateCharge() and getFrequentRenterPoints() were moved here from Customer.statement()
// so that the logic lives with the object that owns the relevant data.
public class Rental {

    // Renamed: _movie -> movie, _daysRented -> daysRented (camelCase, no underscore prefix)
    private Movie movie;
    private int   daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie       = movie;
        this.daysRented  = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    // Moved from Customer.statement(): delegates to the movie's own pricing rule.
    public double calculateCharge() {
        return movie.getCharge(daysRented);
    }

    // Moved from Customer.statement(): delegates to the movie's own points rule.
    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(daysRented);
    }
}
