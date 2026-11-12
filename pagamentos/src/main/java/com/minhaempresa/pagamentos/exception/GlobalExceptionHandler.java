package com.minhaempresa.pagamentos.exception;

import com.minhaempresa.pagamentos.dto.ApiError;
import com.minhaempresa.pagamentos.dto.ApiResponse;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ObjectMapper objectMapper;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            BusinessException ex,
            HttpServletRequest request
    ){
        List<ApiError> errors = List.of(
                ApiError.builder()
                .code(ex.getErrrorCode())
                .mensagem(ex.getErrorMessage())
                .build()
        );

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .errors(errors)
                .traceId(UUID.randomUUID().toString())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            IllegalArgumentException ex,
            HttpServletRequest request
    ) {
        List<ApiError> errors = List.of(
                ApiError.builder()
                        .mensagem(ex.getMessage())
                        .build()
        );

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .errors(errors)
                .traceId(UUID.randomUUID().toString())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiResponse<?>> handleException(
            FeignException ex,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.resolve(ex.status());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        if (ex.responseBody().isPresent()) {
            try {
                byte[] bodyBytes = ex.responseBody().get().array();
                ApiResponse<?> apiResponse = objectMapper.readValue(bodyBytes, ApiResponse.class);

                return ResponseEntity.status(status).body(apiResponse);

            } catch (Exception e) {
                ApiResponse<?> errorFallback = ApiResponse.builder()
                        .success(false)
                        .message("Erro ao processar resposta de erro do serviço externo.")
                        .path(ex.request().url())
                        .build();
                return ResponseEntity.status(status).body(errorFallback);
            }
        }

        ApiResponse<?> noBodyFallback = ApiResponse.builder()
                .success(false)
                .message("O serviço externo não retornou uma resposta válida ou está indisponível.")
                .path(ex.request().url())
                .build();

        return ResponseEntity.status(status).body(noBodyFallback);
    }
}
