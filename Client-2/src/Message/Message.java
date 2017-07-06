package Message;/*
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

import User.User;

public class Message {
	private User poster;        // The person who sent the message
	private long time;          // The unix time when the message was sent.
	private MessageContent message;     // The contents of the message.

	public Message(String poster, Object message, long time){
		this.poster = new User(poster);
		this.message = new MessageContent(message);
		this.time = time;
	}

	public Message(String poster, String message){
		this.poster = new User(poster);
		this.message = new MessageContent(message);

		// We only care about minutes and larger numbers.
		this.time = System.currentTimeMillis()/(1000*60);
	}

	@Override
	public String toString() {
		return this.getDate() + " | " + this.poster.getUsername() + ": " + this.message;
	}

	private String getDate() {

		// We only care about minutes and larger numbers.
		long currentDate = System.currentTimeMillis()/(1000*60);


		// TODO: 02/07/2017 This math.
		long pastMinutes = this.time % (60*60);
		long pastHour = this.time % (60 * 60);

		return pastHour + ":" + pastMinutes;
	}
}
