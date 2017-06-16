package it.neosix.radici.controller;

import java.io.File;

import it.neosix.radici.app.Main;
import it.neosix.radici.dao.PersoneDAO;
import it.neosix.radici.model.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AnagraficaController {

    @FXML
    private TextField nome;

    @FXML
    private TextField cognome;

    @FXML
    private ToggleGroup sesso;

    @FXML
    private TextField luogonascita;

    @FXML 
    private DatePicker datanascita;

    @FXML
    private DatePicker datamorte;

    @FXML
    private ImageView imageView;

    @FXML
    private Button caricaFoto;

    @FXML
    private Button pulisci;

    @FXML
    private Button salva;

    @FXML
    private Text info;

    
    private File mainFile;
    
    
    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    void handleOnClean() {
    	nome.setText(null);
    	cognome.setText(null);
    	sesso.selectToggle(null);
    	luogonascita.setText(null);
    	datanascita.setValue(null);
    	datamorte.setValue(null);
    }

    @FXML
    void handleOnCarica(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter(
    			"Image *.BMP, *.GIF, *.JPEG, *.PNG", "*.BMP", "*.GIF", "*.JPEG", "*.PNG");
    	fc.getExtensionFilters().addAll(extentionFilter);
    			
    	fc.setTitle("Seleziona la foto");
    	File fotoFile = fc.showOpenDialog(new Stage());
    	mainFile = fotoFile;
    	
    	if (fotoFile != null){
    		Main.logga(fotoFile.toURI().toString());
    		
            // snapshot the rounded image.
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
    		Image image = new Image(fotoFile.toURI().toString());
            // store the rounded image in the imageView.
            imageView.setImage(image);
    	}
    }
    
    @FXML
    void handleOnSalva() {
    	byte[] fotoFile = null;
    	if (mainFile.length() > 0){
    		fotoFile = new byte[(int) mainFile.length()];
    	}
    		
    	RadioButton selectedRadioButton =
    	        (RadioButton) sesso.getSelectedToggle();
    	Persona pers = new Persona(
    			nome.getText(),
    			cognome.getText(),
    			selectedRadioButton.getText(),
    			luogonascita.getText(),
    			datanascita.getValue(),
    			datamorte.getValue(),
    			(mainFile.length() > 0 ? fotoFile : null)
    			);
    	PersoneDAO persDAO = new PersoneDAO();
    	persDAO.insert(pers);
    	
  	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize() {
        // set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(
            imageView.getFitWidth(), imageView.getFitHeight()
        );
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);

        // remove the rounding clip so that our effect can show through.
        imageView.setClip(null);

        // apply a shadow effect.
        imageView.setEffect(new DropShadow(20, Color.BLACK));
    }
}
