package HF21.vo;


import org.springframework.stereotype.Component;

@Component
public class HttpResult<T> {
    private int code;
    private String msg;
    private T data;

    public HttpResult() {
    }

    public HttpResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static <T> HttpResult<T> success(String msg, T data) {
        return new HttpResult<>(200, msg, data);
    }

    public static <T> HttpResult<T> error(String msg) {
        return new HttpResult<>(500, msg, null);
    }

    public static <T> HttpResult<T> of(int code, String msg, T data) {
        return new HttpResult<>(code, msg, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
