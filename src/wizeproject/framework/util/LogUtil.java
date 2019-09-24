package wizeproject.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title      : 로그 유틸 ( System.out.print 대체용, PrintStream에서 자주 사용하는 것들만 간추림 )
 * @Packgename : wizeproject.framework.util
 * @Filename   : LogUtil.java
 *
 * @Author  : 
 * @Since   : 2017. 7. 24.
 * @Version : 1.0
 */
public class LogUtil {
	private static Logger printLogger = LoggerFactory.getLogger("printLogger");

	private static void write(String s) {
		printLogger.info(s);
	}

	private static void write(char buf[]) {
		printLogger.info(buf.toString());
	}

	private static void newLine() {
		printLogger.info("\n");
	}

	/**
	 * Prints a boolean value.  The string produced by <code>{@link
	 * java.lang.String#valueOf(boolean)}</code> is translated into bytes
	 * according to the platform's default character encoding, and these bytes
	 * are written in exactly the manner of the
	 * <code>{@link #write(int)}</code> method.
	 *
	 * @param      b   The <code>boolean</code> to be printed
	 */
	public static void print(boolean b) {
		write(b ? "true" : "false");
	}

	/**
	 * Prints a character.  The character is translated into one or more bytes
	 * according to the platform's default character encoding, and these bytes
	 * are written in exactly the manner of the
	 * <code>{@link #write(int)}</code> method.
	 *
	 * @param      c   The <code>char</code> to be printed
	 */
	public static void print(char c) {
		write(String.valueOf(c));
	}

	/**
	 * Prints an integer.  The string produced by <code>{@link
	 * java.lang.String#valueOf(int)}</code> is translated into bytes
	 * according to the platform's default character encoding, and these bytes
	 * are written in exactly the manner of the
	 * <code>{@link #write(int)}</code> method.
	 *
	 * @param      i   The <code>int</code> to be printed
	 * @see        java.lang.Integer#toString(int)
	 */
	public static void print(int i) {
		write(String.valueOf(i));
	}

	/**
	 * Prints a long integer.  The string produced by <code>{@link
	 * java.lang.String#valueOf(long)}</code> is translated into bytes
	 * according to the platform's default character encoding, and these bytes
	 * are written in exactly the manner of the
	 * <code>{@link #write(int)}</code> method.
	 *
	 * @param      l   The <code>long</code> to be printed
	 * @see        java.lang.Long#toString(long)
	 */
	public static void print(long l) {
		write(String.valueOf(l));
	}

	/**
	 * Prints a floating-point number.  The string produced by <code>{@link
	 * java.lang.String#valueOf(float)}</code> is translated into bytes
	 * according to the platform's default character encoding, and these bytes
	 * are written in exactly the manner of the
	 * <code>{@link #write(int)}</code> method.
	 *
	 * @param      f   The <code>float</code> to be printed
	 * @see        java.lang.Float#toString(float)
	 */
	public static void print(float f) {
		write(String.valueOf(f));
	}

	/**
	 * Prints a double-precision floating-point number.  The string produced by
	 * <code>{@link java.lang.String#valueOf(double)}</code> is translated into
	 * bytes according to the platform's default character encoding, and these
	 * bytes are written in exactly the manner of the <code>{@link
	 * #write(int)}</code> method.
	 *
	 * @param      d   The <code>double</code> to be printed
	 * @see        java.lang.Double#toString(double)
	 */
	public static void print(double d) {
		write(String.valueOf(d));
	}

	/**
	 * Prints an array of characters.  The characters are converted into bytes
	 * according to the platform's default character encoding, and these bytes
	 * are written in exactly the manner of the
	 * <code>{@link #write(int)}</code> method.
	 *
	 * @param      s   The array of chars to be printed
	 *
	 * @throws  NullPointerException  If <code>s</code> is <code>null</code>
	 */
	public static void print(char s[]) {
		write(s);
	}

	/**
	 * Prints a string.  If the argument is <code>null</code> then the string
	 * <code>"null"</code> is printed.  Otherwise, the string's characters are
	 * converted into bytes according to the platform's default character
	 * encoding, and these bytes are written in exactly the manner of the
	 * <code>{@link #write(int)}</code> method.
	 *
	 * @param      s   The <code>String</code> to be printed
	 */
	public static void print(String s) {

		String str = s;

		if (str == null) {
			str = "null";
		}
		write(str);
	}

	/**
	 * Prints an object.  The string produced by the <code>{@link
	 * java.lang.String#valueOf(Object)}</code> method is translated into bytes
	 * according to the platform's default character encoding, and these bytes
	 * are written in exactly the manner of the
	 * <code>{@link #write(int)}</code> method.
	 *
	 * @param      obj   The <code>Object</code> to be printed
	 * @see        java.lang.Object#toString()
	 */
	public static void print(Object obj) {
		write(String.valueOf(obj));
	}

	/* Methods that do terminate lines */

	/**
	 * Terminates the current line by writing the line separator string.  The
	 * line separator string is defined by the system property
	 * <code>line.separator</code>, and is not necessarily a single newline
	 * character (<code>'\n'</code>).
	 */
	public static void println() {
		newLine();
	}

	/**
	 * Prints a boolean and then terminate the line.  This method behaves as
	 * though it invokes <code>{@link #print(boolean)}</code> and then
	 * <code>{@link #println()}</code>.
	 *
	 * @param x  The <code>boolean</code> to be printed
	 */
	public static void println(boolean x) {
		print(x);
		newLine();
	}

	/**
	 * Prints a character and then terminate the line.  This method behaves as
	 * though it invokes <code>{@link #print(char)}</code> and then
	 * <code>{@link #println()}</code>.
	 *
	 * @param x  The <code>char</code> to be printed.
	 */
	public static void println(char x) {
		print(x);
		newLine();
	}

	/**
	 * Prints an integer and then terminate the line.  This method behaves as
	 * though it invokes <code>{@link #print(int)}</code> and then
	 * <code>{@link #println()}</code>.
	 *
	 * @param x  The <code>int</code> to be printed.
	 */
	public static void println(int x) {
		print(x);
		newLine();
	}

	/**
	 * Prints a long and then terminate the line.  This method behaves as
	 * though it invokes <code>{@link #print(long)}</code> and then
	 * <code>{@link #println()}</code>.
	 *
	 * @param x  a The <code>long</code> to be printed.
	 */
	public static void println(long x) {
		print(x);
		newLine();
	}

	/**
	 * Prints a float and then terminate the line.  This method behaves as
	 * though it invokes <code>{@link #print(float)}</code> and then
	 * <code>{@link #println()}</code>.
	 *
	 * @param x  The <code>float</code> to be printed.
	 */
	public static void println(float x) {
		print(x);
		newLine();
	}

	/**
	 * Prints a double and then terminate the line.  This method behaves as
	 * though it invokes <code>{@link #print(double)}</code> and then
	 * <code>{@link #println()}</code>.
	 *
	 * @param x  The <code>double</code> to be printed.
	 */
	public static void println(double x) {
		print(x);
		newLine();
	}

	/**
	 * Prints an array of characters and then terminate the line.  This method
	 * behaves as though it invokes <code>{@link #print(char[])}</code> and
	 * then <code>{@link #println()}</code>.
	 *
	 * @param x  an array of chars to print.
	 */
	public static void println(char x[]) {
		print(x);
		newLine();
	}

	/**
	 * Prints a String and then terminate the line.  This method behaves as
	 * though it invokes <code>{@link #print(String)}</code> and then
	 * <code>{@link #println()}</code>.
	 *
	 * @param x  The <code>String</code> to be printed.
	 */
	public static void println(String x) {
		print(x);
		newLine();
	}

	/**
	 * Prints an Object and then terminate the line.  This method calls
	 * at first String.valueOf(x) to get the printed object's string value,
	 * then behaves as
	 * though it invokes <code>{@link #print(String)}</code> and then
	 * <code>{@link #println()}</code>.
	 *
	 * @param x  The <code>Object</code> to be printed.
	 */
	public static void println(Object x) {
		String s = String.valueOf(x);
		print(s);
		newLine();
	}

}