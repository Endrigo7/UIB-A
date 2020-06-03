package br.unit.uibb.controller;

import java.util.List;

import br.unit.uibb.entidades.Cliente;
import br.unit.uibb.repository.ClienteDAOHibernate;

public class ClienteController {

	private static final Integer CLIENTE_STATUS_INVATIVO = 2;
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
	
	public void inativarCliente(String cpf) {
		Cliente cliente = clienteDao.procurar(cpf);
		
		if(cliente == null) {
			throw new RuntimeException("Cliente não foi encontrado");
		}
		
		if(CLIENTE_STATUS_INVATIVO.equals(cliente.getStatus()) ) {
			throw new RuntimeException("Cliente já está desativado");
		}
		
		cliente.setStatus(CLIENTE_STATUS_INVATIVO);
		clienteDao.atualizar(cliente);
		
	}

}
