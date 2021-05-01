package sit.integrated.int221project.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class ResponseException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}
