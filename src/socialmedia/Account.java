package socialmedia;

public class Account extends SocialMedia {
    // private instance variables
    
    private String handle;
    private String description;
    private int id;
    private int endorsedPostCount;

    /**
     * Constructor for initializing accounts.
     * 
     * @param handle account's handle.
     */
    public Account(String handle) {
        this.handle = handle;
    }

    // public getters and setters

    /**
     * The method sets the handle of a specific account.
     * 
     * @param handle account's handle.
     */
    public void setAccountHandle(String handle) {
        this.handle = handle;
    }

    /**
     * The method sets the description of a specific account.
     * 
     * @param description account's description.
     */
    public void setAccountDescription(String description) {
        this.description = description;
    }

    /**
     * The method sets the ID of a specific account.
     * 
     * @param id account's ID.
     */
    public void setAccountID(int id) {
        this.id = id;
    }

    /**
     * The method sets the endorsed post count of a specific account.
     * 
     * @param endorsedPostCount account's endorsement post count.
     */
    public void setEndorsedPostCount(int endorsedPostCount) {
        this.endorsedPostCount = endorsedPostCount;
    }

    /**
     * The method returns the endorsement post count of a specific account.
     * 
     * @return account's endorsement post count.
     */
    public int getEndorsedPostCount() {
        return endorsedPostCount;
    }

    /**
     * The method returns the handle of a specific account.
     * 
     * @return account's handle.
     */
    public String getAccountHandle() {
        return handle;
    }

    /**
     * The method returns the description of a specific account.
     * 
     * @return account's description.
     */
    public String getAccountDescription() {
        return description;
    }

    /**
     * The method returns the ID of a specific account.
     * 
     * @return account's ID.
     */
    public int getAccountID() {
        return id;
    }
}
