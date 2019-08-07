package starter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import starter.model.Goods;
import starter.service.GoddsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GoodsController {

    private GoddsService goddsService;

    @Autowired
    public GoodsController(GoddsService goddsService) {
        this.goddsService = goddsService;
    }

    @PostMapping("/goods/createIndex")
    public ResponseEntity<String> createIndex() {
        goddsService.createIndex();
        return ResponseEntity.ok("Created");
    }

    @PostMapping("/goods/deleteIndex")
    public ResponseEntity<String> deleteIndex() {
        goddsService.deleteIndex();
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/goods/addGoods")
    public ResponseEntity<String> addGoods(Goods goods) {
        goddsService.addGoods(goods);
        return ResponseEntity.ok("added");
    }

    @PostMapping("/goods/delete/{id}")
    public ResponseEntity<String> deleteGoods(@PathVariable("id") Long id) {
        goddsService.deleteGoods(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("goods/getByIds")
    public ResponseEntity<Iterable<Goods>> getById(@RequestBody String[] ids) {
        Iterable<Goods> goods = goddsService.getById(ids);
        return ResponseEntity.ok(goods);
    }

    @GetMapping("/goods/search")
    public ResponseEntity<Map> searchGoods(@RequestBody Goods goods, @RequestParam int page, @RequestParam int size) {
        Page<Goods> goodsPage = goddsService.search(goods, page, size);
        Map m = new HashMap<>();
        m.put("totalElements", goodsPage.getTotalElements());
        m.put("totalPages", goodsPage.getTotalPages());
        m.put("list", goodsPage.getContent());
        return ResponseEntity.ok(m);
    }
}
