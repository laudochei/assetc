/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class Location {
  
    private Integer locationno;
    private String locationid;


    
   
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date co;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sud;
    
    private String craft;
    private String description;
    private String longdescription;
    private String parentname;
    
    
    private String parentcraft;
    private String equipmenttype;	
    private String failurecode;	
    private String systemstatus;	
    private String userstatus;	
    private String criticality;
    
    
    private String planningplant;	
    private String maintenanceplant;	
    private String physicallocation;	
    private String manufacturer;	
    private String partnum;	
    private String modelnum;	
    private String serialnum;	
    private String customfield;
    
    private Boolean HasChildren;
    
    public boolean isNew() {
		return (this.locationno == null);
    }

    public Integer getLocationno() {
        return locationno;
    }

    public void setLocationno(Integer locationno) {
        this.locationno = locationno;
    }

    public String getLocationid() {
        return locationid;
    }

    public void setLocationid(String locationid) {
        this.locationid = locationid;
    }
  
    
    
    
    
    //insert new model data/variaables
    public Date getCo() {
        return co;
    }

    public void setCo(Date co) {
        this.co = co;
    }

    public Date getSud() {
        return sud;
    }

    public void setSud(Date sud) {
        this.sud = sud;
    }

    
    public String getCraft() {
        return craft;
    }

    public void setCraft(String craft) {
        this.craft = craft;
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

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
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

    public String getSystemstatus() {
        return systemstatus;
    }

    public void setSystemstatus(String systemstatus) {
        this.systemstatus = systemstatus;
    }

    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }

    public String getCriticality() {
        return criticality;
    }

    public void setCriticality(String criticality) {
        this.criticality = criticality;
    }

    public String getParentcraft() {
        return parentcraft;
    }

    public void setParentcraft(String parentcraft) {
        this.parentcraft = parentcraft;
    }

    public String getPlanningplant() {
        return planningplant;
    }

    public void setPlanningplant(String planningplant) {
        this.planningplant = planningplant;
    }

    public String getMaintenanceplant() {
        return maintenanceplant;
    }

    public void setMaintenanceplant(String maintenanceplant) {
        this.maintenanceplant = maintenanceplant;
    }

    public String getPhysicallocation() {
        return physicallocation;
    }

    public void setPhysicallocation(String physicallocation) {
        this.physicallocation = physicallocation;
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

    public String getCustomfield() {
        return customfield;
    }

    public void setCustomfield(String customfield) {
        this.customfield = customfield;
    }
    
    public Boolean getHasChildren() {
		return HasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
		HasChildren = hasChildren;
    }

  
@Override
	public String toString() {
		return "Location [locationno=" + locationno + ", locationid=" + locationid 
                                + ", co=" + co + ", sud=" + sud + ", craft=" + craft + ", description=" + description + ", longdescription=" + longdescription + ", parentname=" + parentname
				+ ", equipmenttype=" + equipmenttype + ", failurecode=" + failurecode + ", systemstatus=" + systemstatus + ", userstatus=" + userstatus + ", criticality=" + criticality + ", parentcraft=" + parentcraft
                                + ", planningplant=" + planningplant + ", maintenanceplant=" + maintenanceplant + ", physicallocation=" + physicallocation + ", manufacturer=" + manufacturer + ", partnum=" + partnum + ", modelnum=" + modelnum + ", serialnum=" + serialnum 
                                + ", customfield=" + customfield + "]" + isNew();
	}
 
}