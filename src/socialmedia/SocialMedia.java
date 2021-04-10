package socialmedia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SocialMedia implements SocialMediaPlatform {
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
		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(handle)) {
				int id = accounts.get(i).getAccountID();
				String returnHandle = accounts.get(i).getAccountHandle();
				String description = accounts.get(i).getAccountDescription();
				return "ID: " + id + "\nHandle: " + returnHandle + "\nDescription: " 
				+ description + "\nPost count: " + getAccountTotalPostCount(handle) 
				+ "\nEndorse count: ";
			}
		}
		throw new HandleNotRecognisedException();
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

	public Boolean checkPostID(int id) {
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
				return true;
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
						return endorsePosts.get(endorsePosts.size()-1).getEndorsePostID();
					}
				}
				for (int k = 0 ; k < commentPosts.size() ; k++) {
					if (commentPosts.get(k).getCommentPostID() == id) {
						foundPost = true;
						endorsePosts.add(new EndorsePost(handle, id));
						endorsePosts.get(endorsePosts.size()-1).setEndorsePostID(generateUniqueID());
						endorsePosts.get(endorsePosts.size()-1).setEndorsePostReference("EP@" + handle + ": " + commentPosts.get(k).getCommentPostMessage());
						return endorsePosts.get(endorsePosts.size()-1).getEndorsePostID();
					}
				}
			}
		}
		if (checkPostID(id).equals(Boolean.FALSE)) {
			throw new NotActionablePostException();
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			throw new HandleNotRecognisedException();
		}
		if (foundPost.equals(Boolean.FALSE)) {
			throw new HandleNotRecognisedException();
		}
		return 0;
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
						for (int k = 0 ; k < commentPosts.size() ; k++) {
							if (commentPosts.get(k).getCommentPostID() == id && checkPostMessage(message).equals(Boolean.TRUE)) {
								foundPost = true;
								commentPosts.add(new CommentPost(handle, id, message));
								commentPosts.get(commentPosts.size()-1).setCommentPostID(generateUniqueID());
							}
						}
					}
				}
				if (checkPostID(id).equals(Boolean.FALSE)) {
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
		if (deletedPosts.isEmpty()) {
			deletedPosts.add(new DeletedPost(-1, "The original content was removed from the system and is no longer available."));
		}
		for (int i = 0 ; i < posts.size() ; i++) {
			if (posts.get(i).getPostID() == id) {
				for (int j = 0 ; j < endorsePosts.size() ; j++) {
					if (endorsePosts.get(j).getOriginalPostID() == id) {
						endorsePosts.remove(j);
					}
				}
				for (int k = 0 ; k < commentPosts.size() ; k++) {
					if (commentPosts.get(k).getOriginalPostID() == id) {
						commentPosts.get(k).setOriginalPostID(-1);
					}
				}
				posts.remove(i);
				break;
			}
		}
		for (int a = 0 ; a < endorsePosts.size() ; a++) {
			if (endorsePosts.get(a).getEndorsePostID() == id) {
				for (int k = 0 ; k < commentPosts.size() ; k++) {
					if (commentPosts.get(k).getOriginalPostID() == id) {
						commentPosts.get(k).setOriginalPostID(-1);
					}
				}
				endorsePosts.remove(a);
				break;
			}
		}
		for (int z = 0 ; z < commentPosts.size() ; z++) {
			if (commentPosts.get(z).getCommentPostID() == id) {
				for (int j = 0 ; j < endorsePosts.size() ; j++) {
					if (endorsePosts.get(j).getOriginalPostID() == id) {
						endorsePosts.remove(j);
					}
				}
				for (int k = 0 ; k < commentPosts.size() ; k++) {
					if (commentPosts.get(k).getOriginalPostID() == id) {
						commentPosts.get(k).setOriginalPostID(-1);
					}
				}
				commentPosts.remove(z);
				break;
			}
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
		for (int i = 0 ; i < posts.size() ; i++) {
			if (posts.get(i).getPostID() == id) {
				return "ID: " + posts.get(i).getPostID() + "\nAccount: " + posts.get(i).getPostHandle() 
				+ "\nNo. endorsements: " + findNumberOfEndorsements(id) + " | No. comments: " 
				+ findNumberOfComments(id) + "\n" + posts.get(i).getPostMessage();
			}
		}
		for (int j = 0 ; j < commentPosts.size() ; j++) {
			if (commentPosts.get(j).getCommentPostID() == id) {
				return "ID: " + commentPosts.get(j).getCommentPostID() + "\nAccount: " 
				+ commentPosts.get(j).getCommentPostHandle() + "\nNo. endorsements: " 
				+ findNumberOfEndorsements(id) + " | No. comments: " 
				+ findNumberOfComments(id) + "\n" + commentPosts.get(j).getCommentPostMessage();
			}
		}
		throw new PostIDNotRecognisedException();
	}

	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < posts.size() ; i++) {
			if (posts.get(i).getPostID() == id) {
				sb.append("ID: " + posts.get(i).getPostID());
				sb.append("\nAccount: " + posts.get(i).getPostHandle());
				sb.append("\nNo. endorsements: " + findNumberOfEndorsements(id) + " | No. comments: " + findNumberOfComments(id));
				sb.append("\n" + posts.get(i).getPostMessage());
				sb.append("\n|");
				for (int j = 0 ; j < commentPosts.size() ; j++) {
					if (commentPosts.get(j).getOriginalPostID() == id) {
						sb.append("\n| > ID: " + commentPosts.get(j).getCommentPostID());
						sb.append("\n    Account: " + commentPosts.get(j).getCommentPostHandle());
						sb.append("\n    No. endorsements: " + findNumberOfEndorsements(commentPosts.get(j).getCommentPostID()) 
						+ " | No. comments: " + findNumberOfComments(commentPosts.get(j).getCommentPostID()));
						sb.append("\n    " + commentPosts.get(j).getCommentPostMessage());

						for (int k = 0 ; k < commentPosts.size() ; k++) {
							if (commentPosts.get(k).getOriginalPostID() == commentPosts.get(j).getCommentPostID()) {
								sb.append("\n    |");
								sb.append("\n    | > ID: " + commentPosts.get(k).getCommentPostID());
								sb.append("\n        Account: " + commentPosts.get(k).getCommentPostHandle());
								sb.append("\n        No. endorsements: " + findNumberOfEndorsements(commentPosts.get(k).getCommentPostID()) 
								+ " | No. comments: " + findNumberOfComments(commentPosts.get(k).getCommentPostID()));
								sb.append("\n        " + commentPosts.get(k).getCommentPostMessage());
							}
						}
					}
				}
				return sb;
			}
		}
		throw new PostIDNotRecognisedException();
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
		return commentPosts.size();
	}

	public int getMostEndorsedPost() {
		int tempCount;
		int count = 0;
		int temp = 0;
		int mostEndorsedPostID = endorsePosts.get(0).getOriginalPostID();
		for (int i = 0 ; i < endorsePosts.size()-1 ; i++) {
			temp = endorsePosts.get(i).getOriginalPostID();
			tempCount = 0;
			for (int j = 0 ; j < endorsePosts.size() ; j++) {
				if (temp == endorsePosts.get(j).getOriginalPostID()) {
					tempCount++;
				}
				if (tempCount > count) {
					mostEndorsedPostID = temp;
					count = tempCount;
				}
			}
		}
		return mostEndorsedPostID;
	}

	public int accountHandleToID(String handle) throws HandleNotRecognisedException {
		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(handle)) {
				return accounts.get(i).getAccountID();
			}
		}
		throw new HandleNotRecognisedException();
	}

	public String postIDToHandle(int postID) throws AccountIDNotRecognisedException {
		for (int i = 0 ; i < posts.size() ; i++) {
			if (posts.get(i).getPostID() == postID) {
				return posts.get(i).getPostHandle();
			}
		}
	
		for (int j = 0 ; j < commentPosts.size() ; j++) {
			if (commentPosts.get(j).getOriginalPostID() == postID) {
				return commentPosts.get(j).getCommentPostHandle();
			}
		}
		throw new AccountIDNotRecognisedException();
	}
	

	public int getMostEndorsedAccount() {

	}

	public void erasePlatform() {
		posts.clear();
		endorsePosts.clear();
		commentPosts.clear();
		deletedPosts.clear();
		accounts.clear();
	}

	public void savePlatform(String filename) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(filename)) {
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(posts);
			oos.writeObject(endorsePosts);
			oos.writeObject(commentPosts);
			oos.writeObject(deletedPosts);
			oos.writeObject(accounts);
			oos.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		try (FileInputStream fis = new FileInputStream(filename)) {
			ObjectInputStream ois = new ObjectInputStream(fis);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

