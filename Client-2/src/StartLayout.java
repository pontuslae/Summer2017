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

import External.Singleton;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StartLayout extends Layout {

	TextField inputField;
	String input;

	public StartLayout(){}

	public Scene get(){
		GridPane grid = Singleton.getDefaultGridPane();

		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 1, 0, 2, 1);

		inputField = new TextField();
		grid.add(inputField, 1, 1);
		inputField.addEventFilter(KeyEvent.KEY_TYPED, this::inputFieldListener);
		return new Scene(grid, 300, 275);
	}

	private void inputFieldListener(KeyEvent e){
		if ((int) (e.getCharacter().charAt(0)) == Singleton.NEW_LINE){ // New line
			if (validUserName(inputField.getText())){
				Singleton.debugPrint("StartLayout -> MessageLayout");
				input = inputField.getText();

				Main.getInstance().gotoMessageLayout(inputField.getText());
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
