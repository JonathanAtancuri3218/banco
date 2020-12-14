package ec.ups.edu.banco.dao;


import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.ups.edu.banco.modelo.Transaccion;

@Stateless
public class TransaccionDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Metodo crear transaccion
	 * 
	 * @param transaccion recibe como parametro el objeto transaccion
	 */
	public void crearTransaccion(Transaccion transaccion) {
		em.persist(transaccion);
	}

	/**
	 * Metodo actualizar transaccion
	 * 
	 * @param transaccion recibe como parametro el objeto transaccion
	 */
	public void actualizarTransaccion(Transaccion transaccion) {
		em.merge(transaccion);
	}

	/**
	 * Metodo consultar transaccion
	 * 
	 * @param transaccion recibe como parametro el id del objeto transaccion
	 * @return devuelve el objeto transaccion
	 */
	public Transaccion consultarTransaccion(String transaccion) {
		return em.find(Transaccion.class, transaccion);
	}

	/**
	 * metodo listar transacciones
	 * 
	 * @return devuelve una lista de las transacciones
	 */
	public List<Transaccion> listarTransaccion() {
		String jpql = "SELECT p FROM Transaccion p";
		Query query = em.createQuery(jpql, Transaccion.class);
		return query.getResultList();
	}
	/**
	 * Devuele una lista unicamente de los transacciones con el tipo indicado
	 * @param tipoMov especifica el tipo de movimiento
	 * @param cuenta referencia el numero de cuenta de la persona a consultar las transacciones
	 * @return
	 */
	public List<Transaccion> ListaTipoTransaccion(String tipo, String cuenta) {
		 String jpql = "SELECT * FROM banco.transaccion WHERE tipoTransaccion=:tipo AND numCuenta=:cuenta";
			Query query = em.createNativeQuery(jpql, Transaccion.class);
			query.setParameter("tipo",tipo);
			query.setParameter("cuenta",cuenta);
			List<Transaccion> nivel = query.getResultList();
			return nivel;
	 }
	/**
	 * Devuelve una lista de las transacciones de una cuenta
	 * @param cuenta se pasa como parametro el numero de cuenta
	 * @return 
	 */
	public List<Transaccion> ListaCuentaTransaccion(String cuenta) {
		 String jpql = "SELECT * FROM banco.transaccion WHERE numCuenta=:cuenta";
			Query query = em.createNativeQuery(jpql, Transaccion.class);
			query.setParameter("cuenta",cuenta);
			List<Transaccion> nivel = query.getResultList();
			return nivel;
	 }
	/**
	 * Devuelve una lista de las transacciones entre dos fechas haciendo referencia al numero de cuenta especificado
	 * @param desde fecha desde la cual se desea consultar
	 * @param hasta fecha hasta la cual se desea consultar
	 * @param cuenta numero de cuenta de la cual se consultara las transacciones
	 * @return
	 */
	public List<Transaccion> ListaPorFecha(String desd, String hast, String cuenta) {
		System.out.println(desd);
		System.out.println(hast);
		System.out.println(cuenta);
		 String jpql = "SELECT * FROM banco.transaccion WHERE numCuenta=:cuenta AND fechaTransaccion=:desde";
			Query query = em.createNativeQuery(jpql, Transaccion.class);
			
			query.setParameter("cuenta",cuenta);
			query.setParameter("desde",desd);
			//query.setParameter("hasta",hast);
			List<Transaccion> nivel = query.getResultList();
			return nivel;
	 }
	
}
