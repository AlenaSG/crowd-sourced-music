import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;


public class Song {
  private String name;
  private int decadeId;
  private int id;
  private static List<Song> instances = new ArrayList<Song>();

  public Song(String name, int decadeId) {
    this.name = name;
    this.decadeId = decadeId;
    instances.add(this);
    id = instances.size();
  }

  public String getName() {
    return name;
  }

  public int getDecadeId() {
    return decadeId;
  }

  public int getId() {
   return id;
  }

  public static List<Song> all() {
    String sql = "SELECT id, name FROM songs";
    try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Song.class);
    }
  }

  public static void clear() {
    instances.clear();
  }

  public static Song find(int id) {
    return instances.get(id - 1);
  }

  @Override
  public boolean equals(Object otherSong){
    if (!(otherSong instanceof Song)) {
      return false;
    } else {
      Song newSong = (Song) otherSong;
      return this.getName().equals(newSong.getName()) &&
             this.getId() == newSong.getId() &&
             this.getDecadeId() == newSong.getDecadeId();
    }
  }


  // public void save() {
  //     try(Connection con = DB.sql2o.open()) {
  //       String sql = "INSERT INTO tasks(description, categoryId) VALUES (:description, :categoryId)";
  //       this.id = (int) con.createQuery(sql, true)
  //         .addParameter("description", this.description)
  //         .addParameter("categoryId", this.categoryId)
  //         .executeUpdate()
  //         .getKey();
  //     }
  //   }
}
