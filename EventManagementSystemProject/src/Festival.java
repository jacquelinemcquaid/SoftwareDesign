import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

//Composite class
public class Festival implements Event {

    final private ArrayList<Event> listOfEvents;

    //All of these fields are immutable
    final private String name;
    final private Location location;
    final private LocalDate date;
    final private double perPersonTicketPrice;
    final private int totalTickets;

    /**
     * Constructor
     * @param name
     *      Name of the festival
     * @param pListOfEvents
     *      List of Events
     */
    public Festival(String name, ArrayList<Event> pListOfEvents) {

        //Checking to make sure inputs are valid
        assert name != null;
        assert pListOfEvents != null;

        this.name = name;

        this.listOfEvents = new ArrayList<>();

        int totalPrice = 0;
        boolean sameLocation = true;
        int minTickets = pListOfEvents.get(0).getNumTickets();
        LocalDate firstDate = pListOfEvents.get(0).getDate();

        Location currentLocation = pListOfEvents.get(0).getLocation();

        for (int i=0; i<pListOfEvents.size(); i++){

            //Adding each event to the listOfEvents
            this.listOfEvents.add(pListOfEvents.get(i));

            //Adding the price of the current event to the total price
            totalPrice += pListOfEvents.get(i).getPrice();

            //If the total number of tickets for hte current event is less than the current minimum
            //resetting the minimum to the current ticket value
            if (pListOfEvents.get(i).getNumTickets() < minTickets){
                minTickets = pListOfEvents.get(i).getNumTickets();
            }

            //If the date of the current event is before the set firstDate
            //re-assigning the firstDate value
            if (pListOfEvents.get(i).getDate().isBefore(firstDate)){
                firstDate = pListOfEvents.get(i).getDate();
            }

            //If the current location is different than the next event, assigning a value of false
            //to the sameLocation variable
            if (currentLocation != pListOfEvents.get(i).getLocation()){
                sameLocation = false;
            }

            //The currentLocation is the next event
            currentLocation = pListOfEvents.get(i).getLocation();

        }

        //The total per price will be 20% off the total price of all events
        this.perPersonTicketPrice = totalPrice - (totalPrice*0.2);

        //The total number of tickets to sell is the minimum amount for all the events
        this.totalTickets = minTickets;

        //The date of the festival is equal to the date of the first event
        this.date = firstDate;

        //If the sameLocation variable is still set to true, assigning a location to the festival,
        //as all events are in the same place or there is only one event in the festival
        if (sameLocation){
            this.location = currentLocation;
        }

        //Otherwise, there are multiple locations
        else {
            this.location = Location.Multiple;
        }
    }

    /**
     * Method to get the name of the festival
     * @return
     *      Returning the name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Method to get the location of the festival
     * @return
     *      Returning the location
     */
    @Override
    public Location getLocation() {
        return this.location;
    }

    /**
     * Method to get the local date of the festival
     * @return
     *      Returning the date
     */
    @Override
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Method to get the price of the festival
     * @return
     *      Returning the price
     */
    @Override
    public double getPrice() {
        return this.perPersonTicketPrice;
    }

    /**
     * Method to get the number of tickets
     * @return
     *      Returning the number of tickets
     */
    @Override
    public int getNumTickets() {
        return this.totalTickets;
    }

    /**
     * Method to accept the visitor to the festival
     * @param visitor
     *      Visitor object
     * @return
     *      Returning the cost after iterating through the list and visiting each event
     */
    @Override
    public double acceptVisitor(Visitor visitor) {

        assert visitor != null;

        int cost = 0;

        //Accepting each event within the festival as a visitor
        for (int i=0; i<this.listOfEvents.size(); i++){
            cost += this.listOfEvents.get(i).acceptVisitor(visitor);
        }

        return cost;
    }

    /**
     * Getter for the list of events
     * @return
     *      Returns a copy of the events list
     */
    public ArrayList<Event> getListOfEvents(){
        ArrayList<Event> copy = new ArrayList<>();

        for (int i=0; i<this.listOfEvents.size(); i++){
            copy.add(this.listOfEvents.get(i));
        }

        return copy;
    }
}
