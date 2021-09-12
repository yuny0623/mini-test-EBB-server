package ecobaby.ecobaby.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HTTPStatusCode {
    private int status;
    private boolean success;

    @Id
    private String message;

    public HTTPStatusCode(int status, boolean success, String message) {
        this.status = status;
        this.success = success;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
