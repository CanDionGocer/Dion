import java.util.ArrayList;

public class Playlist {

  ArrayList<Song> playList = new ArrayList<>();

  public void addSong(Song s){
    playList.add(s);
  }
  public int getTotalDuration(){

    int total = 0;
    for(int i = 0;i < playList.size();i++){
      total += playList.get(i).getDuration();
    }
    return total;
  }
  public ArrayList<String> getSongsByArtist(String artists){

    ArrayList<String> artist = new ArrayList<>();

    for(int i = 0;i < playList.size();i++){
      if(playList.get(i).getArtist().equals(artists)){
        artist.add(playList.get(i).getTitle());
      }
    }
    return artist;
  }

  public static void main(String[] args){

    Playlist p = new Playlist();
    p.addSong(new Song("Yellow", "Coldplay", 270));
    p.addSong(new Song("Fix You", "Coldplay", 295));
    p.addSong(new Song("Lose Yourself", "Eminem", 326));

    System.out.println(p.getTotalDuration()); // 891
    System.out.println(p.getSongsByArtist("Coldplay")); // [Yellow, Fix You]

  }
}

class Song {
    private String title;
    private String artist;
    private int duration;

    public Song(String t, String a, int d) {
        title = t;
        artist = a;
        duration = d;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }
}