package github.fushaolei.wpserver.entity;

/**
 * http response
 */
public class Reply<T> {
    private int code;
    private String msg;
    private T data;

    public Reply() {
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


    public void setSuccess(){
        this.setCode(ReplyCode.SUCCESS.getCode());
        this.setMsg(ReplyCode.SUCCESS.getMsg());
    }
    public void setSuccess(T data){
        this.setCode(ReplyCode.SUCCESS.getCode());
        this.setMsg(ReplyCode.SUCCESS.getMsg());
        this.data = data;
    }

    public static <T> Reply<T> success() {
        Reply<T> reply = new Reply<>();
        reply.setCode(ReplyCode.SUCCESS.getCode());
        reply.setMsg(ReplyCode.SUCCESS.getMsg());
        return reply;
    }

    public static <T> Reply<T> success(T data) {
        Reply<T> reply = Reply.success();
        reply.setData(data);
        return reply;
    }

    public static <T> Reply<T> error() {
        Reply<T> reply = new Reply<>();
        reply.setCode(ReplyCode.ERROR.getCode());
        reply.setMsg(ReplyCode.ERROR.getMsg());
        return reply;
    }

    public static <T> Reply<T> error(T data) {
        Reply<T> reply = Reply.success();
        reply.setData(data);
        return reply;
    }

}
