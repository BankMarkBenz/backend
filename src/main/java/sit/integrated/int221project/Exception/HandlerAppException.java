package sit.integrated.int221project.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class HandlerAppException {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> handleException(RequestException e){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ResponseException re = new ResponseException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Asia/Bangkok"))
        );
        return new ResponseEntity<>(re,notFound);
    }

}
