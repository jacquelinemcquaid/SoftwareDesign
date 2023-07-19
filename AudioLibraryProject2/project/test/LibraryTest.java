import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @BeforeEach
    void setUp() {

        //Creating a library
        Library.createLibrary("My Library");
        assertEquals("My Library", Library.getLibrary().getaLibraryName());

        Library myLibrary = Library.getLibrary();

        Song defaultSong = new Song("Bohemian Rhapsody", "Queen");
        myLibrary.setPrototype(defaultSong);
    }

    @Test
    void initialDefaultPlayable() throws NoSuchFieldException {

        try {
            //Accessing my library (created in the beforeEach block)
            Library myLibrary = Library.getLibrary();

            //Accessing private field aPrototype and setting accessibility
            Field defaultPlayable = Library.class.getDeclaredField("aPrototype");
            defaultPlayable.setAccessible(true);

            //Creating a reference to the default playable
            Playable playable = (Playable) defaultPlayable.get(myLibrary);

            //The playable should be "Bohemian Rhapsody" by "Queen"
            //as this is the value I set if the client does not indicate a default playable
            assertTrue(playable instanceof Song);
            assertEquals("Bohemian Rhapsody", ((Song) playable).getTitle());
            assertEquals("Queen", ((Song) playable).getArtist());
        }
        //Catching exceptions
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    void setPrototypeSong() {

        //Accessing my library (created in the beforeEach block)
        Library myLibrary = Library.getLibrary();
        Song newDefaultPlayable = new Song("I Wanna Dance with Somebody", "Whitney Houston");
        myLibrary.setPrototype(newDefaultPlayable);

        try {
            //Accessing private field aPrototype and setting accessibility
            Field defaultPlayable = Library.class.getDeclaredField("aPrototype");
            defaultPlayable.setAccessible(true);

            //Creating a reference to the default playable
            Playable playable = (Playable) defaultPlayable.get(myLibrary);

            //The new default playable should be "I Wanna Dance with Somebody" by "Whitney Houstan"
            assertTrue(playable instanceof Song);
            assertEquals("I Wanna Dance with Somebody", ((Song) playable).getTitle());
            assertEquals("Whitney Houston", ((Song) playable).getArtist());
        }
        //Catching exceptions
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void setPrototypeEpisode() {

        //Accessing my library (created in the beforeEach block)
        Library myLibrary = Library.getLibrary();

        //Creating Podcast object
        Podcast podcast = new Podcast("FirstPodcast", "Host");

        //Creating episode through podcast object
        Podcast.Episode newDefaultPlayable = podcast.new Episode("Episode 1", 1);

        myLibrary.setPrototype(newDefaultPlayable);

        try {
            //Accessing private field aPrototype and setting accessibility
            Field defaultPlayable = Library.class.getDeclaredField("aPrototype");
            defaultPlayable.setAccessible(true);

            //Creating a reference to the default playable
            Playable playable = (Playable) defaultPlayable.get(myLibrary);

            //The new default playable should be the episode
            assertTrue(playable instanceof Podcast.Episode);
            assertEquals("Episode 1", ((Podcast.Episode) playable).getTitle());
            assertEquals(1, ((Podcast.Episode) playable).getEpisodeNumber());
        }
        //Catching exceptions
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    @Test
    void setPrototypePlayList() {

        //Accessing my library (created in the beforeEach block)
        Library myLibrary = Library.getLibrary();

        Song song = new Song("Baby", "Justin Bieber");

        PlayList newDefaultPlayable = new PlayList("MyPlaylist");

        newDefaultPlayable.addPlayable(song);

        myLibrary.setPrototype(newDefaultPlayable);

        try {
            //Accessing private field aPrototype and setting accessibility
            Field defaultPlayable = Library.class.getDeclaredField("aPrototype");
            defaultPlayable.setAccessible(true);

            //Creating a reference to the default playable
            Playable playable = (Playable) defaultPlayable.get(myLibrary);

            //The new default playable should be the playlist with size 1
            assertTrue(playable instanceof PlayList);
            assertEquals("MyPlaylist", ((PlayList) playable).getName());
            assertEquals(1, ((PlayList) playable).getaList().size());
        }
        //Catching exceptions
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }


    @Test
    void createPlayableSong() {

        Library myLibrary = Library.getLibrary();

        Song toAddToPlaylist = new Song("Baby", "Justin Bieber");
        PlayList playlist = new PlayList("myPlaylist");
        playlist.addPlayable(toAddToPlaylist);

        myLibrary.setPrototype(playlist);

        try {
            //Accessing private field aPrototype and setting accessibility
            Field defaultPlayable = Library.class.getDeclaredField("aPrototype");
            defaultPlayable.setAccessible(true);

            //Creating a reference to the default playable
            Playable playable = (Playable) defaultPlayable.get(myLibrary);

            //The new default playable should be "I Wanna Dance with Somebody" by "Whitney Houston"
            assertTrue(playable instanceof PlayList);
            assertEquals("myPlaylist", ((PlayList) playable).getName());
            assertTrue(((PlayList) playable).getaList().get(0) instanceof Song);
            assertEquals("Baby", ((Song) ((PlayList) playable).getaList().get(0)).getTitle());
            assertEquals("Justin Bieber", ((Song) ((PlayList) playable).getaList().get(0)).getaArtist());

            //This assert statement ensures references are not the same
            assertNotSame(playable, (PlayList) myLibrary.createPlayable());

            //This assert statement ensures that the two playlists have the same content
            assertEquals(playable, (PlayList) myLibrary.createPlayable());
        }
        //Catching exceptions
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createPlayableEpisode() {

        //Accessing my library (created in the beforeEach block)
        Library myLibrary = Library.getLibrary();

        //Creating Podcast object
        Podcast podcast = new Podcast("FirstPodcast", "Host");

        //Creating episode through podcast object
        Podcast.Episode newDefaultPlayable = podcast.new Episode("Episode 1", 1);

        myLibrary.setPrototype(newDefaultPlayable);

        try {
            //Accessing private field aPrototype and setting accessibility
            Field defaultPlayable = Library.class.getDeclaredField("aPrototype");
            defaultPlayable.setAccessible(true);

            //Creating a reference to the default playable
            Playable playable = (Playable) defaultPlayable.get(myLibrary);

            //The new default playable should be "I Wanna Dance with Somebody" by "Whitney Houston"
            assertTrue(playable instanceof Podcast.Episode);
            assertEquals("Episode 1", ((Podcast.Episode) playable).getTitle());
            assertEquals(1, ((Podcast.Episode)  playable).getEpisodeNumber());

            //This assert statement ensures references are not the same
            assertNotSame(playable, (Podcast.Episode) myLibrary.createPlayable());

            //This assert statement ensures that the two playlists have the same content
            assertEquals(playable, (Podcast.Episode) myLibrary.createPlayable());
        }
        //Catching exceptions
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    void createPlayablePlayList() {

        //Accessing my library (created in the beforeEach block)
        Library myLibrary = Library.getLibrary();

        Song song = new Song("Baby", "Justin Bieber");

        PlayList newDefaultPlayable = new PlayList("MyPlaylist");

        newDefaultPlayable.addPlayable(song);

        myLibrary.setPrototype(newDefaultPlayable);

        try {
            //Accessing private field aPrototype and setting accessibility
            Field defaultPlayable = Library.class.getDeclaredField("aPrototype");
            defaultPlayable.setAccessible(true);

            //Creating a reference to the default playable
            Playable playable = (Playable) defaultPlayable.get(myLibrary);

            //The new default playable should be the playlist with size 1
            assertTrue(playable instanceof PlayList);
            assertEquals("MyPlaylist", ((PlayList) playable).getName());
            assertEquals(1, ((PlayList) playable).getaList().size());

            //This assert statement ensures references are not the same
            assertNotSame(playable, (PlayList) myLibrary.createPlayable());

            //This assert statement ensures that the two playlists have the same content
            assertEquals(playable, (PlayList) myLibrary.createPlayable());
        }
        //Catching exceptions
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    }