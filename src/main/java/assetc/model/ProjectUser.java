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
public class ProjectUser {
    private Integer projectuserno;
    private String projectuserid;
    private String projectid;
     private String projectuserdesc;
    private Integer userid;
    
    
    public boolean isNew() {
		return (this.projectuserno == null);
    }


    public Integer getProjectuserno() {
        return projectuserno;
    }

    public void setProjectuserno(Integer projectuserno) {
        this.projectuserno = projectuserno;
    }

    public String getProjectuserid() {
        return projectuserid;
    }

    public void setProjectuserid(String projectuserid) {
        this.projectuserid = projectuserid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectuserdesc() {
        return projectuserdesc;
    }

    public void setProjectuserdesc(String projectuserdesc) {
        this.projectuserdesc = projectuserdesc;
    }
    
   
        @Override
	public String toString() {
		return "ProjectUser [projectuserno=" + projectuserno + ", projectuserid=" + projectuserid 
                                + ", projectid=" + projectid + ", projectuserdesc=" + projectuserdesc
                                + ", userid=" + userid + "]" + isNew();
	}
    
   
}
