package com.ankuran.wages.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/centres/{centreId}")
public interface SettlementResource {

	@PostMapping("/settlements")
}
