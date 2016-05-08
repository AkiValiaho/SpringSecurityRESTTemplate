package Utils;

import org.springframework.mock.web.MockMultipartFile;

public class TestingResourceGenerator {
    public static MockMultipartFile generateTestingMultiPartMockFile() {
        return MockMultipartFileUtils.generateMockMultiPartFile("Image", "originalFileName.jpg", "image/jpg", "test.png");
    }
}