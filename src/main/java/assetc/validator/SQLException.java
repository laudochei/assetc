/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.validator;

/**
 *
 * @author Laud.Ochei
 */
public class SQLException extends Exception{
    private static final long serialVersionUID = 1L;
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public SQLException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public SQLException() {
		super();
	}
}
