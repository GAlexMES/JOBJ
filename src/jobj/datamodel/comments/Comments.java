package jobj.datamodel.comments;

import java.util.ArrayList;

/**
 * Saves all comments of one .obj file in an array List.
 * More features come later.
 * @author Alexander Brennecke
 *
 */
public class Comments {
	
	private ArrayList<Comment> commentList;
	
	/**
	 * Initializes the ArrayList
	 */
	public Comments(){
		this.commentList = new ArrayList<>();
	}
	
	/**
	 * Adds a Comment object to the Comment ArrayList
	 * @param comment a Comment Object, which includes the comment text
	 */
	public void addComment(Comment comment){
		commentList.add(comment);
	}

}
