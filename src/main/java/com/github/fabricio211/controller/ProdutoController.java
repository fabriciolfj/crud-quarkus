package com.github.fabricio211.controller;

import com.github.fabricio211.controller.dto.CadastroProdutoDTO;
import com.github.fabricio211.domain.model.Produto;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {

    @GET
    public List<Produto> buscarTodosProdutos() {
        return Produto.listAll();
    }

    @POST
    @Transactional
    public void create(CadastroProdutoDTO dto) {
        var produto = new Produto(dto.getNome(), dto.getValor());
        Produto.persist(produto);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void update(@PathParam("id") Long id, CadastroProdutoDTO dto) {
        Optional<Produto> produto = Produto.findByIdOptional(id);
        produto.map(p -> {
                    p.setNome(dto.getNome());
                    p.setValor(dto.getValor());
                    p.persist(p);
                    return p;
                }).orElseThrow(() -> new NotFoundException());
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        Optional<Produto> produto = Produto.findByIdOptional(id);

        produto.ifPresentOrElse(Produto::delete, () -> {
            throw new NotFoundException();
        });
    }
}
