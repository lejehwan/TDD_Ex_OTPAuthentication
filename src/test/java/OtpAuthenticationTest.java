import org.example.domain.User;
import org.example.exception.DuplicationIdException;
import org.example.repository.MemoryUserRepository;
import org.example.service.UserRegisterService;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class OtpAuthenticationTest {

    private MemoryUserRepository fakeRepository;
    private UserService userService;

    @BeforeEach
    @Test
    void setUp(){
        fakeRepository = new MemoryUserRepository();
        userService = new UserRegisterService(fakeRepository);
    }

    @DisplayName("같은 ID가 없으면 사용자 등록하기")
    @Test
    void userRegister(){
        //given
        User newUser = new User(1L, "lejehwan", "1234");

        //when
        fakeRepository.register(newUser);
        User user = fakeRepository.findById(1L);

        //then
        assertThat(user.getId()).isEqualTo(newUser.getId());
        assertThat(user.getUserName()).isEqualTo(newUser.getUserName());
    }

    @DisplayName("이미 같은 ID가 존재하면 사용자 등록 실패")
    @Test
    void duplicationIdExists(){
        //given
        fakeRepository.register(new User(1L, "lejehwan", "1234"));

        //when
        //then
        assertThatThrownBy(() -> userService.UserRegister(new User(1L, "lejehwan", "1234")))
                .isInstanceOf(DuplicationIdException.class)
                .hasMessageContaining("이미 사용중인 ID 입니다.");
    }

    @Disabled("테스트하고자 하는 내용 이외의 불필요한 코드")
    @DisplayName("userId로 사용자 조회하기")
    @Test
    void findUserById(){

    }

    @Disabled("테스트하고자 하는 내용 이외의 불필요한 코드")
    @DisplayName("로그인 하기")
    @Test
    void userLogin(){

    }

    @Disabled
    @DisplayName("TOTP 발급하기")
    @Test
    void totpIssueRegister(){

    }

    @Disabled
    @DisplayName("HOTP 발급하기")
    @Test
    void hotpIssueRegister(){

    }

    @Disabled("테스트하고자 하는 내용 이외의 불필요한 코드")
    @DisplayName("OTP 발급 확인하기")
    @Test
    void otpConfirm(){

    }

    @Disabled
    @DisplayName("otpId로 OTP 타입 확인하기")
    @Test
    void otpTypeCheck(){

    }

    @Disabled
    @DisplayName("TOTP 인증 하기")
    @Test
    void totpAuthentication(){

    }

    @Disabled
    @DisplayName("HOTP 인증 하기")
    @Test
    void hotpAuthentication(){

    }
}
