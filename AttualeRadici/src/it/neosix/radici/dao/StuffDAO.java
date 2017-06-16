/**
 * 
 */
package it.neosix.radici.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import it.neosix.radici.app.Main;
import it.neosix.radici.model.Stuff;

/**
 * @author NeosiX
 *
 */
public class StuffDAO {
	
	/**
	 * Metodo di Select tutti i record presenti
	 * @return lista di persone.
	 */
	public Stuff select() {
		
		Stuff tempStuff = new Stuff();
		
		Connection conn = DBConnect.getConnection() ;
		
		String sql = "SELECT * FROM STUFF " ;
				
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			ResultSet rs = st.executeQuery() ;
			
			while(rs.next()) {
				tempStuff = (new Stuff(
						rs.getString("NOME_PROJ"), 
						rs.getString("AUTORE"), 
						LocalDate.parse(rs.getString("DATA_CREAZIONE_PROJ")), 
						LocalDate.parse(rs.getString("LAST_MODIFY")))
							);
			}
			
			st.close() ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}
		
		return tempStuff;
	}	

	/**
	 * Metodo per l'insert a db di una configurazione.
	 * @param stuff
	 * 
	 */
	public void insert(Stuff stuff) {

		Connection conn = DBConnect.getConnection() ;
		
		StringBuilder insertTabStuff = new StringBuilder();	
		insertTabStuff.append("INSERT INTO STUFF "	);
		insertTabStuff.append("(NOME_PROJ, AUTORE, DATA_CREAZIONE_PROJ, LAST_MODIFY) ");
		insertTabStuff.append("VALUES (? ,? ,? ,? )");
		
		try {
			PreparedStatement st = conn.prepareStatement(insertTabStuff.toString()) ;
			st.setString(1, stuff.getNome());
			st.setString(2, stuff.getAutore());
			st.setString(3, stuff.getDataCreaz().toString() );
			st.setString(4, stuff.getLast_modify().toString() );
			
			st.executeUpdate();

			Main.logga("inserito " + stuff.toString());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}

	}
	
	/**
	 * Metodo di update
	 * @param stuff
	 * 
	 */
	public void update(Stuff stuff) {
		
		Connection conn = DBConnect.getConnection() ;
		
		StringBuilder updateTabPers = new StringBuilder();
		
		updateTabPers.append("UPDATE STUFF SET "	);
		updateTabPers.append("NOME_PROJ = ?"				);
		updateTabPers.append("AUTORE = ?"			);
		updateTabPers.append("DATA_CREAZIONE_PROJ = ?"	);
		updateTabPers.append("LAST_MODIFY = ?"		);

		try {
			PreparedStatement st = conn.prepareStatement(updateTabPers.toString()) ;
			st.setString(1, stuff.getNome());
			st.setString(2, stuff.getAutore());
			st.setString(3, stuff.getDataCreaz().toString() );
			st.setString(4, stuff.getLast_modify().toString() );
			
			st.executeUpdate();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}

	}
	
	/**
	 * 
	 * Metodo di delete
	 * @param pers_id
	 * @return {@code int} = 1 se cancellato correttamente, altrimenti 0
	 */
	public void delete(int pers_id) {
		
		Connection conn = DBConnect.getConnection() ;
		
		String sql = "DELETE * FROM STUFF" ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.closeConnection();
		}		
		
	}
	
}
