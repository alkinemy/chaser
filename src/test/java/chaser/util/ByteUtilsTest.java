package chaser.util;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class ByteUtilsTest {

	@Test
	public void toByteArray_object_to_primitive() {
		String str = "Hello world!";
		byte[] expected = str.getBytes();

		Byte[] objBytes = new Byte[expected.length];
		for(int i = 0; i < expected.length; i++) {
			objBytes[i] = expected[i];
		}

		byte[] actual = ByteUtils.toByteArray(objBytes);
		Assertions.assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void toByteArray_primitive_to_object() {

	}

}