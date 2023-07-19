import java.util.*;

//Jacqueline McQuaid

public class Drone {

    //The drone stores a 2D array with the previous flights it has taken
    private ArrayList<ArrayList<String>> droneFlights;

    public Drone(){

        //Initializing the field
        ArrayList<ArrayList<String>> flightHistory = new ArrayList<>();
        droneFlights = flightHistory;

    }

    public ArrayList<ArrayList<String>> getFlights(String password) throws Exception {

        if (password.equals("Remote is setting this coordinate.")){
            return this.droneFlights;
        }
        else{
            throw new Exception("Only the remote control can access the flights the drone has taken.");
        }

    }

    public void viewFlightHistory(){
        for (int i=0;i<this.droneFlights.size(); i++){
            System.out.println(this.droneFlights.get(i));
        }
    }
}
