package tacos.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.TacoOrder;
import tacos.data.OrderRepository;

@RestController
@RequestMapping(path = "/api/orders/", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class OrderController {
    private final OrderRepository orderRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder putOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder tacoOrder) {
        tacoOrder.setId(orderId);
        return orderRepo.save(tacoOrder);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder patchOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder patch) {
        TacoOrder tacoOrder = orderRepo.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            tacoOrder.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            tacoOrder.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            tacoOrder.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            tacoOrder.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            tacoOrder.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            tacoOrder.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            tacoOrder.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            tacoOrder.setCcCVV(patch.getCcCVV());
        }
        return tacoOrder;
    }
    @DeleteMapping(path = "/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long id){
        try{
            orderRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e){

        }
    }
}
