package gr.aueb.cf.finalprojectcf6.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @NotNull
    private String username;

    @NotEmpty
    private String password;
}
