package com.ankuran.wages.model.response;

import java.util.HashMap;
import java.util.Map;

public class LinksDTO {

	private String href;
	private String method;
	private String rel;
	private String title;
	
	private static final String href_format = "/centres/%s/employees/%s"; 
	public static final String rel_default = "self";
	public static Map<String, String> methodTitleMap = new HashMap<String, String>();
	
	static {
		methodTitleMap.put("GET", "Get employee details with this employee id.");
		methodTitleMap.put("PUT", "Provide additional employee details for an existing employee with this id.");
		methodTitleMap.put("DELETE", "Delete employee linked with this id.");
		methodTitleMap.put("PATCH", "Update or modify existing employee details with this id.");
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
		this.href = getHrefFor(centreId,employeeId);
		this.method = method;
		this.rel = rel_default;
		this.title = methodTitleMap.get(method);
	}
	private String getHrefFor(String centreId, String employeeId) {
		return String.format(href_format, centreId, employeeId);
	}
}
