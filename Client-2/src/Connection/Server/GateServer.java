package Connection.Server; /*
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

import External.Singleton;
import Layout.MainLayout;

import java.io.IOException;

public class GateServer extends Server {
	
	@Override
	public void run() {
		try {
			MainLayout.setServer(transfer());
		} catch (IOException ex) {
			MainLayout.getInstance().gotoFailedLayout("Couldn't find a server.");
		}
	}

	/**
	 * @return a new server where the data should be sent.
	 * @throws IOException If the input streams are dead.
	 */
	public Server transfer() throws IOException {
		Singleton.debugPrint("Transferring Servers");
		this.socket.setSoTimeout(3000);

		// TODO: 05/07/2017 This has been tested, but needs more verification on the client end.
		String first = this.getIn().readLine();
		String second = this.getIn().readLine();
		Singleton.debugPrint("New Server: " + first + " " + second);

		return new Server(new ServerInfo(first, Integer.parseInt(second)));
	}

}
