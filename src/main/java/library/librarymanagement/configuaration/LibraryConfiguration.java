package library.librarymanagement.configuaration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "validations")
@Data
public class LibraryConfiguration {
    private int assignPeriod;
    private int maximumBookAllocation;
    private int finePerDayInGbp;
}
