package in.himtech.ws.messanger.util;

/**
 * 
 * @author Himanshu 
 *
 * @param <T>
 */
public class RestResponse<T> {

	private String code;

	private String message;

	private T typeObj;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getTypeObj() {
		return typeObj;
	}

	public void setTypeObj(T typeObj) {
		this.typeObj = typeObj;
	}

	@Override
	public String toString() {
		return "RestResponse [code=" + code + ", message=" + message + ", typeObj=" + typeObj + "]";
	}
}
