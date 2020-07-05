package com.fruitnvegie.fruitnvegieapi.services;

import com.fruitnvegie.fruitnvegieapi.dao.RoleRepository;
import com.fruitnvegie.fruitnvegieapi.dao.UserRepository;
import com.fruitnvegie.fruitnvegieapi.exceptions.UserNotFoundException;
import com.fruitnvegie.fruitnvegieapi.models.MyUserPrincipal;
import com.fruitnvegie.fruitnvegieapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User has been registered";
    }

    @Transactional
    @Override
    public String update(User user) {
        Optional<User> userFromDatabase = userRepository.findById(user.getId());
        if (!userFromDatabase.isPresent()) throw new UserNotFoundException("User does not exist");
        // Carry date created timestamp
        user.setDateCreated(userFromDatabase.get().getDateCreated());
        userRepository.save(user);
        return "User with ID " + user.getId() + " has been updated";
    }

    @Transactional
    @Override
    public String delete(Integer id) {
        Optional<User> userToDelete = userRepository.findById(id);
        if (!userToDelete.isPresent()){
            throw new UserNotFoundException("User with ID " + id + " does not exist");
        }
        userRepository.deleteById(id);
        return "User has been deleted";

    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            throw new UserNotFoundException("Users not found");
        }
        return users;
    }

    @Override
    public User getOne(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            throw new UserNotFoundException("User with the ID " + id + " does not exist");
        }
        return user.get();
    }


    @Override
    public User authUser(String emailAddress, String password) throws Exception {
        //First get the user by email to check if the user exists
        User user = userRepository.findUserByEmailAddress(emailAddress);
        if (user == null){
            //Display an error that the user with the email address was not found
            throw new EntityNotFoundException("User with email: " + emailAddress + " not found");
        }
        //Check user entered password if it matches hashed password in database
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }
        //Else return the user if found
        return user;
    }

    @Override
    public User findByEmailAddress(String emailAddress) {
        User user = userRepository.findUserByEmailAddress(emailAddress);
        if (user == null){
            throw new EntityNotFoundException("User with the email " + emailAddress + " not found");
        }
        return user;
    }


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmailAddress(emailAddress);
        if (user == null){
            //TODO: Set an error that the user by that email address cannot be found
            throw new UsernameNotFoundException("Could not find the user " + emailAddress);
        }
        return new MyUserPrincipal(user);
    }
}
