package ec.ups.edu.banco.dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.ups.edu.banco.modelo.DetalleSesion; 

@Stateless
public class SesionDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Metodo crear detalle de sesion
	 * 
	 * @param detalleSesion recibe como parametro el objeto detallesesion
	 */
	public void crearDetalleSesion(DetalleSesion detalleSesion) {
		em.persist(detalleSesion);
	}

	/**
	 * Metodo actualizar detalle
	 * 
	 * @param detalleSesion recibe como parametro el objeto detallesesion
	 */
	public void actualizarDetalleSesion(DetalleSesion detalleSesion) {
		em.merge(detalleSesion);
	}

	/**
	 * Metodo consultar detalle de sesion
	 * 
	 * @param detalleSesion recibe como parametro el id del objeto
	 * @return devuelve el objeto detallesesion
	 */
	public DetalleSesion consultarDetalleSesion(String detalleSesion) {
		return em.find(DetalleSesion.class, detalleSesion);
	}

	/**
	 * Metodo listar detalles de sesion devuelve una lista de detalles de session
	 * 
	 * @return
	 */
	public List<DetalleSesion> listarDetalleSesion() {
		String jpql = "SELECT p FROM DetalleSesion p";
		Query query = em.createQuery(jpql, DetalleSesion.class);
		return query.getResultList();
	}
	/**
	 * Metodo listar detalles de sesion del usuario
	 * @param cedula pasamos como parametro la cedula para traer datos unicamente del usuario cuya cedula sea la especificada
	 * @return devuelve una lista de detalles de sesion
	 */
	public List<DetalleSesion> ListaDetalleSesionUsuario(String cedula) {
		 String jpql = "SELECT * FROM miautransfer.detallesesion WHERE cedulaUsuario=:cedula";
			Query query = em.createNativeQuery(jpql, DetalleSesion.class);
			query.setParameter("cedula",cedula);
			List<DetalleSesion> nivel = query.getResultList();
			return nivel;
	 }
	/**
	 * Metodo listar tipos de sesion ya sean solo exitosos o erroneos
	 * @param tipo pasa como parametro la variable tipo que puede ser exitoso o erroneo
	 * @param cedula parametro cedula para especificar de quien se traera los detalles de sesion
	 * @return devuelve una lista de los detalles de sesion
	 */
	public List<DetalleSesion> ListaTipoSesion(String tipo, String cedula) {
		 String jpql = "SELECT * FROM miautransfer.detallesesion WHERE detalleSesion=:tipo AND cedulaUsuario=:cedula";
			Query query = em.createNativeQuery(jpql, DetalleSesion.class);
			query.setParameter("cedula",cedula);
			query.setParameter("tipo",tipo);
			List<DetalleSesion> nivel = query.getResultList();
			return nivel;
	 }
}
