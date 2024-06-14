/*
 *
 *  * @project : DeliX
 *  * @created : 06/06/2024, 16:17
 *  * @modified : 06/06/2024, 16:17
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.util.helpers;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtils {

    public static File convertListToFile(List<String> list, String fileName) {
        File file = new File(fileName);
        try {
            FileWriter writer = new FileWriter(file);
            for (String str : list) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static boolean checkExcelFormat(MultipartFile fileForUpload) {
        return fileForUpload.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }
}
