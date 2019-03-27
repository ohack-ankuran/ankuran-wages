package com.ankuran.wages.resource.resourceImpl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ankuran.wages.model.response.CentreResponseDTO;
import com.ankuran.wages.model.response.CentresResponseDTO;
import com.ankuran.wages.provider.CentreProvider;
import com.ankuran.wages.resource.CentreResource;


@Component
public class CentreResourceImpl implements CentreResource {

    @Autowired
    private CentreProvider centreProvider;

    @Override
    public ResponseEntity<CentreResponseDTO> getCentreId(long centreId) {
        CentreResponseDTO centreResponseDTO = centreProvider.fetchCentreById(centreId);

        if (centreResponseDTO != null && centreResponseDTO.getId() != null) {
            return new ResponseEntity<CentreResponseDTO>(centreResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<CentreResponseDTO>(HttpStatus.NO_CONTENT);
    }

	@Override
	public ResponseEntity<CentresResponseDTO> getCentres() {
		List<CentreResponseDTO> centreResponseDTOs = centreProvider.fetchCentres();
		if (CollectionUtils.isNotEmpty(centreResponseDTOs)) {
			return new ResponseEntity<CentresResponseDTO>(new CentresResponseDTO(centreResponseDTOs), HttpStatus.OK);
		}
		return new ResponseEntity<CentresResponseDTO>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<CentreResponseDTO> addCentre(CentreResponseDTO centre) {
		Long centreId = centreProvider.addCentre(centre);
		if (centreId != null && centreId > 0) {
			centre.setId(centreId);
			return new ResponseEntity<CentreResponseDTO>(centre, HttpStatus.CREATED);
		} else if (centreId != null && centreId == 0) {
			return new ResponseEntity<CentreResponseDTO>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<CentreResponseDTO>(HttpStatus.BAD_REQUEST);
	}
}
