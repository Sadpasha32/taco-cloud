package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.TacoOrder;
import tacos.MyUser;
import tacos.data.OrderRepository;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderTacoController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderTacoController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("tacoOrder") TacoOrder tacoOrder, Errors errors,
                               SessionStatus sessionStatus, @AuthenticationPrincipal MyUser myUser) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        tacoOrder.setPlacedAt(new Date());
        tacoOrder.setMyUser(myUser);
        orderRepository.save(tacoOrder);
        sessionStatus.setComplete(); // Тут мы уже после сохранения заказа
        // "отпускаем" атрибут tacoOrder, который был на уровне сессии
        return "redirect:/";
    }

}
