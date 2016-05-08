package Utils;

import org.apache.commons.io.FileUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by akivv on 7.5.2016.
 */
public class MockMultipartFileUtils {
    public static MockMultipartFile generateMockMultiPartFile(String name, String originalFileName, String contentType, String fileLocation) {
        try {
            final File file = FileUtils.toFile(ResourceUtils.getURL(fileLocation));
            InputStream inputStream = FileUtils.openInputStream(file);
            return new MockMultipartFile(name, originalFileName, contentType, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
