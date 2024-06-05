package com.projeto.sistema.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projeto.sistema.modelos.Carro;
import com.projeto.sistema.repositorios.CarroRepository;
import com.projeto.sistema.repositorios.CarroService;

import jakarta.annotation.PostConstruct;

@Controller
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;
    @Autowired
    private CarroService carroService;
    
    private List<Carro> carrinho = new ArrayList<>();

    @PostConstruct
    public void init() {
        if (carroRepository.count() == 0) {
        Carro carro1 = new Carro();
        carro1.setNome("Toyota Corolla");
        carro1.setDescricao("O Toyota Corolla é um sedan de porte médio conhecido por sua confiabilidade.");
        carro1.setImagemUrl("https://quatrorodas.abril.com.br/wp-content/uploads/2024/01/01_COROLLA_2024_1FLP4223.jpg?crop=1&resize=1212,909");

        Carro carro2 = new Carro();
        carro2.setNome("Toyota Camry");
        carro2.setDescricao("O Toyota Camry é um sedan de porte médio que oferece uma viagem luxuosa e confortável.");
        carro2.setImagemUrl("https://www.tsusho.com.br/pub/noticias/2022/Capa_Camry.jpg");

        Carro carro3 = new Carro();
        carro3.setNome("Toyota RAV4");
        carro3.setDescricao("O Toyota RAV4 é um SUV compacto popular que oferece uma combinação de espaço, praticidade e estilo.");
        carro3.setImagemUrl("https://cdn.motor1.com/images/mgl/NGjO69/s1/2024-toyota-rav4-hybrid-xse.jpg");

        Carro carro4 = new Carro();
        carro4.setNome("Chevrolet Cruze");
        carro4.setDescricao("O Chevrolet Cruze é um sedan de porte médio que oferece uma combinação de estilo, desempenho e valor.");
        carro4.setImagemUrl("https://www.chevrolet.com.br/content/dam/chevrolet/mercosur/brazil/portuguese/index/cars/2021-cruze-premier/mov/05-images/chevrolet-cruze-xl.jpg?imwidth=960");

        Carro carro5 = new Carro();
        carro5.setNome("Chevrolet Silverado");
        carro5.setDescricao("A Chevrolet Silverado é uma caminhonete pickup de grande porte conhecida por sua robustez, capacidade de reboque e recursos off-road.");
        carro5.setImagemUrl("https://hips.hearstapps.com/hmg-prod/images/2020-chevrolet-silverado-hd-119-1561405281.jpg?crop=0.614xw:0.692xh;0.251xw,0.257xh&resize=768:*");
        
        Carro carro6 = new Carro();
        carro6.setNome("Toyota GR86");
        carro6.setDescricao("O Toyota GR86 é um carro esportivo de tração traseira projetado para ser divertido de dirigir.");
        carro6.setImagemUrl("https://static1.topspeedimages.com/wordpress/wp-content/uploads/2022/12/2022-toyota-gr86.jpg");
        
        Carro carro7 = new Carro();
        carro7.setNome("Toyota Corolla Cross");
        carro7.setDescricao("O Toyota Corolla Cross é um SUV compacto novo que oferece uma combinação de espaço, praticidade e estilo.");
        carro7.setImagemUrl("https://cdn.motor1.com/images/mgl/gpR8g/s1/2021-toyota-corolla-cross-in-thailand.webp");
        
        Carro carro8 = new Carro();
        carro8.setNome("Toyota bZ4X");
        carro8.setDescricao("O Toyota bZ4X é um SUV elétrico totalmente novo que oferece uma autonomia de até 450 km.");
        carro8.setImagemUrl("https://www.digitaltrends.com/wp-content/uploads/2023/02/2023-Toyota-bZ4X-front-three-quarter.jpeg?p=1");
        
        Carro carro9 = new Carro();
        carro9.setNome("Chevrolet Tracker");
        carro9.setDescricao("O O Chevrolet Tracker é um SUV compacto popular que oferece uma combinação de estilo, praticidade e valor.");
        carro9.setImagemUrl("https://maquinasdotempo.blogfolha.uol.com.br/files/2020/06/tracker-01.jpg");
        
        Carro carro10 = new Carro();
        carro10.setNome("Chevrolet Cobalt");
        carro10.setDescricao("Um sedan compacto popular conhecido por sua confiabilidade, economia de combustível e preço acessível.");
        carro10.setImagemUrl("https://cdn.motor1.com/images/mgl/wlpBqG/s1/chevrolet-cobalt---uzbequistao.jpg");
        

        carroRepository.save(carro1);
        carroRepository.save(carro2);
        carroRepository.save(carro3);
        carroRepository.save(carro4);
        carroRepository.save(carro5);
        carroRepository.save(carro6);
        carroRepository.save(carro7);
        carroRepository.save(carro8);
        carroRepository.save(carro9);
        carroRepository.save(carro10);

        }
    }

    
    @GetMapping("/")
    public String home(Model model) {
        List<Carro> todosOsCarros = StreamSupport.stream(carroService.getAllCarros().spliterator(), false)
        .collect(Collectors.toList());
        List<Carro> tresPrimeirosCarros = todosOsCarros.stream().limit(3).collect(Collectors.toList());

        model.addAttribute("carros", tresPrimeirosCarros);
        //model.addAttribute("carros", carroRepository.findAll());
        return "/admin/home";
    }
   @GetMapping("/modelos")
    public String modelos(Model model) {
        model.addAttribute("carros", carroRepository.findAll());
        return "/admin/index";
    }

    @GetMapping("/cadastro")
    public String cadastroForm() {
        return "/admin/cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastroSubmit(@RequestParam String nome, @RequestParam String descricao, @RequestParam String imagemUrl) {
        Carro carro = new Carro();
        carro.setNome(nome);
        carro.setDescricao(descricao);
        carro.setImagemUrl(imagemUrl);
        carroRepository.save(carro);
        return "redirect:/modelos";
    }

    @GetMapping("/carrinho")
    public String carrinho(Model model) {
        model.addAttribute("carrosNoCarrinho", carrinho);
        return "/admin/carrinho";
    }

    

    @PostMapping("/adicionarAoCarrinho")
    public String adicionarAoCarrinho(@RequestParam Long carroId) {
        carroRepository.findById(carroId).ifPresent(carrinho::add);
        return "redirect:/carrinho";
    }

    @PostMapping("/removerDoCarrinho")
    public String removerDoCarrinho(@RequestParam Long carroId) {
        carrinho.removeIf(carro -> carro.getId().equals(carroId));
        return "redirect:/carrinho";
    }
    
    @GetMapping("/carro/{id}")
    public String detalhesCarro(@PathVariable Long id, Model model) {
        Optional<Carro> carroOpt = carroRepository.findById(id);
        if (carroOpt.isPresent()) {
            model.addAttribute("carro", carroOpt.get());
            return "/admin/carro-detalhe";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/contato")
    public String contato(Model model) {
        return "/admin/contato";
    }
}