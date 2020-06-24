package com.example.demo.models.implementation;

import java.util.List;

import com.example.demo.models.dao.IServerDao;
import com.example.demo.models.entity.Server;
import com.example.demo.models.service.IServerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServerImpl implements IServerService {

    @Autowired
    private IServerDao serverDao;

    @Override
    @Transactional(readOnly = true)
    public List<Server> findAll() {
        return serverDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Server findById(Long id) {
        return serverDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Server save(Server server) {
        return serverDao.save(server);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        serverDao.deleteById(id);

    }

}