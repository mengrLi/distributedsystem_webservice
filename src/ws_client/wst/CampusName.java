
package ws_client.wst;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>campusName的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="campusName">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DORVAL"/>
 *     &lt;enumeration value="KIRKLAND"/>
 *     &lt;enumeration value="WESTMOUNT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "campusName")
@XmlEnum
public enum CampusName {

    DORVAL,
    KIRKLAND,
    WESTMOUNT;

    public String value() {
        return name();
    }

    public static CampusName fromValue(String v) {
        return valueOf(v);
    }

}
