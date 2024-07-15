package excepciones;

public class CargaClientesException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CargaClientesException() {}

	@Override
	public String getMessage() {
		return "Error en la carga de clientes";
	}
}
