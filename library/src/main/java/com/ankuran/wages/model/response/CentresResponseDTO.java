package com.ankuran.wages.model.response;

import java.util.List;

public class CentresResponseDTO {

    private List<CentreResponseDTO> centres;
    
	public CentresResponseDTO(List<CentreResponseDTO> centres) {
		super();
		this.centres = centres;
	}

	public List<CentreResponseDTO> getCentres() {
		return centres;
	}

	public void setCentres(List<CentreResponseDTO> centres) {
		this.centres = centres;
	}
    
}
