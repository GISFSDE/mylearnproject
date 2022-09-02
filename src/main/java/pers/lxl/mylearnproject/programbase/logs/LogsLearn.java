package pers.lxl.mylearnproject.programbase.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lxl
 * @Date: 2022/09/02/14:45
 * @Description:
 *
 * logback在启动时，根据以下步骤寻找配置文件：
 * ①在classpath中寻找logback-test.xml文件→
 * ②如果找不到logback-test.xml，则在 classpath中寻找logback.groovy文件→
 * ③如果找不到 logback.groovy，则在classpath中寻找logback.xml文件
 * 如果上述的文件都找不到，则logback会使用JDK的SPI机制查找 META-INF/services/ch.qos.logback.classic.spi.Configurator中的 logback 配置实现类，这个实现类必须实现Configuration接口，使用它的实现来进行配置
 * 如果上述操作都不成功，logback 就会使用它自带的 BasicConfigurator 来配置，并将日志输出到console。
 */
public class LogsLearn {
    private static final Logger logger = LoggerFactory.getLogger(LogsLearn.class);

    public static void main(String[] args) {
        logger.error("严重警告");
        logger.warn("警告");
        logger.info("普通信息");
        logger.debug("调试信息");
        logger.info("Set score {} for Person {} ok.", 100, "帅哥");
    }
}
