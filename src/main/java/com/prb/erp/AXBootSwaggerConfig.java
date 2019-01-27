package com.prb.erp;

import com.chequer.axboot.core.config.AXBootContextConfig;
import com.chequer.axboot.core.parameter.RequestParams;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.prb.erp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.inject.Inject;
import javax.inject.Named;


@Configuration
@EnableSwagger
public class AXBootSwaggerConfig {
    @Inject
    private SpringSwaggerConfig springSwaggerConfig;

    @Bean
    public SwaggerSpringMvcPlugin customImplementation(@Named(value = "axBootContextConfig") AXBootContextConfig axBootContextConfig) {
        String[] splitJdbcUrl = StringUtils.stringSplit(axBootContextConfig.getDataSource().getUrl(), "=");
        String dataBaseName = " ( " + splitJdbcUrl[1] + " )";
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(new ApiInfo("마인드에듀 API" + dataBaseName  , "API Demonstration", axBootContextConfig.getDataSource().getUrl(), "", "", ""))
                .includePatterns("/api/v4.*")
              //  .includePatterns("/api/edu.*")
                .ignoredParameterTypes(AuthenticationPrincipal.class, RequestParams.class);
    }

    @Bean(name = "axBootContextConfig")
    public AXBootContextConfig axBootContextConfig() {
        return new AXBootContextConfig();
    }
}
