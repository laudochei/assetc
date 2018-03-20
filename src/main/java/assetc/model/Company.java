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
public class Company {
    
    private Integer companyno;
    private String companyid;
    private String companyname;
    private String companydesc;
    private String orgid;
    private String createdby;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdon;
    
    
    
    public boolean isNew() {
        return (this.companyno == null);
    }


    public Integer getCompanyno() {
        return companyno;
    }

    public void setCompanyno(Integer companyno) {
        this.companyno = companyno;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanydesc() {
        return companydesc;
    }

    public void setCompanydesc(String companydesc) {
        this.companydesc = companydesc;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    
    
    
    
    @Override
	public String toString() {
		return "Company [compnayno=" + companyno + ", orgid=" + orgid + ", companyid=" + companyid 
                                + ", companyname=" + companyname 
                                + ", createdby=" + createdby + "]" + isNew();
	}
    
    
}
