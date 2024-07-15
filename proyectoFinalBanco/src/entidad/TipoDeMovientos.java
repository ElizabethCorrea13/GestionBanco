package entidad;

public class TipoDeMovientos {
		private int id;
		private String Descripcion;
		
		public TipoDeMovientos() {}
		
		public TipoDeMovientos(int id, String descripcion) {
			this.id = id;
			Descripcion = descripcion;
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDescripcion() {
			return Descripcion;
		}
		public void setDescripcion(String descripcion) {
			Descripcion = descripcion;
		}

		@Override
		public String toString() {
			return "TipoDeMovientos [id=" + id + ", Descripcion=" + Descripcion + "]";
		}
		
		
}
