package chaser.util;

public class StringUtils {

	public static boolean isNotBlank(String target) {
		return !isBlank(target);
	}

	public static boolean isBlank(String target) {
		if (target == null) {
			return true;
		}

		if (target.length() == 0) {
			return true;
		}

		return false;
	}

}
