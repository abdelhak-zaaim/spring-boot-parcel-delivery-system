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

import com.fsdm.pfe.delix.entity.Area;
import com.fsdm.pfe.delix.entity.City;
import com.fsdm.pfe.delix.entity.Province;
import com.fsdm.pfe.delix.model.datamigration.ImportObjectType;
import com.fsdm.pfe.delix.model.enums.ImportFileType;
import com.fsdm.pfe.delix.repository.AreaRepo;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class DataMigrationServiceImpl implements DataMigrationService {
    private final CityRepo cityRepo;
    private final ProvinceRepo provinceRepo;
    private final AreaRepo areaRepo;

    public DataMigrationServiceImpl(CityRepo cityRepo, ProvinceRepo provinceRepo, AreaRepo areaRepo) {
        this.cityRepo = cityRepo;
        this.provinceRepo = provinceRepo;
        this.areaRepo = areaRepo;
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


    public List<String> migrateExel(MultipartFile file) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(file
                .getInputStream());

        XSSFSheet sheetCity = workbook.getSheet("city");
        XSSFSheet sheetProvince = workbook.getSheet("province");
        XSSFSheet sheetArea = workbook.getSheet("area");


        List<String> logs2 = migrateProvinceExel(sheetProvince);
        List<String> logs1 = migrateCityExel(sheetCity);
        List<String> logs3 = migrateAreaExel(sheetArea);

        List<String> logs = new LinkedList<>();
        logs.addAll(logs1);
        logs.addAll(logs2);
        logs.addAll(logs3);

        return logs;
    }

    // FIXME: this technic not good for this project , we need to use the spring batch


    public List<String> migrateProvinceExel(XSSFSheet sheet) {
        List<String> logs = new LinkedList<>();
        int rowNumber = 0;
        for (Row row : sheet) {
            if (rowNumber == 0) {
                rowNumber++;
                continue;
            }

            Iterator<Cell> cells = row.iterator();

            int cid = 0;

            Province province = new Province();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                switch (cid) {
                    case 0:
                        try {
                            province.setCode(cell.getStringCellValue());
                        } catch (Exception e) {
                            logs.add("Error while parsing code for province" + province.getName());
                        }
                        break;
                    case 1:
                        try {
                            province.setCountryCode(cell.getStringCellValue());
                        } catch (Exception e) {
                            logs.add("Error while parsing Country Code for province code: " + province.getCode());
                        }

                        break;
                    case 2:
                        try {
                            province.setName(cell.getStringCellValue());
                        } catch (Exception e) {
                            logs.add("Error while parsing name for province code: " + province.getCode());
                        }
                        break;
                    default:
                        break;
                }

                cid++;
            }

            try {
                provinceRepo.save(province);
            } catch (Exception e) {
                logs.add("Error while saving province: " + province.getCode());
            }
        }
        return logs;
    }

    public List<String> migrateCityExel(XSSFSheet sheet) {
        List<String> logs = new LinkedList<>();
        int rowNumber = 0;

        for (Row row : sheet) {
            if (rowNumber == 0) {
                rowNumber++;
                continue;
            }

            Iterator<Cell> cells = row.iterator();

            int cid = 0;

            City city = new City();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                switch (cid) {
                    case 0:
                        try {
                            city.setCode(cell.getStringCellValue());
                        } catch (Exception e) {
                            logs.add("Error while parsing code for city" + city.getName());
                        }
                        break;
                    case 1:
                        try {
                            city.setCountryCode(cell.getStringCellValue());
                        } catch (Exception e) {
                            logs.add("Error while parsing name for city code: " + city.getCode());
                        }
                        break;
                    case 2:
                        try {
                            city.setName(cell.getStringCellValue());
                        } catch (Exception e) {
                            logs.add("Error while parsing name for city code: " + city.getCode());
                        }
                        break;
                    case 3:
                        try {
                            city.setPostalCode(cell.getStringCellValue());
                        } catch (Exception e) {
                            logs.add("Error while parsing postal code for city code: " + city.getCode());
                        }
                        break;
                    case 4:
                        Province province = provinceRepo.findByCode(cell.getStringCellValue()).orElse(null);
                        if (province == null) {
                            logs.add("Province not found for city code: " + city.getCode());
                            break;
                        }
                        city.setProvinceCode(province);
                        break;
                    default:
                        break;
                }

                cid++;
            }
            try {
                cityRepo.save(city);
            } catch (Exception e) {
                logs.add("Error while saving city: " + city.getCode());
            }
        }

        return logs;
    }

    public List<String> migrateAreaExel(XSSFSheet sheet) {
        List<String> logs = new LinkedList<>();
        int rowNumber = 0;
        for (Row row : sheet) {
            if (rowNumber == 0) {
                rowNumber++;
                continue;
            }

            Iterator<Cell> cells = row.iterator();

            int cid = 0;
            Area area = new Area();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                switch (cid) {
                    case 0:
                        try {
                            area.setCode(cell.getStringCellValue());
                        } catch (Exception e) {
                            logs.add("Error while parsing code for area" + area.getName());
                        }
                        break;
                    case 1:
                        try {
                            area.setName(cell.getStringCellValue());
                        } catch (Exception e) {
                            logs.add("Error while parsing name for area code: " + area.getCode());
                        }
                        break;

                    case 2:
                        try {
                            area.setPostCode(cell.getStringCellValue());
                        } catch (Exception e) {
                            logs.add("Error while parsing Postal Code for area code: " + area.getCode());
                        }
                        break;
                    case 3:
                        try {
                            City city = cityRepo.findByCode(cell.getStringCellValue()).orElse(null);
                            if (city == null) {
                                logs.add("City not found for area code: " + area.getCode());
                                break;
                            }
                            area.setCityCode(city);
                        } catch (Exception e) {
                            logs.add("Error while parsing city code for area code: " + area.getCode());
                        }
                        break;
                    default:
                        break;
                }
                cid++;
            }

            try {
                areaRepo.save(area);
            } catch (Exception e) {
                logs.add("Error while saving area: " + area.getCode());
            }
        }
        return logs;
    }
}
