import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class DecadeTest {

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
  public void Decade_instantiatesCorrectly_true() {
    Decade myDecade = new Decade("90s");
    assertEquals(true, myDecade instanceof Decade);
  }

  @Test
  public void getName_returnsName_90s() {
    Decade myDecade = new Decade("90s");
    assertEquals("90s", myDecade.getName());
  }

  @Test
  public void all_returnsAllInstancesOfDecade_true() {
    Decade firstDecade = new Decade("90s");
    firstDecade.save();
    Decade secondDecade = new Decade("80s");
    secondDecade.save();
    assertEquals(true, Decade.all().get(0).equals(firstDecade));
    assertEquals(true, Decade.all().get(1).equals(secondDecade));
  }

  @Test
  public void clear_emptiesAllDecadesFromList_0() {
    Decade firstDecade = new Decade("90s");
    assertEquals(Decade.all().size(), 0);
  }

  @Test
  public void getId_decadesInstantiateWithAnId_1() {
    Decade testDecade = new Decade("90s");
    testDecade.save();
    assertTrue(testDecade.getId() > 0);
  }

  @Test
  public void find_returnsDecadeWithSameId_secondDecade() {
    Decade firstDecade = new Decade("90s");
    firstDecade.save();
    Decade secondDecade = new Decade("80s");
    secondDecade.save();
    assertEquals(Decade.find(secondDecade.getId()), secondDecade);
  }

  //make sure a Decade instantiates with an empty song list:
  @Test
  public void getSongs_initiallyReturnsEmptyList_ArrayList() {
    Decade testDecade = new Decade("90s");
    assertEquals(0, testDecade.getSongs().size());
  }

  @Test
    public void equals_returnsTrueIfNamesAretheSame() {
      Decade firstDecade = new Decade("90s");
      Decade secondDecade = new Decade("90s");
      assertTrue(firstDecade.equals(secondDecade));
    }

  @Test
    public void save_savesIntoDatabase_true() {
      Decade testDecade = new Decade("90s");
      testDecade.save();
      assertTrue(Decade.all().get(0).equals(testDecade));
    }

  @Test
    public void save_assignsIdToObject() {
      Decade testDecade = new Decade("90s");
      testDecade.save();
      Decade savedDecade = Decade.all().get(0);
      assertEquals(testDecade.getId(), savedDecade.getId());
    }

     @Test
     public void getTasks_retrievesAllSongsFromDatabase_songsList() {
       Decade myDecade = new Decade("90s");
       myDecade.save();
       Song firstSong = new Song("Vogue", myDecade.getId());
       firstSong.save();
       Song secondSong = new Song("Secret", myDecade.getId());
       secondSong.save();
       Song[] songs = new Song[] { firstSong, secondSong };
       assertTrue(myDecade.getSongs().containsAll(Arrays.asList(songs)));
     }
  }
