package net.intuit.assignment.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GameException extends Exception{

    public final HttpStatus code;

    public GameException(String message){
        super(message);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public GameException(HttpStatus code, String message, Throwable cause){
        super(message, cause);
        this.code = code;
    }

    public GameException(HttpStatus code, String message){
        super(message);
        this.code = code;
    }
}

