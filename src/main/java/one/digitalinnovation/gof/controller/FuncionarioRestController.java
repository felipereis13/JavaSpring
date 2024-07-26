package one.digitalinnovation.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.model.Funcionario;
import one.digitalinnovation.gof.service.FuncionarioService;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioRestController {
    
	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping
	public ResponseEntity<Iterable<Funcionario>> buscarTodos() {
		return ResponseEntity.ok(funcionarioService.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(funcionarioService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Funcionario> inserir(@RequestBody Funcionario funcionario) {
		funcionarioService.inserir(funcionario);
		return ResponseEntity.ok(funcionario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario) {
		funcionarioService.atualizar(id, funcionario);
		return ResponseEntity.ok(funcionario);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		funcionarioService.deletar(id);
		return ResponseEntity.ok().build();
	}
}
