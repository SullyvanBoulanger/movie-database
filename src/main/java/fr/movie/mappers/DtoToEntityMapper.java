package fr.movie.mappers;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Abtract class representing a mapper to map Dto to Entity
 * T represents Dto class
 * U represents Entity class
 */
@NoArgsConstructor
@AllArgsConstructor
public abstract class DtoToEntityMapper<T, U> {
    protected Map<String, U> inMemoryEntities = new HashMap<>();

    /**
     * Map a given Dto to an Entity and keep it in memory
     * 
     * @param <U> Dto class
     * @param dto Dto to map
     * @return Entity mapped from Dto
     */
    public abstract U mapDtoToEntity(T dto);

    /**
     * Find an object T from provided map and if not exist, put the provided object
     * at the key
     * 
     * @param <T>          Type of the value
     * @param inMemoryMap  In memory map of the Type requested
     * @param key          String key
     * @param objectoToPut Object to put if the map does not contain key
     * @return Object of Value Type finded or else, the object put
     */
    protected U findInMemoryOrElsePut(String key, U objectoToPut) {
        if (!inMemoryEntities.containsKey(key)) {
            inMemoryEntities.put(key, objectoToPut);

            return objectoToPut;
        }

        return inMemoryEntities.get(key);
    }

}
