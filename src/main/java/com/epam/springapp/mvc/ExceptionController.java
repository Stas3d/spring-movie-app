package com.epam.springapp.mvc;

import com.epam.springapp.exception.NoAuditoriumException;
import com.epam.springapp.exception.NoEventException;
import com.epam.springapp.exception.NoUserException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

//    @ExceptionHandler(NoEventException.class)
//    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
//    public String catchNoEventException() {
//        return "NO EVENT !";
//    }
//
//    @ExceptionHandler(NoUserException.class)
//    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
//    public String catchNoUserException() {
//        return "NO SUCH USER !";
//    }
//
//    @ExceptionHandler(NoAuditoriumException.class)
//    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
//    public String catchNoAuditoriumException() {
//        return "NO SUCH Auditorium !";
//    }
//

    // Controller Based Exception Handling
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value= HttpStatus.CONFLICT, reason="Data integrity violation")  // 409
//    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", ex);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("error");
//        return mav;
//    }

// this is made for the all application
//    @ControllerAdvice
//    class GlobalControllerExceptionHandler {
//        @ResponseStatus(HttpStatus.CONFLICT)  // 409
//        @ExceptionHandler(DataIntegrityViolationException.class)
//        public void handleConflict() {
//            // Nothing to do
//        }
//    }


    // Errors and REST

    class ErrorInfo {
        public final String url;
        public final String ex;

        public ErrorInfo(String url, Exception ex) {
            this.url = url;
            this.ex = ex.getLocalizedMessage();
        }
    }


//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(Exception.class)
//    @ResponseBody ErrorInfo
//    handleBadRequest(HttpServletRequest req, Exception ex) {
//        return new ErrorInfo(req.getRequestURL(), ex);
//    }




}
