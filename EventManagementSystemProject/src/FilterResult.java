import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


//Using strategy design pattern to implement filters (filter = unique strategy)
public class FilterResult {

    private ArrayList<Event> aFilteredEvents;

    /**
     * Constructor
     * @param listToBeFiltered
     *      Takes in a list of events
     */
    public FilterResult(ArrayList<Event> listToBeFiltered){

        assert listToBeFiltered != null;

        aFilteredEvents = new ArrayList<>();

        for (int i=0; i<listToBeFiltered.size(); i++){
            aFilteredEvents.add(listToBeFiltered.get(i));
        }
    }

    /**
     * Applying a filter
     * @param filter
     *      Takes as input a filter object and filters the list
     *      based on the filter applied
     *
     * @return
     *      Returns a filterResult object with a filtered list
     *
     */
    public FilterResult filter(Filter filter){

        assert filter != null;

        ArrayList<Event> copy = new ArrayList<>();

        for (int i=0; i<this.aFilteredEvents.size(); i++){
            copy.add(this.aFilteredEvents.get(i));
        }

        filter.applyFilter(copy);

        return new FilterResult(copy);

    }

    /**
     * Method to get the list of filtered events
     * @return
     *      Returning a copy of the filtered list
     */
    public ArrayList<Event> getaFilteredEvents() {

        ArrayList<Event> copy = new ArrayList<>();

        for (int i=0; i<this.aFilteredEvents.size(); i++){
            copy.add(this.aFilteredEvents.get(i));
        }

        return copy;
    }

    /**
     * Method to calculate the expected profit
     * @return
     *      Return the expected profit of all events
     *      in the FilterResults object
     */
    public double getExpectedProfit(){

        Visitor visitor = new ExpectedProfitVisitor();

        double expectedProfit = 0;

        for (int i=0; i<this.aFilteredEvents.size(); i++) {

            //Visiting each event and calculating profit
            expectedProfit += this.aFilteredEvents.get(i).acceptVisitor(visitor);

        }

        //Returning expected profit
        return expectedProfit;
    }

    /**
     * Method to get the total VIPs in the FilterResult list of events
     * @return
     *      Returning the number of VIPs
     */
    public int getTotalVIPs(){

        Visitor visitor = new NumberOfVIPsVisitor();

        int count = 0;

        for (int i=0; i<this.aFilteredEvents.size(); i++) {

            count += this.aFilteredEvents.get(i).acceptVisitor(visitor);

        }

        return count;

    }
}


