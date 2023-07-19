import java.util.*;

public class stateModifying {

    final private List<Playable> playableObjects = new LinkedList<>();
    final private String name;

    public stateModifying(Playable pPlayable){

        //pPlayable will always be an instance of PlayList
        if (pPlayable instanceof PlayList){

            for (int i=0; i<((PlayList) pPlayable).getaList().size(); i++){
                playableObjects.add(((PlayList) pPlayable).getaList().get(i));
            }

            name = ((PlayList) pPlayable).getName();
        }

        //We will never reach this code because only PlayList objects are used
        else{
            name = null;
        }
    }

    public List<Playable> getPlayableObjects() {
        return playableObjects;
    }

    public String getName() {
        return name;
    }
}
