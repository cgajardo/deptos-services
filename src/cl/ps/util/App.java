/**
 *
 */
package cl.ps.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author pcarreno
 *
 */
public class App {


	private static App instance ;
	public static App getInstance(){
		if( instance == null )
			instance = new App();
		return instance;
	}

	private App(){

	}

	Properties properties ;

    private Properties getProperties() throws IOException{
    	if( properties == null ){
    		properties = new Properties();
    		InputStream inputStream = this.getClass().getClassLoader()
    	           .getResourceAsStream("deptos.properties");
    		properties.load(inputStream);
    	}
    	return properties;
    }

	public String getProperty(String property){
		try{
			return getProperties().getProperty(property);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

}
