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

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

class Singleton {

	static final int NEW_LINE = 13;
	private static final boolean debug = true;


	static void deny(){
		try {
			throw new Exception("Can't instantiate an empty MessageLayout");
		} catch (Exception ex) {
			throw new Error("Unable to throw Exception.");
		}
	}

	/**
	 * @return the default font used for none input text.
	 */
	static Font getDefaultFont(){
		return Font.font("Tahoma", FontWeight.NORMAL, 16);
	}

	static GridPane getDefaultGridPane(){
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		int pad = 5;
		grid.setPadding(new Insets(pad,pad,pad,pad));
		return grid;
	}

	static void debugPrint(String s) {
		// TODO: 15/06/2017 expand to introduce proper logs. 
		if (debug)
			System.out.println(s);
	}

}
