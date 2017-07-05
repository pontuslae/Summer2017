package Connection; /*
	* Created on 05/07/2017.
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
import Layout.MainLayout;

public class ListenServer extends Thread { // TODO: 05/07/2017 Class.
	/*
	The Purpose of this class is to handle listening for receiving data in the background and make connection
	with the server from time to time to make sure you are still conneted.
	 */

	private Server dataserver;
	private static ListenServer single;

	@Override
	public void run() {
		this.dataserver = DataServer.getInstance();
		single = this;

		while (!this.dataserver.socket.isClosed()) {     // While the socket is open.
			if (single != this) break;                   // If there exists more than one instant of this. Kill it.
			Singleton.debugPrint("Socket is not closed.");
			Singleton.sleep(500);
		}

		Singleton.debugPrint("Socket is closed.");

		// Notify the Server that it's dead. And the user.
		Server.getInstance().socket = null; // If the socket is null it will attempt to reconnect it internally.
		MainLayout.getInstance().gotoFailedLayout("Disconnected");

	}
}
