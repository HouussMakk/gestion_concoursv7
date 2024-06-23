package ma.emsi.conours_v8.web;

import lombok.AllArgsConstructor;
import ma.emsi.conours_v8.entities.Concours;
import ma.emsi.conours_v8.entities.Diplome;
import ma.emsi.conours_v8.repositories.ConcoursRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class ConcoursController {

    private ConcoursRepository concoursRepository;

    @GetMapping("/Concours")
    private String Concours(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int p,
                            @RequestParam(name ="size", defaultValue = "4") int s,
                            @RequestParam(name ="Keyword",defaultValue = "")String kw){
        Page<Concours> concoursPage =concoursRepository.findByNomContains(kw,PageRequest.of(p,s));
        model.addAttribute("listConcours",concoursPage.getContent());
        model.addAttribute("pages",new int[concoursPage.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("Keyword",kw);
        return "concours";
    }
    @GetMapping("/deleteConcours")
    public String delete(@RequestParam(name = "id") Long id,String Keyword,int page){
        concoursRepository.deleteById(id);
        return "redirect:/Concours?page="+page+"& Keyword= "+Keyword;
    }
    @GetMapping("/formConcours")
    public String formConcours(Model model){
        model.addAttribute("concours",new Concours());
        return "formConcours";
    }
    @PostMapping("/saveConcours")
    public String saveConcours(Concours concours){
        concoursRepository.save(concours);
        //System.out.println(concours.toString());
        return"redirect:/Concours";
    }
    @GetMapping("/editConcours")
    public String editConcours(@RequestParam(name = "id") Long id, Model model){
        Concours concours=concoursRepository.findById(id).get();
        model.addAttribute("concours",concours);
        return "editConcours";
    }
    @GetMapping("/")
    public String Home(){
        return "redirect:/Concours";
    }
}
