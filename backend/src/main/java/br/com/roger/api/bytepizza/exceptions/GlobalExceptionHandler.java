package br.com.roger.api.bytepizza.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RecursoNaoEncontrado.class)
    public ProblemDetail recursoNaoEncontrado(RecursoNaoEncontrado ex) {
        ProblemDetail problema = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problema.setTitle("Recurso não encontrado");
        return problema;
    }

    @ExceptionHandler(RegrasDeNegocioException.class)
    public ProblemDetail regraDeNegocio(RegrasDeNegocioException ex) {
        ProblemDetail problema = ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(422), ex.getMessage());
        problema.setTitle("Regra de negócio violada");
        return problema;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail erroInesperado(Exception ex) {
        log.error("Erro inesperado", ex);
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro inesperado. Tente novamente!"
        );
        problema.setTitle("Erro interno");
        return problema;
    }
}
