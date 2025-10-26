package boss.team.customerservice.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RefreshScope
public class configTestRestController {
    @Value("${global.params.x}")
    private String x;
    @Value("${global.params.y}")
    private String y;

    @Autowired
    CustomerConfigParams customerConfigParams;

    @GetMapping("/test")
    public Map<String,String> test(){
        return Map.of("x",x,"y",y);
    }

    @GetMapping("/test2")
    public CustomerConfigParams test2(){
        return customerConfigParams;
    }

}
