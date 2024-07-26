package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import one.digitalinnovation.gof.model.Loja;
import one.digitalinnovation.gof.model.LojaRepository;
import one.digitalinnovation.gof.service.LojaService;


@Service
public class LojaServiceImpl implements LojaService {

	@Autowired
	private LojaRepository lojaRepository;


	@Override
	public Iterable<Loja> buscarTodos() {
		return lojaRepository.findAll();
	}

	@Override
	public Loja buscarPorId(Long id) {
		Optional<Loja> loja = lojaRepository.findById(id);
		return loja.get();
	}

	@Override
	public void inserir(Loja loja) {
		salvarLoja(loja);
	}

	@Override
	public void atualizar(Long id, Loja loja) {
		Optional<Loja> lojaBd = lojaRepository.findById(id);
		if (lojaBd.isPresent()) {
			salvarLoja(loja);
		}
	}

	@Override
	public void deletar(Long id) {
		lojaRepository.deleteById(id);
	}

	private void salvarLoja(Loja loja) {
		lojaRepository.save(loja);
	}

}
