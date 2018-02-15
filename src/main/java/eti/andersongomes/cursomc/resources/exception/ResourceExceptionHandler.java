package eti.andersongomes.cursomc.resources.exception;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import eti.andersongomes.cursomc.services.exceptions.AuthorizationException;
import eti.andersongomes.cursomc.services.exceptions.DataIntegrityException;
import eti.andersongomes.cursomc.services.exceptions.FileException;
import eti.andersongomes.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        StandardError erro = new StandardError(System.currentTimeMillis(),HttpStatus.NOT_FOUND.value(),"não encontrado",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
        StandardError erro = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"Integridade de dados",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
        ValidationError erro = new ValidationError(System.currentTimeMillis(),HttpStatus.UNPROCESSABLE_ENTITY.value(),"Erro de validação",e.getMessage(),request.getRequestURI());
        for (FieldError x : e.getBindingResult().getFieldErrors()){
            erro.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request){
        StandardError erro = new StandardError(System.currentTimeMillis(),HttpStatus.FORBIDDEN.value(),"Acesso negado",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
    }
    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request){
        StandardError erro = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"Erro de arquivo",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request){
        HttpStatus code = HttpStatus.valueOf(e.getStatusCode());
        StandardError erro = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"Erro Amazon service",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(code.value()).body(erro);
    }
    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request){
        StandardError erro = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"Erro Amazon Client",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request){
        StandardError erro = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"Erro Amazon S#",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
