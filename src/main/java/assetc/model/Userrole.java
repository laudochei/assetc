/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.model;

/**
 *
 * @author laud.ochei
 */
public class Userrole {
    
  private Integer userroleid;
  private String username;
  private String role;
  
  
  public boolean isNew() {
		return (this.userroleid == null);
    }
  
  

    public Integer getUserroleid() {
        return userroleid;
    }

    public void setUserroleid(Integer userroleid) {
        this.userroleid = userroleid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    @Override
	public String toString() {
		return "Userrole [userroleid=" + userroleid + ", username=" + username + ", role=" + role + "]" + isNew();
	}
 
  
}
