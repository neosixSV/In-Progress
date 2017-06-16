/**
 * 
 */
package it.neosix.radici.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.neosix.radici.app.Main;

/**
 * @author NeosiX
 *
 */
public class SetupDAO {
	
	public static void setup(){
		
		SetupDAO setdao = new SetupDAO();
		for (String string : setdao.SetupTab()) {
			setdao.crea(string);
		}

	}
	
	private void crea(String str) {
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(str) ;
			st.executeUpdate();
			DBConnect.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Main.logga("Create tabelle sul db: " + str);
		
	}

	/**
	 * Script per inizializzare il db
	 * @return 
	 */
	private List<String> SetupTab() {
		
		List<String> creates = new ArrayList<>();
		
		StringBuilder createTabPers = new StringBuilder();
		createTabPers.append("CREATE TABLE PERSONE ("							);
		createTabPers.append("PERSONA_ID INTEGER PRIMARY KEY AUTOINCREMENT, "	);
		createTabPers.append("NOME TEXT	NOT NULL, "								);
		createTabPers.append("COGNOME TEXT NOT NULL, "							);
		createTabPers.append("SESSO CHAR(1) NOT NULL, "							);
		createTabPers.append("LUOGO_NASCITA	TEXT, "								);
		createTabPers.append("DATA_NASCITA TEXT, "								);
		createTabPers.append("DATA_MORTE TEXT DEFAULT 0, "						);
		createTabPers.append("FOTO BLOB);"										);
		creates.add(createTabPers.toString());
		
		StringBuilder createTabGen = new StringBuilder();
		createTabGen.append("CREATE TABLE GENITORIFIGLI ("						);
		createTabGen.append("GENITORE_ID INTEGER NOT NULL,"						);
		createTabGen.append("FIGLIO_ID INTEGER NOT NULL);"						);
		creates.add(createTabGen.toString());
		
		StringBuilder createTabProperties = new StringBuilder();
		createTabProperties.append("CREATE TABLE STUFF ("						);
		createTabProperties.append("NOME_PROJ TEXT, "							);
		createTabProperties.append("AUTORE TEXT, "								);
		createTabProperties.append("DATA_CREAZIONE_PROJ TEXT, "					);
		createTabProperties.append("LAST_MODIFY TEXT ); "						);

		creates.add(createTabProperties.toString());
		
		return creates;

	}
	
	
	public static void main(String [] args) {
		
		SetupDAO.setup();
	
	}	

}
