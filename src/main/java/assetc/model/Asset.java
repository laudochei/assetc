/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.model;

/**
 *
 * @author Laud.Ochei
 */
public class Asset {

private Integer assetno;	
private String assetid;	
private String locationid;	
private String description;
private String longdescription;	
private String equipmenttype;	
private String failurecode;	
private String manufacturer;	
private String partnum;	
private String modelnum;	
private String serialnum;	
private String physicallocation;	
private String customfield;

    public boolean isNew() {
		return (this.assetno == null);
    }

    public Integer getAssetno() {
        return assetno;
    }

    public void setAssetno(Integer assetno) {
        this.assetno = assetno;
    }
 
    
    
    public String getAssetid() {
        return assetid;
    }

    public void setAssetid(String assetid) {
        this.assetid = assetid;
    }

    public String getLocationid() {
        return locationid;
    }

    public void setLocationid(String locationid) {
        this.locationid = locationid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public String getEquipmenttype() {
        return equipmenttype;
    }

    public void setEquipmenttype(String equipmenttype) {
        this.equipmenttype = equipmenttype;
    }

    public String getFailurecode() {
        return failurecode;
    }

    public void setFailurecode(String failurecode) {
        this.failurecode = failurecode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPartnum() {
        return partnum;
    }

    public void setPartnum(String partnum) {
        this.partnum = partnum;
    }

    public String getModelnum() {
        return modelnum;
    }

    public void setModelnum(String modelnum) {
        this.modelnum = modelnum;
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public String getPhysicallocation() {
        return physicallocation;
    }

    public void setPhysicallocation(String physicallocation) {
        this.physicallocation = physicallocation;
    }

    public String getCustomfield() {
        return customfield;
    }

    public void setCustomfield(String customfield) {
        this.customfield = customfield;
    }

    

    
    
    @Override
	public String toString() {
		return "Asset [assetno=" + assetno + ", assetid=" + assetid + ", locationid=" + locationid
                                + ", description=" + description + ", longdescription=" + longdescription 
				+ ", equipmenttype=" + equipmenttype + ", failurecode=" + failurecode 
                                + ", physicallocation=" + physicallocation + ", manufacturer=" + manufacturer + ", partnum=" + partnum + ", modelnum=" + modelnum + ", serialnum=" + serialnum 
                                + ", customfield=" + customfield + "]" + isNew();
	}
    
}
