/***********************************************************************
 * Module:  Comments.java
 * Author:  Mishka
 * Purpose: Defines the Class Comments
 ***********************************************************************/
package com.mobicongo.app.tours.model;

import java.util.*;


public class Comments {
   
   private int commentId;
   private String text;
   private Date date;
   private int userId;
   private int pointofinterestid;
   private int siteid;
   

   public Comments(){
	   //null constructor
   }
   
   public Comments(int commentID, String text, Date date,
		   int userId, int pointofinterestid, int siteid ){
	   this.commentId=commentID;
	   this.text=text;
	   this.date=date;
	   this.userId=userId;
	   this.pointofinterestid=pointofinterestid;
	   this.siteid=siteid;
   }
   
   public String newComment() {
      // TODO: implement
      return null;
   }
	
	public int getCommentId() {
		return commentId;
	}
	
	
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	
	public String getText() {
		return text;
	}
	
	
	public void setText(String text) {
		this.text = text;
	}
	
	
	public Date getDate() {
		return date;
	}
	
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public int getUserId() {
		return userId;
	}
	
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	

	public int getPointofinterestid() {
		return pointofinterestid;
	}
	
	
	public void setPointofinterestid(int pointofinterestid) {
		this.pointofinterestid = pointofinterestid;
	}
	
	
	public int getSiteid() {
		return siteid;
	}
	
	
	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}

}