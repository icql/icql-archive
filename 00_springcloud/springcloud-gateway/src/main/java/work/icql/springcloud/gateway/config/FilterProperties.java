package work.icql.springcloud.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/2/25 16:28
 * @Title FilterProperties
 * @Description FilterProperties
 */
@ConfigurationProperties(prefix = "icql.filter")
@Component
public class FilterProperties {
    private List<String> allowPaths;

    public List<String> getAllowPaths() {
        return allowPaths;
    }

    public void setAllowPaths(List<String> allowPaths) {
        this.allowPaths = allowPaths;
    }
}
