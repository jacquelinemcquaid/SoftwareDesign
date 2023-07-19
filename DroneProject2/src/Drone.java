import java.util.ArrayList;

//Drone class implements Movement interface
//(moves are executed from the drone class)
public class Drone implements Movement {

    //I have a yCoordinate field to keep track of the drone's location to prevent a crash
    final private String name;
    private int yCoordinate;

    /**
     * Constructor
     * @param name of the drone
     */
    public Drone(String name) {
        this.name = name;

        //Initializing y coordinate
        yCoordinate = 0;
    }

    /**
     * Name getter
     * @return drone name
     */
    public String getName() {
        return this.name;
    }

    @Override
    public void executeFlight(Flight flight) throws Exception {

        //Storing a copy of the y-coordinate
        int copyYCoordinate = yCoordinate;

        //Determining whether flight can execute (y-coordinate cannot go below 0)
        for (int i=0; i<flight.getListOfTricks().size(); i++){
            for (int j=0; j<flight.getListOfTricks().get(i).getMovesSequence().size(); j++){
                if (flight.getListOfTricks().get(i).getMovesSequence().get(j).getaDirection() == Direction.DOWN){
                    yCoordinate = yCoordinate - flight.getListOfTricks().get(i).getMovesSequence().get(j).getaDistance();

                    if (yCoordinate < 0){
                        //Resetting y-coordinate to original value since the flight was not executed
                        yCoordinate = copyYCoordinate;
                        throw new Exception("This flight is not valid. Drone will crash to the ground!");
                    }
                }

                else if (flight.getListOfTricks().get(i).getMovesSequence().get(j).getaDirection() == Direction.UP){
                    yCoordinate = yCoordinate + flight.getListOfTricks().get(i).getMovesSequence().get(j).getaDistance();
                }
            }
        }

        //Iterating through the list of tricks from the object flight and executing the movements
        for (int i=0; i < flight.getListOfTricks().size(); i++){
            System.out.println("Executing the trick " + flight.getListOfTricks().get(i).getaTrick());

            //Iterating through the moves of each trick
            for (int j=0; j<flight.getListOfTricks().get(i).getMovesSequence().size(); j++){

                System.out.println("Moving " + flight.getListOfTricks().get(i).getMovesSequence().get(j).getaDirection() + " by a distance of " + flight.getListOfTricks().get(i).getMovesSequence().get(j).getaDistance() + " with a " + flight.getListOfTricks().get(i).getMovesSequence().get(j).getaSpeed() + " speed");

            }

            //If the recording is not OFF, saving the recording with the specified filetype
            if (flight.getListOfTricks().get(i).getaRecording() != Recording.OFF){
                System.out.println("This trick has been recorded. Saving recording as filetype " + flight.getListOfTricks().get(i).getaRecording());
            }

            //Adding an extra line between moves for readability
            System.out.println();
        }
    }

    @Override
    public void executeMove(DroneMove move) throws Exception {

        //Storing a copy of the y-coordinate
        int copyYCoordinate = yCoordinate;

        //Checking if move is valid
        if (move.getaDirection() == Direction.DOWN){
            yCoordinate = yCoordinate - move.getaDistance();

            if (yCoordinate < 0){
                //Resetting yCoordinate to previous value since the flight was not executed
                yCoordinate = copyYCoordinate;
                throw new Exception("Unable to execute move. Drone will crash to the ground!");
            }
        }

        //Executing the move
        System.out.println("Moving " + move.getaDirection() + " by a distance of " + move.getaDistance() + " with a " + move.getaSpeed() + " speed");

        if (move.getaRecording() != Recording.OFF){
            System.out.println("This trick has been recorded. Saving recording as filetype " + move.getaRecording());
        }

    }

    @Override
    public void executeTrick(DroneTrick trick) throws Exception {

        int copyYCoordinate = yCoordinate;

        //Checking if trick is valid
        for (int i=0; i<trick.getMovesSequence().size(); i++){
            if (trick.getMovesSequence().get(i).getaDirection() == Direction.DOWN){
                yCoordinate = yCoordinate - trick.getMovesSequence().get(i).getaDistance();

                if (yCoordinate < 0){
                    //Resetting yCoordinate to original value since the trick was not executed
                    yCoordinate = copyYCoordinate;
                    throw new Exception("Unable to execute trick. Drone will crash to the ground!");
                }
            }

            else if (trick.getMovesSequence().get(i).getaDirection() == Direction.UP){
                yCoordinate = yCoordinate + trick.getMovesSequence().get(i).getaDistance();
            }
        }

        //Creating a new array list called moves
        ArrayList<DroneMove> moves = new ArrayList<>();

        //Setting the moves array equal to the moves sequence of the trick
        moves = trick.getMovesSequence();

        //Printing out the moves of the trick
        for (int i=0; i< moves.size(); i++){
            System.out.println("Moving " + moves.get(i).getaDirection() + " by a distance of " + moves.get(i).getaDistance() + " with a " + moves.get(i).getaSpeed() + " speed");
        }
    }
}
