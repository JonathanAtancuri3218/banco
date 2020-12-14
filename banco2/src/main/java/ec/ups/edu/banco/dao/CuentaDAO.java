package ec.ups.edu.banco.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.ups.edu.banco.modelo.Cuenta;

@Stateless
public class CuentaDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * metodo crear cuenta
	 * 
	 * @param cuenta recibe como paremetro el objeto cuenta
	 */
	public void crearCuenta(Cuenta cuenta) {
		em.persist(cuenta);
	}

	/**
	 * metodo actualizar cuenta
	 * 
	 * @param cuenta recibe como parametro el objeto cuenta
	 */
	public void actualizarCuenta(Cuenta cuenta) {
		em.merge(cuenta);
	}

	/**
	 * metodo consultar cuenta
	 * 
	 * @param cuenta recibe como parametro el id del objeto cuenta
	 * @return devuelve el objeto cuenta
	 */
	public Cuenta consultarCuenta(String cuenta) {
		return em.find(Cuenta.class, cuenta);
	}

	/**
	 * Metodo listar Cuenta Devuelve una lista de cuentas
	 * 
	 * @return
	 */
	public List<Cuenta> listarCuenta() {
		String jpql = "SELECT p FROM Cuenta p";
		Query query = em.createQuery(jpql, Cuenta.class);
		return query.getResultList();
	}
}