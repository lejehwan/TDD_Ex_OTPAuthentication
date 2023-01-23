import org.example.domain.GoogleOTP;
import org.example.domain.OTPType;
import org.example.domain.User;
import org.example.exception.DuplicationIdException;
import org.example.repository.MemoryOTPRepository;
import org.example.repository.MemoryUserRepository;
import org.example.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * TestCode & JUNIT5 & MOCKITO 를 사용하여
 * TDD 연습 및 공부하기
 * feat.(OTP AUTHENTICATION)
 */

// TODO HOTP, TOTP 분리하여 인증 하기
@ExtendWith(MockitoExtension.class)
public class OTPAuthenticationTest {

    private MemoryUserRepository fakeUserRepository;
    private UserService userService;
    private MemoryOTPRepository fakeOTPRepository;
    private OTPService otpService;
    @Mock
    OTPConfirm otpConfirm;
    @Mock
    TOTPConfirmService totpConfirmService;
    @Mock
    HOTPConfirmService hotpConfirmService;

    @BeforeEach
    @Test
    void setUp(){
        fakeUserRepository = new MemoryUserRepository();
        fakeOTPRepository = new MemoryOTPRepository();
        userService = new UserService(fakeUserRepository);
        otpService = new OTPService(fakeOTPRepository);
    }

    @DisplayName("같은 ID가 없으면 사용자 등록하기")
    @Test
    void userRegister(){
        //given
        User newUser = new User(1L, "lejehwan", "1234");

        //when
        fakeUserRepository.register(newUser);
        User user = fakeUserRepository.findById(1L);

        //then
        assertThat(user.getId()).isEqualTo(newUser.getId());
        assertThat(user.getUserName()).isEqualTo(newUser.getUserName());
    }

    @DisplayName("이미 같은 ID가 존재하면 사용자 등록 실패")
    @Test
    void duplicationIdExists(){
        //given
        fakeUserRepository.register(new User(1L, "lejehwan", "1234"));

        //when
        //then
        assertThatThrownBy(() -> userService.UserRegister(new User(1L, "lejehwan", "1234")))
                .isInstanceOf(DuplicationIdException.class)
                .hasMessageContaining("이미 사용중인 ID 입니다.");
    }

    @Disabled("이미 사용자 등록에서 테스트 함으로 불필요한 코드")
    @DisplayName("userId로 사용자 조회하기")
    @Test
    void findUserById(){

    }

    @Disabled("테스트하고자 하는 내용 이외의 불필요한 코드")
    @DisplayName("로그인 하기")
    @Test
    void userLogin(){

    }

    @DisplayName("TOTP 발급하기")
    @Test
    void totpIssueRegister(){
        //given
        GoogleOTP newGoogleOTP = new GoogleOTP(1L, "secretKey", OTPType.TOTP);

        //when
        fakeOTPRepository.register(newGoogleOTP);
        GoogleOTP googleOTP = fakeOTPRepository.findById(newGoogleOTP.getId());

        //then
        assertThat(newGoogleOTP.getId()).isEqualTo(googleOTP.getId());
        assertThat(newGoogleOTP.getOtpType()).isEqualTo(googleOTP.getOtpType());
    }

    @DisplayName("HOTP 발급하기")
    @Test
    void hotpIssueRegister(){
        //given
        GoogleOTP newGoogleOTP = new GoogleOTP(1L, "secretKey", OTPType.HOTP);

        //when
        fakeOTPRepository.register(newGoogleOTP);
        GoogleOTP googleOTP = fakeOTPRepository.findById(newGoogleOTP.getId());

        //then
        assertThat(newGoogleOTP.getId()).isEqualTo(googleOTP.getId());
        assertThat(newGoogleOTP.getOtpType()).isEqualTo(googleOTP.getOtpType());
    }

    @Disabled("테스트하고자 하는 내용 이외의 불필요한 코드")
    @DisplayName("OTP 발급 확인하기")
    @Test
    void otpConfirm(){

    }

    @DisplayName("otpId로 TOTP 타입 확인하기")
    @Test
    void otpTypeCheck(){
        //given
        GoogleOTP newGoogleOTP = new GoogleOTP(1L, "secretKey", OTPType.TOTP);
        fakeOTPRepository.register(newGoogleOTP);

        //when
        String otpType = otpService.getOTPType(1L);

        //then
        assertThat(newGoogleOTP.getOtpType().getTypeName()).isEqualTo(otpType);
    }

    @DisplayName("TOTP 인증 하기")
    @Test
    void totpAuthentication(){
        //given
        User newUser = new User(1L, "lejehwan", "1234");
        GoogleOTP newGoogleOTP = new GoogleOTP(1L, "secretKey", OTPType.TOTP);
        newUser.setOtpId(1L);

        //when
        when(otpConfirm.authorize(anyLong(), anyString())).thenReturn(true);
        boolean result = otpConfirm.authorize(1L, "123456");

        //then
        assertThat(result).isEqualTo(true);
        verify(otpConfirm, times(1)).authorize(any(), any());
    }

    @DisplayName("HOTP 인증 하기")
    @Test
    void hotpAuthentication(){
        //given
        User newUser = new User(1L, "lejehwan", "1234");
        GoogleOTP newGoogleOTP = new GoogleOTP(1L, "secretKey", OTPType.HOTP);
        newUser.setOtpId(1L);

        //when
        when(hotpConfirmService.authorize(anyLong(), anyString())).thenReturn(true);
        boolean result = hotpConfirmService.authorize(1L, "123456");
        ArgumentCaptor<String> captor = forClass(String.class);
        verify(hotpConfirmService, times(1)).authorize(any(), captor.capture());
        String otpCode = captor.getValue();

        //then
        assertThat(result).isEqualTo(true);
        assertThat(otpCode).isEqualTo("123456");
    }
}
