package net.intuit.assignment.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GameException extends Exception{

    public final int code;

    public GameException(String message){
        super(message);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public GameException(Integer code, String message, Throwable cause){
        super(message, cause);
        this.code = code;
    }

    public GameException(Integer code, String message){
        super(message);
        this.code = code;
    }
}

