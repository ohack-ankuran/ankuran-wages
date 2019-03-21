package com.ankuran.wages.mapper;

import com.ankuran.wages.model.CentreDao;
import com.ankuran.wages.model.response.CentreResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CentreMapper {

	public CentreResponseDTO mapCentreDaoToDTO(CentreDao centreDao){
		CentreResponseDTO centreDTO = new CentreResponseDTO();
		if (centreDao != null) {
			if(centreDao.getId() != null) {
				centreDTO.setId(centreDao.getId());
			}
			if(!StringUtils.isEmpty(centreDao.getName())) {
				centreDTO.setName(centreDao.getName());
			}
		}
		return centreDTO;
	}

}
