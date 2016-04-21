package chaser;

import org.junit.Test;

import java.nio.charset.Charset;

public class NewlineTest {

	@Test
	public void newline() {
		byte[] bytes = System.lineSeparator().getBytes(Charset.forName("UTF-8"));
		for(byte b : bytes) {
			System.out.println(b);
		}
	}
}
