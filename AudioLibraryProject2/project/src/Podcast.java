import java.util.*;

/**
 * Represents a single Podcast, with at least a name and a host. Each Podcast aggregates episodes.
 */
public class Podcast{

    private final String aName;
    private final String aHost;
    private final List<Episode> aEpisodes;

    /**
     * Creates a new Podcast.
     *
     * @param pName
     *            the name of the podcast
     * @param pHost
     *            the host of the podcast
     * @pre pName!=null && pHost!=null;
     */
    public Podcast(String pName, String pHost)
    {
        assert pName!=null && pHost!=null;
        aName=pName;
        aHost=pHost;
        aEpisodes= new ArrayList<>();
    }

    /**
     * @return A copied list of all podcast episodes.
     */
    public ArrayList<Episode> getAllEpisodes(){
        return new ArrayList<>(aEpisodes);
    }

    /**
     * Add one episode to the podcast if it is not already in the podcast
     * @param pEpisode to be put into the podcast
     * @pre pEpisode!=null;
     */
    protected void addEpisode(Episode pEpisode) {
        assert pEpisode!=null;
        if(!aEpisodes.contains(pEpisode)) {
            aEpisodes.add(pEpisode);
        }
    }

    /**
     * Create a new episode for this Podcast and adds it to the list
     * @param pTitle
     *          the title of the episode
     * @return the created episode
     */
    public Episode createAndAddEpisode(String pTitle) {
        assert pTitle != null;
        Episode episode = new Episode(pTitle, aEpisodes.size() + 1);
        addEpisode(episode);
        return episode;
    }

    /**
     * retrieve one episode from the podcast
     * @param pIndex
     *          Index of episode to retrieve
     * @return The Episode retrieved
     * @pre aEpisodes.contains(pIndex);
     */
    public Episode getEpisode(int pIndex) {
        assert aEpisodes.contains(pIndex);
        return aEpisodes.get(pIndex);
    }

    /**
     * @return The name of the podcast.
     */
    public String getName() {
        return aName;
    }

    /**
     * @return The host of the podcast.
     */
    public String getHost() {
        return aHost;
    }

    /**
     * Checks is two podcasts are equal based on name and host
     *
     * @param o
     *            The object to compare a podcast to
     * @return    This method returns true if the podcast is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Podcast podcast = (Podcast) o;
        return aName.equalsIgnoreCase(podcast.aName) && aHost.equalsIgnoreCase(podcast.aHost);
    }

    /**
     * Equal podcasts have the same hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(aName, aHost);
    }

    /**
     * Represents a single episode, with at least a title, and an episode number.
     */
    public class Episode implements Playable {

        private final String aTitle;
        private final int aEpisodeNumber;

        /**
         * Creates an episode
         *
         * @param pTitle
         *            title of the episode
         * @param pEpisodeNumber
         *            the episode number that identifies the episode
         * @pre   pPodcast != null && pTitle!=null
         */
        public Episode(String pTitle, int pEpisodeNumber) {
            assert pTitle != null;
            aTitle = pTitle;
            aEpisodeNumber = pEpisodeNumber;
        }

        /**
         * @return The title of the episode.
         */
        public String getTitle() {
            return aTitle;
        }

        /**
         * @return The number of the episode.
         */
        public int getEpisodeNumber() {
            return aEpisodeNumber;
        }

        @Override
        public void play() {
            // Just a stub.
            System.out.println("Now playing " + Podcast.this.getName() + " [" + aEpisodeNumber + "]: " + aTitle);
        }

        /**
         * Copying the current Playable object
         * @return
         *      Returning the copy
         */
        @Override
        public Playable copy() {

            Episode copy = new Episode(this.aTitle, this.aEpisodeNumber);

            return copy;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Episode episode = (Episode) o;
            return aEpisodeNumber == episode.aEpisodeNumber && Objects.equals(aTitle, episode.aTitle);
        }

        @Override
        public int hashCode() {
            return Objects.hash(aTitle, aEpisodeNumber);
        }
    }
}