package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id)  {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"+id+",Tipo:"+ Categoria.class.getName())); //esse
	}
	public List< Categoria> findAll()  {
		List< Categoria> list = repo.findAll();
		return list; //esse
	}
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	public Categoria update(Categoria obj)  throws ObjectNotFoundException {
		Categoria objNew = find(obj.getId());
		updateData(objNew,obj);
		return repo.save(objNew);
	}
	private void updateData(Categoria objNew, Categoria obj) {
		objNew.setNome(obj.getNome());;		
	}
	public Page<Categoria> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
		PageRequest pagRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return repo.findAll(pagRequest);
		
	}
	public void delete(Integer id)  {
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException ex) {
			 throw new DataIntegrityException("Não é possivel excluir categoria que possui produto"); 
		}
	}
	
	public Categoria fromDTO(CategoriaDTO dto) {
		Categoria obj = new Categoria(dto.getId(), dto.getNome());
		return obj;
	}
}
