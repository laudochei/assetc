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
public class Organisation {
    private Integer orgno;
    private String orgid;
    private String orgname;
    private String orgdesc;
    
    public boolean isNew() {
		return (this.orgno == null);
    }

    public Integer getOrgno() {
        return orgno;
    }

    public void setOrgno(Integer orgno) {
        this.orgno = orgno;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOrgdesc() {
        return orgdesc;
    }

    public void setOrgdesc(String orgdesc) {
        this.orgdesc = orgdesc;
    }
    
    
    @Override
	public String toString() {
		return "Organisation [orgno=" + orgno + ", orgid=" + orgid + ", orgname=" + orgname
                                + ", orgdesc=" + orgdesc + "]" + isNew();
	}
   
}
