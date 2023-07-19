public class ExpectedProfitVisitor implements Visitor {

    private double aConcertpercentage = 60;
    private double aWorkshopPercentage = 50;
    private double aGalaPercentage = 30;
    private double aScreeningPercentage = 10;

    /**
     * Method to visit the Concert class
     * @param concert
     *      Concert object
     * @return
     *      Return the expected profit of the concert
     */
    @Override
    public double visitConcert(Concert concert) {

        return concert.getPrice() * concert.getNumTickets() * (this.aConcertpercentage / 100);

    }

    /**
     * Method to visit the Workshop class
     * @param workshop
     *      Workshop object
     * @return
     *      Return the expected profit of the workshop
     */
    @Override
    public double visitWorkshop(Workshop workshop) {

        return workshop.getPrice() * workshop.getNumTickets() * (this.aWorkshopPercentage / 100);

    }

    /**
     * Method to visit the Gala class
     * @param gala
     *      Gala object
     * @return
     *      Return the expected gala profit
     */
    @Override
    public double visitGala(Gala gala) {

        return gala.getPrice() * gala.getNumTickets() * (this.aGalaPercentage / 100);

    }

    /**
     * Method to visit the Screening class
     * @param screening
     *      Screening object
     * @return
     *      Return the expected screening profit
     */
    @Override
    public double visitScreening(Screening screening) {

        return screening.getPrice() * screening.getNumTickets() * (this.aScreeningPercentage / 100);

    }

}
