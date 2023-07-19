public class DroneMove {

    //Fields are final (cannot be changed)
    final private Direction aDirection;
    final private int aDistance;
    final private Speed aSpeed;
    final private Recording aRecording;


    //Constructor: client inputs the direction, speed and recording filetype
    public DroneMove(Direction pDirection, int pDistance, Speed pSpeed, Recording pRecording) throws Exception {
        aDirection = pDirection;
        aDistance = pDistance;
        aSpeed = pSpeed;
        aRecording = pRecording;

        //Distance value must be positive
        if (pDistance <= 0){
            throw new Exception("Distance must be a positive value.");
        }
    }

    //Getters for all the fields. The fields are private so the client is not able to change the internal code
    public Direction getaDirection() {
        return aDirection;
    }

    public int getaDistance() {
        return aDistance;
    }

    public Speed getaSpeed() {
        return aSpeed;
    }

    public Recording getaRecording() {
        return aRecording;
    }
}
