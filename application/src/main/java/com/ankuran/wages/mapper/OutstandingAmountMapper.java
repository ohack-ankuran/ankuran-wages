package com.ankuran.wages.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ankuran.wages.model.OutstandingAmountDao;
import com.ankuran.wages.model.response.OutstandingAmountResponseDTO;

@Component
public class OutstandingAmountMapper {

	public OutstandingAmountResponseDTO mapOutstandingAmountDaoToDTO(OutstandingAmountDao outstandingAmountDao) {
		OutstandingAmountResponseDTO outstandingAmountResponseDTO = new OutstandingAmountResponseDTO();
		if (outstandingAmountDao != null) {
			if (!StringUtils.isEmpty(outstandingAmountDao.getCentreId()))
				outstandingAmountResponseDTO.setCentreId(outstandingAmountDao.getCentreId());
			if (!StringUtils.isEmpty(outstandingAmountDao.getEmployeeId()))
				outstandingAmountResponseDTO.setEmployeeId(outstandingAmountDao.getEmployeeId());
			if (outstandingAmountDao.getOutstanding_amount() != null)
				outstandingAmountResponseDTO.setOutstandingDue(outstandingAmountDao.getOutstanding_amount());
		}
		return outstandingAmountResponseDTO;
	}
}
