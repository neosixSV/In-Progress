/**
 * 
 */
package it.neosix.radici.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.neosix.radici.app.Main;
import it.neosix.radici.model.GenitoriFigli;
import it.neosix.radici.model.Persona;

/**
 * @author NeosiX
 *
 */
public class GenitoriFigliDAO {

	/**
	 * Metodo per costruire l'oggetto persona dal resulset
	 * @param rs
	 * @return istanza Persona 
	 * @throws SQLException
	 */
	private GenitoriFigli buildGenitoriFigli(ResultSet rs) throws SQLException  {
		
		PersoneDAO tempDAO = new PersoneDAO();
		
		Persona figlio;
		Persona padre;
		Persona madre;
		
		figlio = tempDAO.selectByID(rs.getInt("FIGLIO_ID"));
		padre = tempDAO.selectByID(rs.getInt("PADRE_ID"));
		madre = tempDAO.selectByID(rs.getInt("MADRE_ID"));
		
		GenitoriFigli genitoriFigli = new GenitoriFigli(figlio, padre, madre);
		
		return genitoriFigli;
	}
	
	/**
	 * Metodo per l'insert a db di una persona.
	 * @param pers
	 * @return {@code int} = 0 se inserito correttamente, altrimenti 1
	 */
	public int insert(GenitoriFigli genitoriFigli) {
		
		/*
		 * check se esiste gia'
		 */
		GenitoriFigliDAO gendao = new GenitoriFigliDAO();
		
		if (genitoriFigli.getFiglio().equals(gendao.selectByFiglioID(genitoriFigli.getFiglio().getPersonaID()))
			) {
			Main.logga("il figlio " + genitoriFigli.getFiglio().getNome() + " e' gia' presente sul DB");
			return 0;
		}
		//******
		
		Connection conn = DBConnect.getConnection() ;
		
		StringBuilder insertTabGen = new StringBuilder();
		insertTabGen.append("INSERT INTO GENITORIFIGLI "	);
		insertTabGen.append("(GENITORE_ID, FIGLIO_ID) "		);
		insertTabGen.append("VALUES (? ,?)"					);
		
		try {
			PreparedStatement st = conn.prepareStatement(insertTabGen.toString()) ;
			
			Main.logga(String.valueOf(genitoriFigli.getPadre().getPersonaID()));
			
			st.setString(1, String.valueOf(genitoriFigli.getPadre().getPersonaID()));
			st.setString(2, String.valueOf(genitoriFigli.getFiglio().getPersonaID()));
			int res =  st.executeUpdate();
			
			st.setString(1, String.valueOf(genitoriFigli.getMadre().getPersonaID()));
			st.setString(2, String.valueOf(genitoriFigli.getFiglio().getPersonaID()));
			res +=  st.executeUpdate();
			
			StringBuilder spool = new StringBuilder();
			spool.append("inserito :");
			spool.append(genitoriFigli.getFiglio().getNome());
			spool.append("\nsuo padre: ");
			spool.append(genitoriFigli.getPadre().getNome());
			spool.append("\nsua madre: ");
			spool.append(genitoriFigli.getMadre().getNome());		

			Main.logga(spool.toString());
			return res;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}
		
		return 1;
	}

	/**
	 * Metodo di Select tutti i record presenti
	 * @return lista di persone.
	 */
	public List<GenitoriFigli> selectAll() {
		
		List<GenitoriFigli> tempList = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection() ;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT GP.GENITORE_ID AS Padre, GM.GENITORE_ID AS Madre, GP.FIGLIO_ID AS Padre ");
		sql.append("FROM GENITORIFIGLI GP, GENITORIFIGLI GM, PERSONE PP, PERSONE PM "				);
		sql.append("WHERE GP.FIGLIO_ID = GM.FIGLIO_ID "												);
		sql.append("AND GP.GENITORE_ID = PP.PERSONA_ID AND PP.SESSO = 'M' "							);
		sql.append("AND GM.GENITORE_ID = PP.PERSONA_ID AND PP.SESSO = 'F' "							);
				
		try {
			PreparedStatement st = conn.prepareStatement(sql.toString()) ;
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				tempList.add( buildGenitoriFigli(rs)) ;
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
	 * Metodo di Select per nome
	 * @return lista di persone.
	 */
	public Persona selectByFiglioID(int figlio_id) {
		
		PersoneDAO tempDAO = new PersoneDAO();
		Persona tempPers = new Persona();
		
		Connection conn = DBConnect.getConnection() ;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT FIGLIO_ID "	);
		sql.append("FROM GENITORIFIGLI G ");
		sql.append("WHERE G.FIGLIO_ID = ?"																);
		
		try {
			PreparedStatement st = conn.prepareStatement(sql.toString()) ;
			st.setInt(1, figlio_id);
			
			ResultSet rs = st.executeQuery() ;
			
			if(rs.next()) {
				tempPers = tempDAO.selectByID(rs.getInt("FIGLIO_ID"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}
		
		return tempPers;
	}	
	
	/**
	 * Metodo di Select per nome
	 * @return lista di persone.
	 */
	public List<GenitoriFigli> selectByNomeFiglio(String nome) {
		
		List<GenitoriFigli> tempList = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection() ;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT GP.GENITORE_ID AS Padre, GM.GENITORE_ID AS Madre, GP.FIGLIO_ID AS Padre ");
		sql.append("FROM GENITORIFIGLI GP, GENITORIFIGLI GM, PERSONE PP, PERSONE PM "				);
		sql.append("WHERE GP.FIGLIO_ID = GM.FIGLIO_ID "												);
		sql.append("AND GP.GENITORE_ID = PP.PERSONA_ID AND PP.SESSO = 'M' "							);
		sql.append("AND GM.GENITORE_ID = PP.PERSONA_ID AND PP.SESSO = 'F' "							);
		sql.append("AND PP.NOME = ?"																);
		
		
		try {
			PreparedStatement st = conn.prepareStatement(sql.toString()) ;
			st.setString(1, nome);
			
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				tempList.add( buildGenitoriFigli(rs)) ;
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
	public List<GenitoriFigli> selectByCognome(String cognome) {
		
		List<GenitoriFigli> tempList = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection() ;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT GP.GENITORE_ID AS Padre, GM.GENITORE_ID AS Madre, GP.FIGLIO_ID AS Padre ");
		sql.append("FROM GENITORIFIGLI GP, GENITORIFIGLI GM, PERSONE PP, PERSONE PM "				);
		sql.append("WHERE GP.FIGLIO_ID = GM.FIGLIO_ID "												);
		sql.append("AND GP.GENITORE_ID = PP.PERSONA_ID AND PP.SESSO = 'M' "							);
		sql.append("AND GM.GENITORE_ID = PP.PERSONA_ID AND PP.SESSO = 'F' "							);
		sql.append("AND PP.COGNOME = ?"																);
				
		try {
			PreparedStatement st = conn.prepareStatement(sql.toString()) ;
			st.setString(1, cognome);
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				tempList.add( buildGenitoriFigli(rs)) ;
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
	public List<GenitoriFigli> selectByNomeCognome(String nome, String cognome) {
		
		List<GenitoriFigli> tempList = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection() ;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT GP.GENITORE_ID AS Padre, GM.GENITORE_ID AS Madre, GP.FIGLIO_ID AS Padre ");
		sql.append("FROM GENITORIFIGLI GP, GENITORIFIGLI GM, PERSONE PP, PERSONE PM "				);
		sql.append("WHERE GP.FIGLIO_ID = GM.FIGLIO_ID "												);
		sql.append("AND GP.GENITORE_ID = PP.PERSONA_ID AND PP.SESSO = 'M' "							);
		sql.append("AND GM.GENITORE_ID = PP.PERSONA_ID AND PP.SESSO = 'F' "							);
		sql.append("AND PP.NOME = ? and PP.COGNOME = ?"												);
				
		try {
			PreparedStatement st = conn.prepareStatement(sql.toString()) ;
			st.setString(1, nome);
			st.setString(2, cognome);
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				tempList.add( buildGenitoriFigli(rs)) ;
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
	public int update(GenitoriFigli genitoriFigli) {
		
		Connection conn = DBConnect.getConnection() ;
		
		StringBuilder updateTabGen = new StringBuilder();
		
		updateTabGen.append("UPDATE GENITORIFIGLI SET "	);
		updateTabGen.append("GENITORE_ID = ?" 			);
		updateTabGen.append("FIGLIO_ID = ?"	  			);
		
		try {
			PreparedStatement st = conn.prepareStatement(updateTabGen.toString()) ;
			
			st.setString(1, String.valueOf(genitoriFigli.getPadre().getPersonaID()));
			st.setString(1, String.valueOf(genitoriFigli.getFiglio().getPersonaID()));
			int res =  st.executeUpdate();
			
			st.setString(1, String.valueOf(genitoriFigli.getMadre().getPersonaID()));
			st.setString(1, String.valueOf(genitoriFigli.getFiglio().getPersonaID()));
			res +=  st.executeUpdate();
			
			DBConnect.getConnection().close();
			
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
	 * Metodo di update
	 * @param pers_id
	 * @return {@code int} = 1 se cancellato correttamente, altrimenti 0
	 */
	public int delete(int genfigli_id) {
		
		Connection conn = DBConnect.getConnection() ;
		
		String sql = "DELETE * FROM GENITORIFIGLI WHERE GENITORE_ID = ?" ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, genfigli_id);
			
			int res =  st.executeUpdate();
			DBConnect.getConnection().close();
			
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
		Persona pers2 = new Persona("valerio", "fogliaro","M","vibo Valentia",LocalDate.of(1978,6,17),null, null);
		Persona papa = new Persona("Nicola", "Fogliaro","M","mileto",LocalDate.of(1947,6,19),null, null);
		Persona mamma = new Persona("Maria Luisa", "Ierullo","F","vallelonga",LocalDate.of(1949,2,28),null, null);
		
		pers1.setPersonaID(perdao.insert(pers1));
		pers2.setPersonaID(perdao.insert(pers2));	
		papa.setPersonaID(perdao.insert(papa));
		mamma.setPersonaID(perdao.insert(mamma));
		
		GenitoriFigliDAO gendao = new GenitoriFigliDAO();
		GenitoriFigli gen1 = new GenitoriFigli(pers1, papa, mamma);
		GenitoriFigli gen2 = new GenitoriFigli(pers2, papa, mamma);
		
		gendao.insert(gen1);
		gendao.insert(gen2);
		
		Main.logga(perdao.selectAll().toString());
		
	}
}
