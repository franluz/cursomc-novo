package com.nelioalves.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.services.ClienteService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Cliente obj = clienteService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ClienteDTO objDTO, @PathVariable Integer id) {
		objDTO.setId(id);
		Cliente obj= clienteService.update(clienteService.fromDTO(objDTO));
		return ResponseEntity.noContent().build();
		
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete( @PathVariable Integer id) {
		clienteService.delete(id);
	 	return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
													@RequestParam(name="page",defaultValue="0") Integer page,
													@RequestParam(name="linesPerPage",defaultValue="24") Integer linesPerPage,
													@RequestParam(name="orderBy",defaultValue="nome") String orderBy,
													@RequestParam(name="direction",defaultValue="ASC")String direction) throws ObjectNotFoundException {
		Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDTO = list.map(obj-> new ClienteDTO(obj));
		return   ResponseEntity.ok().body(listDTO);
	} 
    
}
