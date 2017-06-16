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

package Gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainGui extends Application {

		private TextField username2;
		private GridPane grid;
		private boolean messageView = false;
		private String userName = "";
		private boolean debug = true;
		private ScrollPane sp;

		ArrayList<Message> messages = new ArrayList<>();

		@Override
		public void start(Stage primaryStage) {
			primaryStage.setTitle("Client");

			primaryStage.show();

			StartLayout sl = new StartLayout();
			primaryStage.setScene(sl.get());

		}

		private void sendMessage(KeyEvent e){
			if ((int) (e.getCharacter().charAt(0)) == NEW_LINE){ // New line

				debugPrint("Send Gui.Message: " + username2.getText());

				if (validMessage(username.getText())){

					messages.add(new Message(userName, username2.getText()));
					username2.setText("");
					displayMessages();
				}
			}
		}

		private void debugPrint(String s) {
			if (debug)
				System.out.println(s);
		}

		private boolean validMessage(String str) {
			return true; // TODO: 12/06/2017 improve this
		}

		void setMessageLayout(){
			debugPrint("Setting Gui.Message layout");

			messageView = true;

			stage.setTitle("Client - " + userName);

			GridPane grid = Singleton.getDefaultGridPane();
			grid = updateMessageDisplay(grid);

			username2 = new TextField();
			grid.add(username2, 1, 10);
			username2.addEventFilter(KeyEvent.KEY_TYPED, this::sendMessage);

			Scene scene = new Scene(grid, 300, 275);
			stage.setScene(scene);
			this.grid = grid;
		}

		private void displayMessages(){
			String t = "";

			for (Message m: messages){
				t += m.toString() + "\n";
			}

			debugPrint(t + "" + messages.size());

			Text scenetitle = new Text(t);
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
			sp.setContent(scenetitle);

		}

		GridPane updateMessageDisplay(GridPane grid) {
			sp = new ScrollPane();
			sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
			sp.setHmin(200);

			String t = "";

			for (Message mes: messages){
				t += mes + "\n";
			}

			Text scenetitle = new Text(t);
			scenetitle.setFont(Singleton.getDefaultFont());
			sp.setContent(scenetitle);
			grid.add(sp, 0, 0, 2, 7);
			return grid;
		}

}
