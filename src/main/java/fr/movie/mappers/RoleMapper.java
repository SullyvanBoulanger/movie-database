package fr.movie.mappers;

import static fr.movie.utils.StringUtils.trim;

import java.util.ArrayList;
import java.util.List;

import fr.movie.dtos.RoleDto;
import fr.movie.entities.Actor;
import fr.movie.entities.Role;
import lombok.AllArgsConstructor;

/**
 * Represents a mapper to map a RoleDto to a Role Entity
 */
@AllArgsConstructor
public class RoleMapper extends DtoToEntityMapper<RoleDto, Role> {

    /**
     * Actor Mapper
     */
    private ActorMapper actorMapper;

    @Override
    public Role mapDtoToEntity(RoleDto dto) {
        String characterName = trim(dto.getCharacterName());
        List<Actor> actors = new ArrayList<>();
        Actor actor = actorMapper.mapDtoToEntity(dto.getActeur());
        actors.add(actor);

        Role role = new Role(characterName, actors);
        return findInMemoryOrElsePut(generateCompositeKey(role), role);
    }

    /**
     * Generate a composite key from a Role
     * 
     * @param role Role
     * @return String composite key
     */
    private String generateCompositeKey(Role role) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(role.getCharacterName())
                .append("|")
                .append(role.getActors().getFirst().getIdentity());

        return stringBuilder.toString();
    }

}
