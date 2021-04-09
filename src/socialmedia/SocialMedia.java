package socialmedia;

import java.io.IOException;
import java.util.ArrayList;

public class SocialMedia implements SocialMediaPlatform {
    ArrayList<Account> accounts = new ArrayList<>();

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
		return accounts.size();
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
