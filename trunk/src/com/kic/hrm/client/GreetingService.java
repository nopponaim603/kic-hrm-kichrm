package com.kic.hrm.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.geolocation.client.Position.Coordinates;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.kic.hrm.client.presenter.ProfilePresenter.state;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.EmployeeQuota;
import com.kic.hrm.data.model.LeaveTask;
import com.kic.hrm.data.model.LoginInfo;
import com.kic.hrm.data.model.StartTimeLog;
import com.kic.hrm.data.model.LeaveTask.progress;
import com.kic.hrm.data.model.StartTimeLog.type;
import com.kic.hrm.data.model.SystemConfig;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	String QuickTest(String testParametor);

	// TODO #09: start create login helper methods in service interface
	String getUserEmail(String token);
	LoginInfo login(String requestUri);
	LoginInfo loginDetails(String token);
	////////////////////
	
	// Addmintator Function
	ArrayList<String> UpdateList(String targetEntity);
	Employee getProfile(Integer targetEmployee);
	Employee addProfile(Employee userEmployee, EmployeeQuota m_employeeQuota,state registerMode);
	boolean deleteProfile(Integer targetEmployee);
	EmployeeQuota getEmployeeQuota(int employeeID);
	boolean sendReportDailyToEmail(String email);
	////////////////
	
	//Leave Function
	boolean createLeaveTask(LeaveTask leavetask);
	boolean approveLeaveTask(LeaveTask leavetask);
	List<LeaveTask> getLeaveTask(progress InProgress, Integer targetEmployee);
	boolean deleteLeaveTask(LeaveTask leavetask);
	boolean addLeaveTaskToCalendar(String token ,LeaveTask leavetask);
	////////////////
	
	// Login Attendance
	boolean LoginAttendance(LoginInfo userInfo, type leaveType, String address);
	String getAddressWithLatLong(String latLong);
	///////////////
	
	// Save File CSV to Google Drive
	String getFileFormGoogleDrive(String token, String idFile);
	String saveCSVtoDrive(String token, String FolderID);
	List<StartTimeLog> getStartTimeLogListDaily(Date m_date);
	
	///////////////
	SystemConfig getSystemConfig();
	void ApplySystemConfig(SystemConfig sysConfig);

	
	void TestCreateAttendance();
	void TestDailyProcessAttendance();
	void TestMonthlyProcessAttendance();
	void TestAllProcessAttendance();
}
