/**
 * Created by pontu on 13/06/2017.
 */
public class Message {
	private String poster;
	private String message;

	Message(String p, String m){
		poster = p;
		message = m;
	}

	@Override
	public String toString() {
		return poster + ": " + message;
	}
}
