package com.zup.proposta.exceptions;

import com.zup.proposta.proposta.RejectedValueProp;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ErrosDefault {

    private final MessageSource messageSource;

    public ErrosDefault(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<RejectedValueProp> exceptions(MethodArgumentNotValidException exception) {
        List<RejectedValueProp> rejectedValueProps = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(fieldError -> {
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            RejectedValueProp erro = new RejectedValueProp(fieldError.getField(), mensagem);
            rejectedValueProps.add(erro);
        });

        return rejectedValueProps;
    }
}
