import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//Strategy: Filter by price
public class FilterByPrice implements Filter {

    //Fields
    final private double aMaxPrice;
    final private double aMinPrice;

    /**
     * Constructor
     * @param pMaxPrice
     *      Filtering by range between min and max price
     */
    public FilterByPrice(double pMinPrice, double pMaxPrice){

        //Input check
        assert pMaxPrice >= 0.0;
        assert pMinPrice >= 0.0;

        aMaxPrice = pMaxPrice;
        aMinPrice = pMinPrice;

    }


    /**
     * Method to apply the filter to the list of events
     * @param events
     *      All events below the max price and above the min price will be
     *      in the filtered list
     */
    @Override
    public void applyFilter(ArrayList<Event> events) {

        //Input check
        assert events != null;

        ArrayList<Event> filteredList = new ArrayList<>();

        //Adding the events that meet the requirements to the filteredList
        for (int i=0; i<events.size(); i++){
            if (events.get(i).getPrice() >= this.aMinPrice && events.get(i).getPrice() <= this.aMaxPrice){
                filteredList.add(events.get(i));
            }
        }

        //Anonymous comparator to sort the prices from min to max price
        Collections.sort(filteredList, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                if (o1.getPrice() - o2.getPrice() < 0) {
                    return -1;
                } else if (o1.getPrice() - o2.getPrice() > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        //Clearing the input list
        events.clear();

        //Adding the filtered events to the input list
        for (int i=0; i<filteredList.size(); i++){
            events.add(filteredList.get(i));
        }

    }

}
