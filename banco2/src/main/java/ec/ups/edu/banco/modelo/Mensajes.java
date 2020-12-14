package ec.ups.edu.banco.modelo;

public class Mensajes {

	private int id;
	private String mensaje;
	
	public Mensajes() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	@Override
	public String toString() {
		return "Mensajes [id=" + id + ", mensaje=" + mensaje + "]";
	}
	
	
}
