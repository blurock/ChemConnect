package info.esblurock.reaction.data.contact.entities;

import info.esblurock.reaction.data.DatabaseObject;
import info.esblurock.reaction.data.description.DescriptionDataData;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class OrganizationDescriptionData extends DatabaseObject {
    @Persistent
    String identifier;
    
    @Persistent
    String organization;
    @Persistent
    String subOrganizationOf;
    @Persistent
    String OrganizationalUnit;
    @Persistent
    String classification;
    
    @Persistent(dependent = "true")
    DescriptionDataData description;
    
    @Persistent(dependent = "true")
    ContactInfoData contactinfo;
    
    @Persistent(dependent = "true")
    ContactLocationData location;

    public OrganizationDescriptionData() {
    }
	public OrganizationDescriptionData(
			String identifier,
			String organization,
			String subOrganizationOf,
			String OrganizationalUnit,
			String classification,
			DescriptionDataData description,
			ContactInfoData contactinfo, 
			ContactLocationData location) {
		super();
		this.identifier = identifier;
		this.organization = organization;
		this.subOrganizationOf = subOrganizationOf;
		this.OrganizationalUnit = OrganizationalUnit;
		this.classification = classification;
		this.description = description;
		this.contactinfo = contactinfo;
		this.location = location;
	}
	public DescriptionDataData getDescription() {
		return description;
	}
	public void setDescription(DescriptionDataData description) {
		this.description = description;
	}
	public ContactInfoData getContactinfo() {
		return contactinfo;
	}
	public void setContactinfo(ContactInfoData contactinfo) {
		this.contactinfo = contactinfo;
	}
	public ContactLocationData getLocation() {
		return location;
	}
	public void setLocation(ContactLocationData location) {
		this.location = location;
	}
	public String getOrganizationtype() {
		return classification;
	}
	public String getOrganization() {
		return organization;
	}
	public String getSubOrganizationOf() {
		return subOrganizationOf;
	}
	public String getOrganizationalUnit() {
		return OrganizationalUnit;
	}
	public String getIdentifier() {
		return identifier;
	}
	
}
