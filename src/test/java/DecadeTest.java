import org.junit.*;
import static org.junit.Assert.*;

public class DecadeTest {

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

  
}
