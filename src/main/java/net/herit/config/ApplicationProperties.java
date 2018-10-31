package net.herit.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@Slf4j
public class ApplicationProperties {

	@Value("${output.msg.base.path}")
	String msgBasePath;
	
	public ApplicationProperties() {
		log.debug("\n======ApplicationProperties contructor.=======\n");
	}
}
