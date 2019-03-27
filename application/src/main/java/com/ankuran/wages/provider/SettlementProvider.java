package com.ankuran.wages.provider;

import java.util.Date;
import java.util.List;

import com.ankuran.wages.model.response.SettlementDTO;

public interface SettlementProvider {

	SettlementDTO addSettlement(SettlementDTO settlementDTO);
	List<SettlementDTO> getSettlements(Long centreId, Date lowerTimeCreated, Date upperTimeCreated);
}
