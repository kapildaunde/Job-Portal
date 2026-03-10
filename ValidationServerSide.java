package com.deepak.backend;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationServerSide {

    private static final String NAME_PATTERN  = "^[a-zA-Z ]{3,30}$";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z-]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,3})$";
    private static final String PASS_PATTERN  = "^[A-Za-z0-9@#_]{6,16}$";

    public boolean nameValidate(String name) {
        if (name == null) return false;
        return Pattern.compile(NAME_PATTERN).matcher(name).matches();
    }

    public boolean emailValidate(String email) {
        if (email == null) return false;
        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    public boolean passwordValidate(String password) {
        if (password == null) return false;
        return Pattern.compile(PASS_PATTERN).matcher(password).matches();
    }
}
