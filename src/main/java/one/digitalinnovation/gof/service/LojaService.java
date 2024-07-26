package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Loja;

public interface LojaService {
    Iterable<Loja> buscarTodos();

	Loja buscarPorId(Long id);

	void inserir(Loja loja);

	void atualizar(Long id, Loja loja);

	void deletar(Long id);

}