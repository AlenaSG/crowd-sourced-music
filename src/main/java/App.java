import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Map;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("decades", Decade.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/decades", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Decade newDecade = new Decade(name);
      newDecade.save();
      model.put("template", "templates/decade-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/decades", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("decades", Decade.all());
      model.put("template", "templates/decades.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/decades/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/decade-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/decades/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Decade decade = Decade.find(Integer.parseInt(request.params(":id")));
      model.put("decade", decade);
      model.put("template", "templates/decade.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("decades/:id/songs/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Decade decade = Decade.find(Integer.parseInt(request.params(":id")));
      model.put("decade", decade);
      model.put("template", "templates/decade-songs-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/songs", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Decade decade = Decade.find(Integer.parseInt(request.queryParams("decadeId")));
      String name = request.queryParams("name");
      Song newSong = new Song(name, decade.getId());
      newSong.save();
      model.put("decade", decade);
      model.put("template", "templates/decade-song-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/decades/:decade_id/songs/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Decade decade = Decade.find(Integer.parseInt(request.params(":decade_id")));
      Song song = Song.find(Integer.parseInt(request.params(":id")));
      model.put("decade", decade);
      model.put("song", song);
      model.put("template", "templates/song.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/decades/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Decade decade = Decade.find(Integer.parseInt(request.params("id")));
      String name = request.queryParams("name");
      decade.update(name);
      String url = String.format("/decades/%d", decade.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //Here, we locate the Song and Decade objects. Then, we retrieve the new song name the user has provided in our update form. We call our new update() method on the Task, and pass in the new description object. OurTask` should now be updated!
    post("/decades/:decade_id/songs/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Song song = Song.find(Integer.parseInt(request.params("id")));
      String name = request.queryParams("name");
      Decade decade = Decade.find(song.getDecadeId());
      song.update(name);
      String url = String.format("/decades/%d/songs/%d", decade.getId(), song.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/decades/:decade_id/songs/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Song song = Song.find(Integer.parseInt(request.params("id")));
      Decade decade = Decade.find(song.getDecadeId());
      song.delete();
      model.put("decade", decade);
      model.put("template", "templates/decade.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
