package socialmedia;

public class Post extends SocialMedia {
    // Private instance variables
    private String handle;
    private String message;
    private int id;

    public Post(String handle, String message) {
        this.handle = handle;
        this.message = message;
    }

    // Public getters and setters
    public void setPostID(int id) {
        this.id = id;
    }

    public String getPostHandle(){
        return handle;
    }
    
    public String getPostMessage(){
        return message;
    }

    public int getPostID() {
        return id;
    }
}

