package ecobaby.ecobaby.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member implements Comparable<Member>{

    @Id
    private String id;
    private String password;
    private int score; //점수

    public Member(String id, String password, int score) {
        this.id = id;
        this.password = password;
        this.score = score;
    }

    public Member() {
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


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Member m) {
        if(this.getScore() > m.getScore())
            return 1;
        else if(this.getScore() == m.getScore()){
            return 1;
        }
        return -1;
    }
}
