package it.neosix.radici.model;


import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;




/**
 * Model class for a Person.
 *
 * Un singolo elemento dell'albero genealogico
 * 
 * Semplice Java Bean
 * (POJO Plain Old Java Object)
 * 
 * @author NeosiX
 */

public class Persona {

	private IntegerProperty personaID = new SimpleIntegerProperty(0);
	private StringProperty nome ;
	private StringProperty cognome;
	private StringProperty sesso;
	private StringProperty luogoNascita;
	private ObjectProperty<LocalDate> dataNascita;
	private ObjectProperty<LocalDate> dataMorte;
	private ObjectProperty<byte[]> foto;

	public Persona() {
		//this("default", "default", "default", "default", null, LocalDate.of(1900, 1, 1), null);
	}

	public Persona(String nome, String cognome, String sesso, String luogoNascita,
			 LocalDate dataNascita, LocalDate dataMorte, byte[] foto) {
		
		//System.out.println(nome + " " + cognome + " " + sesso + " " + luogoNascita + " " + dataNascita + " " + dataMorte);
		/*this.nome = new SimpleStringProperty(nome);
		this.cognome = new SimpleStringProperty(cognome);
		this.sesso = new SimpleStringProperty(sesso);
		this.luogoNascita = new SimpleStringProperty(luogoNascita);
		this.dataNascita = new SimpleObjectProperty<LocalDate>(dataNascita); 
		this.dataMorte = new SimpleObjectProperty<LocalDate>(dataMorte);
		this.foto = new SimpleObjectProperty<byte[]>(foto);*/
	}
	
	/***********************/
	/* getter setter NOME */
    public StringProperty nomeProperty() {
    	if (nome == null){
    		nome = new SimpleStringProperty();
    	}
        return nome;
    }

	public final String getNome() {
        return nomeProperty().get();
    }

    public final void setNome(final String nome) {
    	nomeProperty().set(nome);
    }

    /***********************/
    
    /***********************/
	/* getter setter COGNOME */
    public StringProperty cognomeProperty() {
    	if (cognome == null){
    		cognome = new SimpleStringProperty();
    	}
        return nome;
    }
    
	public final String getCognome() {
        return cognomeProperty().get();
    }

    public final void setCognome(final String cognome) {
    	cognomeProperty().set(cognome);
    }

    /***********************/
       
    /***********************/
	/* getter setter SESSO */
    public StringProperty sessoProperty() {
    	if (sesso == null){
    		sesso = new SimpleStringProperty();
    	}
        return sesso;
    }
    public final String getSesso() {
        return sessoProperty().get();
    }

    public final void setSesso(String sesso) {
        sessoProperty().set(sesso);
    }
    /***********************/
    
    /***********************/
   	/* getter setter LUOGO DI NASCITA */
    public StringProperty luogoNascitaProperty() {
    	if (luogoNascita == null){
    		luogoNascita = new SimpleStringProperty();
    	}
    	return luogoNascita;
    }
    public final String getLuogoNascita() {
        return luogoNascitaProperty().get();
    }

    public final void setLuogoNascita(String luogoNascita) {
    	luogoNascitaProperty().set(luogoNascita);
    }
    /***********************/
    
    /***********************/
   	/* getter setter DATA DI NASCITA */
    public ObjectProperty<LocalDate> dataNascitaProperty() {
    	if (dataNascita == null){
    		dataNascita = new SimpleObjectProperty<>();
    	}
    	return dataNascita;
    }
    public final LocalDate getDataNascita() {
        return dataNascitaProperty().get();
    }

    public final void setDataNascita(LocalDate dataNascita) {
    	dataNascitaProperty().set(dataNascita);
    }
    /***********************/
    
    /***********************/
   	/* getter setter DATA DI MORTE */
    public ObjectProperty<LocalDate> dataMorteProperty() {
    	if (dataMorte == null){
    		dataMorte = new SimpleObjectProperty<>();
    	}
    	return dataMorte;
    }
    public final LocalDate getDataMorte() {
        return dataMorteProperty().get();
    }

    public final void setDataMorte(LocalDate dataMorte) {
    	dataMorteProperty().set(dataMorte);
    }
    /***********************/

    /***********************/
   	/* getter setter PERSONA_ID */
    public IntegerProperty personaIDProperty() {
    	if (personaID == null){
    		personaID = new SimpleIntegerProperty();
    	}
    	return personaID;
    }
    public final Integer getPersonaID() {
        return personaIDProperty().get();
    }

    public final void setPersonaID(int personaID) {
    	personaIDProperty().set(personaID);
    }
    /***********************/

    /***********************/
   	/* getter setter FOTO */
    public ObjectProperty<byte[]> fotoProperty() {
    	if (foto == null){
    		foto = new SimpleObjectProperty<>();
    	}
    	return foto;
    }
    
    public final byte[] getFoto() {
        return fotoProperty().get();
    }
   
    public final void setFoto(byte[] foto) {
    	fotoProperty().set(foto);
    }
    /***********************/   

    
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Persona :"
				+ "[personaID=" + personaID + ", \n "
				+ "nome=" + nome + ", cognome=" + cognome + ", \n "
				+ "sesso=" + sesso + ", luogoNascita=" + luogoNascita + ", \n "
				+ "dataNascita=" + dataNascita.toString() + ", dataMorte=" + dataMorte + "\n"
				+ "]\n";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((dataMorte == null) ? 0 : dataMorte.hashCode());
		result = prime * result + ((dataNascita == null) ? 0 : dataNascita.hashCode());
		result = prime * result + ((luogoNascita == null) ? 0 : luogoNascita.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sesso == null) ? 0 : sesso.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Persona)) {
			return false;
		}
		Persona other = (Persona) obj;
		if (cognome == null) {
			if (other.cognome != null) {
				return false;
			}
		} else if (!cognome.get().equals(other.cognome.get())) {
			return false;
		}
		if (dataMorte == null) {
			if (other.dataMorte != null) {
				return false;
			}
		} else if (!dataMorte.get().equals(other.dataMorte.get())) {
			return false;
		}
		if (dataNascita == null) {
			if (other.dataNascita != null) {
				return false;
			}
		} else if (!dataNascita.get().equals(other.dataNascita.get())) {
			return false;
		}
		if (luogoNascita == null) {
			if (other.luogoNascita != null) {
				return false;
			}
		} else if (!luogoNascita.get().equals(other.luogoNascita.get())) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.get().equals(other.nome.get())) {
			return false;
		}
		if (sesso == null) {
			if (other.sesso != null) {
				return false;
			}
		} else if (!sesso.get().equals(other.sesso.get())) {
			return false;
		}
		return true;
	}
	
	


  
}
