package au.com.scds.dss.dex.model;

import au.com.scds.dss.dex.model.code.State;

public class ResidentialAddress {
	// Not mandatory.
	// If element is present in the XML, then a value must be provided.
	private String AddressLine1;// Unit 1
	// Not mandatory.
	// If element is present in the XML, then a value must be provided.
	private String AddressLine2;// 3 xyz street
	// Mandatory.
	private String Suburb; // Sydney
	// Mandatory.
	private State StateCode; // NSW
	// Mandatory and must be 4 numerical characters.
	private Integer Postcode; // 2905

	public String getAddressLine1() {
		return AddressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		AddressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return AddressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		AddressLine2 = addressLine2;
	}

	public String getSuburb() {
		return Suburb;
	}

	public void setSuburb(String suburb) {
		Suburb = suburb;
	}

	public State getStateCode() {
		return StateCode;
	}

	public void setStateCode(State stateCode) {
		StateCode = stateCode;
	}

	public Integer getPostcode() {
		return Postcode;
	}

	public void setPostcode(Integer postcode) {
		Postcode = postcode;
	}
}
