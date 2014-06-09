package cl.ps.ws;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/user")
@Consumes({"application/json"})
@Produces({"application/json"})
public class UserWS {

	@GET
	@Path("/hello")
	public String hello() {
		return "HELLO CARLOS";
		
	}
	
//	@GET
//	@Path("/login")
//	public User login(){
//		User response = new User();
//		response.setName("Carlos");
//		response.setLastname("Gajardo");
//		response.setApartment("1604");
//		return response;
//		
//	}
	
}
