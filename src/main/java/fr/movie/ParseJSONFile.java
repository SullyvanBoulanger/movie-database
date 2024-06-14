package fr.movie;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.movie.dtos.MovieDto;

public class ParseJSONFile {
    private static String FILE_PATH = "src/main/resources/data/sample.json";
    public static void main(String[] args) {
        File jsonFile = new File(FILE_PATH);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            MovieDto movieDto = objectMapper.readValue(jsonFile, MovieDto.class);
            System.out.println(movieDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
