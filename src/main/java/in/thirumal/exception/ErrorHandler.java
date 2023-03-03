/**
 * 
 */
package in.thirumal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Thirumal
 *
 */
@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BadRequestException handleBadRequestException(BadRequestException badRequestException) {
        return badRequestException;
    }
		
	@ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResourceNotFoundException handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return resourceNotFoundException;
    }
	
	@ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public UnAuthorizedException handleResourceUnAuthorizedException(UnAuthorizedException unAuthorizedException) {
        return unAuthorizedException;
    }
	
}
