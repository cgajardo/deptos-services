/**
 *
 */
package cl.ps.controller;

import java.sql.Connection;
import java.util.List;

import cl.ps.controller.BusinessController;
import cl.ps.controller.exceptions.BusinessControllerException;
import cl.ps.controller.exceptions.InvalidArgumentException;
import cl.ps.entities.Voting;
import cl.ps.model.exceptions.DAOException;
public class VotingBC extends BusinessController {

	/**
	 * Obtiene la lista de todos las votaciones de un edificio en particular
	 * @param buildingId Id del edificio en particular
	 * @return
	 * @throws SQLException
	 * @throws ConnectionException
	 */
	public List<Voting> getByBuilding(long buildingId) throws BusinessControllerException {
		Connection con = getConnection();
		try {
			if( buildingId < 0 )
				throw new InvalidArgumentException("Edificio invalido");
			
			//User user = (User) getEnviroment().get(Constants.ENVIROMENT_USER);
			
//			if(!has_permissions)
//				throw new ForbiddenException(e("exception.user.forbidden"));
			
			return getDAOFactory().getVotingDAO(con).getByBuilding(buildingId);
		}catch (DAOException e){
			throw new BusinessControllerException(e);
		} finally{
			
		}
	}

	/**
	 * Permite guardar una votacion en base de datos.
	 * @param voting El objeto que contiene la información de la votacion a guardar
	 * @return
	 * @throws BusinessControllerException
	 */
	public Voting save(Voting voting) throws BusinessControllerException{
		Connection con = getConnection();
		try {
			if(voting.getId() != null && voting.getId().longValue() > 0)
				getDAOFactory().getVotingDAO(con).update(voting);
			else
				voting.setId(getDAOFactory().getVotingDAO(con).create(voting));
			
			/** AUDITORIA **/
			//track.info("El usuario " + user + " creó el usuario "+createUser);
			
			return voting;
		} catch(DAOException e){
			throw new BusinessControllerException(e);
		} finally{
			
		}
	}
//
//	/**
//	 * @param deleteuser
//	 * @return
//	 * @throws BusinessControllerException
//	 */
//	public boolean delete(User deleteuser) throws BusinessControllerException {
//		//Validación
//		if(deleteuser == null || deleteuser.getId()<=0 )
//			throw new InvalidArgumentException(e("exception.user.invalid"));
//
//		Company company = null;
//		Connection con = getConnection();
//		try {
//			
//			User user = (User)getEnviroment().get(Constants.ENVIROMENT_USER);
//			
//			boolean admin = hasPermission(user, "gps_admin");
//			boolean delete_user = true; //TODO
//			if(!admin)
//				if(!delete_user)
//					throw new ForbiddenException(e("exception.user.forbidden"));
//						
//			company = getDAOFactory().getCompanyDAO(con).getByUser(deleteuser.getId());
//			
//			if( company == null || company.getId() < 0 )
//				throw new BusinessControllerException(e("exception.user.company"));
//			
//			getBCFactory().getQuotaBC(con).restoreCompanyQuota(Constants.QUOTA_KEY_USER, company.getId());
//			boolean success = getDAOFactory().getUserDAO(con).delete(deleteuser.getId()); 
//			
//			/** AUDITORIA **/
//			track.info("El usuario " + deleteuser + " eliminó al usuario "+deleteuser);
//			
//			return success;
//			
//		} catch(QuotaException e) {
//			getBCFactory().getQuotaBC(con).useCompanyQuota(Constants.QUOTA_KEY_USER, company.getId());
//			throw e;
//		}catch(DAOException e){
//			throw new BusinessControllerException(e);
//		} finally{
//			
//		}
//	}
//
//	public User get(User user) throws BusinessControllerException {
//		Connection con = getConnection();
//		try {			
//			if( user == null || user.getId() <= 0 )
//				throw new InvalidArgumentException(e(""));
//			
//			User session_user = (User) getEnviroment().get(Constants.ENVIROMENT_USER);
//			User db_user = getDAOFactory().getUserDAO(con).getById(user.getId(), true, false, false, false);
//			
//			//Permisos
//			boolean admin = hasPermission(session_user, "admin_gps");
//			boolean manage_companies = hasModule(session_user, "admin") && hasPermission(session_user, "manage_companies");
//			boolean manage_users = hasModule(session_user, "personalization") && hasPermission(session_user, "manage_users");
//			boolean has_permissions = admin || manage_companies || (manage_users && session_user.getCompany().getId() == db_user.getCompany().getId());
//			
//			if(!has_permissions)
//				throw new ForbiddenException(e("exception.user.forbidden"));
//			
//			return db_user;
//		}catch (DAOException e){
//			throw new BusinessControllerException(e);
//		} finally{
//			
//		}
//	}
}
