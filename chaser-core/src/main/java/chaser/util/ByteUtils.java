package chaser.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	public static byte[][] chop(byte[] bytes, byte[] delimiter) {
		List<Integer> pivots = new ArrayList<>();
		for(int i = 0; i < bytes.length - delimiter.length + 1; i++) {
			boolean equal = true;
			for(int j = 0; j < delimiter.length; j++) {
				if (delimiter[j] != bytes[i + j]) {
					equal = false;
					break;
				}
			}
			if (equal) {
				pivots.add(i);
				i = i + delimiter.length;
			}
		}

		if (pivots.size() == 0) {
			return new byte[][] { bytes };
		}

		byte[][] result = new byte[pivots.size() + 1][];
		int currentPivot = 0;
		int nextPivot;
		for(int i = 0; i < pivots.size(); i++) {
			nextPivot = pivots.get(i);
			result[i] = Arrays.copyOfRange(bytes, currentPivot, nextPivot);
			currentPivot = nextPivot + delimiter.length;
		}
		result[pivots.size()] = Arrays.copyOfRange(bytes, currentPivot, bytes.length);
		return result;
	}

}
