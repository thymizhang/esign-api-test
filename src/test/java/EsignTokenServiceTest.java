import com.esign.EsignApplication;
import com.esign.service.EsignTokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @Author thymi
 * @Date 2020/12/30
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {EsignApplication.class})
public class EsignTokenServiceTest {

    @Autowired
    EsignTokenService esignTokenService;

    @Test
    public void getToken(){
        String token = esignTokenService.getToken();
        System.out.println("令牌：" + token);
    }
}
