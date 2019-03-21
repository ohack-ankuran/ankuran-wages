package com.ankuran.wages.model.response;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class LinksDTO {

	private String href;
	private String method;
	private String rel;
	private String title;
	
	private static final String href_format_employee = "/centres/%s/employees/%s"; 
	private static final String href_format_activity = "/centres/%s/employees/%s/activities/%s";
	private static final String href_format_group_activity = "/centres/%s/employees/activities/%s";
	public static final String rel_default = "self";
	public static Map<String, String> methodEmployeeTitleMap = new HashMap<String, String>();
	public static Map<String, String> methodActivityTitleMap = new HashMap<String, String>();
	
	static {
		methodEmployeeTitleMap.put("GET", "Get employee details with this employee id.");
		methodEmployeeTitleMap.put("PUT", "Provide additional employee details for an existing employee with this id.");
		methodEmployeeTitleMap.put("DELETE", "Delete employee linked with this id.");
		methodEmployeeTitleMap.put("PATCH", "Update or modify existing employee details with this id.");
		
		methodActivityTitleMap.put("GET", "Get activity details with this activity id.");
		methodActivityTitleMap.put("PUT", "Provide additional activity details for an existing activity with this id.");
		methodActivityTitleMap.put("DELETE", "Delete activity linked with this id.");
		methodActivityTitleMap.put("PATCH", "Update or modify existing activity details with this id.");
	}
	
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	LinksDTO(String centreId, String employeeId, String method) {
		super();
		this.href = getHrefForEmployee(centreId,employeeId);
		this.method = method;
		this.rel = rel_default;
		this.title = methodEmployeeTitleMap.get(method);
	}
	
	LinksDTO(Long centreId, BigInteger groupActivityId, String method) {
		super();
		this.href = getHrefForGroupActivity(centreId,groupActivityId);
		this.method = method;
		this.rel = rel_default;
		this.title = methodEmployeeTitleMap.get(method);
	}
	
	private String getHrefForGroupActivity(Long centreId, BigInteger groupActivityId) {
		return String.format(href_format_group_activity, centreId, groupActivityId.toString());
	}
	private String getHrefForEmployee(String centreId, String employeeId) {
		return String.format(href_format_employee, centreId, employeeId);
	}
	
	LinksDTO(String centreId, String employeeId, String activityId, String method) {
		super();
		this.href = getHrefForActivity(centreId,employeeId, activityId);
		this.method = method;
		this.rel = rel_default;
		this.title = methodActivityTitleMap.get(method);
	}
	private String getHrefForActivity(String centreId, String employeeId, String activityId) {
		return String.format(href_format_activity, centreId, employeeId, activityId);
	}
}
