import java.util.*;

public class Main {
    /*
    The Iterator Design Pattern is a behavioral pattern that allows you to traverse through a collection of objects (like a List, Set, or Tree) without exposing its underlying representation (how it's stored).
     */
    public static void main(String[] args) {
        Playlist myPlaylist = new Playlist();
        myPlaylist.addSong(new Song("Shape of You", true));
        myPlaylist.addSong(new Song("Blinding Lights", false));
        myPlaylist.addSong(new Song("Perfect", true));
        myPlaylist.addSong(new Song("Starboy", false));

        System.out.println("--- Playing Favorites ---");
        SongIterator favs = myPlaylist.getFavoritesIterator();
        while (favs.hasNext()) {
            System.out.println("Playing: " + favs.next().title);
        }

        System.out.println("\n--- Playing Shuffle ---");
        SongIterator shuffle = myPlaylist.getShuffleIterator();
        while (shuffle.hasNext()) {
            System.out.println("Playing: " + shuffle.next().title);
        }
    }

    /* OUTPUT:
    --- Playing Favorites ---
    Playing: Shape of You
    Playing: Perfect

    --- Playing Shuffle ---
    Playing: Blinding Lights
    Playing: Starboy
    Playing: Shape of You
    Playing: Perfect
     */
}

class Song {
    String title;
    boolean isFavorite;

    public Song(String title, boolean isFavorite) {
        this.title = title;
        this.isFavorite = isFavorite;
    }
}

interface SongIterator {
    boolean hasNext();
    Song next();
}

// Normal Sequential Iterator
class SequentialIterator implements SongIterator {
    private List<Song> songs;
    private int position = 0;

    public SequentialIterator(List<Song> songs) {
        this.songs = songs;
    }

    public boolean hasNext() { return position < songs.size(); }
    public Song next() { return songs.get(position++); }
}

// Shuffle Iterator
class ShuffleIterator implements SongIterator {
    private List<Song> shuffledSongs;
    private int position = 0;

    public ShuffleIterator(List<Song> songs) {
        this.shuffledSongs = new ArrayList<>(songs);
        Collections.shuffle(this.shuffledSongs);
    }

    public boolean hasNext() { return position < shuffledSongs.size(); }
    public Song next() { return shuffledSongs.get(position++); }
}

// Favorites Only Iterator
class FavoritesIterator implements SongIterator {
    private List<Song> favorites = new ArrayList<>();
    private int position = 0;

    public FavoritesIterator(List<Song> songs) {
        for (Song s : songs) {
            if (s.isFavorite) favorites.add(s);
        }
    }

    public boolean hasNext() { return position < favorites.size(); }
    public Song next() { return favorites.get(position++); }
}

class Playlist {
    private List<Song> songs = new ArrayList<>();

    public void addSong(Song song) { songs.add(song); }

    public SongIterator getLocalIterator() {
        return new SequentialIterator(songs);
    }

    public SongIterator getShuffleIterator() {
        return new ShuffleIterator(songs);
    }

    public SongIterator getFavoritesIterator() {
        return new FavoritesIterator(songs);
    }
}