package com.pmsystem.action.fr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pmsystem.model.fr.Account;
import com.pmsystem.model.fr.FProject;
import com.pmsystem.model.fr.Friend;

import com.pmsystem.model.fr.Staff;
import com.pmsystem.model.pj.Project;
import com.pmsystem.service.fr.FriendManageService;
import com.pmsystem.model.fr.UserInformation;

public class FriendManageAction extends ActionSupport{
	private Friend friend;
	private UserInformation userinformation;
	private FProject fproject;
	private Map<String, Object> jsonMap;
	private String MyID;
	private FriendManageService friendManageService ;
	private String[] fproject_array;
	private String StaffID;
	private String FriendID;
	private String ProjectID;
	private String AccountID;
	private String newAccountPassword;
	private String oldAccountPassword;

	private boolean success;
	
	public String modifyPassword()
	{
		friendManageService.modifyPassword(AccountID,newAccountPassword);
		
		return SUCCESS;
	}
	
	public String checkOldPassword() throws Exception{

		jsonMap.clear();
		List<Account> temp = friendManageService.checkOldPassword(AccountID,oldAccountPassword);
		//HttpServletResponse response =  ServletActionContext.getResponse();
		if(temp.get(0).getAccount_password().equals(oldAccountPassword))
		{
			jsonMap.put("success", true);
			System.out.println("yes");
			//throw new Exception("this is a test!");
			//ServletActionContext.getResponse().setContentType("text/json; charset=utf-8");
			//ServletActionContext.getResponse().getWriter().write("yes");
		}
		else
		{
			jsonMap.put("success", false);
			System.out.println("no");
			//response.getWriter().print("no");
		}
		
		return SUCCESS;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getOldAccountPassword() {
		return oldAccountPassword;
	}

	public void setOldAccountPassword(String oldAccountPassword) {
		this.oldAccountPassword = oldAccountPassword;
	}

	public String getNewAccountPassword() {
		return newAccountPassword;
	}

	public void setNewAccountPassword(String newAccountPassword) {
		this.newAccountPassword = newAccountPassword;
	}

	public String getAccountID() {
		return AccountID;
	}

	public void setAccountID(String accountID) {
		AccountID = accountID;
	}
	
	public String[] getFproject_array() {
		return fproject_array;
	}

	public void setFproject_array(String[] fprojectArray) {
		fproject_array = fprojectArray;
	}

	public FProject getFproject() {
		return fproject;
	}

	public void setFproject(FProject fproject) {
		this.fproject = fproject;
	}
	
	public String getMyID() {
		return MyID;
	}

	public void setMyID(String myID) {
		MyID = myID;
	}

	
	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}

	public UserInformation getUserinformation() {
		return userinformation;
	}

	public void setUserinformation(UserInformation userinformation) {
		this.userinformation = userinformation;
	}


	public FriendManageAction() {
		jsonMap = new HashMap<String, Object>();
	}

	public String findAllFriend() {
		jsonMap.clear();
		List<Friend> friends = friendManageService.findAllFriend(MyID);
		jsonMap.put("friends", friends);
		jsonMap.put("totalCount", friends.size());
		return SUCCESS;
	}
	
	public String inviteFriendToProject(){	

		friendManageService.inviteFriendToProject(fproject_array, friend.getId());
		
		return SUCCESS;
	}
	
	public String getUserInformation()
	{
		jsonMap.clear();		
		List<UserInformation> userinformation =  friendManageService.getUserInformation(friend.getId());
		jsonMap.put("informations", userinformation);
		jsonMap.put("totalCount", 1);

		return SUCCESS;
	}
	
	public String getProject(){			
		jsonMap.clear();
		List<FProject> fprojects = friendManageService.getProject(MyID);
		jsonMap.put("fprojects", fprojects);
		jsonMap.put("totalCount", fprojects.size());
		//System.out.println("totalCount:"+fprojects.size());
		return SUCCESS;
	}
	
	public String modifyRemark(){
		jsonMap.clear();
		friendManageService.modifyRemark(MyID,friend.getId(),friend.getRemark());
		jsonMap.put("success",true);
		return SUCCESS;		
	}

	public String deleteFriend() {
		jsonMap.clear();
		friendManageService.deleteFriend(FriendID, MyID);
		System.out.println("friendID:" + FriendID);
		jsonMap.put("success", true);
		return SUCCESS;
	}

	public String findStaff() {
		jsonMap.clear();
		List<Staff> staffs = friendManageService.findStaff(StaffID);
		jsonMap.put("staffs", staffs);
		return SUCCESS;
	}

	public String findProject() {
		jsonMap.clear();
		jsonMap.put("projects", friendManageService.findProject(FriendID));
		return SUCCESS;
	}

	public String applyForProject() {
		jsonMap.clear();
		friendManageService.applyForProject(MyID, ProjectID);
		return SUCCESS;
	}

	public String addFriend() {
		jsonMap.clear();
		friendManageService.addFriend(FriendID, MyID);
		jsonMap.put("success", true);
		return SUCCESS;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}


	public FriendManageService getFriendManageService() {
		return friendManageService;
	}

	public void setFriendManageService(FriendManageService friendManageService) {
		this.friendManageService = friendManageService;
	}

	public String getFriendID() {
		return FriendID;
	}

	public void setFriendID(String friendID) {
		FriendID = friendID;
	}

	public String getStaffID() {
		return StaffID;
	}

	public void setStaffID(String staffID) {
		StaffID = staffID;
	}

	public String getProjectID() {
		return ProjectID;
	}

	public void setProjectID(String projectID) {
		ProjectID = projectID;
	}

}
