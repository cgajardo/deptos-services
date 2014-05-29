package cl.ps.ws;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/user")
@Consumes({"application/json"})
@Produces({"application/json"})
public class User {

	@GET
	@Path("/hello")
	public String hello() {
		return "HELLO CARLOS";
		
	}
}
