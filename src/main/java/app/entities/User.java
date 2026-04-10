package app.entities;

public class User {
    int userID;
    String username;
    String password;
    Role role;
    int balance;

    public User(int userID, String username, String password, Role role, int balance) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Role getRolle() {
        return role;
    }

    public void setRolle(Role role) {
        this.role = role;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
