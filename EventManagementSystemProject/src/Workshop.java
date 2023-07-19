import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;

//Type of Event
public class Workshop extends AbstractEvent {

    private ArrayList<Workshop> prerequisites;

    /**
     * Constructor
     *
     * @param pName
     *      Name of the workshop
     * @param pLocation
     *      Location of the workshop
     * @param pDate
     *      Date of the workshop
     * @param pPerPersonTicketPrice
     *      Per person price for the workshop
     * @param pTotalTickets
     *      Total tickets for the workshop
     * @param pPrerequisites
     *      List of Workshop pre-requisites
     */
    public Workshop(String pName, Location pLocation, LocalDate pDate, double pPerPersonTicketPrice, int pTotalTickets, ArrayList<Workshop> pPrerequisites) {

        //Calling the super constructor
        //Super class checks inputs
        super(pName, pLocation, pDate, pPerPersonTicketPrice, pTotalTickets);

        //Checking to make sure inputs are valid
        assert pPrerequisites != null;

        prerequisites = new ArrayList<>();

        //Copying the elements into the prerequisites field
        for (int i=0; i<pPrerequisites.size(); i++){
            this.prerequisites.add(pPrerequisites.get(i));
        }
    }

    public Workshop(String pName, LocalDate pDate, ArrayList<Workshop> pPrerequisites) {

        //Calling the super constructor
        super(pName, pDate);

        //Checking to make sure inputs are valid
        assert pPrerequisites != null;

        prerequisites = new ArrayList<>();

        //Copying the elements into the prerequisites field
        for (int i=0; i<pPrerequisites.size(); i++){
            this.prerequisites.add(pPrerequisites.get(i));
        }
    }

    /**
     * Method to add prerequisite
     * @param workshop
     *      Taking in a Workshop object and adding it to the pre-requisites
     */
    public void addPrerequisite(Workshop workshop){

        assert workshop != null;

        this.prerequisites.add(workshop);
    }

    /**
     * Method to remove prerequisite
     * @param workshop
     *      Taking in a Workshop object and removing it from the
     *      prerequisites list if it is currently a prerequisite
     */
    public void removePrerequisite(Workshop workshop){

        assert workshop != null;

        //If the workshop exists, removing it from the list of pre-reqs
        if (this.prerequisites.contains(workshop)){
            int index = this.prerequisites.indexOf(workshop);
            this.prerequisites.remove(index);
        }
    }

    /**
     * Method to get prerequisite list
     * @return
     *      Returning a copy of the prerequisites
     */
    public ArrayList<Workshop> getPrerequisites(){

        //Creating a copy array
        ArrayList<Workshop> copy = new ArrayList<>();

        for (int i=0; i<this.prerequisites.size(); i++){
            copy.add(this.prerequisites.get(i));
        }

        //Returning a copy of the prerequisites
        return copy;
    }

    /**
     * This method accepts a visitor to implement the visitor design pattern
     * @param visitor
     *      Takes as input a visitor object and implements the visit method
     */
    @Override
    public double acceptVisitor(Visitor visitor) {

        assert visitor != null;

        return visitor.visitWorkshop(this);
    }

}
