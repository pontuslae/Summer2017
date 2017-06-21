package Connection;

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

import External.Singleton;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connector {

	private Socket socket;
	private boolean connected = false;
	private BufferedReader in;
	DataOutputStream out;

	private ServerInfo serverinfo = new ServerInfo();

	public void connect() throws Exception {
		Singleton.debugPrint("Connector: Connecting");
		this.socket = new Socket(serverinfo.getAddress(), serverinfo.getPort());
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.out = new DataOutputStream(this.socket.getOutputStream());
		this.connected = true;
		Singleton.debugPrint("Connector: Connected");
	}


	public boolean isConnected() {
		return this.connected;
	}

	/**
	 * Looks for a response from the server to confirm the message has been sent.
	 * @return
	 */
	private boolean ok() {
		int handshake = 0;
		try {
			handshake = in.read();
		} catch (IOException ex) {
			Singleton.debugPrint("An IOException was thrown when handshaking ok.", ex);
		}

		if (handshake != 1){
			done();
			return false;
		}

		return true;
	}

	private void done() {
		this.connected = false;
		try {
			this.socket.close();
		} catch (IOException ex){
			Singleton.debugPrint("An IOException was thrown when closing the connection socket.", ex);
		}
	}

	private void send(String str) {

	}

	private String receive() {
		return "";
	}

	public String[] getOnlineUsers() {
		return null;
	}

	public boolean matchStatus(Object[] statusObjects){
		return false;
	}

}
