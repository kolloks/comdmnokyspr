package com.dmnoky.prd.validator;

import com.dmnoky.prd.model.User;
import com.dmnoky.prd.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    private UserService userService;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]"
                    +"+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Field.notEmpty");
        if (user.getUsername().length() < 5 || user.getUsername().length() > 255) {
            errors.rejectValue("username", "Size.userForm.username");
        } else if (this.userService.getUserByName(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Field.notEmpty");
        if (!Pattern.compile(EMAIL_PATTERN).matcher(user.getEmail()).matches()) {
            errors.rejectValue("email", "Incorrect.userFrom.email");
        } else if (this.userService.getUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("email","Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Field.notEmpty");
        if (user.getPassword().length() < 5 || user.getPassword().length() > 255) {
            errors.rejectValue("password", "Size.userForm.password");
        } else if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword","Diff.userForm.passwordConfirm");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Field.notEmpty");
        if (user.getFirstName().length() < 2 || user.getFirstName().length() > 20) {
            errors.rejectValue("firstName", "Size.userForm.firstNameOrSecondName");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "secondName", "Field.notEmpty");
        if (user.getSecondName().length() < 2) {
            errors.rejectValue("secondName", "Size.userForm.firstNameOrSecondName");
        }
    }
}
