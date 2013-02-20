// Authentication.java
//
// Informatics 122 Winter 2013
// Code Example
//
// This is an example of using a URLConnection to perform the OAuth Basic
// Authentication Flow against the bit.ly API.  There are a few interesting
// things that make this tricky:
//
// (1) The authentication flow requires an HTTPS connection.  As luck would
//     have it, URLConnection handles HTTPS connections automatically; if the
//     URL begins with "https", it will have a secure conversation.
//
// (2) The request you need to make here is a POST request that also needs to
//     have a special header called "Authentication" set.  It becomes necessary
//     to know the order in which you manipulate a URLConnection object in
//     order to do what needs to be done: (a) create the URLConnection,
//     (b) add the header, (c) specify that you want a POST request, and
//     (d) write the POST request body.
//
// (3) The value in the "Authentication" header is required to be Base64
//     encoded.  Base64 encoding is simply a way to take arbitrary byte
//     sequences and turn them into sequences of printable characters in
//     a relatively narrow range (64 possible characters per six bits).
//     There turns out to be a Base64 encoder built into the Java library
//     (beginning with Java 7), though it's not obvious unless you know
//     where to look.
//
// There is no attempt at good design here; this is merely a demonstration of
// the raw materials you need in order to solve the problem.

package inf122.bitly.example;

import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;


public class Authentication
{
	public static String USERNAME = "put your bit.ly username here";
	public static String PASSWORD = "put your bit.ly password here";
	public static String CLIENT_ID = "put your bit.ly client ID here";
	public static String CLIENT_SECRET = "put your bit.ly client secret here";


	public static void main(String[] args) throws Exception
	{
		// First, we open a connection to the appropriate endpoint of the
		// bit.ly API.

		URLConnection connection =
			new URL("https://api-ssl.bitly.com/oauth/access_token")
			.openConnection();


		// Next, we need to set the "Authorization" header, whose value is
		// required to be "Basic [[encoded username and password]]".  The
		// encoded username and password is to be constructed by concatenating
		// the username and password together, separated by a colon, then
		// Base64 encoding that string.
		//
		// javax.xml.bind.DatatypeConverter, a class built into the Java library,
		// includes, among other things, Base64 encoding functionality: you give
		// it an array of bytes and it'll give you back a Base64-encoded string.

		String usernamePassword = USERNAME + ":" + PASSWORD;
		byte[] usernamePwBytes = usernamePassword.getBytes("UTF-8");
		
		String base64UsernamePw =
			DatatypeConverter.printBase64Binary(usernamePwBytes);


		// Setting a request header requires calling the method addRequestProperty()
		// on the URLConnection object.		

		connection.addRequestProperty(
			"Authorization", "Basic " + base64UsernamePw);


		// The not-at-all-aptly-named setDoOutput(true) method is how you specify
		// that you want your request to be a POST request, as opposed to a GET.

		connection.setDoOutput(true);


		// The body of the POST request is expected to be in this format:
		//
		//     client_id=CLIENT_ID&client_secret=CLIENT_SECRET
		//
		// where CLIENT_ID and CLIENT_SECRET are the client ID and client secret
		// associated with your bit.ly application.
		
		PrintWriter connectionOut =
			new PrintWriter(connection.getOutputStream());
		
			connectionOut.println(
				"client_id=" + CLIENT_ID
				+ "&client_secret=" + CLIENT_SECRET);
		


		// Now that we're done writing all of the data into our request, we
		// ask for an input stream and begin reading the response.  This tells
		// the URLConnection to send the request and wait for a response.
		//
		// Interestingly, the bit.ly API's documentation specifies that the
		// response will look like this:
		//
		//     access_token=ACCESS_TOKEN&login=USERNAME&apiKey=API_KEY
		//
		// However, every attempt I've made to connect to this endpoint has
		// given me back the access token all by itself in the response body
		// instead.  You can't always trust documentation when the rubber
		// meets the road.  :)

		Scanner connectionIn =
			new Scanner(connection.getInputStream());
			System.out.println(
				"The access token is "
				+ connectionIn.nextLine());
		
	}
}
