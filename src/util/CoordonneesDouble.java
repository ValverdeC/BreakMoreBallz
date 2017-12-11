package util;

public class CoordonneesDouble {
		// attributs
		private double x;
		private double y;
		
		// Methodes
		public double getX() {
			return x;
		}
		public void setX(double x) {
			this.x = x;
		}
		public double getY() {
			return y;
		}
		public void setY(double y) {
			this.y = y;
		}
		public CoordonneesDouble(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + "]";
		}
}
