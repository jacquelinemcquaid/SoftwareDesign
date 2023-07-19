public class NumberOfVIPsVisitor implements Visitor {

    /**
     * Method to visit the Concert class
     * @param concert
     *      Concert object
     * @return
     *      Return the size of the VIP list
     */
    @Override
    public double visitConcert(Concert concert) {
        return concert.getVIPs().size();
    }

    /**
     * Method to visit the Workshop class
     * @param workshop
     *      Workshop object
     * @return
     *      Return 0 because workshop does not have a VIPs list
     */
    @Override
    public double visitWorkshop(Workshop workshop) {
        //Workshop does not have any VIPs
        return 0;
    }

    /**
     * Method to visit the Gala class
     * @param gala
     *      Gala object
     * @return
     *      Return the size of the VIP list
     */
    @Override
    public double visitGala(Gala gala) {
        return gala.getVIPS().size();
    }

    /**
     * Method to visit the Screening class
     * @param screening
     *      Screening object
     * @return
     *      Return 0 because the Screening class does not have a VIPs list
     */
    @Override
    public double visitScreening(Screening screening) {
        //Screening does not have VIPs
        return 0;
    }
}
