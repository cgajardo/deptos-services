package cl.ps.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.ps.entities.Voting;
import cl.ps.model.exceptions.QueryException;


public class VotingDAO extends BaseDAO {

	/**
	 * Obtiene la lista de acciones asociadas a una empresa en particular
	 * @param companyId El id de la empresa de la cual se obtendran sus acciones.
	 * @return La lista de las acciones asociadas a una empresa en particular.
	 * @throws QueryException
	 */
	public List<Voting> getByBuilding(long buildingId) throws QueryException {
		PreparedStatement ps = null;
	    ResultSet rs = null;

		//List<Voting> resultList = new LinkedList<Voting>();
		try {

			ps = getConnection().prepareStatement("");

//			ps.setInt(2, mandatory );
//			ps.setLong(3, companyId );

            rs = ps.executeQuery();
//            while(rs.next()){
//            	resultList.add(fill(rs));
//            }
            return null;
        } catch (SQLException e) {
        	throw new QueryException("Error al obtener la lista de acciones desde la base de datos.", e );
        }
        finally {
            closeAll( ps, rs );
        }
	}
	
	public Long create(Voting voting) throws QueryException {
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
			
			Map<String,Object> val = getMap(voting);

			final String sql = createInsertQuery("votings", val);
			
			ps = getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			setInsertParams(ps, val);
			
			if(ps.executeUpdate() == 0)
				throw new SQLException("Ninguna fila fue afectada al intentar insertar una nueva votacion.");
            
            rs = ps.getGeneratedKeys();
            if(rs.next())
            	voting.setId(new Long(rs.getLong(1)));
				
			return voting.getId();
	    } catch (SQLException e) {
        	throw new QueryException("Error al insertar una nueva votacion.", e );
        }
        finally {
            closeAll( ps, rs );
        }	
	}

	public Long update(Voting voting) throws QueryException {
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
			
			Map<String,Object> val = getMap(voting);
			
			final String sql = createUpdateQuery("votings", val, "votingId");

			ps = getConnection().prepareStatement(sql);

			setUpdateParams(ps, val, voting.getId().longValue());

            ps.executeUpdate();
				
			return voting.getId();
	    } catch (SQLException e) {
        	throw new QueryException("Error al actualizar la votacion con id: "+voting.getId().longValue(), e );
        }
        finally {
            closeAll( ps, rs );
        }	
	}
	
	public Map<String,Object> getMap(Voting voting) throws SQLException {
		Map<String,Object> val = new HashMap<String,Object>();

		if(voting.getTitle() != null && voting.getTitle().trim().compareTo("") != 0)
			val.put("title", voting.getTitle().trim());
		
		if(voting.getDescription() != null && voting.getDescription().trim().compareTo("") != 0)
			val.put("description", voting.getDescription().trim());
		
		if(voting.getBuilding() != null && voting.getBuilding().getId() != null && voting.getBuilding().getId().longValue() > 0)
			val.put("buildingId", voting.getBuilding().getId().longValue());
		
		if(voting.getUser() != null && voting.getUser().getId() != null && voting.getUser().getId().longValue() > 0)
			val.put("userId", voting.getUser().getId().longValue());
		
		if(voting.getDateCreated() != null)
			val.put("dateCreated", voting.getDateCreated());
		
		if(voting.getDeleted() != null && (voting.getDeleted().longValue() == 0 || voting.getDeleted().longValue() == 1))
			val.put("deleted", voting.getDeleted().longValue());
		
		if(voting.getPossibleAnwers() != null && voting.getPossibleAnwers().longValue() > 0)
			val.put("possibleAnswers", voting.getPossibleAnwers().longValue());
		
		return val;
	}
}
