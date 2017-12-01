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
public class Build {
    
    private Integer buildno;
    private String buildtype;
    
    public boolean isNew() {
		return (this.buildno == null);
    }

    public Integer getBuildno() {
        return buildno;
    }

    public void setBuildno(Integer buildno) {
        this.buildno = buildno;
    }

    public String getBuildtype() {
        return buildtype;
    }

    public void setBuildtype(String buildtype) {
        this.buildtype = buildtype;
    }
    
    
    
    
    @Override
	public String toString() {
		return "Build [buildno=" + buildno 
                                + ", buidtype=" + buildtype + "]" + isNew();
	}
    
}
