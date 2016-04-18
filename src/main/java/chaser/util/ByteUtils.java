package chaser.util;

public abstract class ByteUtils {

	public static byte[] toByteArray(Byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		byte[] result = new byte[bytes.length];
		for(int i = 0; i < bytes.length; i++) {
			result[i] = bytes[i];
		}
		return result;
	}

	public static Byte[] toByteArray(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		Byte[] result = new Byte[bytes.length];
		for(int i = 0; i < bytes.length; i++) {
			result[i] = bytes[i];
		}
		return result;
	}

}
