import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RunDrone {
    public static void main(String args[]) throws Exception {
        // TODO: illustrate your implementations here

        //There are 3 possible tricks
        //Note: Recording can also be turned on for any trick the client makes
        DroneTrick trick1 = new DroneTrick(Recording.OFF, Tricks.TAKEOFF);
        DroneTrick trick2 = new DroneTrick(Recording.OFF, Tricks.PUCKER, 10, Speed.HIGH, 5, Speed.LOW, 5, Speed.MODERATE, 6, Speed.MODERATE, 10, Speed.MODERATE, 2, Speed.HIGH);
        DroneTrick trick3 = new DroneTrick(Recording.OFF, Tricks.LAND);

        //Creating lists to store the tricks for flight1, flight2, flight3
        ArrayList<DroneTrick> flight1 = new ArrayList<>();
        ArrayList<DroneTrick> flight2 = new ArrayList<>();
        ArrayList<DroneTrick> flight3 = new ArrayList<>();

        //Adding the tricks
        flight1.add(trick1);
        flight1.add(trick1);

        //Adding tricks to flight 2
        flight2.add(trick1);
        flight2.add(trick2);
        flight2.add(trick3);
        flight2.add(trick2);

        //Adding trick to flight3
        flight3.add(trick2);

        //Creating the first flight
        Flight firstFlight = new Flight(flight1, "flight1");

        //Creating the second flight
        Flight secondFlight = new Flight(flight2, "flight2");

        //Creating the third flight
        Flight thirdFlight = new Flight(flight3, "flight3");

        //Using Comparator to compareByTrickNumber
        Comparator<Flight> compareByTrickNumber = new Comparator<Flight>(){
            public int compare(Flight f1, Flight f2){
                if (f1.getListOfTricks().size() > f2.getListOfTricks().size()){
                    return 1;
                }
                else{
                    return -1;
                }
            }
        };

        //Using Comparator to sort by move count
        Comparator<Flight> compareByMoveCount = new Comparator<Flight>(){
            public int compare(Flight f1, Flight f2){
                if (f1.getNumberOfMoves() > f2.getNumberOfMoves()){
                    return 1;
                }
                else{
                    return -1;
                }
            }
        };


        //Creating a list of my three flights
        ArrayList<Flight> listOfFlights = new ArrayList<>();
        listOfFlights.add(firstFlight);
        listOfFlights.add(secondFlight);
        listOfFlights.add(thirdFlight);

        //Printing out the flights with no ordering
        System.out.println("The order of flights with no ordering: ");
        for (int i=0; i<listOfFlights.size(); i++){
            System.out.println(listOfFlights.get(i).getaFlightName());
        }

        System.out.println("*******************************");

        System.out.println("After implementing compareByTrick comparator: ");

        //Flight1 has 2 tricks
        //Flight2 has 4 tricks
        //Flight3 has 1 trick

        //The expected ordering by trick number is flight3, flight1, flight2

        Collections.sort(listOfFlights, compareByTrickNumber);

        for (int i=0; i<listOfFlights.size(); i++){
            System.out.println(listOfFlights.get(i).getaFlightName() + " has " + listOfFlights.get(i).getListOfTricks().size()+ " trick/s and " + listOfFlights.get(i).getNumberOfMoves() + " moves");
        }

        System.out.println("*******************************");

        System.out.println("After implementing compareByMoveCount comparator: ");

        //Flight1 has 4 moves
        //Flight2 has 16 moves
        //Flight3 has 6 moves

        //Expected ordering is flight1, flight3, flight2

        Collections.sort(listOfFlights, compareByMoveCount);

        for (int i=0; i<listOfFlights.size(); i++){
            System.out.println(listOfFlights.get(i).getaFlightName() + " has " + listOfFlights.get(i).getListOfTricks().size()+ " trick/s and " + listOfFlights.get(i).getNumberOfMoves() + " moves");
        }

        System.out.println("*******************************");
        System.out.println("Testing my unique directions method in my flight class.");

        System.out.println("Testing flight 1: expecting 1 unique direction");
        System.out.println("The number of unique movement directions: " + firstFlight.getUniqueMovementDirections());

        System.out.println("Testing flight 2: expecting 3 unique movement directions.");
        System.out.println("The number of unique movement directions: " + secondFlight.getUniqueMovementDirections());

        System.out.println("Testing flight 3: expecting 3 unique movement directions.");
        System.out.println("The number of unique movement directions: " + thirdFlight.getUniqueMovementDirections());

        System.out.println("**************************");
        System.out.println("Testing exceptions: ");

        PuckerNoDistanceSpeedParameters(Recording.OFF, Tricks.PUCKER);

        System.out.println("*******************************");

        TakeOffWithDistanceSpeedParameters(Recording.OFF, Tricks.TAKEOFF, 10, Speed.HIGH, 5, Speed.LOW, 5, Speed.MODERATE, 6, Speed.MODERATE, 10, Speed.MODERATE, 2, Speed.HIGH);

        System.out.println("********************");

        flightCrash();

        System.out.println("********************");


        System.out.println("Testing my drone class");
        System.out.println("Executing a flight: ");

        Drone drone = new Drone("myDrone");

        drone.executeFlight(firstFlight);

        System.out.println("**************************");
        System.out.println("Executing a move: ");

        DroneMove move = new DroneMove(Direction.UP, 9, Speed.LOW, Recording.MP4);

        drone.executeMove(move);

        System.out.println("********************");

        System.out.println("Executing the Land trick: ");

        drone.executeTrick(trick3);

    }




    public static void PuckerNoDistanceSpeedParameters(Recording recording, Tricks trick) throws Exception {

        System.out.println("Trying to create a DroneTrick with the pucker trick as a parameter, but no distance/speed values.");

        try {
            DroneTrick puckerTrick = new DroneTrick(recording, trick);
        } catch (Exception toPrint) {
            System.out.println("Exception message: " + toPrint.getMessage());
        }
    }

    public static void TakeOffWithDistanceSpeedParameters(Recording pRecording, Tricks pTrick, int distanceUp, Speed speedUp, int distanceLeft1, Speed speedLeft1, int distanceLeft2, Speed speedLeft2, int distanceLeft3, Speed speedLeft3, int distanceLeft4, Speed speedLeft4, int distanceDown, Speed speedDown){

        System.out.println("Trying to create a DroneTrick with the takeoff trick as a parameter, indicating distance and speed values");

        try {
            DroneTrick puckerTrick = new DroneTrick(pRecording, pTrick, distanceUp, speedUp, distanceLeft1, speedLeft1, distanceLeft2, speedLeft2, distanceLeft3, speedLeft3, distanceLeft4, speedLeft4, distanceDown, speedDown);
        } catch (Exception toPrint) {
            System.out.println("Exception message: " + toPrint.getMessage());
        }

    }

    public static void flightCrash() throws Exception {

        System.out.println("Executing a flight where the y-coordinate goes below 0.");

        //Creating a landing trick
        DroneTrick trick = new DroneTrick(Recording.AVI, Tricks.LAND);

        //Creating an array to hold my tricks
        ArrayList<DroneTrick> listOfTricks = new ArrayList<>();

        //Adding the landing trick to the array
        listOfTricks.add(trick);

        //Creating a flight
        Flight flight = new Flight(listOfTricks, "newFlight");

        //Creating a drone
        Drone drone1 = new Drone("drone1");

        try {

            drone1.executeFlight(flight);

        } catch (Exception toPrint) {
            System.out.println("Exception message: " + toPrint.getMessage());
        }

    }
}
