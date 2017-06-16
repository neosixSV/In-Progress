/**
 * 
 */
package it.neosix.radici.model;

import java.time.LocalDate;

/**
 * @author NeosiX
 *
 */
public class Stuff {
	
	private String nome;
	private String autore;
	private LocalDate dataCreaz;
	private LocalDate last_modify;

	/**
	 * 
	 */
	public Stuff() {
		// TODO Auto-generated constructor stub
	}

	public Stuff(String nome, String autore, LocalDate dataCreaz, LocalDate last_modify) {
		this.nome = nome;
		this.autore = autore;
		this.dataCreaz = dataCreaz;
		this.last_modify = last_modify;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the autore
	 */
	public String getAutore() {
		return autore;
	}

	/**
	 * @param autore the autore to set
	 */
	public void setAutore(String autore) {
		this.autore = autore;
	}

	/**
	 * @return the dataCreaz
	 */
	public LocalDate getDataCreaz() {
		return dataCreaz;
	}

	/**
	 * @param dataCreaz the dataCreaz to set
	 */
	public void setDataCreaz(LocalDate dataCreaz) {
		this.dataCreaz = dataCreaz;
	}

	/**
	 * @return the last_modify
	 */
	public LocalDate getLast_modify() {
		return last_modify;
	}

	/**
	 * @param last_modify the last_modify to set
	 */
	public void setLast_modify(LocalDate last_modify) {
		this.last_modify = last_modify;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stuff [nome=" + nome + ", autore=" + autore + ", dataCreaz=" + dataCreaz + ", last_modify="
				+ last_modify + "]";
	}
	
	

}

