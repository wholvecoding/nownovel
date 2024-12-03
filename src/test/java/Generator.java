import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class Generator {
    private static final String USERNAME = System.getenv().get("USER");
    /**
     * 项目信息
     */
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static final String JAVA_PATH = "/src/main/java";
    private static final String RESOURCE_PATH = "/src/main/resources";
    private static final String BASE_PACKAGE = "com.wolvescoding.nownovel";
    /*
    数据库信息
     */
    private static final String DATABASE_IP = "localhost";
    private static final String DATABASE_PORT = "3306";
    private static final String DATABASE_NAME = "novel";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "123456";
    public static void main(String[] args) {
        // 传入需要生成的表名，多个用英文逗号分隔，所有用 all 表示
        genCode("all");
    }
    public static void genCode(String tables) {
        FastAutoGenerator.create(String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf-8",DATABASE_IP,DATABASE_PORT,DATABASE_NAME),DATABASE_USERNAME,DATABASE_PASSWORD)
                .globalConfig(builder -> {
                    builder.author("wolvescoding")

                            .commentDate("yyyy/MM/dd")
                            .outputDir(PROJECT_PATH + JAVA_PATH);


                })
                .packageConfig(builder -> builder.parent(BASE_PACKAGE)
                        .entity("dao.entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("dao.mapper")
                        .controller("controller.front")

                        .pathInfo(Collections.singletonMap(OutputFile.xml, PROJECT_PATH + RESOURCE_PATH + "/mapper")))
                .templateConfig(builder -> builder.disable(TemplateType.SERVICE_IMPL)
//                        .disable(TemplateType.SERVICE_IMPL)
//
//
                )
                .strategyConfig(builder -> builder.addInclude(getTables(tables))
                        .controllerBuilder().enableFileOverride()
                        .enableRestStyle()
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .enableFileOverride()
                        .mapperBuilder().enableFileOverride()
                        .entityBuilder().enableFileOverride()


                )

                .execute();




    }

    /**
     * 根据genCode 传入的参数，来决定对应的操作
     * @param tables
     * @return
     */
    private static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));

    }
}
