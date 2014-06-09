package cl.ps.ws;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cl.ps.entities.Voting;
import cl.ps.wrappers.ResponseWrapper;


@Path("/voting")
@Consumes({"application/json"})
@Produces({"application/json"})
public class VotingWS extends BaseWS {
	
	@GET
	@Path("/getByBuilding")
	public ResponseWrapper getByBuilding(){
		ResponseWrapper response = new ResponseWrapper();
		List<Voting> votings = new LinkedList<Voting>();
		Voting voting1 = new Voting(new Long(1), "Votacion 1", "Esta es la votacion 1");
		Voting voting2 = new Voting(new Long(2), "Votacion 2", "Esta es la votacion 2");
		Voting voting3 = new Voting(new Long(3), "Votacion 3", "Esta es la votacion 3");
		Voting voting4 = new Voting(new Long(4), "Votacion 4", "Esta es la votacion 4");
		votings.add(voting1);
		votings.add(voting2);
		votings.add(voting3);
		votings.add(voting4);
		
		response.setData(votings);
		return response;
	}
	
	@POST
	@Path("/save")
	public ResponseWrapper save(Voting voting){
		ResponseWrapper response = prefillResponse();
		Connection con = getConnection();

		log.debug("Se desea guardar al usuario  "+voting);

		try {
			Voting created_voting = getBCFactory().getVotingBC(con).save(voting);
			response.setData(created_voting.getId());
		} catch (Exception e) {
			errorDefault(response, e );
		}
		finally{
			putConnection(con);
		}
		return response;
	}
}
