package browser;

import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.*;

public class test2 extends Application {
	@Override
	public void start(Stage primaryStage) {
		TextField addressBar = new TextField();
		addressBar.setText("https://www.google.com");
		Button goButton = new Button("Go!");
		Label stateLabel = new Label();
		
		stateLabel.setTextFill(Color.RED);
		ProgressBar progressBar = new ProgressBar();
		
		final WebView browser = new WebView();
		final WebEngine webEngine = browser.getEngine();
		//webEngine.setJavaScriptEnabled(false);
	    webEngine.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
		//webEngine.setUserAgent("Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19");
		//webEngine.setUserAgent("Mozilla/5.0 (Linux; Android 6.0.1; RedMi Note 5 Build/RB3N5C; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/68.0.3440.91 Mobile Safari/537.36");
		
		// A Worker load the page
		Worker<Void> worker = webEngine.getLoadWorker();
		
	    // Listening to the status of the worker
		worker.stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue<? extends State>observable, State oldValue, State newValue) {
				stateLabel.setText("Loading state: " + newValue.toString());
				if (newValue == Worker.State.SUCCEEDED) {
					primaryStage.setTitle(webEngine.getLocation());
					stateLabel.setText("Finish!");
				}
			}
		});
		
		// Bind the progress property of Progressbar 
		// with progress property of Worker
		progressBar.progressProperty().bind(worker.progressProperty());
		
		goButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				String url = addressBar.getText();
				//Load the page.
				webEngine.load(url);
			}
		});
		
		VBox root = new VBox();
		root.getChildren().addAll(addressBar, goButton, stateLabel, progressBar, browser);
		
		Scene scene = new Scene(root, 1200, 800);
		
		primaryStage.setTitle("JavaFX WebView Browser");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
    public static void main(String[] args) {
    	launch(args);
    }
}
