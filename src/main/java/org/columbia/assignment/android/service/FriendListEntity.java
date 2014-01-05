package org.columbia.assignment.android.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class FriendListEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2225778534523031507L;
	
	@JsonIgnore
	List <UserDBObject> friendList;
	public FriendListEntity() {
		super();
		friendList = new ArrayList<UserDBObject>();
	}
	
	
	public List<UserDBObject> getFriendList() {
		return friendList;
	}
	public void setFriendList(List<UserDBObject> friendList) {
		this.friendList = friendList;
	}


	@Override
	public String toString() {
		return "FriendListEntity [friendList=" + friendList + "]";
	}
}
