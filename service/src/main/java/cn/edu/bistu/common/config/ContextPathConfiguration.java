package cn.edu.bistu.common.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ContextPathConfiguration {

    /**
     * 协议名称：http
     */
    @Value("${server.schema}")
    private String schema;

    /**
     * 项目context-path：/、/order-system、/work-order
     */
    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * 服务器端口
     */
    @Value("${server.port}")
    private Integer port;

    /**
     * 服务器ip
     */
    @Value("${server.ip}")
    private String ip;

    /**
     * 服务器访问根路径：http://ip:port、http://ip:port/os、http://ip:port/os/work-order
     */
    public String getUrl() {
        String url = getSchema() +
                "://" + getIp() +
                ":" + getPort() +
                (getContextPath().equals("/") ? "" : getContextPath());

        return url;
    }

}
