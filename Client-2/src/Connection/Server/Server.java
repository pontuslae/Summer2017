package Connection.Server;

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

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Server extends Thread {

	private ServerInfo si;              // A ServerInfo Class for holding where to connect to.
	Socket socket;                      // A Socket that holds a TCP client.
	private DataOutputStream out;               // An output stream that transmits data to another socket.
	private BufferedReader in;                  /* A buffered input reader that converts binaries to strings. -
										   And gets information from the socket. */
	private static Server instance;

	@Override
	public void run() {
		super.run();
	}

	public static Server getInstance() {
		return instance;
	}

	public Server() {
		si = new ServerInfo();
		this.instance = this;
	}

	public Server(ServerInfo si) {
		this.si = si;
		this.instance = this;
	}

	public Server(ServerInfo si, Socket socket) {
		this.si = si;
		this.socket = socket;
		this.instance = this;
	}

	public Server(Socket socket) {
		this.socket = socket;
		this.instance = this;
	}

	public ServerInfo getServerInfo() {
		return this.si;
	}

	public void setServerInfo(ServerInfo serverInfo) {
		this.si = serverInfo;
	}

	public Socket getSocket() {
		return this.socket;
	}

	public BufferedReader getIn() {
		return this.in;
	}

	public DataOutputStream getOut() { // get out, get it? It's a pun.
		return this.out;
	}

	/**
	 * BInds the socket to the
	 * @throws Exception
	 */
	public void connect() throws Exception {
		this.socket = new Socket(this.si.getAddress(), si.getPort());
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.out = new DataOutputStream(this.socket.getOutputStream());
	}

	/**
	 * Before interacting with the socket, make sure it works, otherwise throw an exception.
	 * @throws Exception inherited from the connect() method.
	 */
	void verifySocket() throws Exception {
		if (this.socket == null) {
			this.connect();
		} else if (!this.socket.isConnected()) {
			this.connect();
		}
	}

	/**
	 * Sets the object's Socket to null.
	 */
	public void nullifySocket() {
		this.socket = null;
	}

	/**
	 * Sends a message over the socket.
	 * @param str the string to be sent.
	 * @throws Exception inherited from verifySocket() and from the OutputStream.
	 */
	public void send(String str) throws Exception {
		this.verifySocket();
		this.out.writeUTF(str);
		this.close();
	}

	public String read() throws IOException {
		return this.read(150);
	}

	public String read(int nrBytes) throws IOException {
		int[] recv = new int[nrBytes];

		// TODO: 05/07/2017 Improve this algorithm.
		// N = nrbytes
		// O(N) * (O(N) + O(N)) = O(N^2)

		for (int i = 0; i < nrBytes; i++) {                     // Loop for the amount of bytes the user requests.
			int bte = this.in.read();                           // Reads one integer from the socket.
			if (bte == -1) {                                    // -1 indicates end of stream has been reached.
				return intArrayToString(Arrays.copyOf(recv, 0));// Make a copy of the Array in the range that is filled.
			}
			recv[i] = this.in.read();                           // Stores an integer in the array.
		}

		return intArrayToString(recv);// If the entire amount of nrBytes is full without end of stream Return all of it.
	}

	/**
	 * Converts an integer array to a String of characters. O(N) (Not including o(N) from String concat.
	 * @param original the integer array
	 * @return a String of characters from the integer values.
	 */
	private String intArrayToString(int[] original) {
		String str = "";
		for (int val: original) {
			str += (char) val;
		}
		return str;
	}

	/**
	 * Closes the socket
	 * @throws IOException if the socket cannot be accessed.
	 */
	public void close() throws IOException {
		this.socket.close();
	}
}
