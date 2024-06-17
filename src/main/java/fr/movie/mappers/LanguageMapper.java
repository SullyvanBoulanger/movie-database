package fr.movie.mappers;

import static fr.movie.utils.StringUtils.trim;

import fr.movie.entities.Language;

/**
 * Represents a mapper to map a String to a Language Entity
 */
public class LanguageMapper extends DtoToEntityMapper<String, Language> {

    @Override
    public Language mapDtoToEntity(String dto) {
        Language languageToPut = null;
        String languageName = trim(dto);

        if (dto != null) {
            languageToPut = new Language(languageName);
        }

        return findInMemoryOrElsePut(
                languageName,
                languageToPut);
    }

}
