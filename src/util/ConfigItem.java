package util;

public class ConfigItem {
	private String key;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	private String value;
	
	public ConfigItem(String k,String v){
		key=k;
		value=v;
	}
	
}
