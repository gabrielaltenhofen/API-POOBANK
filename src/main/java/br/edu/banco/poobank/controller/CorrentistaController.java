    package br.edu.banco.poobank.controller;

    import br.edu.banco.poobank.model.Correntista;
    import br.edu.banco.poobank.repository.CorrentistaRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import java.util.Map;



    import java.util.List;

    @RestController
    @RequestMapping("/correntista")
    @CrossOrigin("*")
    public class CorrentistaController {

        @Autowired
        private CorrentistaRepository repository;

        @GetMapping
        public List<Correntista> all() {

            return repository.findAll();
        }


        @PostMapping("/login")
        public ResponseEntity login(@RequestBody Map<String, String> credentials) {
            String cpf = credentials.get("cpf");
            String senha = credentials.get("senha");

            Correntista correntista = repository.findByCpfAndSenha(cpf, senha);

            if (correntista != null) {
                return ResponseEntity.ok(correntista);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }

        @PostMapping
        public ResponseEntity criarConta(@RequestBody Correntista correntista) {
            repository.save(correntista);
            return ResponseEntity.ok().build();
        }
        @GetMapping("/obterUltimoNumeroConta")
        public ResponseEntity<String> obterUltimoNumeroConta() {
            String ultimoNumeroConta = repository.findTopByOrderByNumeroContaDesc().getNumeroConta();

            return ResponseEntity.ok(ultimoNumeroConta);
        }
    }
