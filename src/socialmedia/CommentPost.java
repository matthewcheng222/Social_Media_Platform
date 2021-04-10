package socialmedia;

public class CommentPost {
    // Private instance variables
    String handle;
    int id;
    String message;
    int commentPostID;

    public CommentPost(String handle, int id, String message) {
        this.handle = handle;
        this.id = id;
        this.message = message;
    }

    // Public getters and setters
    public void setCommentPostID(int commentPostID) {
        this.commentPostID = commentPostID;
    }

    public int getOriginalPostID() {
        return id;
    }

    public String getCommentPostHandle() {
        return handle;
    }

    public int getCommentPostID() {
        return commentPostID;
    }
  
    public String getCommentPostMessage() {
        return message;
    }
}
