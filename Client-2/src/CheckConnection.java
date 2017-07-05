/*
	* Created on 01/07/2017.
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

import Connection.Server.GateServer;
import Connection.Server.Server;
import External.Singleton;
import Layout.ConnectLayout;

import java.net.ConnectException;


/**
 * Connects to the sever on a background Thread to not interrupt the users control flow.
 */
class CheckConnection extends Thread {
	public void run() {
		Singleton.debugPrint("Checking for a authentication server connection");

		GateServer gs = new GateServer();                       // Init. a gate server that authenticates the user.

		try {
			gs.connect();
			Server newServer = gs.transfer();
			Main.setConnector(newServer);

			// If an Exception is caught, notify the user and revert back to the start screen.
		} catch (ConnectException ex) {

			Singleton.debugPrint("A ConnectException was thrown when connecting to the socket", ex);
			ConnectLayout.failedMessage = ex.getMessage();
			ConnectLayout.failedStatus = true;
		} catch (Exception ex) {
			Singleton.debugPrint("An Exception was thrown when connecting to the socket", ex);

			ConnectLayout.failedMessage = ex.getMessage();
			ConnectLayout.failedStatus = true;
		}
	}

}
