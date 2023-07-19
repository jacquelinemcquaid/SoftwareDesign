import java.util.*;

//Jacqueline McQuaid

public class RunDrone {

    public static void main(String[] args) throws Exception {

        //Creating my drone object
        Drone drone = new Drone();

        //Creating the remote for my drone
        RemoteControl remote = new RemoteControl(drone);

        System.out.println("************************************");

        //Test1: Adding moves to my remote
        System.out.println("Test 1:");
        System.out.println("Configuring the moves using the RemoteControl");
        System.out.println("Adding TAKEOFF, UP, BACKWARDS, LAND (one at a time) to the sequence.");

        remote.addMove(Moves.TAKEOFF);
        remote.addMove(Moves.UP, 5);
        remote.addMove(Moves.BACKWARDS, 10);
        remote.addMove(Moves.LAND);

        //Test2: Client has access to all drones in the sequence
        System.out.println("************************************");
        System.out.println("Test 2: ");
        System.out.println("Client has access to all moves in the sequence:");
        //The client has access to all moves in the sequence
        for (int i = 0; i < remote.getSequenceSize(); i++) {
            System.out.println("The move at index " + i + " is: " + remote.getMove(i));
        }

        System.out.println("************************************");

        //Test 3: Removing the last move of the sequence
        System.out.println("Test 3:");
        System.out.println("Removing the last move from the current sequence:");

        remote.removeLastMove();

        //Showing that the last move was removed
        for (int i = 0; i < remote.getSequenceSize(); i++) {
            System.out.println("The move at index " + i + " is: " + remote.getMove(i));
        }

        System.out.println("************************************");

        //Adding moves to my sequence
        System.out.println("Adding the moves UP, FOCUSANDCAPTURE, BACKWARDS, and LAND to the sequence.");

        remote.addMove(Moves.UP, 7);
        remote.addMove(Moves.FOCUSANDCAPTURE, "picture_1.PNG");
        remote.addMove(Moves.BACKWARDS, 4);
        remote.addMove(Moves.LAND);

        //Showing that these moves have been added to the sequence
        for (int i = 0; i < remote.getSequenceSize(); i++) {
            System.out.println("The move at index " + i + " is: " + remote.getMove(i));
        }

        System.out.println("************************************");

        //Test 4: Flying the drone
        System.out.println("Test 4:");
        System.out.println("I am now happy with my sequence. Flying my pre-programmed drone:");

        remote.flyDrone();

        System.out.println("************************************");


        //Test 5: Clearing my pre-programmed sequence
        System.out.println("Test 5:");
        System.out.println("Clearing my current pre-programmed sequence:");

        remote.removeAllMoves();

        System.out.println("The size of my sequence after clearing is: " + remote.getSequenceSize());

        System.out.println("************************************");
        System.out.println("Adding the moves TAKEOFF, UP, FOCUSANDCAPTURE, and LAND to my sequence.");

        remote.addMove(Moves.TAKEOFF);
        remote.addMove(Moves.UP, 2);
        remote.addMove(Moves.FOCUSANDCAPTURE, "picture2.PDF");
        remote.addMove(Moves.LAND);

        for (int i = 0; i < remote.getSequenceSize(); i++) {
            System.out.println("The move at index " + i + " is: " + remote.getMove(i));
        }

        System.out.println("************************************");


        System.out.println("Flying my drone with the new sequence:");
        remote.flyDrone();

        System.out.println("************************************");


        remote.removeAllMoves();

        System.out.println("Test 6: ");
        System.out.println("EXCEPTIONS:");
        System.out.println("I throw exceptions (and indicate what is wrong) for the following situations:");
        System.out.println("The client does not enter TAKEOFF as the first move of the sequence.");
        System.out.println("The client trys to add the move LAND when the previous move is LAND.");
        System.out.println("The client does not enter LAND as the last move.");
        System.out.println("The client enters a move with incorrect parameters (Ex: enters a file name for moves UP, DOWN, FORWARDS, or BACKWARDS).");
        System.out.println("The client enters a sequence that causes a crash (drone goes below ground level).");
        System.out.println("The client enters an invalid index when trying to access the moves in the pre-programmed sequence.");
        System.out.println("The client tries to remove a move when the sequence is already empty.");
        System.out.println("The client trys to fly the drone before pre-programming a sequence.");

        System.out.println("************************************");

        //See method below
        firstMoveNotTAKEOFF(remote);
        remote.removeAllMoves();
        System.out.println("************************************");

        //See method below
        lastMoveNotLand(remote);
        remote.removeAllMoves();
        System.out.println("************************************");

        //See method below
        ConsecutiveLandMoves(remote);
        remote.removeAllMoves();
        System.out.println("************************************");

        //See method below
        incorrectParameters(remote);
        System.out.println("************************************");
        remote.removeAllMoves();

        //See method below
        DroneCrash(remote);
        remote.removeAllMoves();
        System.out.println("************************************");

        //See method below
        invalidIndex(remote);
        remote.removeAllMoves();
        System.out.println("************************************");

        //See method below
        removeLastMoveEmptySequence(remote);
        remote.removeAllMoves();
        System.out.println("************************************");

        //See method below
        EmptySequence(remote);
        remote.removeAllMoves();
        System.out.println("************************************");

        //Test 7
        System.out.println("Client can view the flight history of their drone which is stored in their drone. They are unable to change the flight history (history is printed to the screen).");
        drone.viewFlightHistory();

    }

    public static void firstMoveNotTAKEOFF(RemoteControl remote1) throws Exception {

        System.out.println("Adding the move UP as the first move in the sequence:");

        try {
            remote1.addMove(Moves.UP, 9);

        } catch (Exception toPrint) {
            System.out.println("Exception message: " + toPrint.getMessage());
        }
    }

    public static void lastMoveNotLand(RemoteControl remote1) throws Exception {

        System.out.println("The last move of the sequence is FORWARDS. Calling FlyDrone method.");

        try {
            remote1.addMove(Moves.TAKEOFF);
            remote1.addMove(Moves.FORWARDS, 9);
            remote1.flyDrone();
        } catch (Exception toPrint) {
            System.out.println("Exception message: " + toPrint.getMessage());
        }
    }

    public static void ConsecutiveLandMoves(RemoteControl remote1) throws Exception {

        System.out.println("Adding two LAND moves in a row.");

        try {
            remote1.addMove(Moves.TAKEOFF);
            remote1.addMove(Moves.LAND );
            remote1.addMove(Moves.LAND);
        } catch (Exception toPrint) {
            System.out.println("Exception message: " + toPrint.getMessage());
        }
    }

    public static void incorrectParameters(RemoteControl remote1) throws Exception {

        System.out.println("Incorrect Parameters.");

        try {
            remote1.removeAllMoves();
            remote1.addMove(Moves.TAKEOFF, 9);
        } catch (Exception toPrint) {
            System.out.println("Exception message TAKEOFF with distance parameter: " + toPrint.getMessage());
        }
        try {
            remote1.removeAllMoves();
            remote1.addMove(Moves.TAKEOFF);
            remote1.addMove(Moves.LAND, 7);
        } catch (Exception toPrint) {
            System.out.println("Exception message LAND with distance parameter: " + toPrint.getMessage());
        }
        try {
            remote1.removeAllMoves();
            remote1.addMove(Moves.TAKEOFF);
            remote1.addMove(Moves.UP);
        } catch (Exception toPrint) {
            System.out.println("Exception message UP with no distance indicated: " + toPrint.getMessage());
        }
        try {
            remote1.removeAllMoves();
            remote1.addMove(Moves.TAKEOFF);
            remote1.addMove(Moves.UP, "picture.JPG");
        } catch (Exception toPrint) {
            System.out.println("Exception message UP with fileName parameter: " + toPrint.getMessage());
        }
        try {
            remote1.removeAllMoves();
            remote1.addMove(Moves.TAKEOFF);
            remote1.addMove(Moves.FOCUSANDCAPTURE, 10);
        } catch (Exception toPrint) {
            System.out.println("Exception message FOCUSANDCAPTURE with distance parameter: " + toPrint.getMessage());
        }
    }

    public static void DroneCrash(RemoteControl remote1) throws Exception {

        System.out.println("Adding a move that causes drone to crash to ground.");

        try {
            remote1.addMove(Moves.TAKEOFF);
            remote1.addMove(Moves.UP, 5 );
            remote1.addMove(Moves.DOWN, 10);
        } catch (Exception toPrint) {
            System.out.println("Exception message: " + toPrint.getMessage());
        }
    }

    public static void invalidIndex(RemoteControl remote1) throws Exception {

        System.out.println("Sequence size is 2. Trying to access index 3.");

        try {
            remote1.addMove(Moves.TAKEOFF);
            remote1.addMove(Moves.UP, 5 );
            remote1.addMove(Moves.LAND);
            remote1.getMove(3);
        } catch (Exception toPrint) {
            System.out.println("Exception message: " + toPrint.getMessage());
        }
    }

    public static void removeLastMoveEmptySequence(RemoteControl remote1) throws Exception {

        System.out.println("Trying to remove last move when sequence is empty.");

        try {
            remote1.removeAllMoves();
            remote1.removeLastMove();
        } catch (Exception toPrint) {
            System.out.println("Exception message: " + toPrint.getMessage());
        }
    }

    public static void EmptySequence(RemoteControl remote1) throws Exception {

        System.out.println("The sequence is empty. Trying to call flyDrone method");

        try {
            remote1.flyDrone();
        } catch (Exception toPrint) {
            System.out.println("Exception message: " + toPrint.getMessage());
        }
    }
}
