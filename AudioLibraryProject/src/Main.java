import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        // TASK: Write runner code to show your work

        Library myLibrary = Library.setLibrary("My Library", "English");

        System.out.println("Test 1: Trying to create another Library instance: ");

        CreatingAnotherLibraryInstance();

        System.out.println("Test 2: Testing case insensitivity and testing prevention of creating duplicate podcasts:");
        Podcast firstPodcast = Podcast.getPodcast("French101", "Dr. G");

        System.out.println("The podcast " + firstPodcast.getName() + " has been created. Requesting the same podcast using lower case letters: ");
        Podcast secondPodcast = Podcast.getPodcast("french101", "dr. g");

        System.out.println("Printing out references of both podcasts showing that no duplicates have been created: ");
        System.out.println(firstPodcast);
        System.out.println(secondPodcast);

        System.out.println("**********************");

        System.out.println("Test 3: Testing case insensitivity and testing prevention of creating duplicate songs:");

        Song firstSong = Song.getSong("Baby", "Justin Bieber");
        System.out.println("The song " + firstSong.getaTitle() + " has been created. Requesting the same podcast using lower case letters: ");

        Song secondSong = Song.getSong("baby", "justin bieber");

        System.out.println("Printing out references of both songs showing that no duplicates have been created: " );
        System.out.println(firstSong);
        System.out.println(secondSong);


        System.out.println("**********************");

        System.out.println("Test 4: Adding a song to library. Then trying to add the same song to the library");

        myLibrary.addSong(firstSong);

        myLibrary.addSong(secondSong);

        System.out.println("**********************");

        System.out.println("Test 5: Adding a podcast to library. Then trying to add the same podcast to the library");

        myLibrary.addPodcast(firstPodcast);

        myLibrary.addPodcast(secondPodcast);

        System.out.println("********************");

        System.out.println("Test 6: Adding a playlist to my library, then trying to add individual songs from that playlist to my library");

        System.out.println("Adding 2 episodes of a podcast and two songs to a playlist");

        Episode e1 = new Episode(firstPodcast, "Episode 1", 1);

        Episode e2 = new Episode(secondPodcast, "Episode 2", 2);

        Song HeyJude = Song.getSong("Hey Jude", "The Beatles");

        Song BohemianRhapsody = Song.getSong("Bohemian Rhapsody", "Queen");

        PlayList playlist1 = new PlayList("FirstPlaylist");

        playlist1.addPlayable(BohemianRhapsody);
        playlist1.addPlayable(e1);
        playlist1.addPlayable(e2);
        playlist1.addPlayable(HeyJude);

        myLibrary.addPlayList(playlist1);

        System.out.println("The two songs should have been added to the library. Trying to add these songs after the playlist has been added: ");

        myLibrary.addSong(HeyJude);
        myLibrary.addSong(BohemianRhapsody);

        System.out.println("*************************");

        System.out.println("Test 7: Creating a second identical playlist with a different name and adding it to my library");

        PlayList playList2 = new PlayList("SamePlaylistDifferentName");

        playList2.addPlayable(BohemianRhapsody);
        playList2.addPlayable(e1);
        playList2.addPlayable(e2);
        playList2.addPlayable(HeyJude);

        myLibrary.addPlayList(playlist1);

        System.out.println("**************************");

        System.out.println("Test 8: Creating a new playlist with all of the same playables but in a different order");

        PlayList playList3 = new PlayList("SamePlaylistDifferentNameDifferentOrder");

        playList3.addPlayable(BohemianRhapsody);
        playList3.addPlayable(HeyJude);
        playList3.addPlayable(e1);
        playList3.addPlayable(e2);

        myLibrary.addPlayList(playList3);

        System.out.println("Playlist was added successfully");


        System.out.println("***********************");

        System.out.println("Test 9: Testing adding podcasts to library");

        Podcast funOnWeekdays = Podcast.getPodcast("Fun On Weekdays", "Jenna");

        Episode funOnFashion = new Episode(funOnWeekdays, "Fun On Fashion", 1);
        Episode howToHeartBreak = new Episode(funOnWeekdays, "How to heartbreak", 2);

       myLibrary.addPodcast(funOnWeekdays);

       System.out.println("Trying to add the podcast to the library again");

       myLibrary.addPodcast(funOnWeekdays);


    }

    public static void CreatingAnotherLibraryInstance() throws Exception {


        try {

            Library secondInstance = Library.setLibrary("SecondInstance", "Description");


        } catch (Exception toPrint) {
            System.out.println("Exception message: " + toPrint.getMessage());
        }

    }
}
