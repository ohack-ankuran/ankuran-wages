package com.ankuran.wages.mapper;

import org.springframework.stereotype.Component;

import com.ankuran.wages.model.SettlementDao;
import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.model.response.SettlementDTO;

@Component
public class SettlementMapper {

	public SettlementDao mapSettlementDtoToDao(SettlementDTO settlementDTO) {
		SettlementDao settlementDao = new SettlementDao();
		if (settlementDTO.getSettledBy() != null) {
			settlementDao.setActor(settlementDTO.getSettledBy().getId());
		}
		settlementDao.setAmount(settlementDTO.getAmount());
		settlementDao.setAmountAfter(settlementDTO.getAmountAfter());
		settlementDao.setAmountBefore(settlementDTO.getAmountBefore());
		settlementDao.setCentreId(settlementDTO.getCentreId());
		if (Boolean.FALSE.equals(settlementDTO.getCorrection())) {
			settlementDao.setCorrection(0);
		} else if (Boolean.TRUE.equals(settlementDTO.getCorrection())) {
			settlementDao.setCorrection(1);
		}
		if (settlementDTO.getCounterparty() != null) {
			settlementDao.setCounterparty(settlementDTO.getCounterparty().getId());
		} 
		settlementDao.setNotes(settlementDTO.getNotes());
		settlementDao.setTimeCreated(settlementDTO.getTimeCreated());
		return settlementDao;
	}
	
	public SettlementDTO mapSettlementDaoToDto(SettlementDao settlementDao) {
		SettlementDTO settlementDTO = new SettlementDTO();
		
		settlementDTO.setAmount(settlementDao.getAmount());
		settlementDTO.setAmountAfter(settlementDao.getAmountAfter());
		settlementDTO.setAmountBefore(settlementDao.getAmountBefore());
		settlementDTO.setCentreId(settlementDao.getCentreId());
		if (settlementDao.getCorrection() > 0) {
			settlementDTO.setCorrection(Boolean.TRUE);
		} else {
			settlementDTO.setCorrection(Boolean.FALSE);
		}
		if (settlementDao.getCounterparty() != null && settlementDao.getCounterparty() > 0) {
			EmployeeResponseDTO emp = new EmployeeResponseDTO();
			emp.setId(settlementDao.getCounterparty());
			settlementDTO.setCounterparty(emp);
		}
		if (settlementDao.getActor() != null && settlementDao.getActor() > 0) {
			EmployeeResponseDTO emp = new EmployeeResponseDTO();
			emp.setId(settlementDao.getActor());
			settlementDTO.setSettledBy(emp);
		}
		settlementDTO.setId(settlementDao.getId());
		settlementDTO.setNotes(settlementDao.getNotes());
		settlementDTO.setTimeCreated(settlementDao.getTimeCreated());
		
		return settlementDTO;
	}
}
