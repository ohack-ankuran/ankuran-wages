package com.ankuran.wages.provider.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ankuran.wages.mapper.OutstandingAmountMapper;
import com.ankuran.wages.model.OutstandingAmountDao;
import com.ankuran.wages.model.response.OutstandingAmountResponseDTO;
import com.ankuran.wages.provider.OutstandingAmountProvider;
import com.ankuran.wages.repository.OutstandingAmountRepository;

@Component
public class OutstansingAmountProviderImpl implements OutstandingAmountProvider {

	@Autowired
	OutstandingAmountRepository outstandingAmountRepository;
	
	@Autowired
	OutstandingAmountMapper outstandingAmountMapper;
	
	@Override
	public OutstandingAmountResponseDTO fetchOutstandingAmountByCentreIDAndEmployeeId(Long centreId, Long employeeId) {
		OutstandingAmountDao outstandingAmountDao = outstandingAmountRepository.findByCentreIdAndEmployeeId(centreId, employeeId);
		OutstandingAmountResponseDTO outstandingAmountResponseDTO = outstandingAmountMapper.mapOutstandingAmountDaoToDTO(outstandingAmountDao);
		return outstandingAmountResponseDTO;
	}

	@Override
	public List<OutstandingAmountResponseDTO> fetchEmployeeOutstandingAmountsByCentreID(Long centreId) {
		List<OutstandingAmountDao> outstandingAmountDaoList = outstandingAmountRepository.findAllByCentreId(centreId);
		List<OutstandingAmountResponseDTO> outstandingAmountDTOs = outstandingAmountDaoList.stream().filter(Objects::nonNull).map(x -> outstandingAmountMapper.mapOutstandingAmountDaoToDTO(x)).collect(Collectors.toList());
		return outstandingAmountDTOs;
	}

	@Override
	public Long addOutstandingAmount(OutstandingAmountResponseDTO outstandingAmountResponseDTO) {
		OutstandingAmountDao outstandingAmountDao = outstandingAmountMapper.mapOutstandingAmountDTOToDao(outstandingAmountResponseDTO);
		outstandingAmountRepository.save(outstandingAmountDao);
		return outstandingAmountDao.getId();
	}

	@Override
	public Long patchOutstandingAmountByCentreIDAndEmployeeId(OutstandingAmountResponseDTO outstandingAmountResponseDTO) {
		OutstandingAmountDao outstandingAmountDao = outstandingAmountRepository.findByCentreIdAndEmployeeId(outstandingAmountResponseDTO.getCentreId(), outstandingAmountResponseDTO.getEmployeeId());
		//if exists->update, else create new.
		if (outstandingAmountDao != null) {
			outstandingAmountDao.setOutstanding_amount(outstandingAmountResponseDTO.getOutstandingDue());
			outstandingAmountRepository.save(outstandingAmountDao);
			return outstandingAmountDao.getId();
		} else {
			Long outstandingAmountId = addOutstandingAmount(outstandingAmountResponseDTO);
			return outstandingAmountId;
		}
	}
}
