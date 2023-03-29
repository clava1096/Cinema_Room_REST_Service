package cinema.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TicketAlreadyPurchasedException.class)
    public ResponseEntity<ErrorResponse> responseTicketAlreadyPurchased(Exception e){
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTockenException.class)
    public ResponseEntity<ErrorResponse> responseWrongTockenException(Exception e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NO_CONTENT.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatOutOfBoundException.class)
    public ResponseEntity<ErrorResponse> responseSeatOutOfBoundException(Exception e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NO_CONTENT.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorResponse> responseWrongPasswordException(Exception e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}