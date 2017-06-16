/**
 * 
 */
package it.neosix.radici.model;


/**
 * @author NeosiX
 *
 */
public class GenitoriFigli {
	
	private Persona figlio;
	private Persona padre;
	private Persona madre;
	
	
	public GenitoriFigli(Persona figlio, Persona padre, Persona madre) {
		this.figlio = figlio;
		this.padre = padre;
		this.madre = madre;
	}
	
	/**
	 * @return the figlio
	 */
	public Persona getFiglio() {
		return figlio;
	}

	/**
	 * @param figlio the figlio to set
	 */
	public void setFiglio(Persona figlio) {
		this.figlio = figlio;
	}

	/**
	 * @return the padre
	 */
	public Persona getPadre() {
		return padre;
	}

	/**
	 * @param padre the padre to set
	 */
	public void setPadre(Persona padre) {
		this.padre = padre;
	}

	/**
	 * @return the madre
	 */
	public Persona getMadre() {
		return madre;
	}

	/**
	 * @param madre the madre to set
	 */
	public void setMadre(Persona madre) {
		this.madre = madre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((figlio == null) ? 0 : figlio.hashCode());
		result = prime * result + ((madre == null) ? 0 : madre.hashCode());
		result = prime * result + ((padre == null) ? 0 : padre.hashCode());
		return result;
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
		GenitoriFigli other = (GenitoriFigli) obj;
		if (figlio == null) {
			if (other.figlio != null)
				return false;
		} else if (!figlio.equals(other.figlio))
			return false;
		if (madre == null) {
			if (other.madre != null)
				return false;
		} else if (!madre.equals(other.madre))
			return false;
		if (padre == null) {
			if (other.padre != null)
				return false;
		} else if (!padre.equals(other.padre))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenitoriFigli [figlio=" + figlio.getNome() + ", padre=" + padre.getNome() + ", madre=" + madre.getNome() + "]";
	}
	
	
	
	
	
	
/*	
	private Integer figlioID;
	private Integer padreID;
	private Integer madreID;
	

	
	
	public GenitoriFigli(Integer figlioID, Integer padreID, Integer madreID) {
		this.figlioID = figlioID;
		this.padreID = padreID;
		this.madreID = madreID;
	}

	*//**
	 * @return the figlioID
	 *//*
	public Integer getFiglioID() {
		return figlioID;
	}
	
	*//**
	 * @param figlioID the figlioID to set
	 *//*
	public void setFiglioID(Integer figlioID) {
		this.figlioID = figlioID;
	}

	*//**
	 * @return the padreID
	 *//*
	public Integer getPadreID() {
		return padreID;
	}

	*//**
	 * @param padreID the padreID to set
	 *//*
	public void setPadreID(Integer padreID) {
		this.padreID = padreID;
	}

	*//**
	 * @return the madreID
	 *//*
	public Integer getMadreID() {
		return madreID;
	}

	*//**
	 * @param madreID the madreID to set
	 *//*
	public void setMadreID(Integer madreID) {
		this.madreID = madreID;
	}

	 (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + figlioID;
		result = prime * result + madreID;
		result = prime * result + padreID;
		return result;
	}

	 (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenitoriFigli other = (GenitoriFigli) obj;
		if (figlioID != other.figlioID)
			return false;
		if (madreID != other.madreID)
			return false;
		if (padreID != other.padreID)
			return false;
		return true;
	}

	 (non-Javadoc)
	 * @see java.lang.Object#toString()
	 
	@Override
	public String toString() {
		return "GenitoriFigli [figlioID=" + figlioID + ", padreID=" + padreID + ", madreID=" + madreID
				+ "]";
	}*/
	
	
	
	



}
