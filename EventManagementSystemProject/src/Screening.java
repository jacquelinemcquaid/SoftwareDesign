import java.time.LocalDate;
import java.util.ArrayList;

//Type of Event
public class Screening extends AbstractEvent {

    private String rating;

    /**
     * Constructor
     * @param pName
     *      Name of the Screening
     * @param pLocation
     *      Location of the Screening
     * @param pDate
     *      Date of the Screening
     * @param pPerPersonTicketPrice
     *      Per person price of the Screening
     * @param pTotalTickets
     *      Total tickets for the screening
     * @param pRating
     *      String representing the rating
     */
    public Screening(String pName, Location pLocation, LocalDate pDate, double pPerPersonTicketPrice, int pTotalTickets, String pRating) {

        //Calling the super constructor
        //Super class checks inputs
        super(pName, pLocation, pDate, pPerPersonTicketPrice, pTotalTickets);

        //Creating a list with the possible ratings
        ArrayList<String> possibleRatings = new ArrayList<>();
        possibleRatings.add("G");
        possibleRatings.add("PG");
        possibleRatings.add("PG-13");
        possibleRatings.add("R");

        //Using an assert statement to ensure the input rating is valid
        assert possibleRatings.contains(pRating);

        this.rating = pRating;
    }

    /**
     * Constructor for comingsoon event
     * @param pName
     *      Name of Screening
     * @param pDate
     *      Date of Screening
     * @param pRating
     *      Rating of Screening
     */
    public Screening(String pName, LocalDate pDate, String pRating) {

        //Calling the super constructor
        super(pName, pDate);

        //Creating a list with the possible ratings
        ArrayList<String> possibleRatings = new ArrayList<>();
        possibleRatings.add("G");
        possibleRatings.add("PG");
        possibleRatings.add("PG-13");
        possibleRatings.add("R");

        //Using an assert statement to ensure the input rating is valid
        assert possibleRatings.contains(pRating);

        this.rating = pRating;
    }


    /**
     * Method to get rating
     * @return
     *      Returning the rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * Method to set rating
     * @param rating
     *      Takes in a string representing the rating
     *      and sets the rating of the Screening if it is valid
     */
    public void setRating(String rating) {

        //Input check
        assert rating != null;

        //Creating a list with the possible ratings
        ArrayList<String> possibleRatings = new ArrayList<>();
        possibleRatings.add("G");
        possibleRatings.add("PG");
        possibleRatings.add("PG-13");
        possibleRatings.add("R");

        //Using an assert statement to ensure the input rating is valid
        assert possibleRatings.contains(rating);

        this.rating = rating;
    }

    /**
     * This method accepts a visitor to implement the visitor design pattern
     * @param visitor
     *      Takes as input a visitor object and implements the visit method
     */
    @Override
    public double acceptVisitor(Visitor visitor) {

        assert visitor != null;

        return visitor.visitScreening(this);
    }
}
