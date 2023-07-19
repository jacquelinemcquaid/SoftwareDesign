import java.time.LocalDate;
import java.util.ArrayList;

//Type of Event
public class Gala extends AbstractEvent {

    private ArrayList<String> VIPs;

    /**
     * Constructor
     * @param pName
     *      Name of the Gala
     * @param pLocation
     *      Location of the Gala
     * @param pDate
     *      Date of the Gala
     * @param pPerPersonTicketPrice
     *      Per person price of the Gala
     * @param pTotalTickets
     *      Total tickets for the Gala
     * @param pVIPs
     *      List of VIPs
     */
    public Gala(String pName, Location pLocation, LocalDate pDate, double pPerPersonTicketPrice, int pTotalTickets, ArrayList<String> pVIPs) {

        //Calling the super constructor
        //Super class checks inputs
        super(pName, pLocation, pDate, pPerPersonTicketPrice, pTotalTickets);

        //Checking to make sure inputs are valid
        assert pVIPs != null;

        this.VIPs = new ArrayList<>();

        //Copying all elements into the VIPs field
        for (int i=0; i<pVIPs.size(); i++){
            this.VIPs.add(pVIPs.get(i));
        }
    }

    /**
     * Constructor for coming soon events
     * @param pName
     *      Name of the Gala
     * @param pDate
     *      Date of the Gala
     * @param pVIPs
     *      List of VIPs
     */
    public Gala(String pName, LocalDate pDate, ArrayList<String> pVIPs) {

        //Calling the super constructor
        super(pName, pDate);

        //Checking to make sure inputs are valid
        assert pVIPs != null;

        this.VIPs = new ArrayList<>();

        //Copying all elements into the VIPs field
        for (int i=0; i<pVIPs.size(); i++){
            this.VIPs.add(pVIPs.get(i));
        }
    }


    /**
     * Method to add a vip
     * @param name
     *      Takes in a String and adds the
     *      name to the VIP list
     */
    public void addVIP(String name){

        assert name != null;

        this.VIPs.add(name);
    }

    /**
     * Method to remove vip
     * @param name
     *      Takes in a String and removes the name from
     *      the VIP list if it is currently in the list
     */
    public void removeVIP(String name){

        assert name != null;

        if (this.VIPs.contains(name)){
            int index = this.VIPs.indexOf(name);
            this.VIPs.remove(index);
        }

    }

    /**
     * Method to get vips
     * @return
     *      Returns a copy of the VIP list
     */
    public ArrayList<String> getVIPS(){

        //Creating a copy array so the reference to the VIP list isn't leaked
        ArrayList<String> copy = new ArrayList<>();

        for (int i=0; i<this.VIPs.size(); i++){
            copy.add(this.VIPs.get(i));
        }

        //Returning the copy array
        return copy;
    }

    /**
     * This method accepts a visitor to implement the visitor design pattern
     * @param visitor
     *      Takes as input a visitor object and implements the visit method
     */
    @Override
    public double acceptVisitor(Visitor visitor) {

        assert visitor != null;

        return visitor.visitGala(this);
    }

}
