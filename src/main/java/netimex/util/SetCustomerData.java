//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.23 at 01:08:36 PM IST 
//


package netimex.util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="customerDataTest" type="{http://util.netimex}CustomerDataTest"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "customerDataTest"
})
@XmlRootElement(name = "setCustomerData")
public class SetCustomerData {

    @XmlElement(required = true)
    protected CustomerDataTest customerDataTest;

    /**
     * Gets the value of the customerDataTest property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerDataTest }
     *     
     */
    public CustomerDataTest getCustomerDataTest() {
        return customerDataTest;
    }

    /**
     * Sets the value of the customerDataTest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerDataTest }
     *     
     */
    public void setCustomerDataTest(CustomerDataTest value) {
        this.customerDataTest = value;
    }

}
