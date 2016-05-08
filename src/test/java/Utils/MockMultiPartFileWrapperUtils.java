package Utils;

import com.valiaho.Domain.Image;
import com.valiaho.Domain.MultipartFileInformationWrapper;
import com.valiaho.Tests.Controllers.Domain.MultipartFileWrapperBuilder;
import com.valiaho.Utils.UtilityMethods;

/**
 * Created by akivv on 8.5.2016.
 */
public class MockMultiPartFileWrapperUtils {
    public static MultipartFileInformationWrapper generateTestMockMultipartFileWrapper(Image image) {
        final MultipartFileInformationWrapper build = buildWrapperWithImage(image);
        return build;
    }

    private static MultipartFileInformationWrapper buildWrapperWithImage(Image image) {
        MultipartFileWrapperBuilder multipartFileWrapperBuilder = new MultipartFileWrapperBuilder();
        setVariablesToMultiPartfileWrapper(image, multipartFileWrapperBuilder);
        return multipartFileWrapperBuilder.build();
    }

    private static void setVariablesToMultiPartfileWrapper(Image image, MultipartFileWrapperBuilder multipartFileWrapperBuilder) {
        multipartFileWrapperBuilder.setCommentLIst(image.getCommentList());
        multipartFileWrapperBuilder.setOriginalFileName(image.getDocumentId());
    }

    public static MultipartFileInformationWrapper generateTestMockMultipatFileWrapper() {
        final Image image = UtilityMethods.buildRandomImageAsRaster();
        final MultipartFileInformationWrapper multipartFileInformationWrapper = buildWrapperWithImage(image);
        return multipartFileInformationWrapper;
    }
}
