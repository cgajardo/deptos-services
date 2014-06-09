/**
 *
 */
package cl.ps.model;

import java.sql.Connection;

import cl.ps.model.exceptions.ConnectionException;
import cl.ps.util.Enviroment;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
public class DAOFactory {

	private boolean TEST = false ; 

	private Enviroment enviroment;
	public void setEnviroment(Enviroment enviroment) {
		this.enviroment = enviroment;

	}

	public Enviroment getEnviroment() {
		return this.enviroment;
	}

	private static DAOFactory instance;
	public static DAOFactory getTestInstance() {
		if( instance == null )
			instance = new DAOFactory();
		instance.TEST = true;
		return instance;
	}

	public DAOFactory(){
	}

	private VotingDAO votingDAO;

	public VotingDAO getVotingDAO(Connection con) throws ConnectionException {
		if(votingDAO == null)
			votingDAO = new VotingDAO();
		votingDAO.setConnection(con);
		votingDAO.setEnviroment(getEnviroment());
		return votingDAO;
	}
}
