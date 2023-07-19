import java.util.*;

//Jacqueline McQuaid

public class RemoteControl {

    //Drone object never changes
    final private Drone drone;
    private ArrayList<DroneMoves> sequenceOfMoves = new ArrayList<>();
    private String errorMessage;
    private int sequenceSize;
    private int xCoordinate;
    private int yCoordinate;
    //The drone has a setter, however, the client has access to this setter since it's public.
    //Therefore I added an extra parameter which requires a specific password to set the coordinates of the drone
    private String passwordForDrone;

    //The remoteControl takes in a drone object
    public RemoteControl(Drone droneToFly) {
        this.drone = droneToFly;
        this.passwordForDrone = "Remote is setting this coordinate.";
    }

    //I have a private method that determines if the move is valid
    private boolean isMoveValid(Moves move) throws Exception {

        //First move must be TAKEOFF
        if (sequenceOfMoves.size() == 0) {
            if (move != Moves.TAKEOFF) {
                this.errorMessage = "Your first move must be TAKEOFF.";
                return false;
            }
            //We now consider the moves after TAKEOFF
        } else {
            //The drone can only takeoff once
            if (move == Moves.TAKEOFF) {
                this.errorMessage = "You can't take off if your plane is already flying!";
                return false;
                //If the previous move added was land, it doesn't make sense for the next move to be land
            } else if (this.sequenceOfMoves.get(this.sequenceOfMoves.size() - 1).getMove() == Moves.LAND) {
                this.errorMessage = "You already programmed your drone to land. If you would like to add more moves, please remove your last move and continue to pre-program your drone.";
                return false;
            }
        }
        return true;
    }

    //I have three addMove methods depending on the Move
    //This method takes in the move and the distance
    public void addMove(Moves move, int distance) throws Exception {

        //Checking if the move is valid
        if (isMoveValid(move)) {

            //There is a special case we have to consider: if the drone moves below ground level (crashes)
            if (move == Moves.DOWN) {
                //Setting an integer variable to the current yCoordinate value
                int validMoveDown = this.yCoordinate;
                //Checking if moving down from the current yCoordinate will go below ground level
                if (validMoveDown - distance < 0) {
                    throw new Exception("This move is not valid. Your drone will crash to the ground!");
                } else {
                    //If the drone doesn't go below ground level, we create a newMove, add it to
                    //the sequenceOfMoves, and update the coordinates
                    DroneMoves newMove = new DroneMoves(move, distance);
                    this.sequenceOfMoves.add(newMove);
                    this.sequenceSize++;
                    this.yCoordinate = this.yCoordinate - distance;
                }
                //For all other moves (UP, FORWARDS, BACKWARDS), we create a newMove, add it to the
                //sequenceOfMoves, and update the coordinates
            } else {
                DroneMoves newMove = new DroneMoves(move, distance);
                this.sequenceOfMoves.add(newMove);
                this.sequenceSize++;

                //Updating coordinates
                if (move == Moves.UP) {
                    this.yCoordinate = this.yCoordinate + distance;
                } else if (move == Moves.FORWARDS) {
                    this.xCoordinate = this.xCoordinate + distance;
                } else if (move == Moves.BACKWARDS) {
                    this.xCoordinate = this.xCoordinate - distance;
                }
            }
            //If the move is not valid, we throw an exception
        } else {
            throw new Exception(this.errorMessage);
        }
    }

    //This method takes in the move (FOCUSANDCAPTURE) and the fileName
    public void addMove(Moves move, String fileName) throws Exception {

        //Checking if the move is valid
        if (isMoveValid(move)) {
            //If valid, creating a newMove and adding it to the sequenceOfMoves
            DroneMoves newMove = new DroneMoves(move, fileName);
            this.sequenceOfMoves.add(newMove);
            this.sequenceSize++;
            //Otherwise, throwing an exception
        } else {
            throw new Exception(this.errorMessage);
        }
    }

    //This method takes in the move (must be TAKEOFF or LAND)
    public void addMove(Moves move) throws Exception {

        //Checking if the move is valid
        if (isMoveValid(move)) {
            //Creating a newMove and adding it to the sequenceOfMoves
            DroneMoves newMove = new DroneMoves(move);
            this.sequenceOfMoves.add(newMove);
            this.sequenceSize++;
            //Otherwise, throwing and exception
        } else {
            throw new Exception(this.errorMessage);
        }
    }

    //Client has the option to remove just the last move
    public void removeLastMove() throws Exception {

        //If the sequence is empty
        if (this.sequenceOfMoves.size()==0){
            throw new Exception("Your sequence is already empty!");
        }

        //Removing just the last move in the sequence
        else if (this.sequenceOfMoves.get(this.sequenceOfMoves.size()-1).getMove() == Moves.UP){
            this.yCoordinate = this.yCoordinate - this.sequenceOfMoves.get(this.sequenceOfMoves.size()-1).getDistance();
        }

        else if (this.sequenceOfMoves.get(this.sequenceOfMoves.size()-1).getMove() == Moves.DOWN){
            this.yCoordinate = this.yCoordinate + this.sequenceOfMoves.get(this.sequenceOfMoves.size()-1).getDistance();
        }

        else if (this.sequenceOfMoves.get(this.sequenceOfMoves.size()-1).getMove() == Moves.FORWARDS){
            this.xCoordinate = this.xCoordinate - this.sequenceOfMoves.get(this.sequenceOfMoves.size()-1).getDistance();
        }

        else if (this.sequenceOfMoves.get(this.sequenceOfMoves.size()-1).getMove() == Moves.BACKWARDS){
            this.xCoordinate = this.xCoordinate + this.sequenceOfMoves.get(this.sequenceOfMoves.size()-1).getDistance();
        }

        this.sequenceOfMoves.remove(this.sequenceOfMoves.size() - 1);
        this.sequenceSize--;
    }

    //Client has the option to remove all the moves and start fresh
    public void removeAllMoves() throws Exception {

        //Using my removeLastMove function to remove all moves
        while (!this.sequenceOfMoves.isEmpty()){
            removeLastMove();
        }


    }

    //Client has access to all moves. They must provide the index of the move
    //and the move at that index is returned. Client is not able to change the internal
    //code through this method
    public Moves getMove(int index) throws Exception {

        //If the sequence is empty, there are no moves
        if (sequenceOfMoves.size() == 0) {
            throw new Exception("Sequence is empty. Please add a sequence of moves using the RemoteControl");
        }

        //If the index is not valid, I raise an exception
        else if (index < 0 || index >= sequenceOfMoves.size()) {
            throw new Exception("Index is not valid. Please use index between 0 and " + (sequenceOfMoves.size() - 1));
        }

        //Returning the move
        return sequenceOfMoves.get(index).getMove();
    }

    //Client has access to the size of the sequence
    public int getSequenceSize() {
        int size = this.sequenceSize;
        return size;
    }

    //The RemoteControl will have a flyDrone method which will execute the sequence in the order added by the client
    public void flyDrone() throws Exception {

        //If there are no sequences in the remote, then the drone will not fly
        if (this.sequenceOfMoves.size() == 0) {
            throw new Exception("There are no moves in your remote control. Please add moves before executing the flyDrone command.");
        }

        //If the last move of the sequence is not LAND, I throw an exception and let the client know that the last move must be LAND
        else if (sequenceOfMoves.get(sequenceOfMoves.size() - 1).getMove() != Moves.LAND) {
            throw new Exception("Your drone's last move must be LAND!");
        } else {

            //The drone hasn't taken off yet, updating these coordinates to (0, 0)
            this.xCoordinate = 0;
            this.yCoordinate = 0;

            //Index to add next flight array to drones flight history
            int index = drone.getFlights(passwordForDrone).size();

            ArrayList<String> flightSequence = new ArrayList<>();

            //The first move is always TAKEOFF. Therefore,
            //This is the first move to be executed
            System.out.println("Taking Off");

            flightSequence.add("Taking Off");

            //Iterating through the moves in the remote control and executing them
            for (int i = 1; i < this.sequenceOfMoves.size() - 1; i++) {
                if (sequenceOfMoves.get(i).getMove() == Moves.BACKWARDS) {
                    System.out.println("Moving BACKWARDS by a distance of " + sequenceOfMoves.get(i).getDistance() + " units.");
                    flightSequence.add("Moving BACKWARDS by a distance of " + sequenceOfMoves.get(i).getDistance() + " units.");
                } else if (sequenceOfMoves.get(i).getMove() == Moves.FORWARDS) {
                    System.out.println("Moving FORWARDS by a distance of " + sequenceOfMoves.get(i).getDistance() + " units.");
                    flightSequence.add("Moving FORWARDS by a distance of " + sequenceOfMoves.get(i).getDistance() + " units.");
                } else if (sequenceOfMoves.get(i).getMove() == Moves.UP) {
                    System.out.println("Moving UP by a distance of " + sequenceOfMoves.get(i).getDistance() + " units.");
                     flightSequence.add("Moving UP by a distance of " + sequenceOfMoves.get(i).getDistance() + " units.");
                } else if (sequenceOfMoves.get(i).getMove() == Moves.DOWN) {
                    System.out.println("Moving DOWN by a distance of " + sequenceOfMoves.get(i).getDistance() + " units.");
                    flightSequence.add("Moving DOWN by a distance of " + sequenceOfMoves.get(i).getDistance() + " units.");
                    //Otherwise, the move is FOCUSANDCAPTURE
                } else {

                    System.out.println("Focusing on object... Capturing image. Saving image as " + sequenceOfMoves.get(i).getFileName());
                    flightSequence.add("Focusing on object... Capturing image. Saving image as " + sequenceOfMoves.get(i).getFileName());

                }
            }
            //The last move is always LAND. Therefore, it is executed last.
            System.out.println("Landing");
            flightSequence.add("Landing");

            //Adding flight sequence to history flights of drone
            drone.getFlights(passwordForDrone).add(flightSequence);

            //The drone has landed. Updating X and Y coordinates to (0, 0)
            this.xCoordinate = 0;
            this.yCoordinate = 0;
        }
    }
}
