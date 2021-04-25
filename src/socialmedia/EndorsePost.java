package socialmedia;

public class EndorsePost extends SocialMedia {
    // private instance variables
    private String handle;
    private int id;
    private int endorseID;
    private String reference;

    /**
     * Constructor for initialising endorsement posts.
     * 
     * @param handle endorsement post's handle.
     * @param id ID of the original post.
     */
    public EndorsePost(String handle, int id) {
        this.handle = handle;
        this.id = id;
    }

    // public getters and setters

    /**
     * The method sets the ID of the endorsement post.
     * 
     * @param endorseID endorsement post's ID.
     */
    public void setEndorsePostID(int endorseID) {
        this.endorseID = endorseID;
    } 

    /**
     * The method sets the post reference of the endorsement post.
     * 
     * @param reference endorsement post's reference.
     */
    public void setEndorsePostReference(String reference) {
        this.reference = reference;
    }

    /**
     * The method sets the original post ID of the endorsement post.
     * 
     * @param id original post ID of the endorsement post.
     */
    public void setOriginalPostID(int id) {
        this.id = id;
    }

    /**
     * The method returns the original post ID of the endorsement post.
     * 
     * @return original post ID of the endorsement post.
     */
    public int getOriginalPostID() {
        return id;
    }
    
    /**
     * The method returns the handle of the endorsement post.
     * 
     * @return endorsement post's handle.
     */
    public String getEndorsePostHandle() {
        return handle;
    }

    /**
     * The method returns the ID of the endorsement post.
     * 
     * @return endorsement post's ID.
     */
    public int getEndorsePostID() {
        return endorseID;
    }

    /**
     * The method returns the post reference of the endorsement post.
     * 
     * @return endorsement post's reference.
     */
    public String getEndorsePostReference() {
        return reference;
    }
}
