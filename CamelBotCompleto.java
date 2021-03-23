import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;


public class CamelBot extends RouteBuilder {

    @Override
  public void configure() throws Exception {
    
    from("telegram:bots")
        .log("command received ${body}")
        .convertBodyTo(String.class)
        .choice()
            .when(simple("${body} == 'joke'"))
                .log("action joke triggered")
                .to("http://api.icndb.com/jokes/random")
                .unmarshal().json(JsonLibrary.Jackson)
                .transform(simple("${body[value][joke]}"))
                .to("telegram:bots")
            .when(simple("${body} == 'publish'"))
                .log("action publish triggered")
            .otherwise()
                .setBody().simple("Action not found. Supported actions:\n *joke\n*publish")
                .to("telegram:bots");


  }

}