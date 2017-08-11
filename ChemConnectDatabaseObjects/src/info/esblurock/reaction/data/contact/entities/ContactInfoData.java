package info.esblurock.reaction.data.contact.entities;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;

@PersistenceCapable
public class ContactInfoData  extends DatabaseObject {

	@Persistent
    String identifier;
    
    @Persistent
    String email;

    @Persistent
    String topSite;

    @Persistent
    String hasSite;

    public ContactInfoData() {
    	
    }
	public ContactInfoData(String identifier, String email, String topSite, String webpage) {
		super();
		this.identifier = identifier;
		this.email = email;
		this.topSite = topSite;
		this.hasSite = webpage;
	}
	
	public void fill(String email, String topSite, String webpage) {
		this.email = email;
		this.topSite = topSite;
		this.hasSite = webpage;		
	}
	/*
	public ContactInfoData(String keyset, ContactInfo info) {
		SetOfAttributeValuePairs set = info.getPropertySet(keyset);
		for(AttributeValuePair pair : set) {
			if(pair.getAttributeValue().equals(ContactInfo.emailKey)) {
				this.email = pair.getPropertyValue();
			} else if(pair.getAttributeValue().equals(ContactInfo.phoneKey)) {
				this.phone = pair.getPropertyValue();
			} else if(pair.getAttributeValue().equals(ContactInfo.webpageKey)) {
				this.webpage = pair.getPropertyValue();
			}
		}
	}
	*/
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTopSite() {
		return topSite;
	}
	public void setTopSite(String topSite) {
		this.topSite = topSite;
	}
	public String getWebSite() {
		return hasSite;
	}
	public void setWebSite(String webpage) {
		this.hasSite = webpage;
	}
	public String getIdentifier() {
		return identifier;
	}

	
}
