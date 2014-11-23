package com.kic.hrm.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.geolocation.client.Position.Coordinates;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kic.hrm.client.presenter.ProfilePresenter.state;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.EmployeeQuota;
import com.kic.hrm.data.model.LeaveTask;
import com.kic.hrm.data.model.StartTimeLog.type;
import com.kic.hrm.data.model.SystemConfig;
import com.kic.hrm.shared.*;
/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
	throws IllegalArgumentException;
	
	// TODO #10: create login helper methods in service asynchronous interface	
	void getUserEmail(String token, AsyncCallback<String> callback);

	void login(String requestUri, AsyncCallback<LoginInfo> asyncCallback);

	void loginDetails(String token, AsyncCallback<LoginInfo> asyncCallback);
	// TODO #10:> end	
	
	void QuickTest(String testParametor , AsyncCallback<String> callback);
	
	//void Register(String userID, AsyncCallback<String> callback);
	
	void addProfile(Employee userEmployee , EmployeeQuota m_employeeQuota, state registerMode, AsyncCallback<Employee> callback);
	
	void getProfile(Integer targetEmployee, AsyncCallback<Employee> callback);
	void getEmployeeQuota(int employeeID, AsyncCallback<EmployeeQuota> callback);
	
	void deleteProfile(Integer targetEmployee, AsyncCallback<Boolean> callback);
	
	void UpdateList(String targetEntity,AsyncCallback<ArrayList<String>> callback);
	
	void getFileFormGoogleDrive(String token, String idFile,
			AsyncCallback<String> callback);
	
	void saveCSVtoDrive(String token, String FolderID,
			AsyncCallback<String> callback);

	void createLeaveTask(LeaveTask leavetask , AsyncCallback<Boolean> callback);
	void approveLeaveTask(LeaveTask leavetask , AsyncCallback<Boolean> callback);
	void getLeaveTask(LeaveTask.progress InProgress,int targetID , AsyncCallback<List<LeaveTask>> callback);
	void deleteLeaveTask(LeaveTask leavetask , AsyncCallback<Boolean> callback);

	void LoginAttendance(LoginInfo userInfo, type leaveType,
			String address,
			AsyncCallback<Boolean> callback);

	void getAddressWithLatLong(String latLong, AsyncCallback<String> callback);

	void sendReportDairyToEmail(String email, AsyncCallback<Boolean> callback);

	void addLeaveTaskToCalendar(String token,
			LeaveTask leavetask, AsyncCallback<Boolean> callback);

	void getSystemConfig(AsyncCallback<SystemConfig> callback);

	void ApplySystemConfig(SystemConfig sysConfig, AsyncCallback<Void> callback);
	
	//
}
