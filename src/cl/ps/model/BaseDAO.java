package cl.ps.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.ps.model.interfaces.IBaseDAO;
import cl.ps.util.Constants;
import cl.ps.util.Enviroment;
import cl.ps.util.LogFactory;

public class BaseDAO implements IBaseDAO{

	protected BaseDAO() {
		this.log = LogFactory.getLogger();
		this.track = LogFactory.getTracker();
    	offset = -1;
    	limit = -1;
	}

	protected Enviroment enviroment;
	public void setEnviroment(Enviroment enviroment) {
		this.enviroment = enviroment;
	}
	public Enviroment getEnviroment() {
		return this.enviroment;
	}

    protected Logger log;
    protected Logger track;

    private int limit;
    private int offset;
    private int totalPage = -1;
    
    public int getTotalPage() {
		return totalPage;
	}
    
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

    private DAOFactory daofactory;
    protected DAOFactory getDAOFactory(){
    	if( daofactory == null )
    		daofactory = new DAOFactory();
    	
    	daofactory.setEnviroment(getEnviroment());
    	return daofactory;
    }

	public void setLimit( int offset, int limit){
		log.debug("SET LIMIT;  OFFSET "+offset+" LIMIT "+limit);
    	this.limit = limit;
    	this.offset = offset;
    }

    public void resetLimit(){
    	offset = -1;
    	limit = -1;
    	totalPage = -1;
    }
    
    /**
     * @param sql
     * @return
     */
    protected String limit(String sql,Integer page,Integer totalRows){
	    
    	log.debug("page "+page+" totalRows "+totalRows);
    	if( page == null )
    		return sql;
    	
	    int offset = -1;
	    int limit = -1;
	    
	    if(page != null ){

		    int[] array = calculateOffsetLimit(page,totalRows);
		    if( array != null && array.length > 1 ){
		    	offset = array[0];
		    	limit = array[1];
		    }
	    }
	    
	    if(offset < 0 || limit < 0)
	    	return sql;
	    
    	return sql+" LIMIT "+offset+", "+limit;
    }

	/**
	 * 
	 * @param page
	 * @param totalRows
	 * @return Retorna un arreglo de int que tiene el offset en su posiciÃ³n 0 y el limit en su posiciÃ³n 1.
	 */
	protected int[] calculateOffsetLimit(Integer page, Integer totalRows) {
		
		int localtotalPage = Constants.PAGE; 
	    if( totalPage > 0 )
	    	localtotalPage = totalPage ;
	    
	    if(totalRows != null ){
	    	
	    	double lastPage = Math.ceil((double)totalRows/(double)localtotalPage);
	    	
	    	if( page > lastPage) {
	    		
		    	page = (int) Math.ceil((double)totalRows/(double)localtotalPage);
		    	
		    	Integer tmp = (Integer)getEnviroment().get(Constants.ENVIROMENT_REQUESTEDPAGE);
		    	
		    	if ( tmp != null && tmp != page ) 
		    		getEnviroment().put(Constants.ENVIROMENT_REQUESTEDPAGE, page);;
	    	}
	    }
	    return new int[]{((page - 1)  * localtotalPage) ,localtotalPage};
	}

	private Connection con;
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
	public Connection getConnection(){
		return con;
	}
	
	protected void setAutoCommit(boolean bool) throws SQLException {
		log.debug("----Se definio el autocommit como "+bool);
		con.setAutoCommit(bool);
	}	
	
	protected void setAutoCommitTrue() {
		//finaliza la transaccion
		log.debug("----Se definio el autocommit como true");
		try{ setAutoCommit(true); } catch ( Exception e){ log.error("Imposible setear el auto commit como verdadero",e); }
		
	}

	protected boolean getAutoCommit() throws SQLException {
		return con.getAutoCommit();
	}

	protected void commit() throws SQLException{
		con.commit();
	}

	protected void rollback(){
		try{
			con.rollback();
		}catch(SQLException e){
			log.debug("Imposible realizar el rollback.",e);
		}
	}

	/**
	 * Permite crear una query de actualizacion
	 * @param table
	 * @param idColumn
	 * @param parameters
	 * @return
	 */
	protected String createUpdateQuery(String table,
			Map<String, Object> parameters,
			String... idColumns) {
		StringBuilder query_update = new StringBuilder();
		query_update.append("UPDATE ").append(table).append(" SET ");

		int size = parameters.size();
		for(Map.Entry<String, Object> entry : parameters.entrySet() ){
			if ( --size == 0 ) //si es el Ãºltimo elemento
				query_update.append( entry.getKey() ).append("= ? ");
			else
				query_update.append( entry.getKey() ).append( "= ? ,");
		}
		if( idColumns != null ){
			query_update.append(" WHERE ");
			size = idColumns.length;
			for(String idColumn : idColumns )
				if ( --size == 0 ) //si es el Ãºltimo elemento
					query_update.append(idColumn).append(" = ? ");
				else
					query_update.append(idColumn).append(" = ? AND ");
		}
		log.debug("[Query]: "+query_update.toString());
		return query_update.toString();
	}
	
	protected String createReplaceQuery(String table,
			Map<String, Object> parameters,
			String... idColumns) {
		StringBuilder query_replace = new StringBuilder();
		query_replace.append("REPLACE INTO ").append(table).append(" SET ");

		int size = parameters.size();
		for(Map.Entry<String, Object> entry : parameters.entrySet() ){
			if ( --size == 0 ) //si es el último elemento
				query_replace.append( entry.getKey() ).append("= ? ");
			else
				query_replace.append( entry.getKey() ).append( "= ? ,");
		}
		if( idColumns != null ){
			query_replace.append(", ");
			size = idColumns.length;
			for(String idColumn : idColumns )
				if ( --size == 0 ) //si es el último elemento
					query_replace.append(idColumn).append(" = ? ");
				else
					query_replace.append(idColumn).append(" = ?, ");
		}
		return query_replace.toString();
	}
	
	/**
	 * @param table
	 * @param idColumn
	 * @return
	 */
	protected String createDeleteQuery(String table, String idColumn) {
		StringBuilder delete_update = new StringBuilder();
		delete_update.append("DELETE FROM ").append(table).append(" WHERE ").append(idColumn).append( " = ? ");
		return delete_update.toString();
	}
	
	/**
	 * @param table
	 * @param idColumn
	 * @return
	 */
	protected String createLogicalDeleteQuery(String table, String idColumn) {
		return createLogicalDeleteQuery(table, idColumn, "deleted");
	}
	
	/**
	 * @param table
	 * @param idColumn
	 * @param field
	 * @return
	 */
	protected String createLogicalDeleteQuery(String table, String idColumn, String field) {
		return "UPDATE "+table+" SET "+field+" = 1 WHERE "+idColumn+" = ?";
	}
	
	/**
	 * @param table
	 * @param idColumn
	 * @return
	 */
	protected String createDeleteQuery(String table, Map<String, Object> parameters) {
		StringBuilder delete_update = new StringBuilder();
		delete_update.append("DELETE FROM ").append(table).append(" WHERE ");
		
		int size = parameters.size();
		for(Map.Entry<String, Object> entry : parameters.entrySet() ){
			if ( --size == 0 ){ //si es el Ãºltimo elemento
				delete_update.append(entry.getKey()).append( " = ? ");
			}else{
				delete_update.append(entry.getKey()).append( " = ? AND ");
			}
		}
		return delete_update.toString();
	}
	
	/**
	 * @param string
	 * @return
	 */
	protected String createInsertQuery(String table,
			Map<String, Object> parameters) {
		StringBuilder query_insert1 = new StringBuilder();
		StringBuilder query_insert2 = new StringBuilder();
		query_insert1.append("INSERT INTO ").append(table).append("(");

		int size = parameters.size();
		//cgajardo: para poder realizar "Inserts" vacios
		if(size==0)
			query_insert1.append(") ");
		
		for(Map.Entry<String, Object> entry : parameters.entrySet() ){
			if ( --size == 0 ){ //si es el Ãºltimo elemento
				query_insert1.append( entry.getKey() ).append(") ");
				query_insert2.append("?");
			}else{
				query_insert1.append( entry.getKey() ).append( ",");
				query_insert2.append("?,");
			}
		}
		query_insert1.append(" VALUES(").append(query_insert2.toString()).append(")");
		log.debug("[Query]: "+query_insert1.toString());
		return query_insert1.toString();
	}
	
	/**
	 * Permite setear los valores de un preparedstatement con un map
	 * @param ps
	 * @param val
	 * @throws SQLException 
	 */
	protected void setInsertParams(PreparedStatement ps, Map<String, Object> val) throws SQLException {
		int contador = 1;
		for(Map.Entry<String, Object> entry : val.entrySet() ) {
			if(entry.getValue() == null)
				ps.setNull(contador++, Types.VARCHAR);
			else {
				if(entry.getValue() instanceof String)
					ps.setObject(contador++, ((String)(entry.getValue())).trim());
				else
					ps.setObject( contador++, entry.getValue());
			}
		}
		
		log.debug("[Query] : "+ps.toString());
	}
	
	/**
	 * @param string
	 * @return
	 */
	protected String createInserUpdatetQuery(String table,
			Map<String, Object> parameters, String idKey) {
		StringBuilder query_insert1 = new StringBuilder();
		StringBuilder query_insert2 = new StringBuilder();
		query_insert1.append("INSERT INTO ").append(table).append("(");

		int size = parameters.size();
		//cgajardo: para poder realizar "Inserts" vacios
		if(size==0)
			query_insert1.append(") ");
		
		for(Map.Entry<String, Object> entry : parameters.entrySet() ){
			if ( --size == 0 ){ //si es el Ãºltimo elemento
				query_insert1.append( entry.getKey() ).append(") ");
				query_insert2.append("?");
			}else{
				query_insert1.append( entry.getKey() ).append( ",");
				query_insert2.append("?,");
			}
		}
		query_insert1.append(" VALUES(").append(query_insert2.toString()).append(") ON DUPLICATE KEY UPDATE ");
		
		size = parameters.size();
		for(Map.Entry<String, Object> entry : parameters.entrySet() ){
			if( entry.getKey().compareToIgnoreCase(idKey) == 0 ) {//ignora la key asociada al id
				--size;
				continue;
			}
			if ( --size == 0 ){ //si es el Ãºltimo elemento
				query_insert1.append( entry.getKey() ).append(" = ? ");
			}else{
				query_insert1.append( entry.getKey() ).append( " = ? ,");
			}
		}
		
		log.debug("[Query]: "+query_insert1.toString());
		return query_insert1.toString();
		
	}
	
	/**
	 * Permite setear los valores de un preparedstatement con un map asociado a un insert on duplicat key
	 * @param ps
	 * @param val
	 * @throws SQLException 
	 */
	protected void setInsertUpdateParams(PreparedStatement ps, Map<String, Object> val , String idKey ) throws SQLException {
		int contador = 1;
		for(Map.Entry<String, Object> entry : val.entrySet() ) {
			if(entry.getValue() instanceof String)
				ps.setObject(contador++, ((String)(entry.getValue())).trim());
			else
				ps.setObject( contador++, entry.getValue());
		}
		
		for(Map.Entry<String, Object> entry : val.entrySet() ) {
			if( entry.getKey().compareToIgnoreCase(idKey) == 0 ) //ignora la key asociada al id
				continue;
			if(entry.getValue() instanceof String)
				ps.setObject(contador++, ((String)(entry.getValue())).trim());
			else
				ps.setObject( contador++, entry.getValue());
		}
		
		log.debug("[Query] : "+ps.toString());
	}
	
	/**
	 * @param ps
	 * @param val
	 * @throws SQLException 
	 */
	protected void setUpdateParams(PreparedStatement ps, Map<String, Object> val, Object... idValue) throws SQLException {
		int contador = 1;
		for(Map.Entry<String, Object> entry : val.entrySet() ){
			if(entry.getValue() instanceof String)
				ps.setObject(contador++, ((String)(entry.getValue())).trim());
			else if(entry.getValue() == null)
				ps.setNull(contador++, Types.NULL);
			else
				ps.setObject( contador++, entry.getValue());
		}
		for(Object obj : idValue )
			ps.setObject( contador++ , obj);
		
		log.debug("[Query] : "+ps.toString());
		
	}
	
	/**
	 * @param ps
	 * @param val
	 * @throws SQLException 
	 */
	protected void setDeleteParams(PreparedStatement ps, long idValue) throws SQLException {
		ps.setLong( 1 , idValue);
		log.debug("[Query] : "+ps.toString());
		
	}
	
	/**
	 * @param ps
	 * @param val
	 * @throws SQLException 
	 */
	protected void setDeleteParams(PreparedStatement ps, Map<String, Object> val) throws SQLException {
		int contador = 1;
		for(Map.Entry<String, Object> entry : val.entrySet() ){
			ps.setObject( contador++ , entry.getValue());
		}
		log.debug("[Query] : "+ps.toString());
		
	}
	
	/**
	 * @param ps
	 * @param val
	 * @throws SQLException 
	 */
	protected void setUpdateParams(PreparedStatement ps, Map<String, Object> val, String keyValue) throws SQLException {
		int contador = 1;
		for(Map.Entry<String, Object> entry : val.entrySet() ){
			ps.setObject( contador++ , entry.getValue());
		}
		ps.setString( contador++ , keyValue);
		
		log.debug("[Query] : "+ps.toString());
		
	}

	/**
	 * Permite cerrar el preparedstatement y el resultset de cualquier consulta
	 * @param ps PreparedStatement que se cerrarÃ¡
	 * @param rs ResultSet que se cerrarÃ¡
	 */
	protected void closeAll(PreparedStatement ps, ResultSet rs){
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("Error al cerrar el resultSet",e);
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("Error al cerrar el PreparedStatement",e);
			}
		}
	}
}
