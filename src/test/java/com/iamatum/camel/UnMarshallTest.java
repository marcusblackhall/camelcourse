package com.iamatum.camel;

import com.iamatum.camel.jaxb.Person;
import org.junit.jupiter.api.Test;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnMarshallTest {


    @Test
    void shouldUnmarshallXml() throws JAXBException, IOException {

        JAXBContext context = JAXBContext.newInstance(Person.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        Path path = Paths.get("src", "data", "input", "person.xml");
        boolean exists = Files.exists(path);
        assertTrue(exists);

        File file = path.toFile();
        List<String> strings = Files.readAllLines(path);

        Person unmarshal = (Person) unmarshaller.unmarshal(file);
        assertThat(unmarshal.getFirstName()).isEqualTo("Ronald");

    }

    @Test
    void marshallObject() throws JAXBException {

        Person personType = new Person();
        personType.setFirstName("George");
        personType.setLastName("benson");

        JAXBContext context = JAXBContext.newInstance(Person.class);

        Marshaller marshaller = context.createMarshaller();
        Path outFile = Paths.get("src/data/created.xml");
        marshaller.marshal( personType,outFile.toFile());

    }
}
