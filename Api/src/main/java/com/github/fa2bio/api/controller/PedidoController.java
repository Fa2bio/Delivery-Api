package com.github.fa2bio.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.assembler.PedidoInputDisassembler;
import com.github.fa2bio.api.assembler.PedidoModelAssembler;
import com.github.fa2bio.api.assembler.PedidoResumoModelAssembler;
import com.github.fa2bio.api.model.PedidoModel;
import com.github.fa2bio.api.model.PedidoResumoModel;
import com.github.fa2bio.api.model.input.PedidoInput;
import com.github.fa2bio.core.data.PageableTranslator;
import com.github.fa2bio.domain.exception.EntityNotFoundException;
import com.github.fa2bio.domain.exception.BusinessException;
import com.github.fa2bio.domain.filter.PedidoFilter;
import com.github.fa2bio.domain.model.Pedido;
import com.github.fa2bio.domain.model.Usuario;
import com.github.fa2bio.domain.repository.PedidoRepository;
import com.github.fa2bio.domain.service.EmissaoPedidoService;
import com.github.fa2bio.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;
	
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;
	
	@GetMapping
	public Page<PedidoResumoModel> pesquisar(@PageableDefault(size = 10) Pageable pageable, PedidoFilter filtro) {
		
		pageable = mapearPageable(pageable);
	
		Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.
				usandoFiltro(filtro),pageable);
		
		List<PedidoResumoModel> pedidosResumoModel = pedidoResumoModelAssembler.
				toCollectionModel(pedidosPage.getContent());
		
		Page<PedidoResumoModel> pedidosResumoModelPage = new PageImpl<>(pedidosResumoModel, pageable,
				pedidosPage.getTotalElements());
		
		return pedidosResumoModelPage;
	}

	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
		
		return pedidoModelAssembler.toModel(pedido);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
			
			// TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);
			return pedidoModelAssembler.toModel(emissaoPedidoService.emitir(novoPedido));
		} catch (EntityNotFoundException e) {
			throw new BusinessException(e.getMessage(),e);
		}
	}
	
	private Pageable mapearPageable(Pageable apiPegeable) {
		var mapeamento = Map.of(
				"codigo", "codigo",
				"subtotal", "subtotal",
				"taxaFrete", "taxaFrete",
				"valorTotal", "valorTotal",
				"dataCriacao", "dataCriacao",
				"restaurante.nome", "restaurante.nome",
				"restaurante.id", "restaurante.id",
				"cliente.id", "cliente.id",
				"cliente.nome", "cliente.nome"
				);
		return PageableTranslator.translate(apiPegeable, mapeamento);
	}
	
}