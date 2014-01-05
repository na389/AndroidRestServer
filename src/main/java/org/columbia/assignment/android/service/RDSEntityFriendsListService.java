package org.columbia.assignment.android.service;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;



@Path("/rdsobjectretrieve")
public class RDSEntityFriendsListService {
//this method will take the list of phone numbers and find the corresponding registered users in the db. 
//and return that created list of USerDBObject to our application.
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFriends(PhoneNumbersEntity phoneNumbersEntity){
		System.out.println("phone number entity size :::"+phoneNumbersEntity.getPhoneNumbers().size());
		FriendListEntity friendListEntity = mapPhoneNumbersWithRegUsers(phoneNumbersEntity.getPhoneNumbers());		
		System.out.println("RDSEntityFriendsListService:"+friendListEntity.toString());
		
		return Response.status(200).entity(friendListEntity).build();
	}

	private FriendListEntity mapPhoneNumbersWithRegUsers(List<String> phoneNumbers) {
		Connection connect = null;
		FriendListEntity friendListEntity = new FriendListEntity();
		try {
			System.out.println("mapPhoneNumbersWithRegUsers");
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://mydbinstance.c1b4bf3mouew.us-east-1.rds.amazonaws.com:3306/mydb";
			connect = (Connection) DriverManager.getConnection(url,"myuser", "mypassword");
			Statement stmnt = (Statement) connect.createStatement();
			ResultSet rst = stmnt.executeQuery("SELECT username,phonenumber,location_lat,location_long FROM users");
			while(rst.next()){
				System.out.println("RSTTTTTTTTTTTTTT:"+rst.getString("username"));
				if(phoneNumbers.contains(rst.getString("phonenumber"))){
					System.out.println("Matched phone number:"+rst.getString("phonenumber"));
					UserDBObject userdbObj = new UserDBObject();
					userdbObj.setUsername(rst.getString("username"));
					userdbObj.setPhoneNumber(rst.getString("phonenumber"));
					userdbObj.setLocation_lat(rst.getString("location_lat"));
					userdbObj.setLocation_long(rst.getString("location_long"));					
					friendListEntity.friendList.add(userdbObj);
				}
			}
			
			return friendListEntity;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		} finally {
			try {
				if(connect != null)
					connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
}
