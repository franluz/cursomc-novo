package com.nelioalves.cursomc.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Produto;

@Service
public class ProdutoService {
	
	public Page<Produto> search(String nome,List<Integer> id){
		return null;
	}
	public Pedido insert(Pedido obj) {
		return null;
	}

}
