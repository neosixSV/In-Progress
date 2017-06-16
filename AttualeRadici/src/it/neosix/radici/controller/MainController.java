package it.neosix.radici.controller;

import java.io.File;

import it.neosix.radici.app.Global;
import it.neosix.radici.app.Main;
import it.neosix.radici.dao.DBConnect;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

/**
 * @author NeosiX
 *
 */
public class MainController {
	
    @FXML private MenuBar 	menubar;
    @FXML private MenuItem 	anagrafiche;
    @FXML private MenuItem 	export;
    @FXML private MenuItem 	saveAs;
    @FXML private MenuItem 	close;
	@FXML private Label 	footerlabel;
	
    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
    private void disableMenuOnProgetto(Boolean bool) {
    	anagrafiche.setDisable(bool);
    	export.setDisable(!bool); //test
    	saveAs.setDisable(!bool); //test
		close.setDisable(bool);
	}
    
    /**
     * Crea un nuovo progetto
     */
    @FXML
    private void handleNew() {
    	footerlabel.setText("handleNew");
    	if (mainApp.showNewProject() == 0)
    		disableMenuOnProgetto(false);
    }

    /**
     * Apre un progetto
     */
    @FXML
    private void handleOpen() {
    	footerlabel.setText("handleOpen");
    	
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().addAll(
    			new FileChooser.ExtensionFilter("DataBase *.db", "*.db"));
    	fc.setTitle("Select DB file");
    	File dbscelto = fc.showOpenDialog(new Stage());
    	if (dbscelto != null)
    		DBConnect.setDB(dbscelto.toString());
    	else DBConnect.setDB("");
    	
    	mainApp.getPrimaryStage().setTitle("Radici - " + Global.getInstance().getkey("VERSION") + " - " + dbscelto.toString());
    	
    	disableMenuOnProgetto(false);
    }

    /**
     * Salva il progetto
     */
    @FXML
    private void handleExport() {
    	footerlabel.setText("handleSave");
/*        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }*/
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
    	footerlabel.setText("handleSaveAs");
/*        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
        }*/
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAnagrafiche() {
    	mainApp.showAnagrafiche();
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Radici");
    	alert.setHeaderText("I versione Albero Genealogico");
    	alert.setContentText("Author: NeosiX - v0.1");

    	alert.showAndWait();
    }
    
    /**
     * Closes the Progetto.
     */
    @FXML
    private void handleClose() {
    	DBConnect.setDB(null);
    	disableMenuOnProgetto(true);
    	mainApp.initRootLayout();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
