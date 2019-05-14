package ua.ita.smartcarservice.service.impl.files;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.ita.smartcarservice.entity.files.CarNorms;
import ua.ita.smartcarservice.exceptions.CarRegisteredAlreadyExсeption;
import ua.ita.smartcarservice.exceptions.ContentNotFoundException;
import ua.ita.smartcarservice.repository.files.CarNormsRepository;
import ua.ita.smartcarservice.service.files.CarNormsService;

import javax.xml.bind.ValidationException;
import java.io.*;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;

@Service
public class CarNormsServiceImpl extends JsonToObjectTransformer implements CarNormsService {

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    @Autowired
    private CarNormsRepository carNormsRepository;

    @Override
    public void storeCarNorms(MultipartFile file) {

        if (file.isEmpty()) {
            throw new ContentNotFoundException(file);
        }

        try (InputStream stream = file.getInputStream()) {

            ObjectMapper objectMapper = new ObjectMapper();

            //to ignore the new fields
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //doesn't fail when fields are missing or empty
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES,false);

            CarNorms[] carNorms = objectMapper.readValue(stream, CarNorms[].class);
            List<CarNorms> listOfCarNorms = Arrays.asList(carNorms);
            listOfCarNorms.forEach(norms -> carNormsRepository.save(norms));

        } catch (Exception e) {
            logger.error("Error during " + file.getOriginalFilename() + " parsing. Details: ", e);
        }
    }

}
