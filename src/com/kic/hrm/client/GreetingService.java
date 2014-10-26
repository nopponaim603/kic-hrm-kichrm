package com.kic.hrm.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.geolocation.client.Position.Coordinates;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.kic.hrm.client.presenter.ProfilePresenter.state;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.EmployeeQuota;
import com.kic.hrm.data.model.LeaveTask;
import com.kic.hrm.data.model.LeaveTask.progress;
import com.kic.hrm.data.model.StartTimeLog.type;
import com.kic.hrm.shared.LoginInfo;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;



	// TODO #09: start create login helper methods in service interface
	String getUserEmail(String token);

	LoginInfo login(String requestUri);

	LoginInfo loginDetails(String token);

	String QuickTest(String testParametor);

	ArrayList<String> UpdateList(String targetEntity);
	Employee getProfile(Integer targetEmployee);
	Employee addProfile(Employee userEmployee, EmployeeQuota m_employeeQuota,
			state registerMode);
	boolean deleteProfile(Integer targetEmployee);
	EmployeeQuota getEmployeeQuota(int employeeID);
	
	String getFileFormGoogleDrive(String token, String idFile);
	String saveCSVtoDrive(String token, String FolderID);

	boolean createLeaveTask(LeaveTask leavetask);
	boolean approveLeaveTask(LeaveTask leavetask);
	List<LeaveTask> getLeaveTask(progress InProgress, int targetID);
	boolean deleteLeaveTask(LeaveTask leavetask);
	

	boolean LoginAttendance(LoginInfo userInfo, type leaveType, String address);
	String getAddressWithLatLong(String latLong);
}
