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

import Connection.Server;
import External.Singleton;
import External.Timer;
import javafx.application.Application;
import javafx.stage.Stage;

// TODO: 02/07/2017 Offload the functionality to another class so the main isn't cluttered.
public class Main extends Application {

	private Stage stage;

	private static Connection.Server server = new Connection.Connector();

	private static Main instance;

	private String storedName = "Undefined";

	public Main() {
		instance = this;
	}

	public static Connection.Server getConnector() {
		return server;
	}

	public static void setConnector(Server ser) {
		server = ser;
	}

	public static Main getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void fromConnectLayout() {

		Singleton.debugPrint("FromConnectLayout");
		// Hangs while sync is alive.
		while (ConnectLayout.sync.isAlive()){
			if (ConnectLayout.failedStatus) {
				// TODO: 21/06/2017 Handle this exception better. 
				System.out.println("Failed to connect to socket! Exiting"); // No debugprint is intentional
				Main.getInstance().gotoFailedLayout();
				return;
			}
		}
		Singleton.debugPrint("Sync confirmed, Thread dead.");
		Main.getInstance().gotoMessageLayout(storedName);
	}

	void gotoFailedLayout() {
		this.stage.setScene(new ErrorLayout().get());
	}

	void gotoMessageLayout(String str){
		storedName = str;
		this.stage.setScene(new MessageLayout(str).get());
	}

	void gotoMessageLayout(){
		this.stage.setScene(new MessageLayout(storedName).get());
	}

	void gotoConnectLayout(String str){
		storedName = str;
		this.stage.setScene(new ConnectLayout().get());
		Main.getInstance().fromConnectLayout();
	}

	void gotoStartLayout() {
		this.stage.setScene(new StartLayout().get());
	}

	@Override
	public void start(Stage primaryStage) {
		Timer timer = new Timer(1000);
		primaryStage.setTitle("Client");
		primaryStage.centerOnScreen();

		StartLayout sl = new StartLayout();
		primaryStage.setScene(sl.get());
		primaryStage.show();
		this.stage = primaryStage;

		timer.stop();
		timer.status("Slow start up");
	}

	public void setStage(){

	}
}

