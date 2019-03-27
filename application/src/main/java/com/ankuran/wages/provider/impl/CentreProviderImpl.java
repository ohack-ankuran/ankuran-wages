package com.ankuran.wages.provider.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ankuran.wages.mapper.CentreMapper;
import com.ankuran.wages.model.CentreDao;
import com.ankuran.wages.model.response.CentreResponseDTO;
import com.ankuran.wages.provider.CentreProvider;
import com.ankuran.wages.repository.CenterRepository;

@Component
public class CentreProviderImpl implements CentreProvider {

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private CentreMapper centreMapper;

    @Override
    public CentreResponseDTO fetchCentreById(long centreId) {
        CentreDao centreDao = centerRepository.findById(centreId);
        CentreResponseDTO centreDTO = centreMapper.mapCentreDaoToDTO(centreDao);
        return centreDTO;
    }

	@Override
	public List<CentreResponseDTO> fetchCentres() {
		List<CentreDao> centreDaoList = centerRepository.findAll();
		List<CentreResponseDTO> centreResponseDTOs = centreDaoList.stream().filter(Objects::nonNull).map(x -> centreMapper.mapCentreDaoToDTO(x)).collect(Collectors.toList());
		return centreResponseDTOs;
	}

	@Override
	public Long addCentre(CentreResponseDTO centre) {
		if (centre != null && !StringUtils.isEmpty(centre.getName())) {
			CentreDao centreDao = centreMapper.mapCentreDtoToDao(centre);
			CentreDao existingCentreDao = centerRepository.findByNameIgnoreCase(centre.getName());
			if (existingCentreDao == null) {
				centerRepository.save(centreDao);
				return centreDao.getId();
			} else {
				return Long.valueOf(0);
			}
		}
		return null;
	}
}
