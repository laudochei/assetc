/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Laud.Ochei
 */
public class Project {
    private Integer projectno;
    private String projectid;
    private String projectname;
    private String companyid;
    private String projectdesc;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date closuredate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationdate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startdate;

    public Date getClosuredate() {
        return closuredate;
    }

    public void setClosuredate(Date closuredate) {
        this.closuredate = closuredate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enddate;
     
    private String financecode;
    private boolean projectcollection;
    private boolean dav;
    private boolean pav;
    private boolean hierarchy;
    private boolean criticality;
    private boolean bom;
    private boolean levelling;
    private boolean in_out;
    
    
    public boolean isNew() {
		return (this.projectno == null);
    }


    public Integer getProjectno() {
        return projectno;
    }

    public void setProjectno(Integer projectno) {
        this.projectno = projectno;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getProjectdesc() {
        return projectdesc;
    }

    public void setProjectdesc(String projectdesc) {
        this.projectdesc = projectdesc;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public String getFinancecode() {
        return financecode;
    }

    public void setFinancecode(String financecode) {
        this.financecode = financecode;
    }

    public boolean isProjectcollection() {
        return projectcollection;
    }

    public void setProjectcollection(boolean projectcollection) {
        this.projectcollection = projectcollection;
    }

    public boolean isDav() {
        return dav;
    }

    public void setDav(boolean dav) {
        this.dav = dav;
    }

    public boolean isPav() {
        return pav;
    }

    public void setPav(boolean pav) {
        this.pav = pav;
    }

    public boolean isHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(boolean hierarchy) {
        this.hierarchy = hierarchy;
    }

    public boolean isCriticality() {
        return criticality;
    }

    public void setCriticality(boolean criticality) {
        this.criticality = criticality;
    }

    public boolean isBom() {
        return bom;
    }

    public void setBom(boolean bom) {
        this.bom = bom;
    }

    public boolean isLevelling() {
        return levelling;
    }

    public void setLevelling(boolean levelling) {
        this.levelling = levelling;
    }

    public boolean isIn_out() {
        return in_out;
    }

    public void setIn_out(boolean in_out) {
        this.in_out = in_out;
    }

    
    
    
    @Override
	public String toString() {
		return "Project [projectno=" + projectno + ", projectid=" + projectid 
                                + ", companyid=" + companyid + ", projectname=" + projectname + ", projectdesc=" + projectdesc + ", creationdate=" + creationdate + ", closuredate=" + closuredate + ", startdate=" + startdate+ ", enddate=" + enddate
				+ ", projectcollection=" + projectcollection + ", dav=" + dav + ", pav=" + pav + ", hierarchy=" + hierarchy + ", criticality=" + criticality + ", levelling=" + levelling + ", bom=" + bom + ", inout=" + in_out
                                + ", financecode=" + financecode + "]" + isNew();
	}
    
   
}
