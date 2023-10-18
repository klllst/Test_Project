package com.test_project.TestApp.Service;

import com.test_project.TestApp.Exceptions.MaterialComponentNotFoundException;
import com.test_project.TestApp.Models.MaterialComponent;
import com.test_project.TestApp.Repositories.MaterialComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;


@Service
public class MaterialComponentService {
    private final MaterialComponentRepository materialComponentRepository;
    @Autowired
    public MaterialComponentService(MaterialComponentRepository materialComponentRepository){
        this.materialComponentRepository = materialComponentRepository;
    }
    @Transactional
    public MaterialComponent addMaterialComponent(String name, BigDecimal cost ){
        MaterialComponent materialComponent = new MaterialComponent(name, cost);
        return materialComponentRepository.save(materialComponent);
    }
    @Transactional()
    public MaterialComponent getMaterialComponent(Long id){
        return materialComponentRepository.findById(id).orElseThrow(() -> new MaterialComponentNotFoundException(id));
    }

    @Transactional
    public List<MaterialComponent> getAllMaterialComponents(){
        return materialComponentRepository.findAll();
    }

    @Transactional
    public MaterialComponent updateMaterialComponent(Long id, String name, BigDecimal cost){
        MaterialComponent materialComponent = getMaterialComponent(id);
        materialComponent.setName(name);
        materialComponent.setCost(cost);
        return materialComponentRepository.save(materialComponent);
    }

    @Transactional
    public MaterialComponent deleteMaterialComponent(Long id){
        MaterialComponent materialComponent = getMaterialComponent(id);
        materialComponentRepository.delete(materialComponent);
        return materialComponent;
    }
    @Transactional
    public void deleteAllMaterialComponents(){
        materialComponentRepository.deleteAll();
    }
}
