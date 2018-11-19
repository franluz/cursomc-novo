package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.repositories.PedidoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	public Pedido buscar(Integer id) throws ObjectNotFoundException{
		if(id==null) {
			return null;
		}
		 Optional<Pedido> ped = pedidoRepository.findById(id);
		 return ped.orElseThrow(()-> new ObjectNotFoundException("Erro ao achar o pedido"));
	}
}
