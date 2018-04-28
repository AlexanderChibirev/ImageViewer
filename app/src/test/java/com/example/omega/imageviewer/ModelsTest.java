package com.example.omega.imageviewer;

import com.example.omega.imageviewer.models.Image;
import com.example.omega.imageviewer.models.ListResources;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Alexander Chibirev on 4/28/2018.
 */
public class ModelsTest {

    private class TestClass {

    }

    @Test
    public void ShouldWorkCorrectTemplate() {//on template and non nullable
        ListResources<Image> imageListResources = new ListResources<>();
        assertNotNull(imageListResources.getResources());
        List<Image> imageList = new ArrayList<>();
        assertNotNull(imageList);
        assertEquals(imageListResources.getResources().getClass(), imageList.getClass());

        ListResources<TestClass> testListResources = new ListResources<>();
        assertNotNull(testListResources.getResources());
        List<TestClass> testClass = new ArrayList<>();
        assertNotNull(testClass);
        assertEquals(testListResources.getResources().getClass(), testClass.getClass());
    }

    @Test
    public void ShouldReturnCorrectFileds() {
        Image imageWithParam = new Image("Image", "str/sd/wesaasd/r.jpg",
                "http/yandexlove/online.com", "25.01.2018");

        assertEquals(imageWithParam.getDate(), "25.01.2018");
        assertEquals(imageWithParam.getName(), "Image");
        assertEquals(imageWithParam.getPath(), "str/sd/wesaasd/r.jpg");
        assertEquals(imageWithParam.getPublicUrl(), "http/yandexlove/online.com");
    }
}
