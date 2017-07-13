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
    String sql = "SELECT id, name, decadeId FROM songs";
    try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Song.class);
    }
  }

  public static Song find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM songs where id=:id";
      Song song = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Song.class);
      return song;
    }
  }

  @Override
  public boolean equals(Object otherSong){
    if (!(otherSong instanceof Song)) {
      return false;
    } else {
      Song newSong = (Song) otherSong;
      return this.getName().equals(newSong.getName()) &&
             this.getDecadeId() == newSong.getDecadeId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO songs(name, decadeId) VALUES (:name, :decadeId);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("decadeId", this.decadeId)
        .executeUpdate()
        .getKey();
    }
  }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE songs SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM songs WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
