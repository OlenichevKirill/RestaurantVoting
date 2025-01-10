package com.example.restaurantvoting.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class NoHtmlValidator implements ConstraintValidator<NoHtml, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || Jsoup.isValid(value, Safelist.none());
    }
}
