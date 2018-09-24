package com.crudvideojuegos.controlador;

import com.crudvideojuegos.model.language.Language;
import com.crudvideojuegos.model.language.LanguageFacade;
import com.crudvideojuegos.model.rating.Rating;
import com.crudvideojuegos.model.rating.RatingFacade;
import com.crudvideojuegos.model.usuario.Usuario;
import com.crudvideojuegos.model.usuario.UsuarioFacade;
import com.crudvideojuegos.model.videojuego.Videojuego;
import com.crudvideojuegos.model.videojuego.VideojuegoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RestControlador
{
    @Autowired
    private UsuarioFacade usuarioFacade;
    @Autowired
    private VideojuegoFacade videojuegoFacade;
    @Autowired
    private RatingFacade ratingFacade;
    @Autowired
    private LanguageFacade languageFacade;

    @PostMapping("/login")
    public ResponseEntity<Integer> login(@RequestBody Usuario customer){
        List<Usuario> l = usuarioFacade.findUsuarioByNombre(customer.getNombre());
        Integer r=0;
        if(l!=null&&!l.isEmpty()){
            Usuario u= l.get(0);
            if(!customer.getContrasenya().equals(u.getContrasenya()))
                r=1;
            else if(l.get(0).getRole().equals("ADMIN"))
                r=3;
            else
                r=2;
        }
        return new ResponseEntity<>(r, HttpStatus.OK);
    }
    @RequestMapping("/list-languages")
    public ResponseEntity<List<Language>> language(){
        List<Language> lista=languageFacade.findAll();
        return new ResponseEntity<List<Language>>(lista, HttpStatus.OK);
    }
    @RequestMapping("/list-usuarios/{id}")
    public Usuario listaUsuarios(@RequestParam long id){
        return usuarioFacade.getOne(id);
    }
    @RequestMapping("/list-ratings")
    public ResponseEntity<List<Rating>> rating(){
        List<Rating> lista=ratingFacade.findAll(Sort.by(Sort.Order.by("id")));
        return new ResponseEntity<List<Rating>>(lista, HttpStatus.OK);
    }

    @RequestMapping("/resourceInternalization")
    public ResponseEntity<Map<String,String>> internalization(@RequestBody String[] local){
        Language lan= languageFacade.findLanguageByLocale(local[0]).get(0);
        Locale locale=new Locale(lan.getLocale());
        Map<String,String> model = new HashMap<String,String>();
        ResourceBundle bundle = ResourceBundle.getBundle("messages",locale);
        for(int i=1; i<local.length; i++){
            String w=bundle.getString(local[i]);
            model.put(local[i], w);
        }
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @RequestMapping("/list-videojuegos")
    public ResponseEntity<List<Videojuego>> juegos(){
        List<Videojuego> lista=videojuegoFacade.findAll();
        return new ResponseEntity<List<Videojuego>>(lista, HttpStatus.OK);
    }
    @RequestMapping("/delete")
    public void delete(@RequestBody long id){
        videojuegoFacade.deleteById(id);
    }
    @RequestMapping("/add")
    public ResponseEntity<Integer> add(@RequestBody Videojuego videojuego){
        ResponseEntity<Integer> res=null;
        try{
            List<Videojuego>l=videojuegoFacade.findVideojuegoByName(videojuego.getName());
            Integer r=0;
            if(l!=null&&!l.isEmpty()){
                res=new ResponseEntity<Integer>(2, HttpStatus.OK);
            }else{
                res=save(videojuego);
            }
        }catch (Exception e){
            res=new ResponseEntity<Integer>(1, HttpStatus.OK);
        }
        return null;
    }
    @RequestMapping("/edit")
    public ResponseEntity<Integer> edit(@RequestBody Videojuego videojuego){
        ResponseEntity<Integer> res=null;
        try{
            Videojuego v=videojuegoFacade.getOne(videojuego.getId());
            if(v==null){
                res=new ResponseEntity<Integer>(1, HttpStatus.OK);
            }else{
                boolean name=false;
                if(!v.getName().equals(videojuego.getName())){
                    List<Videojuego>l=videojuegoFacade.findVideojuegoByName(videojuego.getName());
                    if(l!=null&&!l.isEmpty()){
                        name=true;
                    }
                }
                if(!name){
                    res=save(videojuego);
                    res=new ResponseEntity<Integer>(0, HttpStatus.OK);
                }else{
                    res=new ResponseEntity<Integer>(3, HttpStatus.OK);
                }
            }
        }catch (Exception e){
            res=new ResponseEntity<Integer>(2, HttpStatus.OK);
        }
        return res;
    }
    private ResponseEntity<Integer> save(Videojuego v){
        videojuegoFacade.save(v);
        return new ResponseEntity<Integer>(0, HttpStatus.OK);
    }
}
