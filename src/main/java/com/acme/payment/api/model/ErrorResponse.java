package com.acme.payment.api.model;

import java.util.ArrayList;
import java.util.List;


public class ErrorResponse 
{

    private List<String> errors = new ArrayList<String>();

   
    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}