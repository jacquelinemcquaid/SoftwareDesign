import java.util.ArrayList;

public class Flight {

    //Flights only considers tricks, not individual moves
    final private ArrayList<DroneTrick> aTricks;
    final private String aFlightName;
    final private int uniqueMovementDirections;

    //When a Flight object is created, the client must input a sequence of tricks that represent the Flight
    public Flight(ArrayList<DroneTrick> pTricks, String pFlightName) {
        aTricks = pTricks;
        aFlightName = pFlightName;

        ArrayList<Direction> uniqueDirections = new ArrayList<>();

        //Creating a new list that will hold all of the unique directions from all tricks in the array
        ArrayList<Direction> directions = new ArrayList<>();

        ArrayList<Direction> counterArray = new ArrayList<>();

        //Iterating through the tricks array
        for (int i=0; i<aTricks.size(); i++){

            //Clearing the counter array
            counterArray.clear();

            counterArray = aTricks.get(i).getUniqueDirections();

            for (int j=0; j<counterArray.size(); j++){
                if (!directions.contains(counterArray.get(j))){
                    directions.add(counterArray.get(j));
                }
            }
        }

        //The number of unique movement is equal to the directions array size
        uniqueMovementDirections = directions.size();
    }

    //Client can query the unique movement directions through this getter method
    public int getUniqueMovementDirections() {
        return uniqueMovementDirections;
    }

    //Returning the list of tricks (it is final so the client cannot change the internal values)
    public ArrayList<DroneTrick> getListOfTricks(){

        ArrayList<DroneTrick> copyOfTricks = new ArrayList<>();

        for (int i=0; i<aTricks.size(); i++){
            copyOfTricks.add(aTricks.get(i));
        }

        return copyOfTricks;

    }

    //This method can be used to get the number of unique movements in the aTricks array
    public int getNumberOfMoves(){

        int count = 0;

        for (int i=0; i < aTricks.size(); i++){

            int moveSequence = aTricks.get(i).getMovesSequence().size();

            for (int j=0; j < moveSequence; j++){
                count++;
            }
        }

        return count;
    }

    public String getaFlightName() {
        return aFlightName;
    }
}
