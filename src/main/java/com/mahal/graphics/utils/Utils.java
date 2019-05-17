package com.mahal.graphics.utils;

import java.io.InputStream;
import java.util.Scanner;

public class Utils {

        public static String loadResource(String fileName) throws Exception {
            String result;
            try (InputStream in = Class.forName(Utils.class.getName()).getResourceAsStream(fileName);
                 Scanner scanner = new Scanner(in, "UTF-8")) {
                result = scanner.useDelimiter("\\A").next();
            }
            return result;
        }
        static public final float map(float value,
                                  float istart,
                                  float istop,
                                  float ostart,
                                  float ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }

}
