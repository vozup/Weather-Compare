package com.vozup.weathercompare.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.Serializable;

public class StringResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private String result;

    public StringResult(String result) {
        super();
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
