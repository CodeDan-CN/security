package cn.codedan.demo.model.dto;

import com.sun.org.apache.regexp.internal.RE;
import lombok.Data;

@Data
public class ResponseReusltDTO<T> {

    private Integer code;

    private String message;

    private T Data;

    public ResponseReusltDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseReusltDTO(Integer code, T data) {
        this.code = code;
        Data = data;
    }

    public ResponseReusltDTO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        Data = data;
    }

    public static ResponseReusltDTO ok() {
        return new ResponseReusltDTO<>(200, "success");
    }

    public static ResponseReusltDTO fail(String message) {
        return new ResponseReusltDTO<>(999, message);
    }
}
