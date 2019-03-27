package com.ankuran.wages.provider.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ankuran.wages.mapper.SettlementMapper;
import com.ankuran.wages.model.SettlementDao;
import com.ankuran.wages.model.response.SettlementDTO;
import com.ankuran.wages.provider.SettlementProvider;
import com.ankuran.wages.repository.SettlementRepository;

@Component
public class SettlementProviderImpl implements SettlementProvider {
	
	@Autowired
	SettlementMapper settlementMapper;
	
	@Autowired
	SettlementRepository settlementRepository;

	@Override
	public SettlementDTO addSettlement(SettlementDTO settlementDTO) {
		if (settlementDTO != null && settlementDTO.getCentreId() != null && settlementDTO.getCentreId() > 0
				&& settlementDTO.getTimeCreated() != null && settlementDTO.getAmount() != null
				&& settlementDTO.getAmountBefore() != null && settlementDTO.getAmountAfter() != null) {
			SettlementDao settlement = settlementMapper.mapSettlementDtoToDao(settlementDTO);
			if (!hasExistingSettlement(settlement)) {
				settlementRepository.save(settlement);
			} else {
				settlement.setId(Long.valueOf(0));
			}
			return settlementMapper.mapSettlementDaoToDto(settlement);
		}
		return null;
	}

	private boolean hasExistingSettlement(SettlementDao settlement) {
		Date lowerTimeCreated = new Date(settlement.getTimeCreated().getTime() - 1000);
		Date upperTimeCreated = new Date(settlement.getTimeCreated().getTime() + 1000);
		List<SettlementDao> existingSettlements = settlementRepository.findAllByCentreIdAndTimeCreatedBetweenOrderByTimeCreatedDesc(settlement.getCentreId(), lowerTimeCreated, upperTimeCreated);
		for (SettlementDao existingSettlement : existingSettlements) {
			if (existingSettlement.getAmount() != null && settlement.getAmount() != null && existingSettlement.getAmount().equals(settlement.getAmount())
					&& existingSettlement.getAmountBefore() != null && settlement.getAmountBefore() != null && existingSettlement.getAmountBefore().equals(settlement.getAmountBefore())
					&& existingSettlement.getAmountAfter() != null && settlement.getAmountAfter() != null && existingSettlement.getAmountAfter().equals(settlement.getAmountAfter()))
				return true;
		}
		return false;
	}

	@Override
	public List<SettlementDTO> getSettlements(Long centreId, Date lowerTimeCreated, Date upperTimeCreated) {
		List<SettlementDTO> settlements = new ArrayList<SettlementDTO>();
		List<SettlementDao> settlementDaos = settlementRepository.findAllByCentreIdAndTimeCreatedBetweenOrderByTimeCreatedDesc(centreId, lowerTimeCreated, upperTimeCreated);
		if (CollectionUtils.isNotEmpty(settlementDaos)) {
			settlements = settlementDaos.stream().map(settlementDao -> settlementMapper.mapSettlementDaoToDto(settlementDao)).collect(Collectors.toList());
		}
		return settlements;
	}

}
