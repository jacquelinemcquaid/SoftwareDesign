import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a single Song, with at least a title, and an artist name.
 */
public class Song implements Playable{

    final private String aTitle;
    final private String aArtist;

    //Will store songs in this arraylist
    final private static ArrayList<Song> listOfStoredSongs = new ArrayList<>();

    /**
     * Constructor is private to prevent duplicate objects from being created
     * @param pTitle
     * @param pArtist
     */
    private Song(String pTitle, String pArtist){

        aTitle = pTitle;
        aArtist = pArtist;

    }

    public void play() {
        // Just a stub.
        // We don't expect you to implement an actual music player!
        System.out.println("Now playing " + aTitle);
    }

    /**
     *
     * @param pTitle
     * @param pArtist
     * @return
     *      If the song object has already been created, this stored object is returned.
     *      Otherwise, a new Song object is created and stored
     */
    public static Song getSong(String pTitle, String pArtist){

        for (int i=0; i<listOfStoredSongs.size(); i++) {

            //Using equalsIgnoreCase because title and artist are case insensitive
            if (listOfStoredSongs.get(i).aTitle.equalsIgnoreCase(pTitle) && listOfStoredSongs.get(i).aArtist.equalsIgnoreCase(pArtist)) {
                //Song is already stored. Returning stored object
                return listOfStoredSongs.get(i);
            }

        }

        //If the song was not in the list of stored songs, creating the song and adding it to the list
        Song newSong = new Song(pTitle, pArtist);

        listOfStoredSongs.add(newSong);

        return newSong;

    }

    public String getaTitle() {
        return aTitle;
    }

    public String getaArtist() {
        return aArtist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(aTitle, song.aTitle) && Objects.equals(aArtist, song.aArtist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aTitle, aArtist);
    }
}