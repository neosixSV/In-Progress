/**
 * 
 */
package it.neosix.radici.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import it.neosix.radici.app.Main;
import it.neosix.radici.model.Persona;


/**
 * @author NeosiX
 *
 */
public class PersoneDAO  {

	/**
	 * Metodo per costruire l'oggetto persona dal resulset
	 * @param rs
	 * @return istanza Persona 
	 * @throws SQLException
	 */
	private Persona buildPersona(ResultSet rs) throws SQLException  {
		Persona pers = new Persona(
				rs.getString("NOME"),
				rs.getString("COGNOME"),
				rs.getString("SESSO"),
				rs.getString("LUOGO_NASCITA"),
				(rs.getString("DATA_NASCITA").equals("") ? null : LocalDate.parse(rs.getString("DATA_NASCITA"))),
				(rs.getString("DATA_MORTE").equals("") ? null : LocalDate.parse(rs.getString("DATA_MORTE"))), 
				rs.getBytes("FOTO")
				);
		pers.setPersonaID(rs.getInt("PERSONA_ID"));
		return pers;
	}
	
	/**
	 * Metodo per l'insert a db di una persona.
	 * @param pers
	 * @return {@code int} = ID del' record inserito
	 */
	public int insert(Persona pers) {

		/*
		 * check se esiste gia'
		 */
		PersoneDAO perdao = new PersoneDAO();
		Persona tempPers = new Persona();
		tempPers = perdao.selectByPerson(pers);
		
		if (pers.equals(tempPers)) {
			Main.logga(pers.getNome() + " e' gia' presente sul DB");
			return tempPers.getPersonaID();
		}
		//******
		
		Connection conn = DBConnect.getConnection() ;
		
		//System.out.println("chiedo una connessione" + conn);
		
		StringBuilder insertTabPers = new StringBuilder();	
		insertTabPers.append("INSERT INTO PERSONE "	);
		insertTabPers.append("(NOME, COGNOME, SESSO, LUOGO_NASCITA, DATA_NASCITA, DATA_MORTE, FOTO) ");
		insertTabPers.append("VALUES (? ,? ,? ,? ,? , ?, ?)");
		
		try {
			PreparedStatement st = conn.prepareStatement(insertTabPers.toString(),Statement.RETURN_GENERATED_KEYS) ;
			st.setString(1, pers.getNome());
			st.setString(2, pers.getCognome());
			st.setString(3, pers.getSesso());
			st.setString(4, (pers.getLuogoNascita() != null ? pers.getLuogoNascita() : ""));
			st.setString(5, (pers.getDataNascita() != null ? pers.getDataNascita().toString() : ""));
			st.setString(6, (pers.getDataMorte() != null ? pers.getDataMorte().toString() : ""));
			st.setBytes(7, (pers.getFoto() != null ? pers.getFoto() : null));
					
			st.executeUpdate();
			
			ResultSet rs = st.getGeneratedKeys();
			int generatedKey = 0;
			if (rs.next()) {
			    generatedKey = rs.getInt(1);
			}

			Main.logga("inserito " + pers.getNome() + " " + pers.getCognome());

			return generatedKey;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}
		
		return 0;
	}

	/**
	 * Metodo di Select tutti i record presenti
	 * @return lista di persone.
	 */
	public List<Persona> selectAll() {
		
		List<Persona> tempList = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection() ;
		
		String sql = "SELECT * FROM PERSONE " ;
				
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				tempList.add( buildPersona(rs)) ;
			}
			
			st.close() ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}
		
		return tempList;
	}	

	/**
	 * Metodo di Select per Persona
	 * @return  persona.
	 */
	public Persona selectByPerson(Persona pers) {
		
		Main.logga(pers.toString());
		
		Persona tempPersona = null;
		Connection conn = DBConnect.getConnection() ;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM PERSONE WHERE "	);
		sql.append("NOME = ? AND "					);
		sql.append("COGNOME = ? AND "				);
		sql.append("SESSO = ? AND "					);
		sql.append("LUOGO_NASCITA = ? AND "			);
		sql.append("DATA_NASCITA = ? AND "			);
		sql.append("DATA_MORTE = ? AND "			);
		sql.append("FOTO = ? "						);
				
		try {
			PreparedStatement st = conn.prepareStatement(sql.toString()) ;
			st.setString(1, pers.getNome());
			st.setString(2, pers.getCognome());
			st.setString(3, pers.getSesso());
			st.setString(4, pers.getLuogoNascita());
			st.setObject(5, (pers.getDataNascita() != null ? pers.getDataNascita() : ""));
			st.setObject(6, (pers.getDataMorte() != null ? pers.getDataMorte() : ""));
			st.setBytes(7, (pers.getFoto() != null ? pers.getFoto() : null));
			
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				tempPersona = buildPersona(rs);
			}
			
			st.close() ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}
		
		return tempPersona;
	}
	
	/**
	 * Metodo di Select per ID
	 * @return  persona.
	 */
	public Persona selectByID(int pers_id) {
		
		Persona tempPersona = null;
		Connection conn = DBConnect.getConnection() ;
		
		String sql = "SELECT * FROM PERSONE WHERE PERSONA_ID = ?";
				
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, pers_id);
			
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				tempPersona = buildPersona(rs);
			}
			
			st.close() ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}
		
		return tempPersona;
	}
	
	/**
	 * Metodo di Select per nome
	 * @return lista di persone.
	 */
	public List<Persona> selectByNome(String nome) {
		
		List<Persona> tempList = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection() ;
		
		String sql = "SELECT * FROM PERSONE WHERE NOME = ?";
				
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setString(1, nome);
			
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				tempList.add( buildPersona(rs)) ;
			}
			
			st.close() ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}
		
		return tempList;
	}	
	
	/**
	 * Metodo di Select tutti i record presenti
	 * @return lista di persone.
	 */
	public List<Persona> selectByCognome(String cognome) {
		
		List<Persona> tempList = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection() ;
		
		String sql = "SELECT * FROM PERSONE WHERE COGNOME = ?";
				
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setString(1, cognome);
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				tempList.add( buildPersona(rs)) ;
			}
			
			st.close() ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}
		
		return tempList;
	}	
	
	/**
	 * Metodo di Select tutti i record presenti
	 * @return lista di persone.
	 */
	public List<Persona> selectByNomeCognome(String nome, String cognome) {
		
		List<Persona> tempList = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection() ;
		
		String sql = "SELECT * FROM PERSONE WHERE NOME = ? AND COGNOME = ?";
				
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setString(1, nome);
			st.setString(2, cognome);
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				tempList.add( buildPersona(rs)) ;
			}
			
			st.close() ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}
		
		return tempList;
	}	
	
	/**
	 * Metodo di update
	 * @param pers
	 * @return {@code int} = 1 se inserito correttamente, altrimenti 0
	 */
	public int update(Persona pers) {
		
		Connection conn = DBConnect.getConnection() ;
		
		StringBuilder updateTabPers = new StringBuilder();
		
		updateTabPers.append("UPDATE PERSONE SET "	);
		updateTabPers.append("NOME = ?"				);
		updateTabPers.append("COGNOME = ?"			);
		updateTabPers.append("SESSO = ?"			);
		updateTabPers.append("LUOGO_NASCITA = ?"	);
		updateTabPers.append("DATA_NASCITA = ?"		);
		updateTabPers.append("DATA_MORTE = ?"		);
		updateTabPers.append("FOTO = ?"				);
		updateTabPers.append("WHERE PERSONA_ID = ?"	);
		
		try {
			PreparedStatement st = conn.prepareStatement(updateTabPers.toString()) ;
			st.setString(1, pers.getNome());
			st.setString(2, pers.getCognome());
			st.setString(3, pers.getSesso());
			st.setString(4, pers.getLuogoNascita());
			st.setObject(5, (pers.getDataNascita() != null ? pers.getDataNascita() : ""));
			st.setObject(6, (pers.getDataMorte() != null ? pers.getDataMorte() : ""));
			st.setBytes(7, (pers.getFoto() != null ? pers.getFoto() : null));
			
			int res =  st.executeUpdate();
			return res;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}
		
		return 0;
	}
	
	/**
	 * 
	 * Metodo di delete
	 * @param pers_id
	 * @return {@code int} = 1 se cancellato correttamente, altrimenti 0
	 */
	public int delete(int pers_id) {
		
		Connection conn = DBConnect.getConnection() ;
		
		String sql = "DELETE * FROM PERSONE WHERE PERSONA_ID = ?" ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, pers_id);
			
			int res =  st.executeUpdate();
			
			return res;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}		
		return 0;
		
	}
	
	
	// test
	public static void main(String [] args) throws SQLException {
		
		PersoneDAO perdao = new PersoneDAO();
		Persona pers1 = new Persona("vincenzo", "fogliaro","M","vibo Valentia",LocalDate.of(1975,5,1),null, null);
	/*	Persona pers2 = new Persona("valerio", "fogliaro","M","vibo Valentia",LocalDate.of(1978,6,17),null);
		Persona papa = new Persona("nicola", "fogliaro","M","mileto",LocalDate.of(1947,6,19),null);
		Persona mamma = new Persona("Maria Luisa", "Ierullo","F","vallelonga",LocalDate.of(1949,2,28),null);
		*/
		perdao.insert(pers1);
		//perdao.insert(pers2);	
		//perdao.insert(papa);
		//perdao.insert(mamma);
		
		Main.logga(perdao.selectAll().toString());

	}

}
