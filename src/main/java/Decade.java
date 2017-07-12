import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Decade {
  private String name;
  private int id;

  public Decade(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }


}
