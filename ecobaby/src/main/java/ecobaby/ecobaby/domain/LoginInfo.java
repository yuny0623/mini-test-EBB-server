package ecobaby.ecobaby.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LoginInfo {
    @Id
    private String id;
    private String password;

    public LoginInfo() {
    }

    public LoginInfo(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
