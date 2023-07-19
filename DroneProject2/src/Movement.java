public interface Movement {
    /**
     * Execute a drone movement
     */

    //A drone movement can be defined as a Flight (list of tricks), a DroneMove, or a DroneTrick
    //Therefore we can execute three different types of movements
    void executeFlight(Flight flight) throws Exception;

    void executeMove(DroneMove move) throws Exception;

    void executeTrick(DroneTrick trick) throws Exception;
}
