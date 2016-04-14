package chaser.util;

import java.util.Objects;

public abstract class ByteUtils {

	public static byte[] toByteArray(Byte[] bytes) {
		Objects.requireNonNull(bytes, "Input should not be null");

		byte[] result = new byte[bytes.length];
		for(int i = 0; i < bytes.length; i++) {
			result[i] = bytes[i];
		}
		return result;
	}

	public static Byte[] toByteArray(byte[] bytes) {
		Objects.requireNonNull(bytes, "Input should not be null");

		Byte[] result = new Byte[bytes.length];
		for(int i = 0; i < bytes.length; i++) {
			result[i] = bytes[i];
		}
		return result;
	}

}
