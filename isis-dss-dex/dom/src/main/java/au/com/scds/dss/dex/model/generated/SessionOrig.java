//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.01 at 01:11:47 PM EST 
//


package au.com.scds.dss.dex.model.generated;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.LocalDate;


/**
 * <p>Java class for Session complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Session">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SessionId" type="{}NonEmptyString"/>
 *         &lt;element name="CaseId" type="{}NonEmptyString"/>
 *         &lt;element name="SessionDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ServiceTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TotalNumberOfUnidentifiedClients" type="{}MaxUnidentifiedClients"/>
 *         &lt;element name="FeesCharged" type="{}MinFeesCharged" minOccurs="0"/>
 *         &lt;element name="MoneyBusinessCommunityEducationWorkshopCode" type="{}NonEmptyString" minOccurs="0"/>
 *         &lt;element name="InterpreterPresent" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SessionClients" type="{}SessionClients" minOccurs="0"/>
 *         &lt;element name="TimeMinutes" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="TotalCost" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ExtraItems" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ExtraItemCode" type="{}NonEmptyString" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Session", propOrder = {
    "sessionId",
    "caseId",
    "sessionDate",
    "serviceTypeId",
    "totalNumberOfUnidentifiedClients",
    "feesCharged",
    "moneyBusinessCommunityEducationWorkshopCode",
    "interpreterPresent",
    "sessionClients",
    "timeMinutes",
    "totalCost",
    "quantity",
    "extraItems"
})
public class SessionOrig {

    @XmlElement(name = "SessionId", required = true)
    protected String sessionId;
    @XmlElement(name = "CaseId", required = true)
    protected String caseId;
    @XmlElement(name = "SessionDate", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "date")
    protected LocalDate sessionDate;
    @XmlElement(name = "ServiceTypeId")
    protected int serviceTypeId;
    @XmlElement(name = "TotalNumberOfUnidentifiedClients")
    protected int totalNumberOfUnidentifiedClients;
    @XmlElement(name = "FeesCharged", nillable = true)
    protected BigDecimal feesCharged;
    @XmlElement(name = "MoneyBusinessCommunityEducationWorkshopCode")
    protected String moneyBusinessCommunityEducationWorkshopCode;
    @XmlElement(name = "InterpreterPresent", nillable = true)
    protected Boolean interpreterPresent;
    @XmlElement(name = "SessionClients", nillable = true)
    protected SessionClients sessionClients;
    @XmlElement(name = "TimeMinutes", nillable = true)
    protected Integer timeMinutes;
    @XmlElement(name = "TotalCost", nillable = true)
    protected Integer totalCost;
    @XmlElement(name = "Quantity", nillable = true)
    protected Integer quantity;
    @XmlElement(name = "ExtraItems", nillable = true)
    protected SessionOrig.ExtraItems extraItems;

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the caseId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * Sets the value of the caseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaseId(String value) {
        this.caseId = value;
    }

    /**
     * Gets the value of the sessionDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public LocalDate getSessionDate() {
        return sessionDate;
    }

    /**
     * Sets the value of the sessionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionDate(LocalDate value) {
        this.sessionDate = value;
    }

    /**
     * Gets the value of the serviceTypeId property.
     * 
     */
    public int getServiceTypeId() {
        return serviceTypeId;
    }

    /**
     * Sets the value of the serviceTypeId property.
     * 
     */
    public void setServiceTypeId(int value) {
        this.serviceTypeId = value;
    }

    /**
     * Gets the value of the totalNumberOfUnidentifiedClients property.
     * 
     */
    public int getTotalNumberOfUnidentifiedClients() {
        return totalNumberOfUnidentifiedClients;
    }

    /**
     * Sets the value of the totalNumberOfUnidentifiedClients property.
     * 
     */
    public void setTotalNumberOfUnidentifiedClients(int value) {
        this.totalNumberOfUnidentifiedClients = value;
    }

    /**
     * Gets the value of the feesCharged property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFeesCharged() {
        return feesCharged;
    }

    /**
     * Sets the value of the feesCharged property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFeesCharged(BigDecimal value) {
        this.feesCharged = value;
    }

    /**
     * Gets the value of the moneyBusinessCommunityEducationWorkshopCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneyBusinessCommunityEducationWorkshopCode() {
        return moneyBusinessCommunityEducationWorkshopCode;
    }

    /**
     * Sets the value of the moneyBusinessCommunityEducationWorkshopCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneyBusinessCommunityEducationWorkshopCode(String value) {
        this.moneyBusinessCommunityEducationWorkshopCode = value;
    }

    /**
     * Gets the value of the interpreterPresent property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInterpreterPresent() {
        return interpreterPresent;
    }

    /**
     * Sets the value of the interpreterPresent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInterpreterPresent(Boolean value) {
        this.interpreterPresent = value;
    }

    /**
     * Gets the value of the sessionClients property.
     * 
     * @return
     *     possible object is
     *     {@link SessionClients }
     *     
     */
    public SessionClients getSessionClients() {
        return sessionClients;
    }

    /**
     * Sets the value of the sessionClients property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionClients }
     *     
     */
    public void setSessionClients(SessionClients value) {
        this.sessionClients = value;
    }

    /**
     * Gets the value of the timeMinutes property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTimeMinutes() {
        return timeMinutes;
    }

    /**
     * Sets the value of the timeMinutes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTimeMinutes(Integer value) {
        this.timeMinutes = value;
    }

    /**
     * Gets the value of the totalCost property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalCost() {
        return totalCost;
    }

    /**
     * Sets the value of the totalCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalCost(Integer value) {
        this.totalCost = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuantity(Integer value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the extraItems property.
     * 
     * @return
     *     possible object is
     *     {@link SessionOrig.ExtraItems }
     *     
     */
    public SessionOrig.ExtraItems getExtraItems() {
        return extraItems;
    }

    /**
     * Sets the value of the extraItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionOrig.ExtraItems }
     *     
     */
    public void setExtraItems(SessionOrig.ExtraItems value) {
        this.extraItems = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ExtraItemCode" type="{}NonEmptyString" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "extraItemCode"
    })
    public static class ExtraItems {

        @XmlElement(name = "ExtraItemCode")
        protected List<String> extraItemCode;

        /**
         * Gets the value of the extraItemCode property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the extraItemCode property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getExtraItemCode().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getExtraItemCode() {
            if (extraItemCode == null) {
                extraItemCode = new ArrayList<String>();
            }
            return this.extraItemCode;
        }

    }

}
