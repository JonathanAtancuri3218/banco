package ec.ups.edu.banco.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ups.edu.ec.modelos.Amortizacion;
import ups.edu.ec.modelos.Credito;
import ups.edu.ec.modelos.Transaccion;

@Stateless
public class AmortizacionDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Metodo crear amortizacion
	 * @param amortizacion recibe como parametro un objeto amortizacion
	 */
	public void crearAmortizacion(Amortizacion amortizacion) {
		em.persist(amortizacion);
	}

	/**
	 * Metodo actualizar amortizacion
	 * @param amortizacion recibe como parametro el objeto amortizacion
	 */
	public void actualizarAmortizacion(Amortizacion amortizacion) {
		em.merge(amortizacion);
	}

	/**
	 * Metodo consultar amortizacion
	 * @param amortizacion recibe como parametro el id del objeto amortizacion
	 * @return devuelve el objeto amortizacion
	 */
	public Amortizacion consultarAmortizacion(int amortizacion) {
		return em.find(Amortizacion.class, amortizacion);
	}

	/**Metodo listar amortizacion
	 * 
	 * @return devuelve una lista del objeto
	 */
	public List<Amortizacion> listarAmortizacion() {
		String jpql = "SELECT p FROM Amortizacion p";
		Query query = em.createQuery(jpql, Amortizacion.class);
		return query.getResultList();
	}
	public List<Amortizacion> listarAmortizacionIdCredito(int id) {
		String jpql = "SELECT * FROM miautransfer.amortizacion WHERE idCredito=:idCredito" ;
		Query query = em.createNativeQuery(jpql, Amortizacion.class);
		query.setParameter("idCredito",id);
		System.out.println(query);
		List<Amortizacion> nivel = query.getResultList();
		return nivel;
	}
	public List<Amortizacion> listarAmortizacionEstado(int Id) {
		String jpql = "SELECT * FROM miautransfer.amortizacion WHERE estadoAmortizacion='Vencido' AND idCredito=:idcredito";
		Query query = em.createNativeQuery(jpql, Amortizacion.class);
		query.setParameter("idcredito",Id);
		List<Amortizacion> nivel = query.getResultList();
		return nivel;
	}
	public List<Amortizacion> listarCuotasEstado(int Id) {
		String jpql = "SELECT * FROM miautransfer.amortizacion WHERE estadoAmortizacion='Vencido' OR estadoAmortizacion='Pendiente' AND idCredito=:idcredito ";
		Query query = em.createNativeQuery(jpql, Amortizacion.class);
		query.setParameter("idcredito",Id);
		List<Amortizacion> nivel = query.getResultList();
		return nivel;
	}
}
