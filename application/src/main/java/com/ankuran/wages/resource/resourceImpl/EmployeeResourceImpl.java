package com.ankuran.wages.resource.resourceImpl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.model.response.EmployeesResponseDTO;
import com.ankuran.wages.model.response.OutstandingAmountResponseDTO;
import com.ankuran.wages.provider.EmployeeProvider;
import com.ankuran.wages.provider.OutstandingAmountProvider;
import com.ankuran.wages.resource.EmployeeResource;

@Component
public class EmployeeResourceImpl implements EmployeeResource {


	@Autowired
	private EmployeeProvider employeeProvider;

	@Autowired
	OutstandingAmountProvider outstandingAmountProvider;

	@Override
	public ResponseEntity<EmployeeResponseDTO> getEmployee(Long centreId, Long employeeId) {
		EmployeeResponseDTO employeeResponseDTO = employeeProvider.fetchEmployeeByCentreIDAndEmployeeId(centreId, employeeId);
		OutstandingAmountResponseDTO outstandingAmountResponseDTO = outstandingAmountProvider.fetchOutstandingAmountByCentreIDAndEmployeeId(centreId, employeeId);
		if (employeeResponseDTO != null && employeeResponseDTO.getId() != null) {
			if (outstandingAmountResponseDTO != null && outstandingAmountResponseDTO.getOutstandingDue() != null)
				employeeResponseDTO.setOutstandingDue(outstandingAmountResponseDTO.getOutstandingDue());
			return new ResponseEntity<EmployeeResponseDTO>(employeeResponseDTO, HttpStatus.OK);
		}
		return new ResponseEntity<EmployeeResponseDTO>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<EmployeesResponseDTO> getEmployees(Long centreId) {
		List<EmployeeResponseDTO> employeeResponseDTOs = employeeProvider.fetchEmployeesByCentreID(centreId);
		List<OutstandingAmountResponseDTO> outstandingAmountResponseDTOs = outstandingAmountProvider.fetchEmployeeOutstandingAmountsByCentreID(centreId);

		if (CollectionUtils.isNotEmpty(employeeResponseDTOs)) {
			if (CollectionUtils.isNotEmpty(outstandingAmountResponseDTOs)) {
				for (EmployeeResponseDTO employeeResponseDTO : employeeResponseDTOs) {
					for (OutstandingAmountResponseDTO outstandingAmountResponseDTO : outstandingAmountResponseDTOs) {
						if (employeeResponseDTO.getId().equals(outstandingAmountResponseDTO.getEmployeeId())) {
							employeeResponseDTO.setOutstandingDue(outstandingAmountResponseDTO.getOutstandingDue());
							break;
						}
					}
				}
			}
			return new ResponseEntity<EmployeesResponseDTO>(new EmployeesResponseDTO(employeeResponseDTOs), HttpStatus.OK);
		}
		return new ResponseEntity<EmployeesResponseDTO>(HttpStatus.NO_CONTENT);
	}

}