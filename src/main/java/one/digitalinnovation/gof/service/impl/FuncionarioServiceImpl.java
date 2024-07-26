package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.model.Funcionario;
import one.digitalinnovation.gof.model.FuncionarioRepository;
import one.digitalinnovation.gof.service.FuncionarioService;
import one.digitalinnovation.gof.service.ViaCepService;


@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;


	@Override
	public Iterable<Funcionario> buscarTodos() {
		return funcionarioRepository.findAll();
	}

	@Override
	public Funcionario buscarPorId(Long id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		return funcionario.get();
	}

	@Override
	public void inserir(Funcionario funcionario) {
		salvarFuncionarioComCep(funcionario);
	}

	@Override
	public void atualizar(Long id, Funcionario funcionario) {
		Optional<Funcionario> funcionarioBd = funcionarioRepository.findById(id);
		if (funcionarioBd.isPresent()) {
			salvarFuncionarioComCep(funcionario);
		}
	}

	@Override
	public void deletar(Long id) {
		funcionarioRepository.deleteById(id);
	}

	private void salvarFuncionarioComCep(Funcionario funcionario) {
		String cep = funcionario.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		funcionario.setEndereco(endereco);
		funcionarioRepository.save(funcionario);
	}

}
