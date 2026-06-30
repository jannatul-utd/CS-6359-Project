// New class (extracted from Movie): encapsulates CHILDRENS pricing logic.
// Named constants replace magic numbers 1.5, 3 from the original switch block.
public class ChildrensMovie extends Movie {

    private static final double BASE_PRICE  = 1.5;
    private static final int    BASE_PERIOD = 3;
    private static final double EXTRA_RATE  = 1.5;

    public ChildrensMovie(String title) {
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
