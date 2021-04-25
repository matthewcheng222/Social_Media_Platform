package socialmedia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SocialMedia implements SocialMediaPlatform {
	// declaring arraylist objects accounts, posts, endorsePosts and commentPosts.
	ArrayList<Account> accounts = new ArrayList<>();
	ArrayList<Post> posts = new ArrayList<>();
	ArrayList<EndorsePost> endorsePosts = new ArrayList<>();
	ArrayList<CommentPost> commentPosts = new ArrayList<>();

	/**
	 * The method checks whether the passed string handle has the correct format.
	 * 
	 * @param handle account's handle.
	 * @return true if the format of the account's handle is correct.
	 */
    public Boolean checkHandleFormat(String handle) {
		Boolean correctFormat;
		// setting correctFormat to false if the handle has more than 30 characters or has white spaces.
        if (handle.length() > 30 || handle.contains(" ")) {
            correctFormat = false;
        }
		// setting correctFormat to true if the format of the handle is not incorrect.
        else {
            correctFormat = true;
        }
		return correctFormat;
    }

	/**
	 * The method checks whether the passed string handle exists.
	 * 
	 * @param handle account's handle.
	 * @return true if the account handle exists.
	 */
    public Boolean checkHandleExists(String handle) {
		// setting exists to be false by default.
		Boolean exists = false;
        for (int i = 0 ; i < accounts.size() ; i++) {
            if (accounts.get(i).getAccountHandle().equals(handle)) {
				// setting exists to true if handle exists.
                exists = true;
				// breaking the loop if the handle is found.
				break;
            }
        }
		return exists;
	}

	/**
	 * The method generates a unique ID.
	 * 
	 * @return the unique ID.
	 */
	public int generateUniqueID() {
		// the unique ID starts from 1.
		int id = 1;
		for (int i = 0 ; i < accounts.size() ; i++) {
			// if the ID exists in account's ID, increment ID.
			if (accounts.get(i).getAccountID() == id) {
				id++;
			}
			for (int k = 0 ; k < posts.size() ; k++) {
				// if the ID exists in post's ID, increment ID.
				if (posts.get(k).getPostID() == id) {
					id++;
				}
				for (int m = 0 ; m < endorsePosts.size() ; m++) {
					// if the ID exists in endorsepost's ID, increment ID.
					if (endorsePosts.get(m).getEndorsePostID() == id) {
						id++;
					}
					for (int o = 0 ; o < commentPosts.size() ; o++) {
						// if the ID exists in commentpost's ID, increment ID.
						if(commentPosts.get(o).getCommentPostID() == id) {
							id++;
						}
					}
				}
			}
		}
		return id;
	}

	@Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
		// checking whether the format of the handle is correct and handle does not exist.
		if (checkHandleFormat(handle).equals(Boolean.TRUE) && checkHandleExists(handle).equals(Boolean.FALSE)) {
			// adding a new Account object into accounts arraylist.
			accounts.add(new Account(handle));
			// setting the ID of the created account by calling function generateUniqueID().
			accounts.get(accounts.size()-1).setAccountID(generateUniqueID());
		}
		else if (checkHandleFormat(handle).equals(Boolean.FALSE)) {
			// throw InvalidHandleException if the handle format is incorrect.
			throw new InvalidHandleException();
		}
		else if (checkHandleExists(handle).equals(Boolean.TRUE)) {
			// throw InvalidHandleException if the handle already exists.
			throw new IllegalHandleException();
		}
		// returning the ID of the account created.
		return accounts.get(accounts.size()-1).getAccountID();
	}

	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
		// checking whether the format of the handle is correct and handle does not exist.
		if (checkHandleFormat(handle).equals(Boolean.TRUE) && checkHandleExists(handle).equals(Boolean.FALSE)) {
			// adding a new Account object into accounts arraylist.
			accounts.add(new Account(handle));
			// setting the ID of the created account by calling function generateUniqueID().
			accounts.get(accounts.size()-1).setAccountID(generateUniqueID());
			// setting the description of the created account. 
			accounts.get(accounts.size()-1).setAccountDescription(description);
		}
		else if (checkHandleFormat(handle).equals(Boolean.FALSE)) {
			// throw InvalidHandleException if the handle format is incorrect.
			throw new InvalidHandleException();
		}
		else if (checkHandleExists(handle).equals(Boolean.TRUE)) {
			// throw InvalidHandleException if the handle already exists.
			throw new IllegalHandleException();
		}
		// returning the ID of the account created.
		return accounts.get(accounts.size()-1).getAccountID();
	}

	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {
		// setting foundAccount to false by default.
		Boolean foundAccount = false;
		for (int i = 0 ; i < accounts.size() ; i++) {
			// finding the account with a given ID.
			if (accounts.get(i).getAccountID() == id) {
				// setting foundAccount to true when the account is found.
				foundAccount = true;
				// removing the posts assicoated with the account.
				for (int j = 0 ; j < posts.size() ; j++) {
					if (posts.get(j).getPostHandle().equals(accounts.get(i).getAccountHandle())) {
						posts.remove(j);
					}
				}
				// removing the endorsement posts associated with the account
				for (int k = 0 ; k < endorsePosts.size() ; k++) {
					if (endorsePosts.get(k).getEndorsePostHandle().equals(accounts.get(i).getAccountHandle())) {
						endorsePosts.remove(k);
					}
				}
				// removing the comment posts associated with the account.
				for (int l = 0 ; l < commentPosts.size() ; l++) {
					if (commentPosts.get(l).getCommentPostHandle().equals(accounts.get(i).getAccountHandle())) {
						commentPosts.remove(l);
					}
				}
				// removing the account after removing posts associated with the account.
				accounts.remove(i);
				// breaking the loop since the account is already removed.
				break;
			}
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			// throw AccountIDNotRecognisedException if the account with such ID is not found.
			throw new AccountIDNotRecognisedException();
		}
	}

	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		// setting foundAccount to false by default.
		Boolean foundAccount = false;
		// finding the account with a given handle.
		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(handle)) {
				// setting foundAccount to true when the account is found.
				foundAccount = true;
				// removing the posts assicoated with the account.
				for (int j = 0 ; j < posts.size() ; j++) {
					if (posts.get(j).getPostHandle().equals(handle)) {
						posts.remove(j);
					}
				}
				// removing the endorsement posts associated with the account
				for (int k = 0 ; k < endorsePosts.size() ; k++) {
					if (endorsePosts.get(k).getEndorsePostHandle().equals(handle)) {
						endorsePosts.remove(k);
					}
				}
				// removing the comment posts associated with the account.
				for (int l = 0 ; l < commentPosts.size() ; l++) {
					if (commentPosts.get(l).getCommentPostHandle().equals(handle)) {
						commentPosts.remove(l);
					}
				}
				// removing the account after removing posts associated with the account.
				accounts.remove(i);
				// breaking the loop since the account is already removed.
				break;
			}
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			// throw HandleNotRecognisedException if the account with such handle is not found
			throw new HandleNotRecognisedException();
		}
	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
		// setting foundAccount to false by default.
		Boolean foundAccount = false;
		// finding the account with a given old handle.
		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(oldHandle)) {
				// setting foundAccount to true when the account is found.
				foundAccount = true;
				// checking whether the format of the new handle is correct and the handle does not exist.
				if (checkHandleFormat(newHandle).equals(Boolean.TRUE) && checkHandleExists(newHandle).equals(Boolean.FALSE)) {
					// setting the handle of the account with the new handle.
					accounts.get(i).setAccountHandle(newHandle);
				}
				else if (checkHandleFormat(newHandle).equals(Boolean.FALSE)) {
					// throw InvalidHandleException if the handle format is incorrect.
					throw new InvalidHandleException();
				}
				else if (checkHandleExists(newHandle).equals(Boolean.TRUE)) {
					// throw InvalidHandleException if the handle already exists.
					throw new IllegalHandleException();
				}
			}
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			// throw HandleNotRecognisedException if the account with such handle is not found.
			throw new HandleNotRecognisedException();
		}
	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		// setting foundAccount to false by default.
		Boolean foundAccount = false;
		// finding the account with a given handle.
		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(handle)) {
				// setting foundAccount to true when the account is found.
				foundAccount = true;
				// setting the description of the account with a new one.
				accounts.get(i).setAccountDescription(description);
			}
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			// throw HandleNotRecognisedException if the account with such handle is not found.
			throw new HandleNotRecognisedException();
		}
	}

	/**
	 * The method finds the number of original posts of an account.
	 * 
	 * @param handle account's handle.
	 * @return number of original posts of an account.
	 */
	public int getAccountOriginalPostCount(String handle) {
		// setting count to 0 initially.
		int count = 0;
		// finding posts associated with the account with handle passed.
		for (int i = 0 ; i < posts.size() ; i++) {
			if (posts.get(i).getPostHandle().equals(handle)) {
				// increment count when a post associated with the account is found.
				count++;
			}
		}
		return count;
	}

	/**
	 * The method finds the number of endorsement posts of an account.
	 * 
	 * @param handle account's handle.
	 * @return number of endorsement posts of an account.
	 */
	public int getAccountEndorsePostCount(String handle) {
		// setting count to 0 initially.
		int count = 0;
		// finding endorsement posts associated with the account with handle passed.
		for (int i = 0 ; i < endorsePosts.size() ; i++) {
			if (endorsePosts.get(i).getEndorsePostHandle().equals(handle)) {
				// increment count when a endorsement post associated with the account is found.
				count++;
			}
		}
		return count;
	}

	/**
	 * The method finds the number of comment posts of an account.
	 * 
	 * @param handle account's handle.
	 * @return number of comment posts of an account.
	 */
	public int getAccountCommentPostCount(String handle) {
		// setting count to 0 initially.
		int count = 0;
		// finding comment posts associated with the account with handle passed.
		for (int i = 0 ; i < commentPosts.size() ; i++) {
			if (commentPosts.get(i).getCommentPostHandle().equals(handle)) {
				// increment count when a endorsement post associated with the account is found.
				count++;
			}
		}
		return count;
	}

	/**
	 * The number finds the number of posts. endorsement posts and comment posts of an account.
	 * 
	 * @param handle account's handle.
	 * @return total number of posts of an account. (including original posts, comment posts and endorsement posts)
	 */
	public int getAccountTotalPostCount(String handle) {
		return getAccountOriginalPostCount(handle) + getAccountEndorsePostCount(handle) + getAccountCommentPostCount(handle);
	}

	/**
	 * The method counts the number of posts being endorsed under each account.
	 */
	// TODO
	public void endorsedAccountCount() {
		for (int i = 0 ; i < accounts.size() ; i++) {
			// setting count to 0 initially.
			int count = 0;
			for (int j = 0 ; j < endorsePosts.size() ; j++) {
				for (int k = 0 ; k < posts.size() ; k++) {
					if (endorsePosts.get(j).getOriginalPostID() == posts.get(k).getPostID()) {
						String tempHandle = posts.get(k).getPostHandle();
						if (accounts.get(k).getAccountHandle().equals(tempHandle)) {
							count++;
						}
					}
				}
			}
			accounts.get(i).setEndorsedPostCount(count);
		}
	}
	
	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
		// finding the account matching the passed handle.
		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(handle)) {
				// calling function endorsedAccountCount() which sets the endorsement post count under each account.
				endorsedAccountCount();
				// setting id with the ID of the account found.
				int id = accounts.get(i).getAccountID();
				// setting returnHandle with the handle of the account found.
				String returnHandle = accounts.get(i).getAccountHandle();
				// setting description with the description of the account found.
				String description = accounts.get(i).getAccountDescription();
				return "ID: " + id + "\nHandle: " + returnHandle + "\nDescription: " 
				+ description + "\nPost count: " + getAccountTotalPostCount(handle) 
				+ "\nEndorse count: " + accounts.get(i).getEndorsedPostCount();
			}
		}
		// throw HandleNotRecognisedException if the account with such handle is not found
		throw new HandleNotRecognisedException();
	}

	/**
	 * The method checks whether the format of the posts message is correct.
	 * 
	 * @param message post's message.
	 * @return true if the post message is valid.
	 */
	public Boolean checkPostMessage(String message) {
		Boolean validPostMessage;
		// setting validPostMessage to false if the message is empty or has more than 100 characters.
		if (message.length() > 100 || message.length() == 0) {
			validPostMessage = false;
		}
		// setting validPostMessage to true if the post's message is not incorrect.
		else {
			validPostMessage = true;
		}
		return validPostMessage;
	}

	/**
	 * The method check if a post is commentable.
	 * 
	 * @param id post's ID.
	 * @return true if the post can be commented.
	 */
	public Boolean checkPostCommentable(int id) {
		// checking whether the passed ID refers to an original post.
		for (int i = 0 ; i < posts.size() ; i++) {
			if (posts.get(i).getPostID() == id) {
				// return true since an original post can be commented.
				return true;
			}
		}
		// checking whether the passed ID refers to an endorsement post.
		for (int j = 0 ; j < endorsePosts.size() ; j++) {
			if (endorsePosts.get(j).getEndorsePostID() == id) {
				// return true since an endorsement post can be commented.
				return true;
			}
		}
		// checking whether the passed ID refers to a comment post.
		for (int k = 0 ; k < commentPosts.size() ; k++) {
			if (commentPosts.get(k).getCommentPostID() == id) {
				// return true since a comment post can be commented.
				return true;
			}
		}
		return false;
	} 

	/**
	 * The method check whether the post can be endorsed.
	 * 
	 * @param id post's ID.
	 * @return true if the post with passed ID can be endorsed.
	 */
	public Boolean checkPostEndorsable(int id) {
		// checking whether the passed ID refers to an original post.
		for (int i = 0 ; i < posts.size() ; i++) {
			if (posts.get(i).getPostID() == id) {
				// return true since an original post can be endorsed.
				return true;
			}
		}
		// checking whether the passed ID refers to an endorsement post.
		for (int j = 0 ; j < endorsePosts.size() ; j++) {
			if (endorsePosts.get(j).getEndorsePostID() == id) {
				// breaking the loop since an endorsement post cannot be endorsed.
				break;
			}
		}
		// checking whether the passed ID refers to a comment post.
		for (int k = 0 ; k < commentPosts.size() ; k++) {
			if (commentPosts.get(k).getCommentPostID() == id) {
				// return true since an comment post can be endorsed.
				return true;
			}
		}
		return false;
	}

	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
		// setting foundAccount to false initially.
		Boolean foundAccount = false;
		if (checkPostMessage(message).equals(Boolean.FALSE)) {
			// throw InvalidPostException if the format of the post message is invalid.
			throw new InvalidPostException();
		}
		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(handle) && checkPostMessage(message).equals(Boolean.TRUE)) {
				// setting foundAccount to true since the account is found.
				foundAccount = true;
				// adding a new Post object to arraylist posts.
				posts.add(new Post(handle, message));
				// setting the ID of the post created.
				posts.get(posts.size()-1).setPostID(generateUniqueID());
			}
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			// throw HandleNotRecognisedException if the handle is not found.
			throw new HandleNotRecognisedException();
		}
		// return the ID of the post that is created.
		return posts.get(posts.size()-1).getPostID();
	}

	@Override
	public int endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		// setting foundAccount to false initially.
		Boolean foundAccount = false;
		if (checkPostEndorsable(id).equals(Boolean.FALSE)) {
			// throw NotActionablePostException if the post with passed ID cannot be endorsed.
			throw new NotActionablePostException();
		}
		// finding the account with a given handle.
		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(handle)) {
				// setting foundAccount to true when the account is found.
				foundAccount = true;
				// finding post with passed ID.
				for (int k = 0 ; k < posts.size() ; k++) {
					if (posts.get(k).getPostID() == id) {
						// creating a new EndorsePost object into arraylist endorsePosts.
						endorsePosts.add(new EndorsePost(handle, id));
						// setting the ID of the created endorsement post.
						endorsePosts.get(endorsePosts.size()-1).setEndorsePostID(generateUniqueID());
						// setting the post reference of the created endorsement post with given format.
						endorsePosts.get(endorsePosts.size()-1).setEndorsePostReference("EP@" + handle + ": " + posts.get(k).getPostMessage());
						return endorsePosts.get(endorsePosts.size()-1).getEndorsePostID();
					}
				}
				// finding comment post with passed ID.
				for (int k = 0 ; k < commentPosts.size() ; k++) {
					if (commentPosts.get(k).getCommentPostID() == id) {
						// creating a new EndorsePost object into arraylist endorsePosts.
						endorsePosts.add(new EndorsePost(handle, id));
						// setting the ID of the created endorsement post.
						endorsePosts.get(endorsePosts.size()-1).setEndorsePostID(generateUniqueID());
						// setting the post reference of the created endorsement post with given format.
						endorsePosts.get(endorsePosts.size()-1).setEndorsePostReference("EP@" + handle + ": " + commentPosts.get(k).getCommentPostMessage());
						return endorsePosts.get(endorsePosts.size()-1).getEndorsePostID();
					}
				}
			}
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			// throw HandleNotRecognisedException if the account is not found.
			throw new HandleNotRecognisedException();
		}
		// throw PostIDNotRecognisedException
		throw new PostIDNotRecognisedException();
	}

	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
		// setting foundAccount to false initially.
		Boolean foundAccount = false;
		// setting foundPost to false initially.
		Boolean foundPost = false;
		if (checkPostMessage(message).equals(Boolean.FALSE)) {
			// throw InvalidPostException if the format of the post message is invalid.
			throw new InvalidPostException();
		}
		if (checkPostCommentable(id).equals(Boolean.FALSE)) {
			// throw NotActionablePostException if the post associated with the passed IDis not commentable.
			throw new NotActionablePostException();
		}
		// finding an account matching the passed account handle.
		for (int i = 0 ; i < accounts.size() ; i++) {
			if (accounts.get(i).getAccountHandle().equals(handle)) {
				// setting foundAccount to true when an account is found.
				foundAccount = true;
				// finding the post with a given post ID.
				for (int k = 0 ; k < posts.size() ; k++) {
					if (posts.get(k).getPostID() == id && checkPostMessage(message).equals(Boolean.TRUE)) {
						// setting foundPost to true when a post is found.
						foundPost = true;
						// creating a new CommentPost object into arraylist commentPosts.
						commentPosts.add(new CommentPost(handle, id, message));
						// setting the ID of the created comment post.
						commentPosts.get(commentPosts.size()-1).setCommentPostID(generateUniqueID());
					}
				}
				// finding the comment post with a given post ID.
				for (int k = 0 ; k < commentPosts.size() ; k++) {
					if (commentPosts.get(k).getCommentPostID() == id && checkPostMessage(message).equals(Boolean.TRUE)) {
						// setting foundPost to true when a post is found.
						foundPost = true;
						// creating a new CommentPost object into arraylist commentPosts.
						commentPosts.add(new CommentPost(handle, id, message));
						// setting the ID of the created comment post.
						commentPosts.get(commentPosts.size()-1).setCommentPostID(generateUniqueID());
					}
				}
			}
		}
		if (foundAccount.equals(Boolean.FALSE)) {
			// throw HandleNotRecognisedException if the account is not found.
			throw new HandleNotRecognisedException();
		}
		if (foundPost.equals(Boolean.FALSE)) {
			// throw PostIDNotRecognisedException if the post is not found.
			throw new PostIDNotRecognisedException();
		}
		return commentPosts.get(commentPosts.size()-1).getCommentPostID();
	}

	/**
	 * The method creates a generic empty post if it does not already exist.
	 */
	public void createGenericEmptyPost() {
		// finding post with ID of -1.
		for (int i = 0 ; i < posts.size() ; i++) {
			// skipping if a generic empty post already exists.
			if (posts.get(i).getPostID() == -1) {
				return;
			}
		}
		// adding a new generic empty post with ID of -1.
		posts.add(new Post("The original content was removed from the system and is no longer available.", -1));
	}

	/**
	 * The method check if generic empty post exists.
	 * 
	 * @return true if a generic empty post already exists.
	 */
	public boolean checkGenericEmptyPostPresent() {
		for (int i = 0 ; i < posts.size() ; i++) {
			// return true if a generic empty post is found.
			if (posts.get(i).getPostID() == -1) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
		// if a generic empty post does not exists, call function createGenericEmptyPost() to create one.
		if (!checkGenericEmptyPostPresent()) {
			createGenericEmptyPost();
		}
		// finding the original post matching the post ID passed.
		for (int i = 0 ; i < posts.size() ; i++) {
			if (posts.get(i).getPostID() == id) {
				// finding endorsement posts pointing to the post being deleted.
				for (int j = 0 ; j < endorsePosts.size() ; j++) {
					if (endorsePosts.get(j).getOriginalPostID() == id) {
						// removing the endorsement posts found.
						endorsePosts.remove(j);
					}
				}
				// finding comment posts pointing to the post being deleted.
				for (int k = 0 ; k < commentPosts.size() ; k++) {
					if (commentPosts.get(k).getOriginalPostID() == id) {
						// pointing the comment posts to a generic empty post with ID -1.
						commentPosts.get(k).setOriginalPostID(-1);
					}
				}
				// removing the original post from the arraylist.
				posts.remove(i);
				// breaking the loop since a post is already removed.
				break;
			}
		}
		// finding the endorsement post matching the post ID passed.
		for (int a = 0 ; a < endorsePosts.size() ; a++) {
			if (endorsePosts.get(a).getEndorsePostID() == id) {
				// finding comment posts pointing to the endorsement post being deleted.
				for (int k = 0 ; k < commentPosts.size() ; k++) {
					if (commentPosts.get(k).getOriginalPostID() == id) {
						// pointing the comment posts to a generic empty post with ID -1.
						commentPosts.get(k).setOriginalPostID(-1);
					}
				}
				// removing the endorsement post from the arraylist.
				endorsePosts.remove(a);
				// breaking the loop since a post is already removed.
				break;
			}
		}
		// finding the comment post matching the post ID passed.
		for (int z = 0 ; z < commentPosts.size() ; z++) {
			if (commentPosts.get(z).getCommentPostID() == id) {
				// finding endorsement posts pointing to the comment post being deleted.
				for (int j = 0 ; j < endorsePosts.size() ; j++) {
					if (endorsePosts.get(j).getOriginalPostID() == id) {
						// removing the endorsement posts found.
						endorsePosts.remove(j);
					}
				}
				// finding comment posts pointing to the comment post being deleted.
				for (int k = 0 ; k < commentPosts.size() ; k++) {
					if (commentPosts.get(k).getOriginalPostID() == id) {
						// pointing the comment posts to a generic empty post with ID -1.
						commentPosts.get(k).setOriginalPostID(-1);
					}
				}
				// removing the comment post from the arraylist.
				commentPosts.remove(z);
				// breaking the loop since a post is already removed.
				break;
			}
		}
	}

	/**
	 * The method counts the number of endorsements of an individual post.
	 * 
	 * @param id post's ID.
	 * @return number of endorsements of an individual post.
	 */
	public int findNumberOfEndorsements(int id) {
		// setting endorsementCount to 0 initiaully.
		int endorsementCount = 0;
		// findng endorsement posts pointing to the individual post.
		for (int i = 0 ; i < endorsePosts.size() ; i++) {
			if (endorsePosts.get(i).getOriginalPostID() == id) {
				// increment endorsementCount if an endorsement post pointing to the individual post is found.
				endorsementCount++;
			}
		}
		return endorsementCount;
	}

	/**
	 * The method counts the number of comments of an individual post.
	 * 
	 * @param id post's ID.
	 * @return number of comments of an individual post.
	 */
	public int findNumberOfComments(int id) {
		// setting commentCount to 0 initially.
		int commentCount = 0;
		// finding comment posts pointing to the individual post.
		for (int i = 0 ; i < commentPosts.size() ; i++) {
			if (commentPosts.get(i).getOriginalPostID() == id) {
				// increment commentCount if an comment post pointing to the individual post is found.
				commentCount++;
			}
		}
		return commentCount;
	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		// finding the original post matching the post's ID passed.
		for (int i = 0 ; i < posts.size() ; i++) {
			if (posts.get(i).getPostID() == id) {
				// returning a string showing details of an individual post with the format provided.
				return "ID: " + posts.get(i).getPostID() + "\nAccount: " + posts.get(i).getPostHandle() 
				+ "\nNo. endorsements: " + findNumberOfEndorsements(id) + " | No. comments: " 
				+ findNumberOfComments(id) + "\n" + posts.get(i).getPostMessage();
			}
		}
		// finding the comment post matching the post's ID passed.
		for (int j = 0 ; j < commentPosts.size() ; j++) {
			if (commentPosts.get(j).getCommentPostID() == id) {
				// returning a string showing details of an individual post with the format provided.
				return "ID: " + commentPosts.get(j).getCommentPostID() + "\nAccount: " 
				+ commentPosts.get(j).getCommentPostHandle() + "\nNo. endorsements: " 
				+ findNumberOfEndorsements(id) + " | No. comments: " 
				+ findNumberOfComments(id) + "\n" + commentPosts.get(j).getCommentPostMessage();
			}
		}
		// throw PostIDNotRecognisedException if the post ID does not exist in posts or commentPosts.
		throw new PostIDNotRecognisedException();
	}

	@Override
	public StringBuilder showPostChildrenDetails(int id)
		throws PostIDNotRecognisedException, NotActionablePostException {
		// declaring a new StringBuilder object sb.
		StringBuilder sb = new StringBuilder();
		// declaring a new string array tempString.
		String[] tempString;
		if (checkPostEndorsable(id).equals(Boolean.TRUE)) {
			// appending the stringBilder with the post parent, the post with ID passed in.
			sb.append(showIndividualPost(id));
			sb.append("\n|\n");
			// appending the stringBuilder with the child of the outermost post.
			for (int i = 0 ; i < commentPosts.size() ; i++) {
				if (commentPosts.get(i).getOriginalPostID() == id) {
					tempString = showIndividualPost(commentPosts.get(i).getCommentPostID()).split("\n");
					sb.append("| > " + tempString[0] + "\n");
					for (int s = 1 ; s < tempString.length ; s++) {
						sb.append("    " + tempString[s] + "\n");
					}
					for (int j = 0 ; j < commentPosts.size() ; j++) {
						if (commentPosts.get(j).getOriginalPostID() == commentPosts.get(i).getCommentPostID()) {
							tempString = showIndividualPost(commentPosts.get(j).getCommentPostID()).split("\n");
							sb.append("    |\n    | > " + tempString[0] + "\n");
							for (int t = 1 ; t < tempString.length ; t++) {
								sb.append("        " + tempString[t] + "\n");
							}
							for (int k = 0 ; k < commentPosts.size() ; k++) {
								if (commentPosts.get(k).getOriginalPostID() == commentPosts.get(j).getCommentPostID()) {
									tempString = showIndividualPost(commentPosts.get(k).getCommentPostID()).split("\n");
									sb.append("        |\n        | > " + tempString[0] + "\n");
									for (int u = 1 ; u < tempString.length ; u++) {
										sb.append("            " + tempString[u] + "\n");
									}
								}
							}
						}
					}
				}
			}	
			return sb;
		}
		// throw PostIDNotRecognisedException if a post with such ID is not found.
		throw new PostIDNotRecognisedException();
	}

	@Override
	public int getNumberOfAccounts() {
		// returning the number of accounts by finding the size of arraylist accounts.
		return accounts.size();
	}

	@Override
	public int getTotalOriginalPosts() {
		// returning the number of original posts by finding the size of arraylist posts.
		return posts.size();
	}

	@Override
	public int getTotalEndorsmentPosts() {
		// returning the number of endorsement posts by finding the size of arraylist endorsePosts.
		return endorsePosts.size();
	}

	@Override
	public int getTotalCommentPosts() {
		// returning the number of comment posts by finding the size of arraylist commentPosts.
		return commentPosts.size();
	}

	@Override
	public int getMostEndorsedPost() {
		int tempCount;
		// setting count to 0 initially.
		int count = 0;
		// setting temp to 0 initially.
		int temp = 0;
		// assuming the most endorsed post to be the first post in the arraylist.
		int mostEndorsedPostID = endorsePosts.get(0).getOriginalPostID();
		for (int i = 0 ; i < endorsePosts.size()-1 ; i++) {
			temp = endorsePosts.get(i).getOriginalPostID();
			// resetting tempCount to 0.
			tempCount = 0;
			for (int j = 0 ; j < endorsePosts.size() ; j++) {
				if (temp == endorsePosts.get(j).getOriginalPostID()) {
					// incrementing tempCount when a endorsement post points to the same post. 
					tempCount++;
				}
				// if the current post have more endorsements than previous posts, store it as the most endorsed post.
				if (tempCount > count) {
					mostEndorsedPostID = temp;
					count = tempCount;
				}
			}
		}
		return mostEndorsedPostID;
	}

	@Override
	public int getMostEndorsedAccount() {
		// setting mostEndorsedAccountCount to 0 initially.
		int mostEndorsedAccountCount = 0;
		// assuming the most endorsed account to be the first account in the arraylist.
		int mostEndorsedAccountID = accounts.get(0).getAccountID();
		// call function endorsedAccountCount() to count account's endorsement which stores it into the arraylist.
		endorsedAccountCount();
		for (int i = 0 ; i < accounts.size() ; i++) {
			// if the endorsement post count of the current account is larger than the previous loop, store it as the most endorsed account.
			if (accounts.get(i).getEndorsedPostCount() > mostEndorsedAccountCount) {
				// getting the account ID of account with higher endorsement and store it as mostEndorsedAccountID.
				mostEndorsedAccountID = accounts.get(i).getAccountID();
			}
		}
		return mostEndorsedAccountID;
	}

	@Override
	public void erasePlatform() {
		// clearing all elements of the arraylists.
		posts.clear();
		endorsePosts.clear();
		commentPosts.clear();
		accounts.clear();
	}

	@Override
	public void savePlatform(String filename) throws IOException { 
		try (FileOutputStream fos = new FileOutputStream(filename)) {
			// declaring a new ObjectOutputStream object with FileOutputStream fos.
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// declaring a new arraylist toSerialize to store all arraylists to be serialized.
			ArrayList<Object> toSerialize = new ArrayList<>();
			// adding arraylists to be serialized into toSerialize, including posts, endorsePosts, commentPosts and accounts.
			toSerialize.add(posts);
			toSerialize.add(endorsePosts);
			toSerialize.add(commentPosts);
			toSerialize.add(accounts);
			// writing the arrayList toSerialize into the ObjectOutputStream.
			oos.writeObject(toSerialize);
			// closing the ObjectOutputStream object.
			oos.close();
		} 
		catch (IOException e) {
			// printing the stack trace if an IOException is thrown.
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		// declaring a new arraylist object toDeserialize.
		ArrayList<Object> toDeserialize = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(filename)) {
			// declaring a new ObjectInputStream object with FileInputStream fis.
			ObjectInputStream ois = new ObjectInputStream(fis);
			// reading the object from the ObjectInputStream as arraylist.
			toDeserialize = (ArrayList<Object>) ois.readObject();
			// closing the ObjectInputStream.
			ois.close();
		}
		catch (IOException | ClassNotFoundException e) {
			// printing the stack trace if an IOException or a ClassNotFoundException is found.
			e.printStackTrace();
		}
		// calling function erasePlatform to clear all elements previously on the platform.
		erasePlatform();
		// mapping arraylists from toDeserialize to respective arraylists. 
		ArrayList<Post> deserializedPosts = (ArrayList<Post>) toDeserialize.get(0);
		ArrayList<EndorsePost> deserializedEndorsePost = (ArrayList<EndorsePost>) toDeserialize.get(1);
		ArrayList<CommentPost> deserializedCommentPost = (ArrayList<CommentPost>) toDeserialize.get(2);
		ArrayList<Account> deserializedAccount = (ArrayList<Account>) toDeserialize.get(4);
		// adding arraylist elements from deserialized objects into respective arraylists.
		for (int i = 0 ; i < deserializedPosts.size() ; i++) {
			posts.add(deserializedPosts.get(i));
		}
		for (int j = 0 ; j < deserializedEndorsePost.size() ; j++) {
			endorsePosts.add(deserializedEndorsePost.get(j));
		}
		for (int k = 0 ; k < deserializedCommentPost.size() ; k++) {
			commentPosts.add(deserializedCommentPost.get(k));
		}
		for (int m = 0 ; m < deserializedAccount.size() ; m++) {
			accounts.add(deserializedAccount.get(m));
		}
	}
}
