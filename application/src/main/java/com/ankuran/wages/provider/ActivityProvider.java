package com.ankuran.wages.provider;

import com.ankuran.wages.model.response.ActivityResponseDTO;

public interface ActivityProvider {

	ActivityResponseDTO addActivity(Long centreId, Long employeeId, ActivityResponseDTO activity);

}
