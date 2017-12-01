package assetc.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import assetc.model.Location;
import assetc.model.Task;
import assetc.service.LocationService;
import org.springframework.beans.factory.annotation.Qualifier;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class LocationFormValidator implements Validator {
        
	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	
	@Autowired
	LocationService locationService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Location.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
                                
		Location location = (Location) target;
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "locationid", "NotEmpty.addLocation.locationid");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parentname", "NotEmpty.addLocation.parentname");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.addLocation.description");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "longdescription", "NotEmpty.addLocation.longdescription");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parentcraft", "NotEmpty.addLocation.parentcraft");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "craft", "NotEmpty.addLocation.craft");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "equipmenttype", "NotEmpty.addLocation.equipmenttype");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "failurecode", "NotEmpty.addLocation.failurecode");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "systemstatus", "NotEmpty.addLocation.systemstatus");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userstatus", "NotEmpty.addLocation.userstatus");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "criticality", "NotEmpty.addLocation.criticality");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "co", "NotEmpty.addLocation.co");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sud", "NotEmpty.addLocation.sud");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "planningplant", "NotEmpty.addLocation.planningplant");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maintenanceplant", "NotEmpty.addLocation.maintenanceplant");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "physicallocation", "NotEmpty.addLocation.physicallocation");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "manufacturer", "NotEmpty.addLocation.manufacturer");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "partnum", "NotEmpty.addLocation.partnum");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "modelnum", "NotEmpty.addLocation.modelnum");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "serialnum", "NotEmpty.addLocation.serialnum");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customfield", "NotEmpty.addLocation.customfield");
                
                
                int locationidstatus = locationService.LocationExists(location.getLocationid());
                if (location.isNew() == true) // if true then form is for creating a new location record 
                 { 
                     if (locationidstatus > 0) { 
                        errors.rejectValue("locationid", "Duplicate.addLocation.locationid");  
                 }
                 } 
                else{ // if false then form is for updating existing location record
                     if (locationidstatus < 1) { 
                         errors.rejectValue("locationid", "Absent.addLocation.locationid"); 
                     }
                }
                
		/*
                if(!emailValidator.valid(user.getEmail())){
			errors.rejectValue("email", "Pattern.addUser.email");
		}
		
                if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Diff.addUser.confirmPassword");
		}
                */
               

	}

}