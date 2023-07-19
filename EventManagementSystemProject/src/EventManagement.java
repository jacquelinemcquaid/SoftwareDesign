import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Controller to manage events hosted on EventBrite.
 */
public class EventManagement {

    private List<Event> aHostedEvents = new ArrayList<Event>();

    /**
     * Method to create a new concert event
     * @param name
     *      Name of the concert
     * @param location
     *      Location of the concert
     * @param date
     *      Date of the concert
     * @param perPersonTicketPrice
     *      Per person price
     * @param totalTickets
     *      Total tickets
     * @param artist
     *      Artist performing at the concert
     * @param VIPs
     *      List of VIPs
     * @throws Exception
     *      If the location and date are the same as an existing event, an Exception is thrown
     */
    public void addConcertEvent(String name, Location location, LocalDate date, double perPersonTicketPrice, int totalTickets, String artist, ArrayList<String> VIPs) throws Exception {

        //Ensuring inputs are valid
        assert name != null;
        assert location != null;
        assert date != null;
        assert perPersonTicketPrice >= 0;
        assert totalTickets >= 0;
        assert artist != null;
        assert VIPs != null;

        for (int i=0; i<aHostedEvents.size(); i++){
            //Checking to make sure an event with the same location and date does not already exist
            if (aHostedEvents.get(i).getLocation().equals(location) && aHostedEvents.get(i).getDate().equals(date)){
                throw new Exception(aHostedEvents.get(i).getName() + " has already been scheduled at the location " + aHostedEvents.get(i).getLocation() + " on " + aHostedEvents.get(i).getDate());
            }
        }

        //Creating a new concert event
        Event newConcertEvent = new Concert(name, location, date, perPersonTicketPrice, totalTickets, artist, VIPs);

        //Adding the event to the list of hosted events
        aHostedEvents.add(newConcertEvent);


    }

    /**
     * Method to create a concert coming soon event
     * @param name
     *      Name of the concert
     * @param date
     *      Date of the concert
     * @param artist
     *      Artist performing
     * @param VIPs
     *      List of VIPs
     */
    public void addConcertEvent(String name, LocalDate date, String artist, ArrayList<String> VIPs){

        assert name != null;
        assert date != null;
        assert artist != null;
        assert VIPs != null;

        //Creating a new concert event
        Event newConcertEvent = new Concert(name, date, artist, VIPs);

        //Adding the event to the list of hosted events
        aHostedEvents.add(newConcertEvent);

    }

    /**
     * Method to add a Gala event
     * @param name
     *      Name of the Gala
     * @param location
     *      Location of the Gala
     * @param date
     *      Date of the Gala
     * @param perPersonTicketPrice
     *      Per person price
     * @param totalTickets
     *      Total tickets
     * @param VIPs
     *      List of VIPs
     * @throws Exception
     *      If and event with the same location and date already exists, an Exception is thrown
     */
    public void addGalaEvent(String name, Location location, LocalDate date, double perPersonTicketPrice, int totalTickets, ArrayList<String> VIPs) throws Exception {

        assert name != null;
        assert location != null;
        assert date != null;
        assert perPersonTicketPrice >= 0;
        assert totalTickets >= 0;
        assert VIPs != null;

        for (int i=0; i<aHostedEvents.size(); i++){
            //Checking to make sure an event with the same location and date does not already exist
            if (aHostedEvents.get(i).getLocation().equals(location) && aHostedEvents.get(i).getDate().equals(date)){
                throw new Exception(aHostedEvents.get(i).getName() + " has already been scheduled at the location " + aHostedEvents.get(i).getLocation() + " on " + aHostedEvents.get(i).getDate());
            }
        }

        //Creating a new gala event
        Event newGalaEvent = new Gala(name, location, date, perPersonTicketPrice, totalTickets, VIPs);

        //Adding the event to the list of hosted events
        aHostedEvents.add(newGalaEvent);


    }

    /**
     * Method to add a coming soon Gala event
     * @param name
     *      Name of Gala
     * @param date
     *      Date of Gala
     * @param VIPs
     *      List of VIPs
     */
    public void addGalaEvent(String name, LocalDate date, ArrayList<String> VIPs) {

        assert name != null;
        assert date != null;
        assert VIPs != null;

        //Creating a new gala event
        Event newGalaEvent = new Gala(name, date, VIPs);

        //Adding the event to the list of hosted events
        aHostedEvents.add(newGalaEvent);


    }

    /**
     * Method to create a new Screening event
     * @param name
     *      Name of Screening
     * @param location
     *      Location of Screening
     * @param date
     *      Date of Screening
     * @param perPersonTicketPrice
     *      Per person price
     * @param totalTickets
     *      Total tickets
     * @param rating
     *      Rating of Screening
     * @throws Exception
     *      Exception thrown if an event with the same date and location already exists
     */
    public void addScreeningEvent(String name, Location location, LocalDate date, double perPersonTicketPrice, int totalTickets, String rating) throws Exception {

        assert name != null;
        assert location != null;
        assert date != null;
        assert perPersonTicketPrice >= 0;
        assert totalTickets >= 0;
        assert rating != null;

        for (int i=0; i<aHostedEvents.size(); i++){
            //Checking to make sure an event with the same location and date does not already exist
            if (aHostedEvents.get(i).getLocation().equals(location) && aHostedEvents.get(i).getDate().equals(date)){
                throw new Exception(aHostedEvents.get(i).getName() + " has already been scheduled at the location " + aHostedEvents.get(i).getLocation() + " on " + aHostedEvents.get(i).getDate());
            }
        }

        //Creating a new screening event
        Event newScreeningEvent = new Screening(name, location, date, perPersonTicketPrice, totalTickets, rating);

        //Adding the event to the list of hosted events
        aHostedEvents.add(newScreeningEvent);



    }

    /**
     * Method for creating a comingsoon screening event
     * @param name
     *      Name of Screening
     * @param date
     *      Date of Screening
     * @param rating
     *      Rating of Screening
     *
     */
    public void addScreeningEvent(String name, LocalDate date, String rating) {

        assert name != null;
        assert date != null;
        assert rating != null;

        //Creating a new screening event
        Event newScreeningEvent = new Screening(name, date, rating);

        //Adding the event to the list of hosted events
        aHostedEvents.add(newScreeningEvent);

    }

    /**
     * Method to create a workshop event
     * @param name
     *      Name of workshop
     * @param location
     *      Location of workshop
     * @param date
     *      Date of workshop
     * @param perPersonTicketPrice
     *      Per person price
     * @param totalTickets
     *      Total tickets
     * @param prerequisites
     *      Workshop prerequisites
     * @throws Exception
     *      Exception thrown of an event already exists with location and date
     */
    public void addWorkshopEvent(String name, Location location, LocalDate date, double perPersonTicketPrice, int totalTickets, ArrayList<Workshop> prerequisites) throws Exception {

        assert name != null;
        assert location != null;
        assert date != null;
        assert perPersonTicketPrice >= 0;
        assert totalTickets >= 0;
        assert prerequisites != null;


        for (int i=0; i<aHostedEvents.size(); i++){
            //Checking to make sure an event with the same location and date does not already exist
            if (aHostedEvents.get(i).getLocation().equals(location) && aHostedEvents.get(i).getDate().equals(date)){
                throw new Exception(aHostedEvents.get(i).getName() + " has already been scheduled at the location " + aHostedEvents.get(i).getLocation() + " on " + aHostedEvents.get(i).getDate());
            }
        }

        //Creating a new workshop event
        Event newWorkshopEvent = new Workshop(name, location, date, perPersonTicketPrice, totalTickets, prerequisites);

        //Adding the event to the list of hosted events
        aHostedEvents.add(newWorkshopEvent);

    }

    /**
     * Method to create comingsoon workshop event
     * @param name
     *      Name of the workshop
     * @param date
     *      Date of the workshop
     * @param prerequisites
     *      Prerequisites for the workshop
     */
    public void addWorkshopEvent(String name, LocalDate date, ArrayList<Workshop> prerequisites) {

        assert name != null;
        assert date != null;
        assert prerequisites != null;

        //Creating a new workshop event
        Event newWorkshopEvent = new Workshop(name, date, prerequisites);

        //Adding the event to the list of hosted events
        aHostedEvents.add(newWorkshopEvent);
    }

    public void addFestivalEvent(String name, ArrayList<Event> pListOfEvents) throws Exception {

        assert name != null;
        assert pListOfEvents != null;

        Event festival = new Festival(name, pListOfEvents);

        for (int i=0; i<aHostedEvents.size(); i++){
            if (festival.getDate().equals(aHostedEvents.get(i).getDate()) && festival.getLocation().equals(aHostedEvents.get(i).getLocation())){
                throw new Exception("Cannot create festival. There is already an event at the same location on this date.");
            }
        }

        aHostedEvents.add(festival);
    }

    /**
     * Method to get the list of hosted events
     * @return
     *      Returning a copy of the list of events
     */
    public ArrayList<Event> getHostedEvents(){

        ArrayList<Event> copy = new ArrayList<>();

        for (int i=0; i<aHostedEvents.size(); i++){
            copy.add(aHostedEvents.get(i));
        }
        return copy;
    }

    /**
     * Method to remove the last added event
     */
    public void removeLastEvent(){

        int index = this.aHostedEvents.size()-1;

        this.aHostedEvents.remove(index);


    }
}
