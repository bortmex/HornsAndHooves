package ru.javaproject.hornsandhooves.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import ru.javaproject.hornsandhooves.util.ValidationUtil;
import ru.javaproject.hornsandhooves.util.exception.ApplicationException;
import ru.javaproject.hornsandhooves.util.exception.ErrorType;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalControllerExceptionHandler{
    private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

/*    @ExceptionHandler({NoHandlerFoundException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorInfo noHandlerFoundException(ApplicationException ex) {

        int code = 1000;
        String message = "No handler found for your request.";
        return new ErrorInfo(code, message);
    }*/

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView applicationErrorHandler(HttpServletRequest req, ApplicationException appEx) throws Exception {
        return getView(req, appEx, appEx.getType(), appEx.getMsgCode(), appEx.getHttpStatus());
    }

    public ModelAndView getView(HttpServletRequest req, Exception e, ErrorType type, String msg, HttpStatus httpStatus) throws Exception {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        log.error("Exception at request " + req.getRequestURL(), rootCause);
        ModelAndView mav = new ModelAndView("exception/exception");
        mav.setStatus(httpStatus);
        mav.addObject("typeMessage", type.getErrorCode());
        mav.addObject("exception", rootCause);
        mav.addObject("message", msg != null ? msg : rootCause.toString());
        return mav;
    }
}
