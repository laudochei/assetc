/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import assetc.model.Asset;
import assetc.service.AssetService;
import assetc.service.LocationService;


//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class BuildFormValidator implements Validator {
        
	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	
	
        @Autowired
	AssetService assetService;
        
        @Autowired
	LocationService locationService;
        
       
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Asset.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
            
               
		Asset asset = (Asset) target;
                
                //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "assetid", "NotEmpty.addAsset.assetid");
                
                
                /*
               int assetidstatus = assetService.AssetExists(asset.getAssetid());
               if (asset.isNew() == true) // if true then form is for creating a new asset record 
                { 
                    if (assetidstatus > 0) { 
                      errors.rejectValue("assetid", "Duplicate.addAsset.assetid");  
                }
                } 
               else{ // if false then form is for updating existing record
                    if (assetidstatus < 1) { 
                        errors.rejectValue("assetid", "Absent.addAsset.assetid"); 
                    }
               }
                
               int locationidstatus = locationService.LocationExists(asset.getLocationid());
                if (locationidstatus < 1) { 
                    errors.rejectValue("locationid", "Absent.addAsset.locationid");  
                }
                
                */
                
                
	}

}