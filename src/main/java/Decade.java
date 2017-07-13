import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Decade {
  private String name;
  private static List<Decade> instances = new ArrayList<Decade>();
  private int id;
  private List<Song> songs;

  public Decade(String name) {
    this.name = name;
    instances.add(this);
    id = instances.size();
    songs = new ArrayList<Song>();
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public static List<Decade> all() {
    String sql = "SELECT id, name FROM decades";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Decade.class);
    }
  }

  public static Decade find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM decades WHERE id=:id;";
      Decade decade = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Decade.class);
      return decade;
    }
  }

  public List<Song> getSongs() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM songs WHERE decadeId=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Song.class);
    }
  }

  @Override
  public boolean equals(Object otherDecade) {
    if (!(otherDecade instanceof Decade)) {
      return false;
    } else {
      Decade newDecade = (Decade) otherDecade;
      return this.getName().equals(newDecade.getName()); //&&
            //this.getId() == newDecade.getId(); (Ids are never the same-dynamic)
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO decades (name) VALUES (:name);";
      this. id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE decades SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
