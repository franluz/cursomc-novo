package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id){
		Optional<Cliente> obj= clienteRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Cliente nao encontrado com o ID"+id));
	}
	public Cliente update(Cliente obj)  throws ObjectNotFoundException {
		Cliente newObj = find(obj.getId());
		updateDate(newObj,obj);
		return clienteRepository.save(obj);
	}
	private void updateDate(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	public Page<Cliente> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
		PageRequest pagRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return clienteRepository.findAll(pagRequest);
		
	}
	public void delete(Integer id)  {
		find(id);
		try {
			clienteRepository.deleteById(id);
		}catch(DataIntegrityViolationException ex) {
			 throw new DataIntegrityException("Não é possivel excluir cliente pois o mesmo possui pedidos"); 
		}
	}
	public Cliente fromDTO(ClienteDTO dto) {
		return  new Cliente(dto.getId(),dto.getNome(),dto.getEmail(),null,null);
	}
}
