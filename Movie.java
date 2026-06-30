// Abstract base class for all movie types.
// Using a class hierarchy instead of int price-code constants removes Primitive Obsession
// and eliminates the switch statements scattered across Customer and Rental.
public abstract class Movie {

    // Renamed: _title -> title (camelCase, no underscore prefix)
    private String title;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // Moved from Customer.statement() switch block: each subclass owns its pricing rule.
    public abstract double getCharge(int daysRented);

    // Default: 1 point per rental. New-release overrides to award a bonus point.
    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
