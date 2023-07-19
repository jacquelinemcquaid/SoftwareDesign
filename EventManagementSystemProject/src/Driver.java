import java.time.LocalDate;
import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) throws Exception {

        //Creating an EventManagement object
        //Bob can only access events through the event management object
        EventManagement managingEvents = new EventManagement();

        //Bob can create any type of event from the event Management class

        ArrayList<String> concert1VIPs = new ArrayList<>();
        concert1VIPs.add("First");
        concert1VIPs.add("Second");
        concert1VIPs.add("Third");

        ArrayList<String> concert2VIPs = new ArrayList<>();
        concert2VIPs.add("VIP");

        ArrayList<String> galaVIPSs = new ArrayList<>();
        galaVIPSs.add("galaVIP1");

        ArrayList<String> galaVIPSs2 = new ArrayList<>();
        galaVIPSs.add("galaVIP2");
        galaVIPSs.add("galaVIP3");

        //No pre-requisites
        ArrayList<Workshop> prerequisites = new ArrayList<>();

        managingEvents.addConcertEvent("Concert 1", Location.BellCentre, LocalDate.of(2022, 7, 20), 8.0, 200, "Justin Bieber", concert1VIPs);
        //Coming soon
        managingEvents.addConcertEvent("Concert 2", LocalDate.of(2022, 11, 22), "Harry Styles", concert2VIPs);

        managingEvents.addGalaEvent("Gala 1", Location.PlaceDesArts, LocalDate.of(2022, 8, 9), 100.0, 20, galaVIPSs);
        //Coming soon
        managingEvents.addGalaEvent("Gala 2", LocalDate.of(2022, 10, 9), galaVIPSs2);

        managingEvents.addScreeningEvent("Screening 1", Location.ParcJeanDrapeau, LocalDate.of(2022, 6, 12), 300.0, 50, "G");
        //Coming soon
        managingEvents.addScreeningEvent("Screening 2", LocalDate.of(2022, 9, 12), "PG");

        managingEvents.addWorkshopEvent("Workshop 1", Location.BellCentre, LocalDate.of(2022, 11, 20), 12.0, 20, prerequisites);
        //Coming soon
        managingEvents.addWorkshopEvent("Workshop 1", LocalDate.of(2022, 4, 20), prerequisites);

        //Bob can use the event list getter to create festivals
        //He could iterate through this list and pick different events to create festivals with
        //For this example, I will use the entire list of events to create a festival
        managingEvents.addFestivalEvent("Festival 1", managingEvents.getHostedEvents());

        //Printing out the name of the current events in the manage events object
        System.out.println("************************");
        System.out.println("Printing events in the managingEvents object created by Bob:");
        for (int i=0; i<managingEvents.getHostedEvents().size(); i++){
            System.out.println("Name: " + managingEvents.getHostedEvents().get(i).getName() + ", Price: " + managingEvents.getHostedEvents().get(i).getPrice() + ", Location: " + managingEvents.getHostedEvents().get(i).getLocation());
        }
        System.out.println("************************");

        //Bob can filter the list based on price, location, or multiple filters
        //He creates a new FilterResult object so the manageEvents list remains unchanged
        Filter filterByPrice = new FilterByPrice(10.0, 200.0);
        System.out.println("Filtering original list by price range 10.0$-200.0$");
        System.out.println("Printing out the filtered list");

        FilterResult filteredList = new FilterResult(managingEvents.getHostedEvents());

        FilterResult firstFilteredResult = filteredList.filter(filterByPrice);

        for (int i=0; i<firstFilteredResult.getaFilteredEvents().size(); i++){
            System.out.println("Name: " + firstFilteredResult.getaFilteredEvents().get(i).getName() + ", Price: " + firstFilteredResult.getaFilteredEvents().get(i).getPrice() + ", Location: " + firstFilteredResult.getaFilteredEvents().get(i).getLocation());
        }

        System.out.println("************************");

        System.out.println("Filtering original list to include location BellCenter and ParcJeanDrapeau:");

        //Bob creates a list of locations he wants to filter by
        ArrayList<Location> listOfLocations = new ArrayList<>();
        listOfLocations.add(Location.BellCentre);
        listOfLocations.add(Location.ParcJeanDrapeau);

        //Bob creates a FilterByLocation object
        Filter byLocation = new FilterByLocation(listOfLocations);

        //Bob creates a new FilterResult object by calling the filter method on the original filterResult object
        FilterResult filterByLocation = filteredList.filter(byLocation);

        System.out.println("Printing out the filtered list");
        for (int i=0; i<filterByLocation.getaFilteredEvents().size(); i++){
            System.out.println("Name: " + filterByLocation.getaFilteredEvents().get(i).getName() + ", Price: " + filterByLocation.getaFilteredEvents().get(i).getPrice() + ", Location: " + filterByLocation.getaFilteredEvents().get(i).getLocation());
        }

        System.out.println("************************");

        System.out.println("Filtering the original list for events at either BellCenter or ParcJeanDrapeau, and the price must be between 10.0-200.0");
        //Bob creates a list of filters he wants to apply to the list of events
        ArrayList<Filter> listOfFilters = new ArrayList<>();
        listOfFilters.add(filterByPrice);
        listOfFilters.add(byLocation);
        Filter byLocationAndPrice = new MultipleFilters(listOfFilters);

        FilterResult filteredByPriceAndLocation = filteredList.filter(byLocationAndPrice);


        System.out.println("Printing out the filtered list");
        for (int i=0; i<filteredByPriceAndLocation.getaFilteredEvents().size(); i++){
            System.out.println("Name: " + filteredByPriceAndLocation.getaFilteredEvents().get(i).getName() + ", Price: " + filteredByPriceAndLocation.getaFilteredEvents().get(i).getPrice() + ", Location: " + filteredByPriceAndLocation.getaFilteredEvents().get(i).getLocation());
        }

        System.out.println("************************");

        //Bob can calculate the expected profit
        System.out.println("Calculating expected profit of all events in the original list");
        System.out.println("**NOTE: Bob can calculate the expected profit on any of the filtered lists (FilterResult objects) created");
        System.out.println("Using the following percentages: Concert=60%, workshop=50%, gala=30%, screening=10%");

        double expectedProfit = filteredList.getExpectedProfit();
        System.out.println("The expected profit is: " + expectedProfit);

        System.out.println("************************");

        System.out.println("Calculating total number of VIPs (we would expect 7 (the individual events added) + 7 (festival event = all prior events) = 14");

        int numberVIPs = filteredList.getTotalVIPs();
        System.out.println("The number of VIPs is: " + numberVIPs);

        System.out.println("************************");

        System.out.println("Edge cases/Error handling");

        System.out.println("1: Creating duplicate location/date event");

        try {
            managingEvents.addConcertEvent("Concert 3", Location.BellCentre, LocalDate.of(2022, 7, 20), 200.0, 200, "Justin Bieber", concert1VIPs);

        } catch (Exception e){
            System.out.println(e.toString());
        }

        System.out.println("************************");

        System.out.println("2: Using getters on coming soon events (does not break execution of program)");

        //The event at the first index is a coming soon event
        System.out.println("Location does not exist: " + managingEvents.getHostedEvents().get(1).getLocation());
        System.out.println("Prices are not set: " + managingEvents.getHostedEvents().get(1).getPrice());
        System.out.println("Number of tickets is not set: " + managingEvents.getHostedEvents().get(1).getNumTickets());

        System.out.println("************************");






    }
}
