import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

//Created an abstract class to re-use common code in subclasses
public abstract class AbstractEvent implements Event {

    //All the following fields are immutable as indicated by pdf instructions
    final private String name;
    final private Location location;
    final private LocalDate date;
    final private double perPersonTicketPrice;
    final private int totalTickets;

    /**
     * Constructor with all information provided
     * @param pName
     *      Name of event
     * @param pLocation
     *      Location of event
     * @param pDate
     *      Date of event
     * @param pPerPersonTicketPrice
     *      Per Ticket Price of the event
     * @param pTotalTickets
     *      Total number of tickets for the event
     */
    public AbstractEvent(String pName, Location pLocation, LocalDate pDate, double pPerPersonTicketPrice, int pTotalTickets) {

        //Ensuring the inputs are valid
        assert pName != null;
        assert pLocation != null;
        assert pDate != null;
        assert pPerPersonTicketPrice >= 0.0;
        assert  pTotalTickets >= 0;

        this.name = pName;
        this.location = pLocation;
        this.date = pDate;
        this.perPersonTicketPrice = pPerPersonTicketPrice;
        this.totalTickets = pTotalTickets;

    }

    /**
     * Constructor for coming soon events
     * Location, per person price, and ticket number are not provided
     * @param pName
     *      Name is provided
     * @param pDate
     *      Date is provided
     */
    public AbstractEvent(String pName, LocalDate pDate){

        assert pName != null;
        assert pDate != null;

        this.name = pName;
        this.date = pDate;
        this.location = Location.COMINGSOON;
        this.totalTickets = 0;
        this.perPersonTicketPrice = 0;

    }

    /**
     * Method to getName
     * @return
     *      Name of event
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Method to getLocation
     * @return
     *      Location of event
     */
    @Override
    public Location getLocation() {

        return this.location;
    }

    /**
     * Method to getDate
     * @return
     *      Date of event
     */
    @Override
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Method to getPerPersonPrice
     * @return
     *      Per-person price of event
     */
    @Override
    public double getPrice() {
        return this.perPersonTicketPrice;
    }

    /**
     * Method to getNumTickets
     * @return
     *      Total number of tickets for the event
     */
    @Override
    public int getNumTickets() {
        return this.totalTickets;
    }


}
