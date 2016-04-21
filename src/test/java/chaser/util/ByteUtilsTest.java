package chaser.util;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

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
	public void chop_1byte_delimiter() {
		byte[][] targetElement = new byte[][] {
			"hello".getBytes(),
			"world".getBytes(),
			"bear".getBytes()
		};

		String delimiter = "\n";

		byte[] target = (new String(targetElement[0]) + delimiter + new String(targetElement[1]) + delimiter + new String(targetElement[2])).getBytes();
		byte[] delimiterByte = delimiter.getBytes();

		byte[][] chopped = ByteUtils.chop(target, delimiterByte);

		for(byte[] result : chopped) {
			Assertions.assertThat(chopped).contains(result);
		}
	}

	@Test
	public void chop_multiple_bytes_delimiter() {
		byte[][] targetElement = new byte[][] {
			"hello".getBytes(),
			"world".getBytes(),
			"bear".getBytes()
		};

		String delimiter = "\r\n";

		byte[] target = (new String(targetElement[0]) + delimiter + new String(targetElement[1]) + delimiter + new String(targetElement[2])).getBytes();
		byte[] delimiterByte = delimiter.getBytes();

		byte[][] chopped = ByteUtils.chop(target, delimiterByte);

		for(byte[] result : chopped) {
			Assertions.assertThat(chopped).contains(result);
		}
	}

	@Test
	public void arrays_copyOfRange() {
		byte[][] array = new byte[3][];
		array[0] = "hello".getBytes();
		array[1] = "world".getBytes();
		array[2] = "bear".getBytes();

		byte[][] result = Arrays.copyOfRange(array, 0, array.length - 1);

		Assertions.assertThat(result).hasSize(array.length - 1);
		Assertions.assertThat(result).contains(array[0], array[1]);
		Assertions.assertThat(result).doesNotContain(array[2]);
	}

}