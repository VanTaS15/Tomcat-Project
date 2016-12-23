package tokenizer;

import java.util.StringTokenizer;

/**
 * Parse an Apache log file with StringTokenizer
 * https://examples.javacodegeeks.com/core-java/util/regex/parse-an-apache-logfile-with-regular-expressions/
 */
public class Apache implements LogExample {

    public static void main(String argv[]) {

        StringTokenizer matcher = new StringTokenizer(logEntryLine);

        System.out.println("tokens = " + matcher.countTokens());

        // StringTokenizer CAN NOT count if you are changing the delimiter!
        // if (matcher.countTokens() != NUM_FIELDS) {
        //   System.err.println("Bad log entry (or bug in StringTokenizer?):");
        //   System.err.println(logEntryLine);
        // }
        System.out.println("Hostname: " + matcher.nextToken());

        // StringTokenizer makes you ask for tokens in order to skip them:
        matcher.nextToken(); // eat the "-"

        matcher.nextToken(); // again

        System.out.println("Date/Time: " + matcher.nextToken("]"));

        //matcher.nextToken(" "); // again
        System.out.println("Request: " + matcher.nextToken("\""));

        matcher.nextToken(" "); // again

        System.out.println("Response: " + matcher.nextToken());

        System.out.println("ByteCount: " + matcher.nextToken());

        System.out.println("Referer: " + matcher.nextToken("\""));

  matcher.nextToken(" "); // again

        System.out.println("User-Agent: " + matcher.nextToken("\""));
    }
}
/**
 * Common fields for Apache Log demo.
 */
interface LogExample {

    /**
     * The number of fields that must be found.
     */
    public static final int NUM_FIELDS = 9;
    /**
     * The sample log entry to be parsed.
     */
    public static final String logEntryLine = "123.45.67.89 - - [27/Oct/2000:09:27:09 -0400] \"GET /java/javaResources.html HTTP/1.0\" 200 10450 \"-\" \"Mozilla/4.6 [en] (X11;U;OpenBSD 2.8 i386 ; Nav)\"";
}
