package br.com.themykael.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.themykael.loja.dao.CategoriaDAO;
import br.com.themykael.loja.dao.ProdutoDAO;
import br.com.themykael.loja.modelo.Categoria;
import br.com.themykael.loja.modelo.Produto;
import br.com.themykael.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		Produto produto = produtoDao.buscarPorId(1L);
		
		System.out.println(produto.getPreco());
		List<Produto> buscarTodos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		buscarTodos.forEach(p -> System.out.println(p.getNome()));
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		//Produto celular = new Produto("Zenfone 5", "Nice!", new BigDecimal("1600"), celulares);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		//produtoDao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	}

}
