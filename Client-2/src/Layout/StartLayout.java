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

import Connection.IgnoreThisTCP;
import External.Singleton;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static Connection.Server.DataServer.MOCK_STATUS;

public class StartLayout implements Layout {

	private TextField inputField;

	StartLayout(){}

	public Scene get(){

		// If we are using a mock server. Start it.
		if (MOCK_STATUS){
			Thread itt = new IgnoreThisTCP();
			itt.start();
		}

		GridPane grid = Singleton.getDefaultGridPane();

		Text scenetitle = new Text("      Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 4, 4, 2, 1);

		inputField = new TextField();
		inputField.setPrefWidth(160);
		grid.add(inputField, 4, 5);
		inputField.addEventFilter(KeyEvent.KEY_TYPED, this::inputFieldListener);
		Scene start = new Scene(grid, 240, 270);

		return start;
	}

	private void inputFieldListener(KeyEvent e){
		if ((int) (e.getCharacter().charAt(0)) == Singleton.NEW_LINE){ // New line
			if (validUserName(inputField.getText())){
				Singleton.debugTransitionPrint("Layout.StartLayout", "Layout.MessageLayout");

				if (inputField.getText().equals("pontus")) // TODO: 05/07/2017 this should be removed  later.
				{
					MainLayout.getInstance().gotoMessageLayout(inputField.getText());
				} else
					MainLayout.getInstance().gotoConnectLayout(inputField.getText());
			}
		}
	}


	/**
	 * @return if the string is of appropriate length.
	 */
	private boolean validUserName(String str) {

		// TODO: 12/06/2017 Evaluate if the user name is appropriate and valid.
		// TODO: 12/06/2017 double check with SQL server if unique username.

		return (str.length() < 16) && (str.length() > 4);
	}

}
