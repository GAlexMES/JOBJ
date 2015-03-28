package jobj.comments;

import java.util.ArrayList;

public class Comments {
	
	private ArrayList<Comment> commentList;
	
	public Comments(){
		this.commentList = new ArrayList<>();
	}
	
	public void addComment(Comment comment){
		commentList.add(comment);
	}

}
