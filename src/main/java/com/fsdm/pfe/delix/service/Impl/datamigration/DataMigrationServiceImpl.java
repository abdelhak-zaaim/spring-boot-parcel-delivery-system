/*
 *
 *  * @project : DeliX
 *  * @created : 04/06/2024, 20:50
 *  * @modified : 04/06/2024, 20:50
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.datamigration;

import com.fsdm.pfe.delix.model.datamigration.ImportObjectType;
import com.fsdm.pfe.delix.model.enums.ImportFileType;
import com.fsdm.pfe.delix.repository.CityRepo;
import com.fsdm.pfe.delix.repository.ProvinceRepo;
import com.fsdm.pfe.delix.service.DataMigrationService;
import com.fsdm.pfe.delix.service.Impl.datamigration.csv.CsvDataReaderImpl;
import com.fsdm.pfe.delix.service.Impl.datamigration.datatypes.CityDataProcessor;
import com.fsdm.pfe.delix.service.Impl.datamigration.datatypes.CityDataWriter;
import com.fsdm.pfe.delix.service.Impl.datamigration.datatypes.ProvinceDataWriter;
import com.fsdm.pfe.delix.service.Impl.datamigration.excel.ExcelDataReader;
import com.fsdm.pfe.delix.service.datamegration.DataMigration;
import com.fsdm.pfe.delix.service.datamegration.DataProcessor;
import com.fsdm.pfe.delix.service.datamegration.DataWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DataMigrationServiceImpl implements DataMigrationService {
    private final CityRepo cityRepo;
    private final ProvinceRepo provinceRepo;

    public DataMigrationServiceImpl(CityRepo cityRepo, ProvinceRepo provinceRepo) {
        this.cityRepo = cityRepo;
        this.provinceRepo = provinceRepo;
    }

    @Override
    public void migrateData(MultipartFile file, ImportObjectType objectType) throws IOException {
        ImportFileType fileType = getFileType(file);

        if (objectType == null) {
            throw new IllegalArgumentException("Unsupported object type");
        }
        DataWriter dataWriter;
        DataProcessor dataProcessor;
        switch (objectType) {
            case CITY:
                dataWriter = new CityDataWriter(cityRepo);
                dataProcessor = new CityDataProcessor();
                break;
            case PROVINCE:
                dataWriter = new ProvinceDataWriter(provinceRepo);
                dataProcessor = new CityDataProcessor();
                break;
            default:
                throw new IllegalArgumentException("Unsupported object type");
        }


        if (fileType == null) {
            throw new IllegalArgumentException("Unsupported file type");
        }
        switch (fileType) {
            case EXCEL:
                DataMigration dataMigrationExel = new DataMigrationImpl(new ExcelDataReader(), dataWriter, dataProcessor);
                dataMigrationExel.migrateData(file);
                break;
            case CSV:
                DataMigration dataMigrationCsv = new DataMigrationImpl(new CsvDataReaderImpl(), dataWriter, dataProcessor);
                dataMigrationCsv.migrateData(file);
                break;
            default:
                throw new IllegalArgumentException("Unsupported file type");
        }

    }

    public ImportFileType getFileType(MultipartFile file) {
        if (file.getOriginalFilename() == null) {
            return null;
        }
        String fileName = file.getOriginalFilename();
        if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
            return ImportFileType.EXCEL;
        } else if (fileName.endsWith(".csv")) {
            return ImportFileType.CSV;
        } else {
            return null;
        }
    }
}
