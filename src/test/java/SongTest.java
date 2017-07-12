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
      String deleteSongsQuery = "DELETE FROM songs *;";
      String deleteDecadesQuery = "DELETE FROM decades *;";
      con.createQuery(deleteSongsQuery).executeUpdate();
      con.createQuery(deleteDecadesQuery).executeUpdate();
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
  public void getId_songsInstantiateWithAnID_1() {
    Song mySong = new Song("Vogue", 1);
    mySong.save();
    assertTrue(mySong.getId() > 0);
  }

  @Test
  public void find_returnsSongWithSameId_secondSong() {
    Song firstSong = new Song("Vogue", 1);
    firstSong.save();
    Song secondSong = new Song("Secret", 1);
    secondSong.save();
    assertEquals(Song.find(secondSong.getId()), secondSong);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Song firstSong = new Song("Vogue", 1);
    Song secondSong = new Song("Vogue", 1);
    //System.out.println("first song: " + firstSong.getDecadeId());
    //System.out.println("second song: " + secondSong.getDecadeId());
    assertTrue(firstSong.equals(secondSong));
  }

  @Test
  public void save_returnsTrueIfNameAretheSame() {
    Song mySong = new Song("Vogue", 1);
    mySong.save();
    assertTrue(Song.all().get(0).equals(mySong));
  }

  @Test
  public void all_returnsAllInstancesOfSong_true() {
    Song firstSong = new Song("Vogue", 1);
    firstSong.save();
    Song secondSong = new Song("Secret", 1);
    secondSong.save();
    assertEquals(true, Song.all().get(0).equals(firstSong));
    assertEquals(true, Song.all().get(1).equals(secondSong));
  }

  @Test
  public void save_assignsIdToObject() {
    Song mySong = new Song("Vogue", 1);
    mySong.save();
    Song savedSong = Song.all().get(0);
    assertEquals(mySong.getId(), savedSong.getId());
  }

  @Test
  public void save_savesDecadeIdIntoDB_true() {
    Decade myDecade = new Decade("90s");
    myDecade.save();
    Song mySong = new Song("Vogue", myDecade.getId());
    mySong.save();
    Song savedSong = Song.find(mySong.getId());
    assertEquals(savedSong.getDecadeId(), myDecade.getId());
  }
}
