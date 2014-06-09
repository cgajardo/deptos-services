/**
 *
 */
package cl.ps.controller;

import java.sql.Connection;

import cl.ps.util.Enviroment;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
public class BusinessControllerFactory {


	private Enviroment enviroment;
	public void setEnviroment(Enviroment enviroment) {
		this.enviroment = enviroment;
	}
	public Enviroment getEnviroment(){
		return this.enviroment;
	}

	private static BusinessControllerFactory instance;
	public static BusinessControllerFactory getInstance() {
		if( instance == null )
			instance = new BusinessControllerFactory();
		return instance;
	}

	public BusinessControllerFactory(){

	}

	private VotingBC votingBC;
	public VotingBC getVotingBC(Connection con) {
		if( votingBC == null )
			votingBC = new VotingBC();
		votingBC.setConnection(con);
		votingBC.setEnviroment(getEnviroment());
		return votingBC;
	}
}