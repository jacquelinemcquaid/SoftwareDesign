import java.util.Objects;

/**
 * Represents a single episode, with at least a title, and an episode number.
 */
public class Episode implements Playable {

    private final Podcast aPodcast;
    private final String aTitle;
    private final int aEpisodeNumber;

    /**
     * Creates an episode
     *
     * @param pPodcast
     *            Podcast that this episode is a part of
     * @param pTitle
     *            title of the episode
     * @param pEpisodeNumber
     *            the episode number that identifies the episode
     * @pre   pPodcast != null && pTitle!=null
     *              the Podcast and Title cannot be null
     * @throws IllegalArgumentException
     *              Throws an exception if conditions are not met
     */
    Episode(Podcast pPodcast, String pTitle, int pEpisodeNumber) {
        assert (pPodcast != null) && (pTitle != null);

        aPodcast = pPodcast;
        aTitle = pTitle.toLowerCase();
        aEpisodeNumber = pEpisodeNumber;
        aPodcast.addEpisode(this);
    }


    public Podcast getaPodcast() {
        return aPodcast;
    }

    public String getaTitle() {
        return aTitle;
    }

    public int getaEpisodeNumber() {
        return aEpisodeNumber;
    }

    @Override
    public void play() {
        System.out.println("Now playing " + aPodcast.getName() + " [" + aEpisodeNumber + "]: " + aTitle);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Episode episode = (Episode) o;
        return aEpisodeNumber == episode.aEpisodeNumber && Objects.equals(aPodcast, episode.aPodcast) && Objects.equals(aTitle, episode.aTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aPodcast, aTitle, aEpisodeNumber);
    }
}
