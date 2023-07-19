import java.util.ArrayList;

public class DroneTrick {

    //Fields are final
    final private Recording aRecording;
    final private Tricks aTrick;
    final private ArrayList<DroneMove> movesSequence;
    final private ArrayList<Direction> uniqueDirections;

    //This constructor is for the TakeOff or Land tricks as the client does not control the
    //distances or speeds (Takeoff and Land should be the same every time)
    public DroneTrick(Recording pRecording, Tricks pTrick) throws Exception {

        if (pTrick == Tricks.PUCKER){
            throw new Exception("If you would like to create a pucker trick, please provide the 6 distance and 6 speed parameters.");
        }
        aRecording = pRecording;
        aTrick = pTrick;
        movesSequence = new ArrayList<>();

        //Initializing the unique directions array
        uniqueDirections = new ArrayList<>();

        //Adding the moves that represent the trick to the movesSequence
        if (pTrick == Tricks.TAKEOFF){

            //If the trick is takeoff, the drone moves up at low speed, then up at moderate speed
            DroneMove move1 = new DroneMove(Direction.UP, 5, Speed.LOW, aRecording);
            DroneMove move2 = new DroneMove(Direction.UP, 5, Speed.MODERATE, aRecording);

            //Adding the moves to the sequence
            movesSequence.add(move1);
            movesSequence.add(move2);

            uniqueDirections.add(Direction.UP);
        }

        else if (pTrick == Tricks.LAND){

            //If the trick is land, the client does not control the distances or speeds as landing is
            //the same every time
            DroneMove move1 = new DroneMove(Direction.DOWN, 5, Speed.MODERATE, aRecording);
            DroneMove move2 = new DroneMove(Direction.DOWN, 5, Speed.LOW, aRecording);


            //Adding the moves to the sequence
            movesSequence.add(move1);
            movesSequence.add(move2);


            //Adding the unique directions to the directions array
            uniqueDirections.add(Direction.DOWN);

        }
    }

    //This constructor is for the pucker trick. The client inputs speed and distance parameters,
    //unlike Takeoff or land
    public DroneTrick(Recording pRecording, Tricks pTrick, int distanceUp, Speed speedUp, int distanceLeft1, Speed speedLeft1, int distanceLeft2, Speed speedLeft2, int distanceLeft3, Speed speedLeft3, int distanceLeft4, Speed speedLeft4, int distanceDown, Speed speedDown) throws Exception {

        if (pTrick != Tricks.PUCKER){
            throw new Exception("You cannot control the speed and distance for the TakeOff or Land tricks.");
        }

        //Initializing field variables
        aRecording = pRecording;
        aTrick = pTrick;
        movesSequence = new ArrayList<>();
        uniqueDirections = new ArrayList<>();

        //If the move is pucker, the drone moves up, turns left, turns left, turns left
        //turns left, then flys down. The client decides the speeds and distances
        DroneMove move1 = new DroneMove(Direction.UP, distanceUp, speedUp, aRecording);
        DroneMove move2 = new DroneMove(Direction.LEFT, distanceLeft1, speedLeft1, aRecording);
        DroneMove move3 = new DroneMove(Direction.LEFT, distanceLeft2, speedLeft2, aRecording);
        DroneMove move4 = new DroneMove(Direction.LEFT, distanceLeft3, speedLeft3, aRecording);
        DroneMove move5 = new DroneMove(Direction.LEFT, distanceLeft4, speedLeft4, aRecording);
        DroneMove move6 = new DroneMove(Direction.DOWN, distanceDown, speedDown, aRecording);

        //Adding the moves to the sequence
        movesSequence.add(move1);
        movesSequence.add(move2);
        movesSequence.add(move3);
        movesSequence.add(move4);
        movesSequence.add(move5);
        movesSequence.add(move6);

        //Adding the unique directions to the directions array
        uniqueDirections.add(Direction.UP);
        uniqueDirections.add(Direction.LEFT);
        uniqueDirections.add(Direction.DOWN);
    }


    public Recording getaRecording() {
        return aRecording;
    }

    public Tricks getaTrick() {
        return aTrick;
    }

    public ArrayList<Direction> getUniqueDirections() {

        ArrayList<Direction> copiedDirections = new ArrayList<>();

        //Creating a copy of the directions array and returning the copy
        for (int i=0; i<uniqueDirections.size(); i++){
            copiedDirections.add(uniqueDirections.get(i));
        }
        return copiedDirections;
    }

    public ArrayList<DroneMove> getMovesSequence() {

        ArrayList<DroneMove> copiedMoves = new ArrayList<>();

        //Returning a copy of the array list of moves so the client can't add any moves
        //to the internal array
        for (int i=0; i<movesSequence.size(); i++){
            copiedMoves.add(movesSequence.get(i));
        }
         return copiedMoves;
    }
}
