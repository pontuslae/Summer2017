/*
	* Copyright (c) 2017 Pontus Laestadius
	*
	* Permission is hereby granted, free of charge, to any person obtaining
	* a copy of this software and associated documentation files (the
	* "Software"), to deal in the Software without restriction, including
	* without limitation the rights to use, copy, modify, merge, publish,
	* distribute, sublicense, and/or sell copies of the Software, and to
	* permit persons to whom the Software is furnished to do so, subject to
	* the following conditions:
	*
	* The above copyright notice and this permission notice shall be
	* included in all copies or substantial portions of the Software.
	*
	* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
	* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
	* MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
	* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
	* LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
	* OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
	* WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUIfx extends Application {

	TextField username;
	Stage stage;
	GridPane grid;

	ArrayList messages = new ArrayList();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Client");

		primaryStage.show();

		GridPane grid = getGridPane();

		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 1, 0, 2, 1);

		username = new TextField();
		grid.add(username, 1, 1);
		username.addEventFilter(KeyEvent.KEY_TYPED, this::eventFilter);

		Scene scene = new Scene(grid, 300, 275);
		primaryStage.setScene(scene);
		stage = primaryStage;
		this.grid = grid;

	}

	private void eventFilter(KeyEvent e){
		if ((int) (e.getCharacter().charAt(0)) == 13){ // New line

			// TODO: 12/06/2017 Evaluate if the user name is appropriate and valid.
			// TODO: 12/06/2017 Open up a new scene with the message layout.
			if (validUserName(username.getText()))
				setMessageLayout();
		}

	}
	
	private void sendMessage(KeyEvent e){
		if ((int) (e.getCharacter().charAt(0)) == 13){ // New line

			if (validMessage(username.getText())){
				username.setText("");
				messages.add(username.getText());
				updateMessageDisplay(grid);
			}
		}
	}


	/**
	 * @return if the string is of appropriate length.
	 */
	private boolean validUserName(String str) {
		return (str.length() < 16) && (str.length() > 4);
	}
	
	private boolean validMessage(String str) {
		return true; // TODO: 12/06/2017 improve this 
	}

	void setMessageLayout(){

		GridPane grid = getGridPane();

		grid = updateMessageDisplay(grid);

		username = new TextField();
		grid.add(username, 0, 15);
		username.addEventFilter(KeyEvent.KEY_TYPED, this::sendMessage);

		Scene scene = new Scene(grid, 300, 475);
		stage.setScene(scene);
		this.grid = grid;
	}

	GridPane updateMessageDisplay(GridPane grid) {
		ScrollPane sp = new ScrollPane();
		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		sp.setHmin(200);


		String t = "";

		for (int i = 0; i < messages.size(); i++){
			t = messages.get(i) + "\n";

		}
		Text scenetitle = new Text(t);
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		sp.setContent(scenetitle);
		grid.add(sp, 0, 0, 2, 15);
		return grid;
	}

	public static GridPane getGridPane(){
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		return grid;
	}
}