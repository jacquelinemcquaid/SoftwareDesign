/**
 * Represents an audio object that can be played
 */
interface Playable {

    /**
     * Plays the audio to the user
     */
    void play();

    /**
     * Copies the Playable object
     * @return
     *      Returns a copied Playable object
     */
    Playable copy();
}