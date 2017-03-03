package com.epam.springapp.mvc;

import com.epam.springapp.exception.NoEventException;
import com.epam.springapp.exception.mvcexception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "service")
public class MultiportController {

    @ResponseBody
    @RequestMapping(value = "/multiport", method = RequestMethod.POST)
    public String removeEventByName(@RequestParam("name") String name,
                                     @RequestParam("file") MultipartFile file) throws NoEventException {
//        if (name == null) throw new OrderNotFoundException();
//        return eventServiceManager.removeEvent(name);
        return "";
    }
}
