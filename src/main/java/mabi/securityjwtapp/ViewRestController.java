package mabi.securityjwtapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewRestController {

	
	@RequestMapping("/site")
	public String show() {
		String show = "Hello on my site";
		return show;
	}
}
