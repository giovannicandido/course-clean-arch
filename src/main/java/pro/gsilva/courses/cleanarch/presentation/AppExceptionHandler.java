package pro.gsilva.courses.cleanarch.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pro.gsilva.courses.cleanarch.domain.error.DomainException;
import pro.gsilva.courses.cleanarch.domain.error.GenericErrorException;
import pro.gsilva.courses.cleanarch.presentation.v0.response.error.ProblemDetailFactory;
import pro.gsilva.courses.cleanarch.presentation.v0.response.error.ValidationErrorResponse;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private final GenericErrorException DEFAULT_INTERNAL_SERVER_ERROR = new GenericErrorException();
    private final String DEFAULT_INTERNAL_SERVER_ERROR_LOG_MESSAGE = "Error detected by application. Message ID %s";
    private final ProblemDetailFactory problemDetailFactory;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetail handleException(Exception ex) {
        var problemDetail =  problemDetailFactory.build(DEFAULT_INTERNAL_SERVER_ERROR);
        var logMessage = DEFAULT_INTERNAL_SERVER_ERROR_LOG_MESSAGE
                .formatted(problemDetail.getProperties().get("messageID"));
        log.error(logMessage, ex);
        return problemDetail;
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ProblemDetail> handleDomainException(DomainException ex) {
        ProblemDetail problemDetail = problemDetailFactory.build(ex);
        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var problemDetails =  ProblemDetail.forStatus(HttpStatus.BAD_REQUEST.value());
        problemDetails.setType(URI.create("/problem-details/validation-error"));
        Map<String, Object> properties = extractValidationErrors(ex);
        problemDetails.setProperties(properties);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
              problemDetails
        );
    }

    private static Map<String, Object> extractValidationErrors(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Set<ValidationErrorResponse> errors = new HashSet<>();

        for (FieldError fieldError : fieldErrors) {
            errors.add(new ValidationErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        Map<String, Object> properties = Map.of(
                "parameter",
                ex.getParameter().getParameter().getName(),
                "validations",
                errors
        );
        return properties;
    }
}
