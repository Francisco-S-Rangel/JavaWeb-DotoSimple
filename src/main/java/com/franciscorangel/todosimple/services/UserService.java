package com.franciscorangel.todosimple.services;

import java.util.Optional;


import com.franciscorangel.todosimple.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franciscorangel.todosimple.repositories.TaskRepository;
import com.franciscorangel.todosimple.repositories.UserRepository;

@Service
public class UserService {

  /* 

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    


  public User findById(Long id){

    Optional<User> user = this.userRepository.findById(id);

    return user.orElseThrow(() -> new RuntimeException(
        "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()
    )); // Como o user é opcional se ele tiver facíl eu jogo uma exceção
  }

  @Transactional //Serve para fazer uma persistencia no banco
  public User create(User obj){
    obj.setId(null);
    obj = this.userRepository.save(obj);
    return obj;
  }

  @Transactional
  public User update(User obj){
    User newObj = findById(obj.getId());
    newObj.setPassword(obj.getPassword());
    return this.userRepository.save(newObj);
  }

  public void delete(Long id){

    findById(id);
    try {
        this.userRepository.deleteById(id);
    } catch (Exception e) {
        throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
    }

    */

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
    }

    @Transactional
    public User create(User obj) {
        obj.setId(null);
        obj = this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }

    @Transactional
    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }
    
}
