package enterprises.tanheta.feedbares.model;

public class CommentModel {

    private String userName;

    private String commentary;

    public CommentModel() {}

    public CommentModel(String userName, String commentary) {
        this.userName = userName;
        this.commentary = commentary;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
