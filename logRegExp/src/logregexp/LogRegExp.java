package logregexp;

/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
 * $Id: LICENSE,v 1.8 2004/02/09 03:33:38 ian Exp $
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating (and standardizing) the Java 
 * language and environment is gratefully acknowledged.
 * 
 * The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
 * inventing predecessor languages C and C++ is also gratefully acknowledged.
 */
import java.util.regex.*;

/**
 * Common fields for Apache Log demo.
 */
interface LogExample {
  /** The number of fields that must be found. */
  public static final int NUM_FIELDS = 9;

  /** The sample log entry to be parsed. */
  public static final String logEntryLine = "123.45.67.89 - - [27/Oct/2000:09:27:09 -0400] \"GET /java/javaResources.html HTTP/1.0\" 200 10450 \"-\" \"Mozilla/4.6 [en] (X11; U; OpenBSD 2.8 i386; Nav)\"";

}


/**
 * Parse an Apache log file with Regular Expressions
 */
public class LogRegExp implements LogExample {

  public static void main(String argv[]) {

    String logEntryPattern = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";

    System.out.println("Using RE Pattern:");
    System.out.println(logEntryPattern);

    System.out.println("Input line is:");
    System.out.println(logEntryLine);

    Pattern p = Pattern.compile(logEntryPattern);
    Matcher matcher = p.matcher(logEntryLine);
    if (!matcher.matches() || 
      NUM_FIELDS != matcher.groupCount()) {
      System.err.println("Bad log entry (or problem with RE?):");
      System.err.println(logEntryLine);
      return;
    }
    System.out.println("IP Address: " + matcher.group(1));
    System.out.println("Date&Time: " + matcher.group(4));
    System.out.println("Request: " + matcher.group(5));
    System.out.println("Response: " + matcher.group(6));
    System.out.println("Bytes Sent: " + matcher.group(7));
    if (!matcher.group(8).equals("-"))
      System.out.println("Referer: " + matcher.group(8));
    System.out.println("Browser: " + matcher.group(9));
  }
}


