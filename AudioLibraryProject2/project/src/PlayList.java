import java.util.*;

/**
 * Represents a sequence of playables to play in FIFO order.
 */
public class PlayList implements Playable {

    //Original PlayList fields
    private List<Playable> aList = new LinkedList<>();
    private String aName;

    //Two stacks: one for undo commands and one for redo commands
    private Stack<stateModifying> listOfCommandsUndo = new Stack<>();
    private Stack<stateModifying> listOfCommandsRedo = new Stack<>();

    //Keeping track of whether the last method was a state modifying method
    private boolean lastMethodStateModifying;
    private stateModifyingMethods lastMethod;

    //The only variables we need to keep track of in the case of redo are those of the removePlayable method
    private int removePlayableIndex;

    //Keeping track of whether a state modifying method was called after the undo method
    private boolean lastMethodUndo;
    private boolean stateModifyingAfterUndo;

    /**
     * Creates a new empty playlist.
     *
     * @param pName
     *            the name of the list
     * @pre pName!=null;
     */
    public PlayList(String pName) {
        assert pName != null;
        aName = pName;
    }


    /**
     * Adds a playable at the end of this playlist.
     *
     * @param pPlayable
     *            the content to add to the list
     * @pre pPlayable!=null;
     */

    public void addPlayable(Playable pPlayable) {
        assert pPlayable != null;

        //Adding the current state to the undo stack (before adding the new playable)
        listOfCommandsUndo.push(new stateModifying(this));

        //Adding new playable to the list
        aList.add(pPlayable);

        //Updating fields
        lastMethodStateModifying = true;
        lastMethod = stateModifyingMethods.ADDPLAYABLE;

        if (lastMethodUndo == true) {
            stateModifyingAfterUndo = true;
        }

        lastMethodUndo = false;
    }


    /**
     * remove a playable from the Playlist given its index
     * @param pIndex
     *          the index of playable to be removed
     * @return the removed playable
     */

    public Playable removePlayable(int pIndex) {

        assert pIndex >= 0 && pIndex < aList.size();

        //Adding current state to the stack of undo commands
        listOfCommandsUndo.push(new stateModifying(this));

        //Updating fields
        lastMethodStateModifying = true;
        lastMethod = stateModifyingMethods.REMOVEPLAYABLE;

        removePlayableIndex = pIndex;

        if (lastMethodUndo == true) {
            stateModifyingAfterUndo = true;
        }

        lastMethodUndo = false;

        return aList.remove(pIndex);
    }

    /**
     * @return The name of the playlist.
     */
    public String getName() {

        //Updating fields
        lastMethodStateModifying = false;
        lastMethodUndo = false;

        return aName;
    }

    /**
     * modify the name of the playlist
     * @param pName
     *          the new name of the playlist
     */

    public void setName(String pName) {
        assert pName != null;

        //Adding current state to the stack to undo
        listOfCommandsUndo.push(new stateModifying(this));
        this.aName = pName;

        //Updating fields
        lastMethodStateModifying = true;
        lastMethod = stateModifyingMethods.SETNAME;

        if (lastMethodUndo == true) {
            stateModifyingAfterUndo = true;
        }

        lastMethodUndo = false;
    }

    /**
     * Iterating through the playlist to play playable content.
     */
    @Override
    public void play() {
        for(Playable playable:aList){
            playable.play();
        }

        //Updating fields
        lastMethodStateModifying = false;
        lastMethodUndo = false;
    }

    /**
     * Copying the current Playable object
     * @return
     *      Returning the copy
     */
    @Override
    public Playable copy() {

        PlayList copy = new PlayList(this.aName);

        copy.aList = this.aList;

        lastMethodStateModifying = false;

        lastMethodUndo = false;

        return copy;
    }

    /**
     * change the order how playlist play the playables it contains
     */

    public void shuffle() {

        //Adding current state to the undo stack
        listOfCommandsUndo.push(new stateModifying(this));

        Collections.shuffle(aList);

        lastMethodStateModifying = true;
        lastMethod = stateModifyingMethods.SHUFFLE;

        if (lastMethodUndo == true) {
            stateModifyingAfterUndo = true;
        }

        lastMethodUndo = false;
    }


    /**
     * Checks is two playlists are equal based on playable objects and their order
     *
     * @param o
     *            The object to compare a playlist to
     * @return    This method returns true if the playlist is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayList playList = (PlayList) o;
        return this.aList.equals(playList.aList);
    }

    /**
     * Equal playlists have the same hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(aList);
    }



    public void undo() {

        lastMethodUndo = true;

        //We enter this code only if there are methods to be undone
        if (!listOfCommandsUndo.isEmpty()){

            //Adding the undone command to the list of commands to redo
            listOfCommandsRedo.push(new stateModifying(this));

            stateModifying command = listOfCommandsUndo.pop();

            aList.clear();

            for (int i = 0; i < command.getPlayableObjects().size(); i++) {
                aList.add(command.getPlayableObjects().get(i));
            }

            aName = command.getName();

        }

        lastMethodStateModifying = false;

    }

    public void redo() {

        if (lastMethodStateModifying == true || stateModifyingAfterUndo == true) {

            if (lastMethod == stateModifyingMethods.ADDPLAYABLE) {

                //Adding the current playlist to the list of commands to undo
                listOfCommandsUndo.push(new stateModifying(this));

                //Finding the last index and the playable at that index
                int index = this.aList.size()-1;
                Playable playable = this.aList.get(index);

                //Adding the playable to the list again
                this.aList.add(playable);

            }

            else if (lastMethod == stateModifyingMethods.REMOVEPLAYABLE){

                //Adding the current playlist to the list of commands to undo
                listOfCommandsUndo.push(new stateModifying(this));

                if (this.aList.size() > removePlayableIndex){
                    this.removePlayable(removePlayableIndex);
                }

                else {
                    //The redo command was not executed
                    listOfCommandsUndo.pop();
                }

            }

            else if (lastMethod == stateModifyingMethods.SHUFFLE){
                //Adding the current playlist to the list of commands to undo
                listOfCommandsUndo.push(new stateModifying(this));

                this.shuffle();

            }

            else if (lastMethod == stateModifyingMethods.SETNAME){

                //Adding the current playlist to the list of commands to undo
                listOfCommandsUndo.push(new stateModifying(this));

                //Don't have to call any methods because the name is already set

                //Resetting these fields
                lastMethodUndo = false;
                stateModifyingAfterUndo = false;
            }
        }

        else {

            if (!listOfCommandsRedo.isEmpty()){

                //The current state is pushed to the undo list
                listOfCommandsUndo.push(new stateModifying(this));

                stateModifying command = listOfCommandsRedo.pop();

                aList.clear();

                for (int i = 0; i < command.getPlayableObjects().size(); i++) {
                    aList.add(command.getPlayableObjects().get(i));
                }

                aName = command.getName();

            }

        }

    }

    public List<Playable> getaList() {

        List<Playable> copy = new LinkedList<>();

        for (int i=0; i<aList.size(); i++){
            copy.add(aList.get(i));
        }
        return copy;
    }
}
