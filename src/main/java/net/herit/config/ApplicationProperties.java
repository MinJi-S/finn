package net.herit.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration	// 어플리케이션 컨텍스트 또는 빈 팩토리라고 알려주는 것
@MapperScan(basePackages = {"net.herit.service."})
@Data
@Slf4j
public class ApplicationProperties {

	@Value("${output.msg.base.path}")
	String msgBasePath;
	
	public ApplicationProperties() {
		log.debug("\n======ApplicationProperties contructor.=======\n");
	}

	/**
	 * SqlSessionFactory 설정 빈 등록
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);

		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*Mapper.xml");

		sessionFactory.setMapperLocations(res);

		return sessionFactory.getObject();
	}
}
