package entidad;

public class Localidad {
	private int id;
	private int idProvincia;
	private String nombre;
	
	public Localidad() {}

	public Localidad(int id, int idProvincia, String nombre) {
		super();
		this.id = id;
		this.idProvincia = idProvincia;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Localidad [id=" + id + ", idProvincia=" + idProvincia + ", nombre=" + nombre + "]";
	}
	
	
}

