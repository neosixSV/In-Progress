package it.neosix.radici.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import it.neosix.radici.app.Main;
import it.neosix.radici.dao.PersoneDAO;
import it.neosix.radici.model.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;

public class ListaAnagraficheController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

	@FXML
	private TableView<Persona> personeTable;
	
	@FXML
	private TableColumn<Persona, String> nomeColumn;
	@FXML
	private TableColumn<Persona, String> cognomeColumn;
	@FXML
	private TableColumn<Persona, String> sessoColumn;
	@FXML
	private TableColumn<Persona, String> luogoNascitaColumn;
	@FXML
	private TableColumn<Persona, LocalDate> dataNascitaColumn;
	@FXML
	private TableColumn<Persona, LocalDate> dataMorteColumn;
	@FXML
	private TableColumn<Persona, Image> fotoColumn;
	
    private Main mainApp;
    
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
	//private ObservableList<Persona> personData = FXCollections.observableArrayList();
	
	public ListaAnagraficheController() {
		PersoneDAO perDAO = new PersoneDAO();
		for (Persona persona : perDAO.selectAll()) {
			//personData.add(persona);
			Main.logga(persona.toString());
		}
	}
	
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
		// Initialize the columns.
    	nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
    	cognomeColumn.setCellValueFactory(cellData -> cellData.getValue().cognomeProperty());
    	sessoColumn.setCellValueFactory(cellData -> cellData.getValue().sessoProperty());
    	luogoNascitaColumn.setCellValueFactory(cellData -> cellData.getValue().luogoNascitaProperty());
    	dataNascitaColumn.setCellValueFactory(cellData -> cellData.getValue().dataNascitaProperty());
    	dataMorteColumn.setCellValueFactory(cellData -> cellData.getValue().dataMorteProperty());
		
    	//DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		
		
		// Add data to the table
		//personeTable.setItems(personData);
		
    }
}
