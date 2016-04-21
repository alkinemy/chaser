package chaser.util;

import java.util.List;

public abstract class StringUtils {

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

	public static String join(List<String> strings, String joiner) {
		if (strings == null || isBlank(joiner)) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < strings.size(); i++) {
			String target = strings.get(i);
			if (i == strings.size() - 1) {
				sb.append(target);
			} else {
				sb.append(target)
					.append(joiner);
			}
		}
		return sb.toString();
	}

}
