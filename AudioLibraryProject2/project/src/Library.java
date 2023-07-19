import java.util.*;

/**
 * Represents an Audio library, with individual Song titles, Podcasts and playlists.
 */
public class Library {

    private final ArrayList<Song> aSongs = new ArrayList<>();
    private final ArrayList<Podcast> aPodcasts = new ArrayList<>();
    private final ArrayList<PlayList> aPlaylists = new ArrayList<>();

    private final String aLibraryName;
    private final Optional<String> aLibraryDescription;

    private static Library INSTANCE=null;

    //Using prototype design pattern to create default playable
    //If the client does not provide a default playable, I pre-set it
    private Playable aPrototype = new Song("Bohemian Rhapsody", "Queen");

    /**
     * Creates a library with description
     *
     * @param pLibraryName
     *            Podcast name of the library.
     * @param pLibraryDescription
     *            The description of the library.
     * @pre   pLibraryName!=null && pLibraryDescription!=null;
     */
    // Private constructor with description
    private Library(String pLibraryName, String pLibraryDescription){
        assert pLibraryName!=null && pLibraryDescription!=null;
        aLibraryName=pLibraryName;
        aLibraryDescription=Optional.of(pLibraryDescription);
    }

    /**
     * Creates a library without description
     *
     * @param pLibraryName
     *            Podcast name of the library.
     * @pre   pLibraryName!=null;
     */
    // Private constructor without description
    private Library(String pLibraryName){
        assert  pLibraryName!=null;
        aLibraryName=pLibraryName;
        aLibraryDescription=Optional.empty();
    }

    /**
     * Calls the constructor to create a library if the library has not been initialized.
     *
     * @param pLibraryName
     *            Podcast name of the library.
     * @pre pLibraryName!=null;
     */
    public static void createLibrary(String pLibraryName)
    {
        assert pLibraryName != null;
        if(INSTANCE==null) {
            INSTANCE = new Library(pLibraryName);
        }
    }

    /**
     * Calls the constructor to create a library if the library has not been initialized.
     *
     * @param pLibraryName
     *            Podcast name of the library.
     * @param pLibraryDescription
     *            The description of the library.
     * @pre pLibraryName !=null && pLibraryDescription!=null;
     */
    public static void createLibrary(String pLibraryName, String pLibraryDescription)
    {
        assert pLibraryName !=null && pLibraryDescription!=null;
        if(INSTANCE==null) {
            INSTANCE = new Library(pLibraryName, pLibraryDescription);
        }
    }

    /**
     * @return The single Library instance.
     */
    public static Library getLibrary(){
        return INSTANCE;
    }

    /**
     * Adds a Song to the library. Duplicate Songs aren't added twice.
     *
     * @param pSong
     *            the Song to add
     * @pre pSong!=null;
     */
    public void addSong(Song pSong) {
        assert pSong!=null;
        for(Song song: aSongs) {
            if (song.equals(pSong)) {  //equal
                System.out.println("This song "+song.getTitle()+" is already present in the library");
                return;
            }
        }
            aSongs.add(pSong);
    }

    /**
     * Adds a PlayList to the library. All Songs from the list are also added as individual Songs to the library.
     *
     * @param pList
     *            the PlayList to add
     * @pre pList!=null;
     */
    public void addPlayList(PlayList pList) {
        assert pList!=null;
        for(PlayList playlist: aPlaylists) {
            if (playlist.equals(pList)) {  //equal
                System.out.println("This playlist "+playlist.getName()+" is already present in the library");
                return;
            }
        }
        aPlaylists.add(pList);
    }

    /**
     * Adds a Podcast to the library. All Episodes from the list are also added as individual episodes to the library.
     *
     * @param pPodcast
     *            the Podcast to add
     * @pre pPodcast!=null;
     */
    public void addPodcast(Podcast pPodcast){
        assert pPodcast != null;
        for (Podcast podcast : aPodcasts) {
            if (podcast.equals(pPodcast)) {  //equal
                System.out.println("This podcast "+podcast.getName()+" is already present in the library");
                return;
            }
        }
        aPodcasts.add(pPodcast);
    }

    /**
     * Sets the default playable
     * @param pPrototype
     *      the default prototype set by the client
     *
     * @pre pPrototype != null
     */
    public void setPrototype(Playable pPrototype){
        assert pPrototype != null;
        aPrototype = pPrototype;

    }

    /**
     *
     * @return
     *      returning a copy of the prototype
     */
    public Playable createPlayable(){

        return aPrototype.copy();
    }

    /**
     * Getter for the final library name
     * @return
     *      return the name of the library
     */
    public String getaLibraryName() {
        return aLibraryName;
    }
}
