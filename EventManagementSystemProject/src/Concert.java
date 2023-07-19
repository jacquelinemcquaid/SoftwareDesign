import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

//Type of Event
public class Concert extends AbstractEvent {

    private String artist;
    private ArrayList<String> VIPs;

    /**
     * Constructor
     * @param pName
     *      Name of the concert
     * @param pLocation
     *      Location of the concert
     * @param pDate
     *      Date of the concert
     * @param pPerPersonTicketPrice
     *      Per person price of the concert
     * @param pTotalTickets
     *      Total tickets for the concert
     * @param artist
     *      Concert Artist name
     * @param pVIPs
     *      List of VIPs attending the concert
     */
    public Concert(String pName, Location pLocation, LocalDate pDate, double pPerPersonTicketPrice, int pTotalTickets, String artist, ArrayList<String> pVIPs) {

        //Calling the super constructor
        //There are input checks in the super constructor
        super(pName, pLocation, pDate, pPerPersonTicketPrice, pTotalTickets);

        //Checking extra inputs that aren't part of the abstract class
        assert artist != null;
        assert pVIPs != null;

        //Initializing fields
        this.artist = artist;
        this.VIPs = new ArrayList<>();

        //Copying all elements into the VIPs field
        for (int i=0; i<pVIPs.size(); i++){
            this.VIPs.add(pVIPs.get(i));
        }
    }

    /**
     * Constructor for coming soon events
     * @param pName
     *      Name
     * @param pDate
     *      Date
     * @param artist
     *      Artist
     * @param pVIPs
     *      VIP list
     */
    public Concert(String pName, LocalDate pDate, String artist, ArrayList<String> pVIPs) {

        //Calling the super constructor
        super(pName, pDate);

        //Checking extra inputs that aren't part of the abstract class
        assert artist != null;
        assert pVIPs != null;

        //Initializing fields
        this.artist = artist;
        this.VIPs = new ArrayList<>();

        //Copying all elements into the VIPs field
        for (int i=0; i<pVIPs.size(); i++){
            this.VIPs.add(pVIPs.get(i));
        }
    }

    /**
     * Method to get Artist
     * @return
     *      Returning the name of the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Method to get VIPs
     * @return
     *      Returning the list of VIPs
     */
    public ArrayList<String> getVIPs() {

        //Creating a copy of the list
        ArrayList<String> copy = new ArrayList<>();

        for (int i=0; i<VIPs.size(); i++){
            copy.add(VIPs.get(i));
        }
        return copy;
    }

    /**
     * Method to set Artist
     * @param artist
     *      Takes in an artist and sets the artist of the concert
     */
    public void setArtist(String artist) {

        assert artist != null;

        this.artist = artist;
    }

    /**
     * Method to add vip
     * @param name
     *      Takes in the name of the VIP and adds them to the list
     */
    public void addVIP(String name){

        assert name != null;
        this.VIPs.add(name);
    }

    /**
     * Method to remove vip
     * @param name
     *      Takes in the name of the VIP and removes them from
     *      the list if they are currently a VIP
     */
    public void removeVIP(String name){

        assert name != null;

        if (this.VIPs.contains(name)){
            int index = this.VIPs.indexOf(name);
            this.VIPs.remove(index);
        }

    }


    /**
     * This method accepts a visitor to implement the visitor design pattern
     * @param visitor
     *      Takes as input a visitor object and implements the visit method
     */
    @Override
    public double acceptVisitor(Visitor visitor) {

        assert visitor != null;

        return visitor.visitConcert(this);
    }

}
