// New class (extracted from Movie): encapsulates NEW_RELEASE pricing logic.
// Overrides getFrequentRenterPoints() to award bonus for 2+ day rentals,
// removing the special-case if-block that lived in Customer.statement().
public class NewReleaseMovie extends Movie {

    private static final double DAILY_RATE = 3.0;

    public NewReleaseMovie(String title) {
        super(title);
    }

    @Override
    public double getCharge(int daysRented) {
        return daysRented * DAILY_RATE;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }
}
