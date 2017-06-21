package External;

public class Timer {
	long start;
	long end = 0;
	int expected;

	public Timer() {
		this.start(300);
	}

	public Timer(int expected) {
		this.start(expected);
	}

	public void start(int expected) {
		this.expected = expected;
		this.start = System.currentTimeMillis();
	}

	public long stop() {
		return (this.end = System.currentTimeMillis());
	}

	public long elapse() {
		return this.end - this.start;
	}

	/**
	 * @return true if the the start time plus the expected time is less than the end time.
	 */
	public boolean status() {
		return elapse() > this.expected;
	}


	public void status(String str) {
		if (this.status())
			Singleton.debugPrint("TIMER: " + elapse() + "(" + str + ")");
	}
}
