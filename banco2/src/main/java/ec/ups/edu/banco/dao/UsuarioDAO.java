package ec.ups.edu.banco.dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.ups.edu.banco.modelo.Transaccion;
import ec.ups.edu.banco.modelo.Usuario;

@Stateless
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Metodo crear usuario
	 * @param usuario recibe como parametro el objeto usuario
	 */
	public void crearUsuario(Usuario usuario) {
		em.persist(usuario);
	}

	/**
	 * Metodo actualizar usuario
	 * @param usuario recibe como parametro el objeto usuario
	 */
	public void actualizarUsuario(Usuario usuario) {
		em.merge(usuario);
	}

	/**
	 * Metodo contulstar usuario
	 * @param usuario recibe como parametro el id del usuario
	 * @return devuelve el objeto usuario
	 */
	public Usuario consultarUsuario(String usuario) {
		return em.find(Usuario.class, usuario);
	}

	/**
	 * Metodo listar usuario
	 * @return devuelve una lista de usuarios
	 */
	public List<Usuario> listarUsuario() {
		String jpql = "SELECT p FROM Usuario p";
		Query query = em.createQuery(jpql, Usuario.class);
		return query.getResultList();
	}
	/**
	 * Devuelve una lista de los usuarios cuyo tipo de ral sea no nulo
	 * esto se hace para comprobar si es un cliente normal o una administrador, ya sea cajero, administrador o jefe de credito	
	 * @return
	 */
	public List<Usuario> ConsultarUsuarioTipo() {
		 String jpql = "SELECT * FROM banco.usuario WHERE tipoUsuario='Administrador' OR tipoUsuario='Cajero' OR tipoUsuario='Asistente de Captaciones'";
			Query query = em.createNativeQuery(jpql, Usuario.class);
			List<Usuario> nivel = query.getResultList();
			return nivel;
	 }
	/**Metodo para consultar el tipo de usuario en este caso unicamente los clientes no administradores
	 * Devuelve una listar de usuarios clientes
	 * @return
	 */
	public List<Usuario> ConsultarUsuarioTipoCliente() {
		 String jpql = "SELECT * FROM banco.usuario WHERE tipoUsuario='Cliente'";
			Query query = em.createNativeQuery(jpql, Usuario.class);
			List<Usuario> nivel = query.getResultList();
			return nivel;
	 }
	/**
	 * Metodo para consultar la cuenta del usuario especificado
	 * @param pasamos como parametro usuario que es el email de inicio de sesion
	 * @return devuelve una lista con los datos del usuario consultado
	 */
	public List<Usuario> ConsultarNumCuentaUsuario(String usuario) {
		 String jpql = "SELECT * FROM banco.usuario WHERE emailUsuario=:usuario";
			Query query = em.createNativeQuery(jpql, Usuario.class);
			query.setParameter("usuario",usuario);
			List<Usuario> nivel = query.getResultList();
			return nivel;
	 }
	/**
	 * Metodo para consultar el los datos del usuario con el numero de cuenta especificado
	 * @param cuenta numero de cuenta
	 * @return devuelve una lista de los datos del usuario especificado por el numero de cuenta
	 */
	public List<Usuario> ConsultarCuenta(String cuenta) {
		 String jpql = "SELECT * FROM banco.usuario WHERE numCuenta=:cuenta";
			Query query = em.createNativeQuery(jpql, Usuario.class);
			query.setParameter("cuenta",cuenta);
			List<Usuario> nivel = query.getResultList();
			return nivel;
	 }
}
