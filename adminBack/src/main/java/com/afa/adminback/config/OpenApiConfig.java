package com.afa.adminback.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//package com.afa.adminback.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
//
//@Configuration
//@EnableSwagger2WebMvc
//如更改回这个配置，应更改本文件名为swaggerConfig.java
//public class swaggerConfig {
//    /**
//     * 创建API应用
//     * apiInfo() 增加API相关信息
//     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
//     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
//     *
//     * @return
//     */
//    @Bean
//    public Docket restApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("标准接口")
//                .apiInfo(apiInfo("Spring Boot中使用Swagger2构建RESTful APIs", "1.0"))
//                .useDefaultResponseMessages(true)
//                .forCodeGeneration(false)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.afa.adminback.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    /**
//     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
//     * 访问地址：http://ip:port/swagger-ui.html
//     *
//     * @return
//     */
//    private ApiInfo apiInfo(String title, String version) {
//        return new ApiInfoBuilder()
//                .title(title)
//                .description("更多请关注: https://blog.csdn.net/xqnode")
//                .termsOfServiceUrl("https://blog.csdn.net/xqnode")
//                .contact(new Contact("xqnode", "https://blog.csdn.net/xqnode", "xiaqingweb@163.com"))
//                .version(version)
//                .build();
//    }
//}
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().info(new Info() //
                .title("SpringDoc API Test") //
                .description("SpringDoc Simple Application Test") //
                .version("0.0.1"));
    }
}