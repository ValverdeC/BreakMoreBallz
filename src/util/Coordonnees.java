package util;

/** 
 * Classe utilitaire permettant de stocker les coordonnées aux différents éléments graphiques 
 * Coordonnées stockées sous forme d'entiers. 
 */
public class Coordonnees implements Comparable<Coordonnees> {
	// attributs
	private int x;
	private int y;
	
	// Methodes
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Coordonnees(int x, int y) {
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
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordonnees other = (Coordonnees) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Coordonnees o) {
	  if (x == o.x) {
		  return y - o.y;		  
	  }
	  return x - o.x;
	}
	
}
