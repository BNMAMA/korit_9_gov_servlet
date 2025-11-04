package com.korit.servlet_study.ch05;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Servlet {


    public void doget(Request req, Response response) {

    }

    public void doPost(Request req, Response response) {

    }
}
