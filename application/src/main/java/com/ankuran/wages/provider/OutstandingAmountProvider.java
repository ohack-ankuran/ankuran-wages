package com.ankuran.wages.provider;

import java.util.List;

import com.ankuran.wages.model.response.OutstandingAmountResponseDTO;

public interface OutstandingAmountProvider {

    public OutstandingAmountResponseDTO fetchOutstandingAmountByCentreIDAndEmployeeId(Long centreId, Long employeeId);

	public List<OutstandingAmountResponseDTO> fetchEmployeeOutstandingAmountsByCentreID(Long centreId);
}