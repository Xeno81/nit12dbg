package blog.data;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Permissions implements Serializable{


	private static final long serialVersionUID = 1L; 
	protected String createPost, removePost, editPost;
	protected String registerUser, createUser, removeUser, editUser;
	protected String createComment, removeComment;

	@Id @GeneratedValue	
	private long id;
	
	public Permissions() {}
	
	public String getCreatePost() {
		return createPost;
	}
	public void setCreatePost(String createPost) {
		this.createPost = createPost;
	}
	public String getRemovePost() {
		return removePost;
	}
	public void setRemovePost(String removePost) {
		this.removePost = removePost;
	}
	public String getEditPost() {
		return editPost;
	}
	public void setEditPost(String editPost) {
		this.editPost = editPost;
	}
	public String getRegisterUser() {
		return registerUser;
	}
	public void setRegisterUser(String registerUser) {
		this.registerUser = registerUser;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getRemoveUser() {
		return removeUser;
	}
	public void setRemoveUser(String removeUser) {
		this.removeUser = removeUser;
	}
	public String getEditUser() {
		return editUser;
	}
	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}
	public String getCreateComment() {
		return createComment;
	}
	public void setCreateComment(String createComment) {
		this.createComment = createComment;
	}
	public String getRemoveComment() {
		return removeComment;
	}
	public void setRemoveComment(String removeComment) {
		this.removeComment = removeComment;
	}
	
	
	
	
}
	