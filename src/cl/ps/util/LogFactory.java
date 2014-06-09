/**
 *
 */
package cl.ps.util;

import org.apache.log4j.Logger;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
public class LogFactory {

	public static final String DEFAULT_LOGGER = "LOGGER";
	public static final String DEFAULT_TRACKER = "TRACKER";
	public static final String DEFAULT_LOGSEARCH = "LOGSEARCH";
	public static final String DEFAULT_LOGEMAIL = "LOGEMAIL";

	private static LogFactory factory;
	private Logger logger;
	private Logger tracker;
	private Logger logsearch;
	private Logger logemail;


	public static Logger getLogger(){
		if(factory == null ){
			factory = new LogFactory();
			factory.init();
		}
		return factory.logger;
	}
	
	public static Logger getTracker(){
		if(factory == null ){
			factory = new LogFactory();
			factory.init();
		}
		return factory.tracker;
	}
	
	public static Logger getLogsearch(){
		if(factory == null ){
			factory = new LogFactory();
			factory.init();
		}
		return factory.logsearch;
	}	
	
	public static Logger getLogemail(){
		if(factory == null ){
			factory = new LogFactory();
			factory.init();
		}
		return factory.logemail;
	}	

	private void init(){
		System.out.println("Iniciando el logger...");
		logger = Logger.getLogger(DEFAULT_LOGGER);
		tracker = Logger.getLogger(DEFAULT_TRACKER);
		logsearch = Logger.getLogger(DEFAULT_LOGSEARCH);
		logemail = Logger.getLogger(DEFAULT_LOGEMAIL);
	}

}
