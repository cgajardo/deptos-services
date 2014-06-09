package cl.ps.model.interfaces;

import java.util.Locale;

import cl.ps.util.Enviroment;

public interface IBaseDAO {
	
	public void setEnviroment(Enviroment enviroment);
	
	public Enviroment getEnviroment();

	public void setLimit( int offset, int limit);

}
