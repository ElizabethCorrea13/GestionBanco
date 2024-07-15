package excepciones;

public class CargaDescolgablesException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CargaDescolgablesException() {}

	@Override
	public String getMessage() {
		return "Error en la carga de los descolgables";
	}
	
}
