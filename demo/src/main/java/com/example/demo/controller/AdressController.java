package com.example.demo.controller;

import com.example.demo.controller.ExeptionHandeling.AdressNotFoundExeption;
import com.example.demo.controller.ExeptionHandeling.ObjectNotEmpty;
import com.example.demo.controller.validitor.AdressValiditor;
import com.example.demo.model.entity.Adress;
import com.example.demo.model.service.AdressService;
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
public class AdressController implements ControllerInterface<Adress> {

    @Autowired
    AdressService adressService;
    @Autowired
    AdressValiditor adressValiditor;


    @PostMapping("/save")
    @Override
    public void save(@RequestBody Adress entity, BindingResult  bindingResult) {

            adressValiditor.validate(entity,bindingResult);
            if(bindingResult.hasErrors())
                throw new ObjectNotEmpty(bindingResult.getAllErrors().toString()) ;

            adressService.save(entity);
            /*Adress savedAdress=adressService.save(entity);
            URI url = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(savedAdress.getId()).toUri();
            return ResponseEntity.created(url).build();*/

    }



    @PostMapping("/update")
    @Override
    public void update(@RequestBody Adress entity, BindingResult  bindingResult) {
        adressValiditor.validate(entity,bindingResult);
        if(bindingResult.hasErrors())
            throw new ObjectNotEmpty(bindingResult.getAllErrors().get(0).toString()) ;
        Adress adressFind= adressService.selectById(entity.getId());
        adressService.update(entity);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void delete(@PathVariable long id) {
        try {
            adressService.delete(id);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @GetMapping("/findAll")
    @Override
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
    @Override
    public Adress findById(@PathVariable  Long id) {

            Optional<Adress> adress = Optional.ofNullable(adressService.selectById(id));
            if (! adress.isPresent())
                throw  new AdressNotFoundExeption(" can not find adress with id:"+ id);

            return adressService.selectById(id);

    }


}
