package com.ankuran.wages.resource.resourceImpl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.model.response.EmployeeStoreResponseDTO;
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

	@Override
	public ResponseEntity<EmployeeStoreResponseDTO> addEmployee(Long centreId, EmployeeResponseDTO employee) {
		employee.setCentre(centreId);
		/*
		 * Run validations:
		 * check if centre exists, check for mandatory fields in employee before proceeding
		 */
		Long employeeId = employeeProvider.addEmployee(employee);
		if (employeeId != null && employeeId > 0) {
			Long outstandingAmountId = outstandingAmountProvider.addOutstandingAmount(new OutstandingAmountResponseDTO(employee.getOutstandingDue(),
					centreId, employeeId));
			if (outstandingAmountId != null && outstandingAmountId > 0) {
				return new ResponseEntity<EmployeeStoreResponseDTO>(new EmployeeStoreResponseDTO(employeeId, centreId), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<EmployeeStoreResponseDTO>(HttpStatus.EXPECTATION_FAILED);
			}
		}
		return new ResponseEntity<EmployeeStoreResponseDTO>(HttpStatus.FORBIDDEN);
	}

	@Override
	public ResponseEntity<EmployeeResponseDTO> deleteEmployee(Long centreId, Long employeeId) {
		if (centreId != null && centreId > 0 && employeeId != null && employeeId > 0) {
			EmployeeResponseDTO employeeResponseDTO = employeeProvider.deleteEmployeeByCentreIDAndEmployeeId(centreId, employeeId);
			OutstandingAmountResponseDTO outstandingAmountResponseDTO = outstandingAmountProvider.fetchOutstandingAmountByCentreIDAndEmployeeId(centreId, employeeId);
			if (employeeResponseDTO != null && employeeResponseDTO.getId() != null) {
				if (outstandingAmountResponseDTO != null && outstandingAmountResponseDTO.getOutstandingDue() != null)
					employeeResponseDTO.setOutstandingDue(outstandingAmountResponseDTO.getOutstandingDue());
				return new ResponseEntity<EmployeeResponseDTO>(employeeResponseDTO, HttpStatus.OK);
			}
			return new ResponseEntity<EmployeeResponseDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EmployeeResponseDTO>(HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<EmployeeResponseDTO> patchEmployee(Long centreId, Long employeeId,
			EmployeeResponseDTO employee) {
		if (centreId != null && centreId > 0 && employeeId != null && employeeId > 0 
				&& employee != null && employee.getCentre() != null && employee.getCentre() > 0
				&& employee.getId() != null && employee.getId() > 0) {
			EmployeeResponseDTO employeeResponseDTO = employeeProvider.patchEmployeeByCentreIDAndEmployeeId(centreId, employeeId, employee);
			if (employeeResponseDTO != null) {
				if (employee.getOutstandingDue() != null && employee.getOutstandingDue() > 0) {
					outstandingAmountProvider.patchOutstandingAmountByCentreIDAndEmployeeId(new OutstandingAmountResponseDTO(employee.getOutstandingDue(), centreId, employeeId));
					employeeResponseDTO.setOutstandingDue(employee.getOutstandingDue());
				}
				return new ResponseEntity<EmployeeResponseDTO>(employeeResponseDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<EmployeeResponseDTO>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<EmployeeResponseDTO>(HttpStatus.BAD_REQUEST);
	}

}