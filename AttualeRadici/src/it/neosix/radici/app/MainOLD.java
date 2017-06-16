/*package it.neosix.radici.app;

	
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import radiciApp.Main;
import radiciController.MainController;
import radiciModel.Global;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class MainOLD extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
			BorderPane root = (BorderPane)loader.load();
					
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			initRootLayout();
			
			//qui ci va :
			MainController controller = loader.getController() ;
			
			RubricaModel model = new RubricaModel() ;
			controller.setModel(model) ;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
    *//**
     * Layout iniziale
     *//*
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
			loader.setLocation(MainOLD.class.getResource("Main.fxml"));
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
			primaryStage.setScene(scene);
			//primaryStage.setMaximized(true);
					
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
*/