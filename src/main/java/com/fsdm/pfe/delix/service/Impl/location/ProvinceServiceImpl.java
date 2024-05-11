/*
 *
 *  * @project : DeliX
 *  * @created : 11/05/2024, 14:26
 *  * @modified : 11/05/2024, 14:26
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.location;

import com.fsdm.pfe.delix.entity.location.Province;
import com.fsdm.pfe.delix.repository.location.ProvinceRepo;
import com.fsdm.pfe.delix.service.location.ProvinceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepo provinceRepo;

    public ProvinceServiceImpl(ProvinceRepo provinceRepo) {
        this.provinceRepo = provinceRepo;
    }

    @Override
    public Province create(Province province) {
        return provinceRepo.save(province);
    }

    @Override
    public Province update(Province province) {
        return provinceRepo.save(province);
    }

    @Override
    public Province findById(Long id) {
        return provinceRepo.findById(id).orElse(null);
    }

    @Override
    public Province findByCode(String code) {
        return provinceRepo.findByCode(code).orElse(null);
    }

    @Override
    public List<Province> findAll() {
        return provinceRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        provinceRepo.deleteById(id);
    }

    @Override
    public void deleteByCode(String code) {
        provinceRepo.deleteByCode(code);
    }

    @Override
    public List<Province> saveAll(List<Province> provinces) {
        return provinceRepo.saveAll(provinces);
    }
}
