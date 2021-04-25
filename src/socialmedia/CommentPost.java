package socialmedia;

public class CommentPost extends SocialMedia {
    // private instance variables

    private String handle;
    private int id;
    private String message;
    private int commentPostID;

    /**
     * Constructor for initializing commentPosts. 
     * 
     * @param handle account's handle.
     * @param id original post's ID.
     * @param message comment post's message.
     */
    public CommentPost(String handle, int id, String message) {
        this.handle = handle;
        this.id = id;
        this.message = message;
    }

    // public getters and setters
    
    /**
     * The method sets the ID of a specific comment post.
     * 
     * @param commentPostID comment post's ID.
     */
    public void setCommentPostID(int commentPostID) {
        this.commentPostID = commentPostID;
    }

    /**
     * The method sets the original post's ID of a specific comment post.
     * 
     * @param id original post's ID.
     */
    public void setOriginalPostID(int id) {
        this.id = id;
    }

    /**
     * The method returns the original post's ID of a specific comment post.
     * 
     * @return comment post's original post ID.
     */
    public int getOriginalPostID() {
        return id;
    }

    /**
     * The method returns the handle of a specific comment post.
     * 
     * @return comment post's handle.
     */
    public String getCommentPostHandle() {
        return handle;
    }

    /**
     * The method returns the ID of a specific comment post.
     * 
     * @return comment post's ID.
     */
    public int getCommentPostID() {
        return commentPostID;
    }
    
    /**
     * The method returns the message of a specific comment post.
     * 
     * @return comment post's message.
     */
    public String getCommentPostMessage() {
        return message;
    }
}
