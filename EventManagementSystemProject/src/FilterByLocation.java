import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FilterByLocation implements Filter {

    final private ArrayList<Location> aLocation;

    /**
     * Constructor
     * @param pLocation
     *      Locations to be filtered
     *      There may be many locations
     */
    public FilterByLocation(ArrayList<Location> pLocation){

        //Input check
        assert pLocation != null;

        aLocation = new ArrayList<>();

        for (int i=0; i<pLocation.size(); i++){
            aLocation.add(pLocation.get(i));
        }

    }

    /**
     *
     * @param events
     *      List of events to be filtered
     */
    @Override
    public void applyFilter(ArrayList<Event> events) {

        ArrayList<Event> filteredEvents = new ArrayList<>();

        for (int i=0; i<events.size(); i++){
            if (this.aLocation.contains(events.get(i).getLocation())){
                filteredEvents.add(events.get(i));
            }
        }

        events.clear();

        //NOTE: THERE MAY BE NO EVENTS MATCHING THE FILTER
        for (int i=0; i<filteredEvents.size(); i++){
            events.add(filteredEvents.get(i));
        }


        //If we are filtering by more than 1 location,
        //I sort them so all locations are grouped together in the
        //filtered list
        if (aLocation.size() > 1){

            //Anonymous comparator to sort the locations
            Collections.sort(events, new Comparator<Event>() {
                @Override
                public int compare(Event o1, Event o2) {
                    return o1.getLocation().compareTo(o2.getLocation());
                }
            });

        }






    }
}
