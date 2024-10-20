package br.com.cev.restcontrollers;

import br.com.cev.dao.ProdutoCreateDao;
import br.com.cev.dao.ProdutoDao;
import br.com.cev.service.ProdutoDaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/produto")
public class ProdutoRestController extends AbstractRestController{

    private final ProdutoDaoService produtoDaoService;

    public ProdutoRestController(
            ProdutoDaoService produtoDaoService
    ) {
        this.produtoDaoService = produtoDaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDao> getProdutoById(
            @PathVariable Long id
    ){
        Optional<ProdutoDao> produtoDao = produtoDaoService.getById(id);

        return produtoDao
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDao>> getProdutoList(
            @PageableDefault Pageable pageable
    ){
        Page<ProdutoDao> produtoListPaginada = produtoDaoService.getProdutoListPaginada(pageable);

        return ResponseEntity.ok(produtoListPaginada);
    }

    //TODO daqui para baixo se der tempo todos os end-points devem ser acessados apenas quando o jwt for valido, apenas se der tempo de fazer a parte de login
    @PostMapping
    public ResponseEntity<ProdutoDao> createProduto(
            @RequestBody ProdutoCreateDao produto
    ){
        ProdutoDao produtoDao = produtoDaoService.create(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDao);
    }

    @PatchMapping
    public ResponseEntity<ProdutoDao> patchProduto(
            @RequestBody ProdutoDao produto
    ){
        Optional<ProdutoDao> produtoDaoOptional = produtoDaoService.patch(produto);

        return produtoDaoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<ProdutoDao> putProduto(
            @RequestBody ProdutoDao produto
    ){
        ProdutoDao produtoDao = produtoDaoService.put(produto);

        if(produto.id() == null){
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoDao);
        }else {
            return ResponseEntity.ok(produto);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(
            @PathVariable(name = "id") Long produtoId
    ){

        boolean deletado = produtoDaoService.delete(produtoId);

        if(deletado){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }

}
