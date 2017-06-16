package it.neosix.radici.app;
	
import java.io.IOException;

import it.neosix.radici.controller.AnagraficaController;
import it.neosix.radici.controller.ListaAnagraficheController;
import it.neosix.radici.controller.MainController;
import it.neosix.radici.controller.NewProjectController;
import it.neosix.radici.model.Persona;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

/**
 * @author NeosiX
 *
 */
public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private static Boolean log = true;
	
	/**
     * The data as an observable list of Persons.
     */
    private ObservableList<Persona> personData = FXCollections.observableArrayList();
		
	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		
		this.primaryStage.setTitle("Radici - " + Global.getInstance().getkey("VERSION"));
		String icon = "/resource/" + Global.getInstance().getkey("ICON");
		this.primaryStage.getIcons().add(new Image(icon));
						
		initRootLayout();
	
	}

    /**
     * Layout iniziale
     */
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Main.fxml"));
			rootLayout = (BorderPane) loader.load();

			MainController controller = loader.getController();
			controller.setMainApp(this);

			Scene scene = new Scene(rootLayout,
					Screen.getPrimary().getVisualBounds().getWidth() * 0.8,
					Screen.getPrimary().getVisualBounds().getHeight() * 0.8 );
//			Integer.parseInt(Global.getInstance().getkey("WIDTH")),
//			Integer.parseInt(Global.getInstance().getkey("LENGHT")));
			scene.getStylesheets().add(getClass().getResource(
					Global.getInstance().getkey("CSS")).toExternalForm());
			
//			primaryStage.setMaximized(true);
			
			primaryStage.setTitle("Radici - " + Global.getInstance().getkey("VERSION"));
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
     * New Project
     * return 0 if OK, 1 if cancel, -1 if not expected
     */
    public int showNewProject() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("NewProject.fxml"));
            BorderPane page = (BorderPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Project");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NewProjectController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            
            dialogStage.showAndWait();
            
            if (controller.isOkClicked()) {
            	//DBConnect.setDB(controller.getFile());
            	this.primaryStage.setTitle("Radici - " + Global.getInstance().getkey("VERSION") + " - " + controller.getFile());
            	return 0; //ok
			}
            
            if (controller.isCancelClicked()) {
				return 1;
			}

        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
	
   
    /**
     * con questo metodo innesto un nuovo pane a destra del rootlayout.
     */
    public void showAnagrafiche() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Anagrafica.fxml"));
            
/*            AnagraficaController controller = loader.getController();
            controller.setMainApp(this);*/
            Pane newLoadedPane =  loader.load();     
            rootLayout.setRight(newLoadedPane);
            
            //////////////// ////////////////
            
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(Main.class.getResource("ListaAnagrafiche.fxml"));
            /*ListaAnagraficheController controllerLista = loader.getController();
            controllerLista.setMainApp(this);*/
            Pane newLoadedPane2 =  loader2.load();     
            rootLayout.setLeft(newLoadedPane2);
            
            
 /*           BorderPane page = (BorderPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Anagrafica");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();*/
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void logga(String str) {
		if (log){ 
			System.out.println(str);
		}	
	}
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
	public static void main(String[] args) {
		launch(args);
	}
}
