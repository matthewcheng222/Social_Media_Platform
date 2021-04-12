package socialmedia;

public class EndorsePost extends SocialMedia {
    // Private instance variables
    String handle;
    int id;
    int endorseID;
    String reference;

    public EndorsePost(String handle, int id) {
        this.handle = handle;
        this.id = id;
    }

    // Public getters and setters
    public void setEndorsePostID(int endorseID) {
        this.endorseID = endorseID;
    } 

    public void setEndorsePostReference(String reference) {
        this.reference = reference;
    }

    public void setOriginalPostID(int id) {
        this.id = id;
    }

    public int getOriginalPostID() {
        return id;
    }
    
    public String getEndorsePostHandle() {
        return handle;
    }

    public int getEndorsePostID() {
        return endorseID;
    }
}
