public class Main {
    public static void main(String[] args){
        // create some movies
        // create some customers
        // create some rentals
        Movie movie1 = new Movie("title1", PriceCode.REGULAR);
        Movie movie2 = new Movie("title2", PriceCode.NEW_RELEASE);
        Movie movie3 = new Movie("title3", PriceCode.CHILDRENS);

        //
        Customer customer = new Customer("Xiaokai");

        customer.addRental(new Rental(movie1, 3));
        customer.addRental(new Rental(movie2, 2));

        customer.addRental(new Rental(movie3, 10));

        //
        System.out.println("XML statement:");
        System.out.println(customer.xmlStatement());

    }

}