import java.util.ArrayList;

public class MultipleFilters implements Filter {

    private ArrayList<Filter> filters;

    /**
     * Constructor
     * @param listOfFilters
     *      A list of filters to be iterated through and applied to a list
     *      when the applyFilter method is called
     */
    public MultipleFilters(ArrayList<Filter> listOfFilters){

        assert listOfFilters != null;

        filters = new ArrayList<>();

        for (int i=0; i<listOfFilters.size(); i++){
            filters.add(listOfFilters.get(i));
        }
    }

    /**
     * Method to apply the filter
     * @param listOfEvents
     *      List of events to be filtered
     */
    @Override
    public void applyFilter(ArrayList<Event> listOfEvents) {

        assert listOfEvents != null;

        for (int i=0; i<filters.size(); i++){
            filters.get(i).applyFilter(listOfEvents);
        }


    }
}
