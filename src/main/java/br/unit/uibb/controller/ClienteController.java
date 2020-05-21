package br.unit.uibb.controller;

import java.util.List;

import br.unit.uibb.entidades.Cliente;
import br.unit.uibb.repository.ClienteDAOHibernate;

public class ClienteController {

	private ClienteDAOHibernate clienteDao;

	public ClienteController() {
		clienteDao = new ClienteDAOHibernate();
	}

	public boolean inserir(Cliente cliente) {
		clienteDao.inserir(cliente);
		return true;
	}

	public void atualizar(Cliente cliente) {
		clienteDao.atualizar(cliente);
	}

	public Cliente procurar(String cpf) {
		return clienteDao.procurar(cpf);
	}
	
	public List<Cliente> listaTodosClientes() {
		return clienteDao.getAll();
	}

}
