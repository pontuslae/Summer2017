package Connection;

import java.net.*;

class IgnoreThisTCP {
	public static void main(String argv[]) throws Exception {
		System.out.println("Listening...");
		new ServerSocket(9005).accept();
		System.out.println("Connected");
	}
}