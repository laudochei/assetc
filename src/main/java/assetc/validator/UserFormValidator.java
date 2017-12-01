package assetc.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import assetc.model.User;
import assetc.service.UserService;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class UserFormValidator implements Validator {
        
	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "NotEmpty.addUser.firstname");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "NotEmpty.addUser.lastname");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.addUser.username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.addUser.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.addUser.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","NotEmpty.addUser.confirmPassword");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.addUser.address");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.addUser.phone");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "NotEmpty.userForm.sex");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.userForm.country");
                
                /*
                int status = userService.UserExists(user.getUsername());
                if (status > 0) { 
                      errors.rejectValue("username", "Duplicate.addUser.username");  
                }
                */
                
                
               int userstatus = userService.UserExists(user.getUsername());
                if (user.isNew() == true) // if true then form is for creating a new user record 
                 { 
                     if (userstatus  > 0) { 
                        errors.rejectValue("username", "Duplicate.addUser.username");  
                 }
                 } 
                else{ // if false then form is for updating existing user record
                     if (userstatus  < 1) { 
                         errors.rejectValue("username", "Absent.addLocation.username"); 
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
                
                
                /*
		if(user.getNumber()==null || user.getNumber()<=0){
			errors.rejectValue("number", "NotEmpty.userForm.number");
		}
		
		if(user.getCountry().equalsIgnoreCase("none")){
			errors.rejectValue("country", "NotEmpty.userForm.country");
		}
		*/
		
		
                /*
		if (user.getFramework() == null || user.getFramework().size() < 2) {
			errors.rejectValue("framework", "Valid.userForm.framework");
		}

		if (user.getSkill() == null || user.getSkill().size() < 3) {
			errors.rejectValue("skill", "Valid.userForm.skill");
		}
                */

	}

}