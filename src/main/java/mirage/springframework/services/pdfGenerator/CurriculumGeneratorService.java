package mirage.springframework.services.pdfGenerator;

import com.lowagie.text.DocumentException;

import java.io.IOException;

/**
 * Created by Mirage on 19/12/2016.
 */
public interface CurriculumGeneratorService {

    String generateCurriculumToPdf(Integer id);
}
