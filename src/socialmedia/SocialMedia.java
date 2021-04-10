package socialmedia;

import java.io.IOException;
import java.util.ArrayList;

public class SocialMedia implements SocialMediaPlatform {
    ArrayList<Account> accounts = new ArrayList<>();
	ArrayList<Post> posts = new ArrayList<>();
	ArrayList<EndorsePost> endorsePosts = new ArrayList<>();
	ArrayList<CommentPost> commentPosts = new ArrayList<>();
	ArrayList<DeletedPost> deletedPosts = new ArrayList<>();

    public Boolean checkHandleFormat(String handle) {
		Boolean correctFormat;
        if (handle.length() > 30 || handle.contains(" ")) {
            correctFormat = false;
        }
        else {
            correctFormat = true;
        }
		return correctFormat;
    }

    public Boolean checkHandleExists(String handle) {
		Boolean exists = false;
		
        for (int i = 0 ; i < accounts.size() ; i++) {
            if (accounts.get(i).getAccountHandle().equals(handle)) {
                exists = true;
				break;
            }
        }
		return exists;
    }


	public int generateUniqueID() {
		int id = 1;
		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountID() == id) {
				id++;
			}
			for (int k = 0 ; k < posts.size() ; k++) {
				if (posts.get(k).getPostID() == id) {
					id++;
				}
				for (int m = 0 ; m < endorsePosts.size() ; m++) {
					if (endorsePosts.get(m).getEndorsePostID() == id) {
						id++;
					}
					for (int o = 0 ; o < commentPosts.size() ; o++) {
						if(commentPosts.get(o).getCommentPostID() == id) {
							id++;
						}
					}
				}
			}
		}
		return id;
	}

	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
		if (checkHandleFormat(handle).equals(Boolean.TRUE) && checkHandleExists(handle).equals(Boolean.FALSE)) {
			accounts.add(new Account(handle));
			accounts.get(accounts.size()-1).setAccountID(generateUniqueID());
		}
		else if (checkHandleFormat(handle).equals(Boolean.FALSE)) {
			throw new InvalidHandleException();
		}
		else if (checkHandleExists(handle).equals(Boolean.TRUE)) {
			throw new IllegalHandleException();
		}
		return accounts.get(accounts.size()-1).getAccountID();
	}

	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
		if (checkHandleFormat(handle).equals(Boolean.TRUE) && checkHandleExists(handle).equals(Boolean.FALSE)) {
			accounts.add(new Account(handle));
			accounts.get(accounts.size()-1).setAccountID(generateUniqueID());
			accounts.get(accounts.size()-1).setAccountDescription(description);
		}
		else if (checkHandleFormat(handle).equals(Boolean.FALSE)) {
			throw new InvalidHandleException();
		}
		else if (checkHandleExists(handle).equals(Boolean.TRUE)) {
			throw new IllegalHandleException();
		}
		return accounts.get(accounts.size()-1).getAccountID();
	}

	public void removeAccount(int id) throws AccountIDNotRecognisedException {
		Boolean foundAccount = false;
		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountID() == id) {
				foundAccount = true;
				accounts.remove(i);
				break;
			}
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			throw new AccountIDNotRecognisedException();
		}
	}

	public void removeAccount(String handle) throws HandleNotRecognisedException {
		Boolean foundAccount = false;
		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(handle)) {
				foundAccount = true;
				accounts.remove(i);
				break;
			}
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			throw new HandleNotRecognisedException();
		}
	}

	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
		Boolean foundAccount = false;
		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(oldHandle)) {
				foundAccount = true;
				if (checkHandleFormat(newHandle).equals(Boolean.TRUE) && checkHandleExists(newHandle).equals(Boolean.FALSE)) {
					accounts.get(i).setAccountHandle(newHandle);
				}
				else if (checkHandleFormat(newHandle).equals(Boolean.FALSE)) {
					throw new InvalidHandleException();
				}
				else if (checkHandleExists(newHandle).equals(Boolean.TRUE)) {
					throw new IllegalHandleException();
				}
			}
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			throw new HandleNotRecognisedException();
		}
	}

	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		Boolean foundAccount = false;
		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(handle)) {
				foundAccount = true;
				accounts.get(i).setAccountDescription(description);
			}
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			throw new HandleNotRecognisedException();
		}
	}

	public int getAccountOriginalPostCount(String handle) {
		int count = 0;
		for (int i = 0 ; i < posts.size() ; i++) {
			if (posts.get(i).getPostHandle().equals(handle)) {
				count++;
			}
		}
		return count;
	}

	public int getAccountEndorsePostCount(String handle) {
		int count = 0;
		for (int i = 0 ; i < endorsePosts.size() ; i++) {
			if (endorsePosts.get(i).getEndorsePostHandle().equals(handle)) {
				count++;
			}
		}
		return count;
	}

	public int getAccountCommentPostCount(String handle) {
		int count = 0;
		for (int i = 0 ; i < commentPosts.size() ; i++) {
			if (commentPosts.get(i).getCommentPostHandle().equals(handle)) {
				count++;
			}
		}
		return count;
	}

	public int getAccountTotalPostCount(String handle) {
		return getAccountOriginalPostCount(handle) + getAccountEndorsePostCount(handle) + getAccountCommentPostCount(handle);
	}

	public String showAccount(String handle) throws HandleNotRecognisedException {
		Boolean foundAccount = false;

		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(handle)) {
				foundAccount = true;
				int ID = accounts.get(i).getAccountID();
				String returnHandle = accounts.get(i).getAccountHandle();
				String description = accounts.get(i).getAccountDescription();
				return "ID: " + ID + "\nHandle: " + returnHandle + "\nDescription: " + description + "\nPost count: " + getAccountTotalPostCount(handle) + "\nEndorse count: ";
			}
			
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			throw new HandleNotRecognisedException();
		}
		return null;
	}

	public Boolean checkPostMessage(String message) {
		Boolean validPostMessage;
		if (message.length() > 100 || message.length() == 0) {
			validPostMessage = false;
		}
		else {
			validPostMessage = true;
		}
		return validPostMessage;
	}

	public Boolean checkPostIDOriginal(int id) {
		for (int i = 0 ; i < posts.size() ; i++) {
			if (posts.get(i).getPostID() == id) {
				return true;
			}
		}
		for (int j = 0 ; j < endorsePosts.size() ; j++) {
			if (endorsePosts.get(j).getEndorsePostID() == id) {
				break;
			}
		}
		for (int k = 0 ; k < commentPosts.size() ; k++) {
			if (commentPosts.get(k).getCommentPostID() == id) {
				break;
			}
		}
		return false;
	}

	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
		Boolean foundAccount = false;

		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(handle) && checkPostMessage(message).equals(Boolean.TRUE)) {
				foundAccount = true;
				posts.add(new Post(handle, message));
				posts.get(posts.size()-1).setPostID(generateUniqueID());
			}
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			throw new HandleNotRecognisedException();
		}
		return posts.get(posts.size()-1).getPostID();
	}

	public int endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		Boolean foundAccount = false;
		Boolean foundPost = false;

		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(handle)) {
				foundAccount = true;
				for (int k = 0 ; k < posts.size() ; k++) {
					if (posts.get(k).getPostID() == id) {
						foundPost = true;
						endorsePosts.add(new EndorsePost(handle, id));
						endorsePosts.get(endorsePosts.size()-1).setEndorsePostID(generateUniqueID());
						endorsePosts.get(endorsePosts.size()-1).setEndorsePostReference("EP@" + handle + ": " + posts.get(k).getPostMessage());
					}
				}
			}
		}
		if (checkPostIDOriginal(id).equals(Boolean.FALSE)) {
			throw new NotActionablePostException();
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			throw new HandleNotRecognisedException();
		}
		if (foundPost.equals(Boolean.FALSE)) {
			throw new HandleNotRecognisedException();
		}	
		return endorsePosts.get(endorsePosts.size()-1).getEndorsePostID();
	}

	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
				Boolean foundAccount = false;
				Boolean foundPost = false;
		
				for (int i = 0 ; i < accounts.size() ; i++) {
					if (accounts.get(i).getAccountHandle().equals(handle)) {
						foundAccount = true;
						for (int k = 0 ; k < posts.size() ; k++) {
							if (posts.get(k).getPostID() == id && checkPostMessage(message).equals(Boolean.TRUE)) {
								foundPost = true;
								commentPosts.add(new CommentPost(handle, id, message));
								commentPosts.get(commentPosts.size()-1).setCommentPostID(generateUniqueID());
							}
						}
					}
				}
				if (checkPostIDOriginal(id).equals(Boolean.FALSE)) {
					throw new NotActionablePostException();
				}
				if (foundAccount.equals(Boolean.FALSE)) {
					throw new HandleNotRecognisedException();
				}
				if (foundPost.equals(Boolean.FALSE)) {
					throw new PostIDNotRecognisedException();
				}
				if (checkPostMessage(message).equals(Boolean.FALSE)) {
					throw new InvalidPostException();
				}
				return commentPosts.get(commentPosts.size()-1).getCommentPostID();
	}

	public void deletePost(int id) throws PostIDNotRecognisedException {
		if (deletedPosts.size() > 0) {

		}
		else {
			deletedPosts.add(new DeletedPost(-1, "The original content was removed from the system and is no longer available."));
		}
	}

	public int findNumberOfEndorsements(int id) {
		int endorsementCount = 0;
		for (int i = 0 ; i < endorsePosts.size() ; i++) {
			if (endorsePosts.get(i).getOriginalPostID() == id) {
				endorsementCount++;
			}
		}
		return endorsementCount;
	}

	public int findNumberOfComments(int id) {
		int commentCount = 0;
		for (int i = 0 ; i < commentPosts.size() ; i++) {
			if (commentPosts.get(i).getOriginalPostID() == id) {
				commentCount++;
			}
		}
		return commentCount;
	}

	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		Boolean foundPost = false;
		for (int i = 0 ; i < posts.size() ; i++) {
			if (posts.get(i).getPostID() == id) {
				foundPost = true;
				return "ID: " + posts.get(i).getPostID() + "\nAccount: " + posts.get(i).getPostHandle() 
				+ "\nNo. endorsements: " + findNumberOfEndorsements(id) + " | No. comments: " 
				+ findNumberOfComments(id) + "\n" + posts.get(i).getPostMessage();
			}
		}
		if (foundPost.equals(Boolean.FALSE)) {
			throw new PostIDNotRecognisedException();
		}
		return null;
	}

	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public int getTotalOriginalPosts() {
		return posts.size();
	}

	public int getTotalEndorsmentPosts() {
		return endorsePosts.size();
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

