import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a single Podcast, with at least a name and a host. Each Podcast aggregates episodes.
 */
public class Podcast {

    final private String aName;
    final private String aHost;

    private final List<Episode> aEpisodes = new ArrayList<>();

    final private static ArrayList<Podcast> listOfStoredPodcasts = new ArrayList<>();

    /**
     * Private constructor in order to prevent duplicate objects being created
     *
     * @param pName
     *          Podcast has a name
     * @param pHost
     *          Podcast has a host
     */
    private Podcast(String pName, String pHost){

        aName = pName;
        aHost = pHost;

    }


    /**
     *
     * @param pName
     *          Takes in the name of the podcast
     * @param pHost
     *          Takes in the name of the podcast
     * @return
     *          Returns the stored podcast if it has already been created
     *          Otherwise, a new podcast is created as this object does not yet exist
     */
    public static Podcast getPodcast(String pName, String pHost){


        for (int i=0; i<listOfStoredPodcasts.size(); i++){
            if (listOfStoredPodcasts.get(i).aName.equalsIgnoreCase(pName) && listOfStoredPodcasts.get(i).aHost.equalsIgnoreCase(pHost)){
                return listOfStoredPodcasts.get(i);
            }
        }

        //If podcast is not already stored, creating a new podcast
        Podcast newPodcast = new Podcast(pName, pHost);

        //Adding podcast to stored podcasts list
        listOfStoredPodcasts.add(newPodcast);

        //Returning new podcast
        return newPodcast;
    }


    /**
     * Add one episode to the podcast
     * @param pEpisode to be put into the podcast
     * @pre
     */
    protected void addEpisode(Episode pEpisode) {
        if(!aEpisodes.contains(pEpisode)) {
            aEpisodes.add(pEpisode);
        }
    }

    /**
     * retrieve one episode from the podcast
     * @param pIndex
     *
     */

    public Episode getEpisode(int pIndex) {

        if (pIndex < aEpisodes.size()){
            return aEpisodes.get(pIndex);
        }

        else{
            return null;
        }

    }

    /**
     *
     * @return
     *      Returns the number of episodes in the podcast
     */
    public int getNumberOfEpisodes(){
        return aEpisodes.size();
    }


    /**
     *
     * @return
     *      Returns the name of the podcast which is final
     */
    public String getName() {
        return aName;
    }

    /**
     *
     * @return
     *      Returns the name of the host of the podcast
     */
    public String getaHost() {
        return aHost;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Podcast podcast = (Podcast) o;
        return Objects.equals(aName, podcast.aName) && Objects.equals(aHost, podcast.aHost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aName, aHost);
    }
}