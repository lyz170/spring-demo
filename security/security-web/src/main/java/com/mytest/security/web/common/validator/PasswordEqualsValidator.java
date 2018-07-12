package com.mytest.security.web.common.validator;

import com.mytest.security.web.sec01.model.SEC01Form;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Administrator on 2017/12/23.
 */
public class PasswordEqualsValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return SEC01Form.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasFieldErrors("password")) {
            return;
        }
        SEC01Form form = (SEC01Form) o;
        String password = form.getPassword();
        String passwordConfirm = form.getPasswordConfirm();
        if (!password.equals(passwordConfirm)) {
            errors.rejectValue("password",
                    "PasswordEqualsValidator.passwordResetForm.password",
                    "password and confirm password must be same.");
        }
    }
}
