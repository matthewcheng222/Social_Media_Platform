package socialmedia;

public class Post extends SocialMedia {
    // private instance variables
    private String handle;
    private String message;
    private int id;

    /**
     * Constructor for initializing posts.
     * 
     * @param handle handle of account creating the post.
     * @param message post's message.
     */
    public Post(String handle, String message) {
        this.handle = handle;
        this.message = message;
    }

    /**
     * Constructor for initializing posts.
     * 
     * @param message post's message.
     * @param id post's ID.
     */
    public Post(String message, int id) {
        this.message = message;
        this.id = id;
    }

    // public getters and setters

    /**
     * The method sets the ID of the post.
     * 
     * @param id post's ID.
     */
    public void setPostID(int id) {
        this.id = id;
    }

    /**
     * The method returns the handle of the post.
     * 
     * @return handle of account creating the post.
     */
    public String getPostHandle(){
        return handle;
    }
    
    /**
     * The method returns the message of the post.
     * 
     * @return post's message.
     */
    public String getPostMessage(){
        return message;
    }

    /**
     * The method returns the ID of the post.
     * 
     * @return post's ID.
     */
    public int getPostID() {
        return id;
    }
}

