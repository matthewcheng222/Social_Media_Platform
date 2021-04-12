package socialmedia;

public class DeletedPost extends SocialMedia {
    // Private instance variables
    int id;
    String message;

    public DeletedPost(int id, String message) {
        this.id = id;
        this.message = message;
    }

    // Public getters and setters
    public int getDeletedPostID() {
        return id;
    }

    public String getDeletedPostMessage() {
        return message;
    }
}
