package application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private Gender gender;
    private Integer age;
    private MobileCarrier mobileCarrier;
}
