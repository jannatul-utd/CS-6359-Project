public class XmlStatement{
    public String generate(Customer customer){
        StringBuilder result = new StringBuilder();
        result.append("");
        result.append(customer.getName());
                result.append("\n");
        for (Rental rental : customer.getRentals())
        {
            result.append(rental.getMovie().getTitle());
        result.append("\n");
            result.append(rental.getCharge());
                    result.append("\n");

        }
        result.append(customer.getTotalCharge());
        result.append("\n");
        result.append(customer.getTotalFrequentRenterPoints());
                result.append("\n");
        return result.toString();
    }
}