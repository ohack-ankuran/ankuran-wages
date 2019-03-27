package com.ankuran.wages.provider;

import java.util.List;

import com.ankuran.wages.model.response.CentreResponseDTO;

public interface CentreProvider {

    public CentreResponseDTO fetchCentreById(long centreId);

	public List<CentreResponseDTO> fetchCentres();

	public Long addCentre(CentreResponseDTO centre);
}
