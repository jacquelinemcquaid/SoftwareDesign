import java.util.ArrayList;

/**
 * Represents an Audio library, with individual Song titles, Podcasts and playlists.
 */
public class Library {

    /**
     * Creates a single instance of a Library
     * Adds your design of fields for Library
     */
    private static Library currentLibrary = null;

    final private String aName;
    final private String aNaturalLanguageDescription;
    final private ArrayList<Song> listOfSongs;
    final private ArrayList<Podcast> listOfPodcasts;
    final private ArrayList<PlayList> listOfPlaylists;
    final private ArrayList<Episode> listOfEpisodes;


    /**
     * Constructor is private in order to create a single instance of the class
     *  @param pName
     *          The name of the library
     * @param pNaturalLanguageDescription
     *          Natural language description of the library
     */
    private Library(String pName, String pNaturalLanguageDescription) {

        aName = pName;
        aNaturalLanguageDescription = pNaturalLanguageDescription;

        //Initializing all lists holding songs, podcasts, playlists, and episodes
        listOfSongs = new ArrayList<>();
        listOfPodcasts = new ArrayList<>();
        listOfPlaylists = new ArrayList<>();
        listOfEpisodes = new ArrayList<>();
    }


    /**
     * Client can set the library name and natural language description if it has not already been created
     *
     * @param pName
     *          Client determines the name of their library
     * @param pNaturalLanguageDescription
     *          Client determines the natural language description
     * @return
     *          The instance of the library is returned
     * @throws Exception
     *          If the client has already created a library (there is already an instance), an error is thrown
     */
    public static Library setLibrary(String pName, String pNaturalLanguageDescription) throws Exception{

        if (currentLibrary == null){
            currentLibrary = new Library(pName, pNaturalLanguageDescription);
        }

        else{
            throw new Exception("Your library already exists. Please use getLibrary to access your library.");
        }

        //Returning currentLibrary
        return currentLibrary;
    }

    /**
     * Constructor that does not have a natural language description
     * @param pName
     *      Takes the name of the library as a parameter
     * @return
     *      If no instance of library exists, the single instance of the library is created
     *      and the NaturanLanguageDescription is assigned a value of null. The Library instance is returned
     *
     * @throws Exception
     *      If there is already a Library instance, an Exception is thrown
     */
    public static Library setLibrary(String pName) throws Exception{

        if (currentLibrary == null){
            currentLibrary = new Library(pName, null);
        }

        else{
            throw new Exception("Your library already exists. Please use getLibrary to access your library.");
        }

        //Returning currentLibrary
        return currentLibrary;
    }


    /**
     * If an instance has already been created, the client can access the instance with this static method
     * @return
     *      Returning the instance of the Library
     */
    public static Library getLibrary()
    {
        return currentLibrary;
    }


    /**
     * Adds a Song to the library. Duplicate Songs aren't added twice.
     *
     * @param pSong the Song to add
     */
    public void addSong(Song pSong) {

        String pTitle = pSong.getaTitle();
        String pArtist = pSong.getaArtist();

        Song addingSong = Song.getSong(pTitle, pArtist);

        boolean duplicateSong = false;

        for (int i=0; i<listOfSongs.size(); i++){
            if (listOfSongs.get(i).getaTitle().equalsIgnoreCase(addingSong.getaTitle()) && listOfSongs.get(i).getaArtist().equalsIgnoreCase(addingSong.getaArtist())){
                System.out.println("The song " + addingSong.getaTitle() + " by " + addingSong.getaArtist() + " is already in your library");
                duplicateSong = true;
            }

        }
        if (!duplicateSong){
            System.out.println("Adding " + addingSong.getaTitle() + " by " + addingSong.getaArtist() + " to your library");
            listOfSongs.add(addingSong);
        }


    }

    /**
     * Adds a PlayList to the library. All Songs from the list are also added as individual Songs to the library.
     *
     * @param pList
     *            the PlayList to add
     * @pre pList!=null;
     */
    public void addPlayList(PlayList pList) {

        boolean playlistInLibrary = false;

        for (int i=0; i<listOfPlaylists.size(); i++){
            if (listOfPlaylists.get(i).equals(pList)){
                System.out.println("This playlist is already in your library");
                playlistInLibrary = true;
            }
        }

        if (!playlistInLibrary){

            listOfPlaylists.add(pList);

            for (int i=0; i<pList.getaList().size(); i++){

                if (pList.getaList().get(i).getClass().equals(Song.class)) {

                    Song toAdd = (Song) pList.getaList().get(i);

                    listOfSongs.add(toAdd);
                }
            }
        }
    }

    /**
     * Adds a Podcast to the library. All Episodes from the list are also added as individual episodes to the library.
     *
     * @param pPodcast
     *            the Podcast to add
     * @pre pPodcast!=null;
     */
    public void addPodcast(Podcast pPodcast) {

        String pName = pPodcast.getName();
        String pHost = pPodcast.getaHost();

        Podcast addingPodcast = Podcast.getPodcast(pName, pHost);

        boolean podcastInLibrary = false;

        for (int i=0; i<listOfPodcasts.size(); i++){
            if (listOfPodcasts.get(i).getName().equalsIgnoreCase(addingPodcast.getName()) && listOfPodcasts.get(i).getaHost().equalsIgnoreCase(addingPodcast.getaHost())){
                System.out.println("The podcast " + addingPodcast.getName() + " hosted by " + addingPodcast.getaHost() + " is already in your library.");
                podcastInLibrary = true;
            }

        }
        if (!podcastInLibrary){
            System.out.println("Adding " + addingPodcast.getName() + " hosted by " + addingPodcast.getaHost() + " to your library");
            listOfPodcasts.add(addingPodcast);

            for (int i=0; i<pPodcast.getNumberOfEpisodes(); i++){
                if (!listOfEpisodes.contains(pPodcast.getEpisode(i))){
                    listOfEpisodes.add(pPodcast.getEpisode(i));
                }
            }

        }

    }

}
