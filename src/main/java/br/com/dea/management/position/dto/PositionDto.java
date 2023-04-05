package br.com.dea.management.position.dto;

import br.com.dea.management.position.domain.Position;
import br.com.dea.management.user.domain.User;
import br.com.dea.management.user.dto.UserDto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto {

    private Long id;
    private String description;
    private String seniority;

}
