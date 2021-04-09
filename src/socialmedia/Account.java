package socialmedia;

public class Account{
    // Private instance variables
    private String handle;
    private String description;
    private int id;

    public Account(String handle) {
        this.handle = handle;
    }

    // Public getters and setters
    public void setAccountHandle(String handle) {
        this.handle = handle;
    }
    
    public void setAccountDescription(String description) {
        this.description = description;
    }

    public void setAccountID(int id) {
        this.id = id;
    }

    public String getAccountHandle() {
        return handle;
    }

    public String getAccountDescription() {
        return description;
    }

    public int getAccountID() {
        return id;
    }
}
