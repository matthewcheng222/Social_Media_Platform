package socialmedia;

import java.io.IOException;

public class SocialMedia implements SocialMediaPlatform {

	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void removeAccount(int id) throws AccountIDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	public void removeAccount(String handle) throws HandleNotRecognisedException {
		// TODO Auto-generated method stub

	}

	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
		// TODO Auto-generated method stub

	}

	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		// TODO Auto-generated method stub

	}

	public String showAccount(String handle) throws HandleNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deletePost(int id) throws PostIDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumberOfAccounts() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTotalOriginalPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTotalEndorsmentPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTotalCommentPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMostEndorsedPost() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMostEndorsedAccount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void erasePlatform() {
		// TODO Auto-generated method stub

	}

	public void savePlatform(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}
}
