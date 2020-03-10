package com.example.demo.controller;

import com.example.demo.controller.exception.AdressNotFoundExeption;
import com.example.demo.controller.exception.ObjectNotEmpty;
import com.example.demo.controller.validitor.AdressValiditor;
import com.example.demo.model.MessageModel;
import com.example.demo.entity.Adress;
import com.example.demo.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



/**
 * Created by abdoli on 3/2/2020.
 */
@RestController
@RequestMapping("/adress")
public class AdressController {

    @Autowired
    AdressService adressService;
    @Autowired
    AdressValiditor adressValiditor;


    @PostMapping("/save")
    public MessageModel save(@RequestBody Adress entity, BindingResult  bindingResult) {

        try {
            adressValiditor.validate(entity,bindingResult);
            if(bindingResult.hasErrors())
                throw new ObjectNotEmpty(bindingResult.getAllErrors().toString()) ;

            adressService.save(entity);
            return new MessageModel(false, "SUCCESS");
        }
        catch (Exception e){
            return new MessageModel(true, e.getMessage());
        }
    }



    @PostMapping("/update")
    public void update(@RequestBody Adress entity, BindingResult  bindingResult) {
        adressValiditor.validate(entity,bindingResult);
        if(bindingResult.hasErrors())
            throw new ObjectNotEmpty(bindingResult.getAllErrors().get(0).toString()) ;
        Adress adressFind= adressService.selectById(entity.getId());
        adressService.update(entity);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        try {
            adressService.delete(id);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @GetMapping("/findAll")
    public List<Adress> findAll() {
        try {
            return adressService.selectAll();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{id}")
    public Adress findById(@PathVariable  Long id) {

            Optional<Adress> adress = Optional.ofNullable(adressService.selectById(id));
            if (! adress.isPresent())
                throw  new AdressNotFoundExeption(" can not find adress with id:"+ id);

            return adressService.selectById(id);

    }


}
