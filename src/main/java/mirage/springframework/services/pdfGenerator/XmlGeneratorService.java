package mirage.springframework.services.pdfGenerator;

import java.io.File;

/**
 * Created by Mirage on 19/12/2016.
 */
public interface XmlGeneratorService {

    File generateXml(Integer curriculum);
}
