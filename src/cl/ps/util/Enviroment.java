package cl.ps.util;

import java.util.HashMap;
import java.util.Map;

public class Enviroment {

	private Map<String, Object> enviroment;
	private Map<String, Object> getEnviroment(){
		if( enviroment == null ){
			enviroment = new HashMap<String, Object >();
		}
		return enviroment;
	}
	
	public Object get(String key){
		return getEnviroment().get(key);
	}
	
	public Object put(String key,Object value){
		return getEnviroment().put(key,value);
	}

	/**
	 * @return
	 */
	public boolean isEmpty() {
		return getEnviroment().isEmpty();
		
	}
}
