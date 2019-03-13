package com.ankuran.wages.provider.impl;

import com.ankuran.wages.mapper.EmployeeMapper;
import com.ankuran.wages.model.EmployeeDao;
import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.provider.EmployeeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import com.ankuran.wages.repository.EmployeeRepository;
import org.springframework.stereotype.Component;


@Component
public class EmployerProviderImpl implements EmployeeProvider {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponseDTO fetchEmployeeByCentreIDAndFullName(Long centreId, String fullName) {

        EmployeeDao employeeDao =  employeeRepository.findByCenterIdAndAndFullName(centreId, fullName);
        EmployeeResponseDTO employeeResponseDTO = employeeMapper.mapEmployeeDaoToDTO(employeeDao);;
        return employeeResponseDTO;
    }

}