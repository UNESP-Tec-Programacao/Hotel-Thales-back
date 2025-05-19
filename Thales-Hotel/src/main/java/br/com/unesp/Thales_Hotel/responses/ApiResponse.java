package br.com.unesp.Thales_Hotel.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;

    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
