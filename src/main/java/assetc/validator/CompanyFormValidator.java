/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.validator;

import assetc.model.Company;
import assetc.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Laud.Ochei
 */
@Component
public class CompanyFormValidator implements Validator{
    
        @Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	
	@Autowired
	CompanyService companyService;
        
        
	@Override
	public boolean supports(Class<?> clazz) {
		return Company.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
            
               
		Company company = (Company) target;
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyid", "NotEmpty.addCompany.companyid");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyname", "NotEmpty.addCompany.companyname");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companydesc", "NotEmpty.addCompany.companyorgdesc");
                
                /*
                int companyidstatus = companyService.OrganIDExists(organisation.getOrgid());               
                int companynamestatus = companyService.OrganNameExists(organisation.getOrgname());                
                if (organisation.isNew() == true) // if true then form is for creating a new organisation record 
                 { 
                     if (orgidstatus > 0) { 
                      errors.rejectValue("orgid", "Duplicate.addOrg.orgid");  
                    }
                     if (orgnamestatus > 0) { 
                      errors.rejectValue("orgname", "Duplicate.addOrg.orgname");  
                    }
                 } 
                */
                
                
                
                
                
	}
    
}
