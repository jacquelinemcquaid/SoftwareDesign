import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayListTest {

    @Test
    void addPlayable() {

        PlayList playlist = new PlayList("My playlist");

        Song song = new Song("Baby", "Justin Bieber");
        Song song2 = new Song("I Wanna Dance with Somebody", "Whitney Houston");
        Song song3 = new Song("Savage Love", "Jason Derulo");

        playlist.addPlayable(song);
        playlist.addPlayable(song2);
        playlist.addPlayable(song3);

        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));
        assertEquals(song3, playlist.getaList().get(2));
    }

    @Test
    void removePlayable() {

        PlayList playlist = new PlayList("My playlist");

        Song song = new Song("Baby", "Justin Bieber");
        Song song2 = new Song("I Wanna Dance with Somebody", "Whitney Houston");
        Song song3 = new Song("Savage Love", "Jason Derulo");

        playlist.addPlayable(song);
        playlist.addPlayable(song2);
        playlist.addPlayable(song3);

        playlist.removePlayable(0);

        assertEquals(2, playlist.getaList().size());
        assertEquals(song2, playlist.getaList().get(0));
        assertEquals(song3, playlist.getaList().get(1));
    }

    @Test
    void undoNormal() {

        PlayList playlist = new PlayList("My playlist");

        Song song = new Song("Baby", "Justin Bieber");
        Song song2 = new Song("I Wanna Dance with Somebody", "Whitney Houston");
        Song song3 = new Song("Savage Love", "Jason Derulo");

        playlist.addPlayable(song);
        playlist.addPlayable(song2);
        playlist.addPlayable(song3);

        //Song3 should have been undone
        playlist.undo();

        assertEquals(2, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));

    }

    @Test
    void undoNoUndoesLeft() {

        PlayList playlist = new PlayList("My playlist");

        Song song = new Song("Baby", "Justin Bieber");
        Song song2 = new Song("I Wanna Dance with Somebody", "Whitney Houston");
        Song song3 = new Song("Savage Love", "Jason Derulo");

        playlist.addPlayable(song);
        playlist.addPlayable(song2);
        playlist.addPlayable(song3);

        playlist.undo();
        playlist.undo();
        playlist.undo();

        //This is an extra undo
        //The list should be empty and nothing else is changed
        playlist.undo();

        assertEquals(0, playlist.getaList().size());


    }

    @Test
    void multipleUndoes() {

        PlayList playlist = new PlayList("My playlist");

        Song song = new Song("Baby", "Justin Bieber");
        Song song2 = new Song("I Wanna Dance with Somebody", "Whitney Houston");
        Song song3 = new Song("Savage Love", "Jason Derulo");

        playlist.addPlayable(song);
        playlist.addPlayable(song2);
        playlist.addPlayable(song3);

        assertEquals(3, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));
        assertEquals(song3, playlist.getaList().get(2));

        playlist.undo();

        assertEquals(2, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));

        playlist.undo();

        assertEquals(1, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));

        playlist.undo();

        assertEquals(0, playlist.getaList().size());


    }

    @Test
    void redoNormal() {

        PlayList playlist = new PlayList("My playlist");

        Song song = new Song("Baby", "Justin Bieber");
        Song song2 = new Song("I Wanna Dance with Somebody", "Whitney Houston");
        Song song3 = new Song("Savage Love", "Jason Derulo");

        playlist.addPlayable(song);
        playlist.addPlayable(song2);
        playlist.addPlayable(song3);

        assertEquals(3, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));
        assertEquals(song3, playlist.getaList().get(2));

        playlist.undo();

        assertEquals(2, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));

        playlist.redo();
        assertEquals(3, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));
        assertEquals(song3, playlist.getaList().get(2));

    }

    @Test
    void redoNoRedoesLeft() {

        PlayList playlist = new PlayList("My playlist");

        Song song = new Song("Baby", "Justin Bieber");
        Song song2 = new Song("I Wanna Dance with Somebody", "Whitney Houston");
        Song song3 = new Song("Savage Love", "Jason Derulo");

        playlist.addPlayable(song);
        playlist.addPlayable(song2);
        playlist.addPlayable(song3);

        assertEquals(3, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));
        assertEquals(song3, playlist.getaList().get(2));

        playlist.undo();

        assertEquals(2, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));

        playlist.redo();
        assertEquals(3, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));
        assertEquals(song3, playlist.getaList().get(2));

        //This should not affect anything
        playlist.redo();

        assertEquals(3, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));
        assertEquals(song3, playlist.getaList().get(2));

    }


    @Test
    void multipleRedoes() {

        PlayList playlist = new PlayList("My playlist");

        Song song = new Song("Baby", "Justin Bieber");
        Song song2 = new Song("I Wanna Dance with Somebody", "Whitney Houston");
        Song song3 = new Song("Savage Love", "Jason Derulo");

        playlist.addPlayable(song);
        playlist.addPlayable(song2);
        playlist.addPlayable(song3);

        assertEquals(3, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));
        assertEquals(song3, playlist.getaList().get(2));

        playlist.undo();

        assertEquals(2, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));

        playlist.undo();

        assertEquals(1, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));

        playlist.undo();

        assertEquals(0, playlist.getaList().size());

        playlist.redo();
        assertEquals(1, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));

        playlist.redo();
        assertEquals(2, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));

        playlist.redo();
        assertEquals(3, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));
        assertEquals(song3, playlist.getaList().get(2));
    }

    @Test
    void redoAfterStateModifying() {

        PlayList playlist = new PlayList("My playlist");

        Song song = new Song("Baby", "Justin Bieber");
        Song song2 = new Song("I Wanna Dance with Somebody", "Whitney Houston");
        Song song3 = new Song("Savage Love", "Jason Derulo");

        playlist.addPlayable(song);
        playlist.addPlayable(song2);
        playlist.addPlayable(song3);

        assertEquals(3, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));
        assertEquals(song3, playlist.getaList().get(2));

        playlist.redo();

        assertEquals(4, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));
        assertEquals(song3, playlist.getaList().get(2));
        assertEquals(song3, playlist.getaList().get(3));

    }

    @Test
    void UndoStateModifyingRedo() {

        PlayList playlist = new PlayList("My playlist");

        Song song = new Song("Baby", "Justin Bieber");
        Song song2 = new Song("I Wanna Dance with Somebody", "Whitney Houston");
        Song song3 = new Song("Savage Love", "Jason Derulo");

        playlist.addPlayable(song);
        playlist.addPlayable(song2);
        playlist.addPlayable(song3);

        assertEquals(3, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));
        assertEquals(song3, playlist.getaList().get(2));

        playlist.undo();

        assertEquals(2, playlist.getaList().size());
        assertEquals(song, playlist.getaList().get(0));
        assertEquals(song2, playlist.getaList().get(1));

        playlist.removePlayable(0);

        assertEquals(1, playlist.getaList().size());
        assertEquals(song2, playlist.getaList().get(0));

        playlist.redo();

        assertEquals(0, playlist.getaList().size());
    }
}