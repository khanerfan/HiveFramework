package enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "contentType")
@XmlEnum
public enum ContentType {

	@XmlEnumValue("image")
	image("image"), @XmlEnumValue("video")
	video("video"), @XmlEnumValue("audio")
	audio("audio");

	private final String value;

	private ContentType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static ContentType fromValue(String v) {
		for (ContentType c : ContentType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}


}
