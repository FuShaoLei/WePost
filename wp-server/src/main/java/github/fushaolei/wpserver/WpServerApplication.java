package github.fushaolei.wpserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = "github.fushaolei.wpserver.filter")
@SpringBootApplication
public class WpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WpServerApplication.class, args);
    }

}
