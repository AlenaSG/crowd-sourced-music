import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class SongTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/crowd_sourced_music_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM songs *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void Song_instantiatesCorrectly_true() {
    Song mySong = new Song("Vogue", 1);
    assertEquals(true, mySong instanceof Song);
  }

  @Test
  public void Song_instantiatesWithName_String() {
    Song mySong = new Song("Vogue", 1);
    assertEquals("Vogue", mySong.getName());
  }

  @Test
  public void Song_instantiatesWithDecadeId_1() {
    Song mySong = new Song("Vogue", 1);
    assertEquals(1, mySong.getDecadeId());
  }

  @Test
  public void all_returnsAllInstancesOfSong_true() {
    Song firstSong = new Song("Vogue", 1);
    Song secondSong = new Song("Secret", 2);
    assertEquals(true, Song.all().contains(firstSong));
    assertEquals(true, Song.all().contains(secondSong));
  }

  @Test
  public void clear_emptiesAllSongsFromArrayList_0() {
    Song firstSong = new Song("Vogue", 1);
    Song.clear();
    assertEquals(0, Song.all().size());
  }

  @Test
  public void getId_songsInstantiateWithAnID_1() {
    //Song.clear();
    Song mySong = new Song("Vogue", 1);
    assertEquals(1, mySong.getId());
  }

  @Test
  public void find_returnsSongWithSameId_secondTask() {
    Song firstSong = new Song("Vogue", 1);
    Song secondSong = new Song("Secret", 2);
    assertEquals(Song.find(secondSong.getId()), secondSong);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Song firstSong = new Song("Vogue", 1);
    Song secondSong = new Song("Vogue", 1);
    assertTrue(firstSong.equals(secondSong));
  }

}
