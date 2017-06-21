package Connection;

import External.Singleton;

import java.io.IOException;
import java.net.*;

class IgnoreThisTCP {


	public void mock(String receive, String send){
		System.out.println("Listening...");
		try {
			Socket socket = new ServerSocket(9005).accept();
		} catch (IOException ex) {
			Singleton.debugPrint("Exception was thrown during mocking", ex);
		}
		System.out.println("Connected");
	}

	public static void main(String argv[]) throws Exception {
		System.out.println("Listening...");
		new ServerSocket(9005).accept();
		System.out.println("Connected");
	}
}