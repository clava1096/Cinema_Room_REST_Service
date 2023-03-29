package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WrongTockenException extends RuntimeException{
    public WrongTockenException(){
        super("Wrong token!");
    }
}
