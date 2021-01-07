import com.esign.EsignApplication;
import com.esign.service.EsignPersonalAuthService;
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
public class EsignPersonalAuthServiceTest {

    @Autowired
    EsignPersonalAuthService esignPersonalAuthService;

    @Test
    public void getAccountId(){
        String accountId = esignPersonalAuthService.getAccountId("18675887158");
        System.out.println(accountId);
    }
}
