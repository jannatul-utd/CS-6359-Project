// New class (extracted from Movie): encapsulates REGULAR pricing logic.
// Named constants replace magic numbers 2, 1.5 from the original switch block.
public class RegularMovie extends Movie {

    private static final double BASE_PRICE  = 2.0;
    private static final int    BASE_PERIOD = 2;
    private static final double EXTRA_RATE  = 1.5;

    public RegularMovie(String title) {
        super(title);
    }

    @Override
    public double getCharge(int daysRented) {
        double charge = BASE_PRICE;
        if (daysRented > BASE_PERIOD) {
            charge += (daysRented - BASE_PERIOD) * EXTRA_RATE;
        }
        return charge;
    }
}
