package com.ankuran.wages.provider.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ankuran.wages.mapper.EmployeeMapper;
import com.ankuran.wages.model.EmployeeDao;
import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.provider.EmployeeProvider;
import com.ankuran.wages.repository.EmployeeRepository;


@Component
public class EmployerProviderImpl implements EmployeeProvider {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponseDTO fetchEmployeeByCentreIDAndFullName(Long centreId, String fullName) {
        EmployeeDao employeeDao =  employeeRepository.findByCentreIdAndFullName(centreId, fullName);
        EmployeeResponseDTO employeeResponseDTO = employeeMapper.mapEmployeeDaoToDTO(employeeDao);
        return employeeResponseDTO;
    }
    
    @Override
    public EmployeeResponseDTO fetchEmployeeByCentreIDAndEmployeeId(Long centreId, Long employeeId) {
        EmployeeDao employeeDao =  employeeRepository.findByCentreIdAndId(centreId, employeeId);
        EmployeeResponseDTO employeeResponseDTO = employeeMapper.mapEmployeeDaoToDTO(employeeDao);
        return employeeResponseDTO;
    }

	@Override
	public List<EmployeeResponseDTO> fetchEmployeesByCentreID(Long centreId) {
		List<EmployeeDao> employeeDaoList = employeeRepository.findAllByCentreIdAndStatusNotOrderByFullNameAsc(centreId, Byte.valueOf("0"));
		List<EmployeeResponseDTO> employeeResponseDTOs = employeeDaoList.stream().filter(Objects::nonNull).map(x -> employeeMapper.mapEmployeeDaoToDTO(x)).collect(Collectors.toList());
		return employeeResponseDTOs;
	}

	@Override
	public Long addEmployee(EmployeeResponseDTO employeeResponseDTO) {
		Date lowerTimeOfJoining = new Date(employeeResponseDTO.getTimeOfJoining().getTime() - 10000);
		Date upperTimeOfJoining = new Date(employeeResponseDTO.getTimeOfJoining().getTime() + 10000);
		List<EmployeeDao> existingEmployeesDao = employeeRepository.findByCentreIdAndFullNameAndJoiningTimeBetween(employeeResponseDTO.getCentre(),
				employeeResponseDTO.getFullName(), lowerTimeOfJoining, upperTimeOfJoining);
		for (EmployeeDao existingEmployeeDao : existingEmployeesDao) {
			if (existingEmployeeDao != null && existingEmployeeDao.getId() != null && existingEmployeeDao.getId() > 0) {
				return Long.valueOf(0);
			}
		}
		EmployeeDao employeeDao = employeeMapper.mapEmployeeDTOToDao(employeeResponseDTO);
		employeeRepository.save(employeeDao);
		return employeeDao.getId();
	}

	@Override
	public EmployeeResponseDTO deleteEmployeeByCentreIDAndEmployeeId(Long centreId, Long employeeId) {
		EmployeeDao employeeDao =  employeeRepository.findByCentreIdAndId(centreId, employeeId);
		if (employeeDao != null && employeeDao.getStatus() != null && employeeDao.getStatus() > 0) {
			employeeDao.setStatus(Byte.valueOf("0"));
			employeeRepository.save(employeeDao);
			EmployeeResponseDTO employeeResponseDTO = employeeMapper.mapEmployeeDaoToDTO(employeeDao);
			return employeeResponseDTO;
		} else {
			return null;
		}
	}

	@Override
	public EmployeeResponseDTO patchEmployeeByCentreIDAndEmployeeId(Long centreId, Long employeeId, EmployeeResponseDTO employeeResponseDTO) {
		EmployeeDao employeeDao =  employeeRepository.findByCentreIdAndId(centreId, employeeId);
		if (employeeDao != null && employeeDao.getCentreId() != null && employeeDao.getCentreId().equals(employeeResponseDTO.getCentre())
				&& employeeDao.getId() != null && employeeDao.getId().equals(employeeResponseDTO.getId())) {
			EmployeeDao employeeDaoToPatch = employeeMapper.mapEmployeeDTOToDao(employeeResponseDTO);
			employeeDaoToPatch.setJoiningTime(employeeDao.getJoiningTime());
			employeeDaoToPatch.setId(employeeDao.getId());
			//default is active inside mapper
			if (employeeResponseDTO.getActive() != null && Boolean.FALSE.equals(employeeResponseDTO.getActive())) {
				employeeDaoToPatch.setStatus(Byte.valueOf("0"));
			}
			employeeRepository.save(employeeDaoToPatch);
			return employeeMapper.mapEmployeeDaoToDTO(employeeDaoToPatch);
		} else {
			return null;
		}
	}

}