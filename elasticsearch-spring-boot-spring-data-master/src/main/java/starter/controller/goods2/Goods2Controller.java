package starter.controller.goods2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import starter.model.goods2.Goods2;
import starter.service.Goods2Service;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Goods2Controller {

    @Autowired
    private Goods2Service goods2Service;

    @PostMapping("goods2/createIndex")
    public ResponseEntity<String> createIndex() {
        goods2Service.createIndex();
        return ResponseEntity.ok("Created");
    }

    @PostMapping("goods2/add")
    public ResponseEntity<String> add(@RequestBody Goods2 goods) {
        goods2Service.addGoods(goods);
        return ResponseEntity.ok("added");
    }

    @GetMapping("goods2/search")
    public ResponseEntity<Map> searchGoods(@RequestBody Goods2 goods2, @RequestParam int page, @RequestParam int size) {
        Page<Goods2> goodsPage = goods2Service.search(goods2, page, size);
        Map m = new HashMap<>(3);
        m.put("totalElements", goodsPage.getTotalElements());
        m.put("totalPages", goodsPage.getTotalPages());
        m.put("list", goodsPage.getContent());
        return ResponseEntity.ok(m);
    }
}
