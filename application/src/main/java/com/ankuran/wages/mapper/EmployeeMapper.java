package com.ankuran.wages.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ankuran.wages.model.EmployeeDao;
import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.model.response.SpouseResponseDTO;

@Component
public class EmployeeMapper {

	public EmployeeResponseDTO mapEmployeeDaoToDTO(EmployeeDao employeeDao){
		EmployeeResponseDTO employeeDTO = new EmployeeResponseDTO();
		if (employeeDao != null) {
			if(employeeDao.getStatus() != null && employeeDao.getStatus() > 0) {
				employeeDTO.setActive(true);
			}
			if(employeeDao.getCentreId() != null) {
				employeeDTO.setCentre(employeeDao.getCentreId());
			}
			if(employeeDao.getJoiningTime() != null) {
				employeeDTO.setTimeOfJoining(employeeDao.getJoiningTime());
			}
			if(employeeDao.getId() != null) {
				employeeDTO.setId(employeeDao.getId());
			}
			if(!StringUtils.isEmpty(employeeDao.getFullName())) {
				employeeDTO.setFullName(employeeDao.getFullName());       
			}
			if(!StringUtils.isEmpty(employeeDao.getMobileNo())) {
				employeeDTO.setMobile(employeeDao.getMobileNo());
			}
			if(!StringUtils.isEmpty(employeeDao.getSpouseFullName()) || !StringUtils.isEmpty(employeeDao.getSpouseEmployeeId())) {
				employeeDTO.setHusband(new SpouseResponseDTO(employeeDao.getSpouseFullName(), employeeDao.getSpouseEmployeeId()));
			}
		}
		return employeeDTO;
	}

}