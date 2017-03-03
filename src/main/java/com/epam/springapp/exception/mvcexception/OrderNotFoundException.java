package com.epam.springapp.exception.mvcexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Stanislav_Kryzhanovs on 10/20/2016.
 */


// Using HTTP Status Codes
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Order")  // 404
public class OrderNotFoundException extends RuntimeException{

//    public OrderNotFoundException() {
//    }
}
