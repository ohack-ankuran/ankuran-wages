package com.ankuran.wages.mapper;

import com.ankuran.wages.model.response.EmployeeResponseDTO;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ankuran.wages.model.EmployeeDao;

@Component
public class EmployeeMapper {

    public EmployeeResponseDTO mapEmployeeDaoToDTO(EmployeeDao employeeDao){
        EmployeeResponseDTO employeeDTO = new EmployeeResponseDTO();
        if(employeeDao.getId() != null) {
            employeeDTO.setId(employeeDao.getId());
        }
        if(!StringUtils.isEmpty(employeeDao.getFullName())) {
            employeeDTO.setFullName(employeeDao.getFullName());
        }
        if(employeeDao.getCenterId() != null) {
            employeeDTO.setCenterId(employeeDao.getCenterId());
        }
        if(!StringUtils.isEmpty(employeeDao.getSpouseEmployeeId())) {
            employeeDTO.setSpouseEmployeeId(employeeDao.getSpouseEmployeeId());
        }
        if(!StringUtils.isEmpty(employeeDao.getSpouseFullName())) {
            employeeDTO.setSpouseFullName(employeeDao.getSpouseFullName());
        }
        if(!StringUtils.isEmpty(employeeDao.getMobileNo())) {
            employeeDTO.setMobileNo(employeeDao.getMobileNo());
        }
        if(employeeDao.getStatus() != null) {
            employeeDTO.setStatus(employeeDao.getStatus());
        }
        if(employeeDao.getCreatedAt() != null) {
            employeeDTO.setCreatedAt(employeeDao.getCreatedAt());
        }
        if(employeeDao.getJoiningTime() != null) {
            employeeDTO.setJoiningTime(employeeDao.getJoiningTime());
        }

        return employeeDTO;
    }
}