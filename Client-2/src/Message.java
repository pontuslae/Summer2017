class Message {
	private String poster;
	private String message;

	Message(String p, String m){
		this.poster = p;
		this.message = m;
	}

	@Override
	public String toString() {
		return this.poster + ": " + this.message;
	}
}
