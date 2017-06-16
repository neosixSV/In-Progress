/**
 * 
 */
package it.neosix.radici.model;

import java.util.ArrayList;

import java.util.List;

import it.neosix.radici.dao.PersoneDAO;


/**
 * Insieme di tutte le {@link Persona} dell'Albero Genealogico,
 * ed i metodi per poterlo gestire.
 * 
 * @author NeosiX
 *  
 */
public class AlberoModel {

	private List<Persona> albero;
	
	public AlberoModel() {
		this.albero = new ArrayList<Persona>();		
	}
	
	/**
	 * Aggiunge una persona all'albero se questa non esiste gia',
	 * altrimenti restituisce {@code false}
	 * 
	 * @param pers la nuova persona da aggiungere
	 * @return {@code false} se esiste gia', 
	 * {@code true} se inserito correttamente.
	 * 
	 */
	public boolean addPersona(Persona pers) {
		if (albero.contains(pers)) {
			return false;
		} else {
			albero.add(pers);
			System.out.println("inserito " + pers.getNome() + " nell'albero");
			return true;
		}		
	}

	/**
	 * Rimuove una persona all'albero se questa esiste gia',
	 * altrimenti restituisce {@code false}
	 * 
	 * @param pers la persona da rimuovere
	 * @return {@code true} se rimossa correttamente, 
	 * {@code false} se non esiste.
	 * 
	 */
	public boolean removePersona(Persona pers) {
		if (albero.contains(pers)) {
			albero.remove(pers);
			System.out.println("rimosso " + pers.getNome() + " dall'albero");
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Cerca nell'albero per nome
	 * @param nome Stringa da ricercare nel campo nome
	 * @return {@code List<Persona>} la lista dei nomi trovati.
	 */
	public List<Persona> findByNome(String nome) {
		List<Persona> tempList = new ArrayList<Persona>();
		for (Persona persona : albero) {
			if (persona.getNome().equals(nome)){
				tempList.add(persona);
			}
		}
		return tempList;
	}
	
	/**
	 * Cerca nell'albero per noe e cognome
	 * @param nome Stringa da ricercare nel campo nome
	 * @param cognome Stringa da ricercare nel campo cognome
	 * @return {@code List<Persona>} la lista dei nomi trovati.
	 */
	public List<Persona> findByNomeCognome(String nome, String cognome) {
		List<Persona> tempList = new ArrayList<Persona>();
		for (Persona persona : albero) {
			if (persona.getNome().equals(nome) && persona.getCognome().equals(cognome)){
				tempList.add(persona);
			}
		}
		return tempList;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AlberoModel: \n[" + albero + "]";
	}
	
	
	/* main di test */
	public static void main(String [] args) {

		AlberoModel albero = new AlberoModel();

		PersoneDAO persDAO = new PersoneDAO();
		
		for (Persona pers : persDAO.selectAll()) {
			albero.addPersona(pers);
		}
		
		
		/*Persona pers1 = new Persona("vincenzo", "fogliaro","m","vibo Valentia",null,LocalDate.of(1975,5,1));
		boolean r1 = albero.addPersona(pers1);
		Persona pers2 = new Persona("valerio", "fogliaro","m","vibo Valentia",null,LocalDate.of(1978,6,17));
		boolean r2 = albero.addPersona(pers2);
		Persona pers3 = new Persona("andrea", "fogliaro","m","vibo Valentia",null,LocalDate.of(1983,4,12));
		boolean r3 = albero.addPersona(pers3);
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		//System.out.println("findByNome:" + albero.findByNome("vincenzo"));
		//System.out.println("findByNomeCognome:" + albero.findByNomeCognome("vincenzo", "fogiaro"));
		System.out.println("remove:" + albero.removePersona(pers3));
		System.out.println("fine");
		*/
		
		System.out.println(albero.toString());
		
		
	}


	
}
