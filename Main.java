// New class (HW2): entry point that exercises statement() and xmlStatement().
public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer("John Smith");
        customer.addRental(new Rental(new RegularMovie("The Matrix"), 3));
        customer.addRental(new Rental(new NewReleaseMovie("Independence Day"), 2));
        customer.addRental(new Rental(new ChildrensMovie("Toy Story"), 5));

        System.out.println("=== Text Statement ===");
        System.out.println(customer.statement());

        System.out.println("\n=== XML Statement ===");
        System.out.println(customer.xmlStatement());
    }
}
