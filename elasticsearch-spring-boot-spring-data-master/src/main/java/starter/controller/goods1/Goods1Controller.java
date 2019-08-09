package starter.controller.goods1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import starter.model.goods1.Goods1;
import starter.service.Goods1Service;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Goods1Controller {

    @Autowired
    private Goods1Service goods1Service;
    
    @PostMapping("/goods1/createIndex")
    public ResponseEntity<String> createIndex() {
        goods1Service.createIndex();
        return ResponseEntity.ok("Created");
    }

    @PostMapping("/goods1/deleteIndex")
    public ResponseEntity<String> deleteIndex() {
        goods1Service.deleteIndex();
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/goods1/add")
    public ResponseEntity<String> addGoods(Goods1 goods1) {
        goods1Service.addGoods(goods1);
        return ResponseEntity.ok("added");
    }

    @PostMapping("/goods1/delete/{id}")
    public ResponseEntity<String> deleteGoods(@PathVariable("id") Long id) {
        goods1Service.deleteGoods(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("goods1/getByIds")
    public ResponseEntity<Iterable<Goods1>> getById(@RequestBody String[] ids) {
        Iterable<Goods1> goods = goods1Service.getById(ids);
        return ResponseEntity.ok(goods);
    }

    @GetMapping("/goods1/search")
    public ResponseEntity<Map> searchGoods(@RequestBody GoodsVo goodsVo, @RequestParam int page, @RequestParam int size) {
        Page<Goods1> goodsPage = goods1Service.search(goodsVo, page, size);
        Map m = new HashMap<>(3);
        m.put("totalElements", goodsPage.getTotalElements());
        m.put("totalPages", goodsPage.getTotalPages());
        m.put("list", goodsPage.getContent());
        return ResponseEntity.ok(m);
    }
}
