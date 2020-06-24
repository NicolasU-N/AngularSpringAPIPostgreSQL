package com.example.demo.models.implementation;

import java.util.List;

import com.example.demo.models.dao.IRolDao;
import com.example.demo.models.entity.Rol;
import com.example.demo.models.service.IRolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolImpl implements IRolService {

    @Autowired
    private IRolDao rolDao;

    @Override
    @Transactional(readOnly = true)
    public List<Rol> findAll() {
        return rolDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Rol findById(Long id) {
        return rolDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Rol save(Rol rol) {
        return rolDao.save(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public Rol findByName(String name) {
        
        return rolDao.findByName(name);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        rolDao.deleteById(id);

    }

    
    
}