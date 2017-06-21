/*
	* Created on 21/06/2017.
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

import Connection.Connector;
import External.Singleton;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ConnectingLayout implements Layout {

	static Thread sync;

	public Scene get() {

		GridPane grid = Singleton.getDefaultGridPane();

		Text scenetitle = new Text("Connecting.");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 1, 0, 2, 1);

		sync = new Thread(new checkConnection());
		sync.start();

		return new Scene(grid, 300, 275);
	}

}

class checkConnection extends Thread {

	public void run() {
		Singleton.debugPrint("Checking for server connection");

		Connector connector = Main.getConnector();
		try {
			connector.connect();
		} catch (Exception ex) {
			Singleton.debugPrint("An Exception was thrown when connecting to the socket", ex);

		}

		// Hangs the client while not connected.
		while (!connector.isConnected());
		this.stop();
	}

}
