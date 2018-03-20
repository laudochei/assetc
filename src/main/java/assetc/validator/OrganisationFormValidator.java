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
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import assetc.model.Organisation;
import assetc.service.OrganisationService;



@Component
public class OrganisationFormValidator implements Validator {
        
	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	
	@Autowired
	OrganisationService organisationService;
        
        
	@Override
	public boolean supports(Class<?> clazz) {
		return Organisation.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
            
               
		Organisation organisation = (Organisation) target;
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgid", "NotEmpty.addOrg.orgid");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgname", "NotEmpty.addOrg.orgname");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgdesc", "NotEmpty.addOrg.orgdesc");
                               
                int orgidstatus = organisationService.OrganIDExists(organisation.getOrgid());               
                int orgnamestatus = organisationService.OrganNameExists(organisation.getOrgname());                
                if (organisation.isNew() == true) // if true then form is for creating a new organisation record 
                 { 
                     if (orgidstatus > 0) { 
                      errors.rejectValue("orgid", "Duplicate.addOrg.orgid");  
                    }
                     if (orgnamestatus > 0) { 
                      errors.rejectValue("orgname", "Duplicate.addOrg.orgname");  
                    }
                 } 
                
                
                
                
                
                
	}

}