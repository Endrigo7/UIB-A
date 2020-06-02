package br.unit.uibb.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.unit.uibb.entidades.Cliente;

public class ClienteDAOHibernate {

	public void inserir(Cliente cliente) {

		Transaction transaction = null;

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

			transaction = session.beginTransaction();
			session.saveOrUpdate(cliente);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public void atualizar(Cliente cliente) {
		inserir(cliente);
	}

	public Cliente procurar(String cpf) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

			String hql = "SELECT c FROM Cliente c WHERE c.cpf = :cpf";

			Query query = session.createQuery(hql, Cliente.class);
			query.setParameter("cpf", cpf);
			
			Cliente cliente = (Cliente) query.getSingleResult();
			
			session.close();
			
			return  cliente;
		} catch (Exception e) {
		}
		return null;
	}

	public List<Cliente> getAll() {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

			String hql = "SELECT c FROM Cliente c";

			return session //
					.createQuery(hql, Cliente.class) //
					.list(); //
		} catch (Exception e) {
		}

		return new ArrayList<Cliente>();
	}

}
