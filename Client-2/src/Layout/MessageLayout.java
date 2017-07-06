package Layout;/*
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

import External.Singleton;
import Message.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class MessageLayout implements Layout {

	private TextField messageField;
	private String userName = "";
	private ScrollPane sp = new ScrollPane();
	private Text person = new Text("Another person");

	private ArrayList<Message> messages = new ArrayList<>();

	MessageLayout() {
		Singleton.deny();
	}

	MessageLayout(String un) {
		this.userName = un;
	}

	public Scene get(){
		Singleton.debugPrint("Setting MessageLayout");

		GridPane grid = Singleton.getDefaultGridPane(5);
		grid = updateMessageDisplay(grid);
		grid = getLeftSide(grid);

		grid.add(person, 14, 0);
		messageField = new TextField();
		grid.add(messageField, 7, 43, 15, 4);
		messageField.addEventFilter(KeyEvent.KEY_TYPED, this::sendMessage);



		return new Scene(grid, 600, 289);
	}

	private void sendMessage(KeyEvent e){
		if ((int) (e.getCharacter().charAt(0)) == Singleton.NEW_LINE){ // New line

			Singleton.debugPrint("Send Message.Message: " + messageField.getText());

			if (validMessage(messageField.getText())){

				try {
					MainLayout.getServer().send(messageField.getText());
				} catch (Exception ex){
					Singleton.debugPrint("Couldn't send message", ex);
				}

				this.messages.add(new Message(userName, messageField.getText()));
				messageField.setText("");
				displayMessages();
			}
		}
	}

	private boolean validMessage(String str) {
		if (str.length() < 1){
			return false;
		}
		return true; // TODO: 12/06/2017 improve this
	}

	private void displayMessages(){
		String t = "";

		for (Message m: messages)
			t += m.toString() + "\n";

		Text scenetitle = new Text(t);
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		sp.setContent(scenetitle);

	}

	private GridPane updateMessageDisplay(GridPane grid) {
		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

		String t = "";

		for (Message mes: messages)
			t += mes + "\n";

		Text scenetitle = new Text(t);
		scenetitle.setFont(Singleton.getDefaultFont());
		sp.setContent(scenetitle);
		grid.add(sp, 7, 1, 15, 41);
		Text con = new Text("Connected status");
		con.setFont(Singleton.getDefaultFont(11));
		grid.add(con, 17, 42);
		return grid;
	}

	private GridPane getLeftSide(GridPane grid) {

		Text helpText1 = new Text("Online users");
		ListView<String> list = new ListView<>();
		ObservableList<String> items = FXCollections.observableArrayList (
				"Single", "Double", "Suite", "Family App");
		list.setItems(items);
		list.setPrefWidth(50);
		list.setPrefHeight(280);
		list.setBackground(null);

		grid.add(helpText1, 0, 0);
		grid.add(list, 0, 1, 7, 46);
		return grid;
	}
}
