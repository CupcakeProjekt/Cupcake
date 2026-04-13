package app.entities;
//User
public class User {
    int userID;
    String email;
    String password;
    Role role;
    int balance;

    public User(int userID, String email, Role role, int balance) {
        this.userID = userID;
        this.email = email;
        this.role = role;
        this.balance = balance;
    }

    public User(int userID, String email, String password, Role role, int balance) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.role = role;
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
