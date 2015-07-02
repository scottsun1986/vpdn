package util;

public class StringHelper {
	public static String cutString(String ori, int num) {
		if (ori == null) {
			return null;
		} else {
			if (ori.length() < num)
				return ori;
			else {
				String returned = ori.substring(0, num);
				return returned+"...";
			}
		}
	}
}
