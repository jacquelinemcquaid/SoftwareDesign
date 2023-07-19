import java.util.*;

//Jacqueline McQuaid

public class DroneMoves {

    //The fields are final which makes them immutable
    final private String fileName;
    final private int distance;
    final private Moves move;

    //I have three constructors depending on if the move requires a distance, a fileName, or neither
    //Note: If the move does not have a distance, I set it to -1. If the move does not have a fileName, I set it to ""
    public DroneMoves(Moves pMove, int pDistance) throws Exception {

        //If the input move is TAKEOFF, LAND, or FOCUSANDCAPTURE, it does not take in a distance value
        if (pMove == Moves.TAKEOFF || pMove == Moves.LAND || pMove == Moves.FOCUSANDCAPTURE)
            throw new Exception("The only moves that require a distance parameter are UP, DOWN, FORWARDS, BACKWARDS.");
        else{
            distance = pDistance;
            move  = pMove;
            fileName = "";
        }
    }

    //If the move is FOCUSANDCAPTURE, it will require a fileName
    public DroneMoves(Moves pMove, String pFileName) throws Exception {

        //If the move is not FOCUSANDCAPTURE, I throw an exception and give the client an error message
        if (pMove != Moves.FOCUSANDCAPTURE){
            throw new Exception("The only move that requires a file name is FOCUSANDCAPTURE.");
        }
        //If the filename contains .JPG, .PNG, .RAW, or .PDF then it has a valid extension type
        else if (pFileName.contains(".JPG") || pFileName.contains(".PNG") ||pFileName.contains(".RAW") ||pFileName.contains(".PDF")){
            fileName = pFileName;
            move = pMove;
            distance = -1;
        }
        //Otherwise, I throw an error letting the client know that the filetype is wrong
        else{
            throw new Exception("File format must be one of JPG, PDF, RAW, PNG.");
        }
    }

    //If the move is of the type TAKEOFF OR LAND, then the only parameter DroneMoves requires is the move parameter
    public DroneMoves(Moves pMove) throws Exception {

        //If the move is not TAKEOFF or LAND, then I throw an error message
        if (pMove == Moves.UP || pMove == Moves.DOWN || pMove == Moves.FORWARDS || pMove == Moves.BACKWARDS || pMove == Moves.FOCUSANDCAPTURE){
            throw new Exception("If the move you are adding is UP, DOWN, FORWARDS, or BACKWARDS, it requires a distance. If it is FOCUSANDCAPTURE, it requires a file name.");
        }

        else{
            move = pMove;
            distance = -1;
            fileName = "";
        }
    }

    //The DroneMoves class only has getters (and the fields are final)
    //The getters give the remoteControl access to the fields of each move
    //There are no setters necessary for this class
    public String getFileName() {
        return fileName;
    }

    public int getDistance() {
        return distance;
    }

    public Moves getMove() {
        return move;
    }
}
