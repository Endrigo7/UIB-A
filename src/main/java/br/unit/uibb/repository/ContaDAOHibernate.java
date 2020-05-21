package br.unit.uibb.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.unit.uibb.entidades.Conta;

public class ContaDAOHibernate {

	public void inserir(Conta conta) {

		Transaction transaction = null;

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

			transaction = session.beginTransaction();
			session.saveOrUpdate(conta);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public void atualizar(Conta conta) {
		inserir(conta);
	}
	
	public void remover(String numero, String senha) {
		
		Conta conta = procurar(numero, senha);
		
		Transaction transaction = null;

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

			transaction = session.beginTransaction();
			session.remove(conta);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public boolean existe(String numero) {
		Conta conta = procurar(numero);
		return conta != null;
	}
	
	public Conta procurar(String numero) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

			String hql = "SELECT c FROM Conta c WHERE c.numero = :numero";

			Query query = session.createQuery(hql, Conta.class);
			query.setParameter("numero", numero);
			
			return (Conta) query.getSingleResult();
		} catch (Exception e) {
		}
		return null;
	}

	public Conta procurar(String numero, String senha) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			String hql = "SELECT c FROM Conta c WHERE c.numero = :numero and c.senha = :saldo";

			Query query = session.createQuery(hql, Conta.class);
			query.setParameter("numero", numero);
			query.setParameter("saldo", senha);
			
			return (Conta) query.getSingleResult();
		} catch (Exception e) {
		}
		return null;
	}

	public List<Conta> getAll() {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

			String hql = "SELECT c FROM Conta c";

			return session //
					.createQuery(hql, Conta.class) //
					.list(); //
		} catch (Exception e) {
		}

		return new ArrayList<Conta>();
	}

	public List<Conta> procurarContas(String cpf) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

			String hql = "SELECT c FROM Conta c WHERE c.cliente.cpf = :cpf";

			Query query = session.createQuery(hql, Conta.class);
			query.setParameter("cpf", cpf);
			
			return query.getResultList();
		} catch (Exception e) {
		}

		return new ArrayList<Conta>();
	}

	public List<Conta> procurarContasJava8(String cpf) {
		return procurarContas(cpf);
	}
}
