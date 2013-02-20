// Shortening.java
//
// Informatics 122 Winter 2013
// Code Example
//
// An example of using the bit.ly API to shorten a URL.  Note that there is no
// attempt here to parse the response coming back from bit.ly, though that's
// something you would want to handle in your project; shortening a URL can
// fail, and, of course, you'll need to know what the shortened URL actually
// is if it succeeds.  Also, no attempt at clean design has been made here;
// this is just a demonstration of the raw materials.

package inf122.bitly.example;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;


public class Shortening
{
	// Replace this with whatever URL you'd like to shorten.
	public static String URL_TO_SHORTEN = "http://www.ics.uci.edu/~thornton/";

	// The Authentication class, also part of this example, shows how to get
	// one of these.
	public static String ACCESS_TOKEN = "put a valid bit.ly access_token here";


	public static void main(String[] args) throws Exception
	{
		// Query parameters in URLs need to be URL encoded, in case they have
		// special characters in them that have special meaning inside of URLs.
		// A good example of that is the "longUrl" query parameter in bit.ly's
		// /v3/shorten operation; it is required to be a URL, which will have a
		// number of potentially special characters in it (e.g., colons, slashes,
		// question marks, ampersands), so it's imperative that we URL encode
		// this parameter.

		String urlEncodedLongUrl = URLEncoder.encode(URL_TO_SHORTEN, "UTF-8");


		// Open a connection to the bit.ly API.

		URLConnection connection =
			new URL(
				"https://api-ssl.bitly.com/v3/shorten?longUrl=" + urlEncodedLongUrl
				+ "&access_token=" + ACCESS_TOKEN)
			.openConnection();


		// Since we didn't add any headers and are now asking for an input stream,
		// the URLConnection interprets this to be a GET request (i.e., no body)
		// automatically.  Interestingly, there's no setting that says "GET" or
		// "POST"; it's a matter of which methods I call on the URLConnection
		// that determines which HTTP verb will be used for the connection.
	
		Scanner connectionIn = new Scanner(connection.getInputStream());
		
			// Here, we're just reading the entire HTTP response body and printing
			// it out, though you'll instead want to parse it (perhaps using the
			// "JSON in Java" library) and use the values inside of it.

			while (connectionIn.hasNextLine())
			{
				System.out.println(connectionIn.nextLine());
			}
		}
	
}
