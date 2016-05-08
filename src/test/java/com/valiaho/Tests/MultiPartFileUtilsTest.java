package com.valiaho.Tests;

import com.valiaho.Tests.ContextConfiguration.GenericTestContext;
import com.valiaho.Utils.MultiPartFileUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.fail;

/**
 * Created by akivv on 9.4.2016.
 */
@ContextConfiguration(classes = {GenericTestContext.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MultiPartFileUtilsTest {


    @Autowired
    private MultiPartFileUtils multiPartFileUtils;
    private MultipartFile multiPartFile;
    @Before
    public void before() throws IOException {
        final File file = FileUtils.toFile(ResourceUtils.getURL("test.png"));
        InputStream inputStream = FileUtils.openInputStream(file);
        this.multiPartFile = new MockMultipartFile("image", null, "image/png", inputStream);
    }

    @Test
    public void saveMultiPartFileToPersistence() throws Exception {
        final String[] s = multiPartFileUtils.saveMultiPartFileToPersistence(multiPartFile, null);
        if (s[1].contains(":")) {
            fail("Some nasty characters in filename");
        }
        if (!s[1].contains(".png")) {
            fail("Proper MIME-type is not present in file ending");
        }
    }
}