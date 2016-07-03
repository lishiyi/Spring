package info.shiyi;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = -6463052236469808931L;

    private Integer id;
    private String firstName;
    private String lastName;
    private Double money;

    // setter/getter..

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}