package com.ankuran.wages.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ankuran.wages.model.EmployeeDao;
import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.model.response.PersonNameDTO;
import com.ankuran.wages.model.response.SpouseResponseDTO;

@Component
public class EmployeeMapper {

    public EmployeeResponseDTO mapEmployeeDaoToDTO(EmployeeDao employeeDao){
        EmployeeResponseDTO employeeDTO = new EmployeeResponseDTO();
        if(employeeDao.getStatus() != null && employeeDao.getStatus() > 0) {
        	employeeDTO.setActive(true);
        }
        if(employeeDao.getCentreId() != null) {
            employeeDTO.setCentre(employeeDao.getCentreId());
        }
        if(employeeDao.getJoiningTime() != null) {
            employeeDTO.setDateOfJoining(employeeDao.getJoiningTime());
        }
        if(employeeDao.getId() != null) {
            employeeDTO.setId(employeeDao.getId());
        }
        if(!StringUtils.isEmpty(employeeDao.getFullName())) {
        	PersonNameDTO nameDTO = new PersonNameDTO(null, null, employeeDao.getFullName());
        	employeeDTO.setName(nameDTO);       
        }
        if(!StringUtils.isEmpty(employeeDao.getMobileNo())) {
            employeeDTO.setMobileNo(employeeDao.getMobileNo());
        }
        mapSpouseDetails(employeeDTO, employeeDao);
        return employeeDTO;
    }

	private void mapSpouseDetails(EmployeeResponseDTO employeeDTO, EmployeeDao employeeDao) {
		PersonNameDTO nameDTO = null;
		if (!StringUtils.isEmpty(employeeDao.getSpouseFullName())) {
			nameDTO = new PersonNameDTO(null, null, employeeDao.getSpouseFullName());
		}
		String spouseEmployeeId = !StringUtils.isEmpty(employeeDao.getSpouseEmployeeId()) ? employeeDao.getSpouseEmployeeId() : null;
		if (spouseEmployeeId != null || nameDTO != null) {
			employeeDTO.setHusband(new SpouseResponseDTO(nameDTO, spouseEmployeeId));
		}
	}
}