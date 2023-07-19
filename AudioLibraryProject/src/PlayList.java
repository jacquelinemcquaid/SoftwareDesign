import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a sequence of playables to play in FIFO order.
 */
public class PlayList implements Playable {

    private final List<Playable> aList = new LinkedList<>();
    final private String aName;
    private final int aNext;

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
        aNext = 0;
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
        aList.add(pPlayable);
    }


    @Override
    public void play() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayList playList = (PlayList) o;
        return aNext == playList.aNext && Objects.equals(aList, playList.aList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aList, aNext);
    }

    public List<Playable> getaList() {
        return aList;
    }

    public int getaNext() {
        return aNext;
    }
}
