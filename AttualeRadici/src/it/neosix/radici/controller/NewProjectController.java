/**
 * 
 */
package it.neosix.radici.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import it.neosix.radici.app.Main;
import it.neosix.radici.dao.DBConnect;
import it.neosix.radici.dao.SetupDAO;
import it.neosix.radici.dao.StuffDAO;
import it.neosix.radici.model.Stuff;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


/**
 * @author NeosiX
 *
 */
public class NewProjectController {

	private Stage dialogStage;
    private boolean okClicked = false;
    private boolean cancelClicked = false;
    //private String path;
	private File file;


    @FXML
    private Text info;

    @FXML
    private TextField nometext;

    @FXML
    private TextField autoretext;

    @FXML
    private TextField txtPath;
    
    @FXML
    private Button btnBrowse;

    @FXML
    private Button btnCanc;

    @FXML
    private Button btnOK;

    @FXML
    void handleBrowse() {
    	DirectoryChooser directoryChooser = new DirectoryChooser(); 
    	directoryChooser.setTitle("Seleziona la cartella");
    	File path = directoryChooser.showDialog(new Stage());
    	if (path != null)
    		txtPath.setText(path.getPath());
    	file = new File(path.getPath(), nometext.getText() + ".db");
    }
    
    @FXML
    void handleCanc() {
    	dialogStage.close();
    	cancelClicked = true;
    }

    @FXML
    void handleOK() throws IOException {
    	if (checkInput()) {
    		okClicked = true;
    		if (file.createNewFile()){
    			DBConnect.setDB(file.toString());
    			SetupDAO.setup();
    			
    			StuffDAO stuffDAO = new StuffDAO();
    			stuffDAO.insert(new Stuff(nometext.getText(), autoretext.getText(), LocalDate.now(), LocalDate.now()));
    			
    			Main.logga(file.toString() + " File creato");
    		} else {
    			Main.logga(file.toString() + " File NON creato");
			}
    		//MainController.setDb(file.toString());
        	//mainApp.getPrimaryStage().setTitle("Radici - " + Global.getInstance().getkey("VERSION") + " - " + file.toString());
    		dialogStage.close();
		}
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        // Set the dialog icon.
        // this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }   
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    public boolean isCancelClicked() {
        return cancelClicked;
    }
    
    private boolean checkInput() {
    	
    	String errorMessage = "";
    	    	
    	if (nometext.getText() == null || nometext.getText().length() == 0) {
    		errorMessage += "Devi inserire il Nome\n";
    		nometext.requestFocus();
		} 
    	if (txtPath.getText() == null || txtPath.getText().length() == 0) {
    		errorMessage += "Devi inserire il Path\n";
    		txtPath.requestFocus();
		} else {
			if (file.getPath() == null) {
	    		errorMessage += "Il Path deve esistere\n";
	    		txtPath.requestFocus();
			}
		}

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Correggli gli input");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        	info.setText(errorMessage);
        	return false;
        }

	}

	public String getFile() {
		return file.getName();
	}

}
