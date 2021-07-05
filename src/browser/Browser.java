package browser;
	
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Browser extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Button buttonURL = new Button("Load Page https://www.duckduckgo.com");
			Button buttonHtmlString = new Button("Load HTML string");
			Button buttonHtmlFile = new Button("Load test.html");
			
			final WebView browser = new WebView();
			final WebEngine webEngine = browser.getEngine();
			
			buttonURL.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					String url = "https://www.duckduckgo.com";
					//Load a page from remote url.
					webEngine.load(url);
					System.out.println("loading");
				}
			});
			
			buttonHtmlString.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					String html = "<html><body><h1>Hello</h1><h2>Hello</h2></body></html>";
					//Load HTML String
					webEngine.loadContent(html);
				}
			});
			
			buttonHtmlFile.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					try {
					    File file = new File("src/browser/test.html");
					    URL url = file.toURI().toURL();
					    System.out.println("Local URL: " + url.toString());
					    webEngine.load(url.toString());
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
			});
			
			VBox root = new VBox();
			root.setPadding(new Insets(5));
			root.setSpacing(5);
			root.getChildren().addAll(buttonURL, buttonHtmlString, buttonHtmlFile, browser);
			
			Scene scene = new Scene(root, 1200, 800);
			
			primaryStage.setTitle("JavaFX WebView Browser");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
