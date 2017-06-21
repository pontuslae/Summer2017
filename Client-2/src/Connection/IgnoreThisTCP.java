package Connection;

import External.Singleton;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

class IgnoreThisTCP {

	ServerSocket serverSocket;

	IgnoreThisTCP() {

		try {
			this.serverSocket = new ServerSocket(9005);
		} catch (IOException ex) {
			Singleton.debugPrint("Exception was thrown during mocking", ex);
		}
	}


	public void mock(String receive, byte send){
		try {
			Socket socket = serverSocket.accept();

			BufferedReader inFromClient =
					new BufferedReader(new InputStreamReader(socket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
			String rl = inFromClient.readLine();

			Singleton.debugPrint(rl);

			outToClient.write(send);
		} catch (IOException ex) {
			Singleton.debugPrint("Exception was thrown during mocking", ex);
		}
	}

	public static void main(String argv[]) throws Exception {
		System.out.println("Listening...");
		new ServerSocket(9005).accept();
		System.out.println("Connected");
	}
}