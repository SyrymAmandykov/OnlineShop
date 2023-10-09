package domain.users;

import domain.order.Order;
import domain.role.Role;

public class Users {

    private Long id;
    private String email;
    private String password;
    private String fullName;
    private Integer phoneNumber;
    private Role role;

    public Users(Long id, String email, String password, String fullName, Integer phoneNumber, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        if (fullName == null){
            throw new IllegalArgumentException("user full name must be not null");
        } else {
            this.fullName = fullName;
        }
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
