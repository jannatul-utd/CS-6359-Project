public class Movie {


    private String title;
    private PriceCode priceCode;
    

    
    public Movie(String title, PriceCode priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }
    
    public PriceCode getPriceCode() {
        return priceCode;
    }
    
    public void setPriceCode(PriceCode priceCode) {
        this.priceCode = priceCode;
    }
    
    public String getTitle() {
        return title;
    }

    public double getCharge(int daysRented){
        double charge = 0;
        switch (priceCode) {
            case REGULAR:
                charge = 2;
                if (daysRented > 2) {
                    charge += (daysRented - 2) * 1.5;
                }
                break;
            case NEW_RELEASE:
                charge = daysRented * 3;
                break;
            case CHILDRENS:
                charge += 1.5;
                if (daysRented > 3) {
                    charge = (daysRented - 3) * 1.5;
                }
            break;
        }
            return charge;

    }


public int getFrequentRenterPoints(int daysRented){
            if (priceCode == PriceCode.NEW_RELEASE && daysRented >1){
                return 2;
            }
            return 1;
    }
}