package cl.ps.controller;

import java.sql.Connection;

import org.apache.log4j.Logger;

import cl.ps.model.DAOFactory;
import cl.ps.util.Enviroment;
import cl.ps.util.LogFactory;

public class BusinessController {

	protected Logger log;
	protected Logger track;
	protected Logger logsearch;
	protected Logger logemail;
	private DAOFactory daoFactory;
	private BusinessControllerFactory bcFactory;
	private Enviroment enviroment;	
	private Connection con;
	
	public synchronized Enviroment getEnviroment() {
		if(enviroment == null)
			enviroment = new Enviroment();
		return enviroment;
	}

	public void setEnviroment(Enviroment enviroment) {
		this.enviroment = enviroment;
	}

	public DAOFactory getDAOFactory(){
		if(daoFactory == null )
			this.daoFactory = new DAOFactory();
		this.daoFactory.setEnviroment(getEnviroment());
		return this.daoFactory;
	}

	public void setDAOFactory( DAOFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	/**
	 * @param bcFactory
	 */
	public void setBCFactory(BusinessControllerFactory bcFactory) {
		this.bcFactory = bcFactory;
	}

	protected BusinessControllerFactory getBCFactory(){
		if(bcFactory == null )
			this.bcFactory = new BusinessControllerFactory();
		this.bcFactory.setEnviroment(getEnviroment());
		return this.bcFactory;
	}

	protected BusinessController(){
		this.log = LogFactory.getLogger();
		this.track = LogFactory.getTracker();
		this.logsearch = LogFactory.getLogsearch();		
		this.logemail = LogFactory.getLogemail();		
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
	public Connection getConnection(){
		return con;
	}
}
