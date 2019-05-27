package br.com.blz.testjava.controller;

import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void add(@RequestBody Produto produto){
        produto.setMarketable(false);
         produtoService.addProduto(produto);
    }
    @PutMapping("/edit/{sku}")
    public @ResponseBody Produto edit(@PathVariable Long sku,Produto produto){

        return produtoService.updateProduto(sku,produto);
    }
    @GetMapping("/get/{sku}")
    public @ResponseBody Produto search(@PathVariable Long sku){

        return produtoService.searchProduto(sku);
    }
    @DeleteMapping("/del/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public void del(@PathVariable Long sku){

        produtoService.deleteProduto(sku);
    }

}
