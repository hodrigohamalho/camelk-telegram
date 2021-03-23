import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;


public class CamelBot extends RouteBuilder {

    @Override
  public void configure() throws Exception {
    
    from("timer:helloWorld")
        .log("live demo with camel-k!");

  }

}