public class Rental { //data class: this class is just a data holder and does not have any behavior, 
    private Movie _movie;
    private int   _daysRented;
    
    public Rental(Movie movie, int daysRented) {
        _movie      = movie;
        _daysRented = daysRented;
    }
    
    public int getDaysRented() {
        return _daysRented;
    }
    
    public Movie getMovie() {
        return _movie;
    }
    //we need a function to calculate the amount for each rental, because the calculation logic is in the customer class which is not the responsibility of the customer class, it should be in the rental class
}