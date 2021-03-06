package External;

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

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Singleton {

	public static final int NEW_LINE = 13;
	private static final boolean debug = true;


	public static void deny(){
		try {
			throw new Exception("Can't instantiate an empty Layout.MessageLayout");
		} catch (Exception ex) {
			throw new Error("Can't instantiate an empty Layout.MessageLayout - Unable to throw Exception.");
		}
	}

	/**
	 * @return the default font used for none input text.
	 */
	public static Font getDefaultFont(){
		return getDefaultFont(16);
	}

	/**
	 * @return the default font used for none input text.
	 */
	public static Font getDefaultFont(int fontSize){
		return Font.font("Tahoma", FontWeight.NORMAL, fontSize);
	}

	public static GridPane getDefaultGridPane(){
		return getDefaultGridPane(10);
	}

	public static GridPane getDefaultGridPane(int gap){
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(gap);
		grid.setVgap(gap);
		int pad = 0;
		grid.setPadding(new Insets(pad,pad,pad,pad));
		grid.setBackground(Background.EMPTY);
		return grid;
	}

	public static void debugPrint(String s) {
		// TODO: 15/06/2017 expand to introduce proper logs. 
		if (debug)
			System.out.println("DP: " + s);
	}

	public static void debugPrint(String s, Exception ex) { // TODO: 21/06/2017 handle exceptions
		// TODO: 15/06/2017 expand to introduce proper logs.
		debugPrint(s);
		if (debug)
			ex.printStackTrace();
	}

	public static void debugTransitionPrint(String... str) {
		// TODO: 15/06/2017 expand to introduce proper logs.
		if (debug){
			String combinedString = "";
			for (String s: str)
				combinedString += s + " -> ";
			combinedString = combinedString.substring(0, combinedString.length() -4);
			System.out.println("DP: " + combinedString);
		}

	}

	// TODO: 05/07/2017 This is a lazy implementation. Improve it.
	public static void sleep(int value) {
		long t1 = System.currentTimeMillis();
		while (t1 < System.currentTimeMillis() + value);
	}

}
