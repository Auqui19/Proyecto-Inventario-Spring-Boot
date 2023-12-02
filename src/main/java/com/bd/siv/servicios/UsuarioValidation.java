package com.bd.siv.servicios;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bd.siv.modelo.Usuario;

public class UsuarioValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombreusuario", "NotEmpty.usuario.nombreusuario");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario", "NotEmpty.usuario.usuario");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.usuario.password");

	}

}
