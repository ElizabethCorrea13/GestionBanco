package entidad;

public class TipoDeCuenta {
	
	private int id;
	private String NombreDeCuenta;
	
	
	public TipoDeCuenta() {}
	
	public TipoDeCuenta(int id, String nombreDeCuenta) {
		super();
		this.id = id;
		NombreDeCuenta = nombreDeCuenta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreDeCuenta() {
		return NombreDeCuenta;
	}

	public void setNombreDeCuenta(String nombreDeCuenta) {
		NombreDeCuenta = nombreDeCuenta;
	}

	@Override
	public String toString() {
		return "TipoDeCuenta [id=" + id + ", NombreDeCuenta=" + NombreDeCuenta + "]";
	}
	
}