package com.qkrtprjs.happyexercise.index;

import com.qkrtprjs.happyexercise.config.auth.LoginMember;
import com.qkrtprjs.happyexercise.config.auth.dto.SessionMember;
import com.qkrtprjs.happyexercise.dto.CartItemResponseDto;
import com.qkrtprjs.happyexercise.dto.CartResponseDto;
import com.qkrtprjs.happyexercise.dto.ItemResponseDto;
import com.qkrtprjs.happyexercise.dto.OrderDetailResponseDto;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import com.qkrtprjs.happyexercise.entitiy.member.MemberRepository;
import com.qkrtprjs.happyexercise.service.CartItemService;
import com.qkrtprjs.happyexercise.service.CartService;
import com.qkrtprjs.happyexercise.service.ItemService;
import com.qkrtprjs.happyexercise.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.jws.WebParam;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final ItemService itemService;
    private final OrderService orderService;
    private final MemberRepository memberRepository;
    private final CartService cartService;
    private final CartItemService cartItemService;

    @GetMapping("/")
    private String index(Model model, @LoginMember SessionMember loginMember) {
        model.addAttribute("loginMember", loginMember);
        List<ItemResponseDto> itemResponseDtoList = itemService.findAll();
        model.addAttribute("itemList", itemResponseDtoList);
        return "index";
    }

    @GetMapping("/auth/login")
    private String login() {
        return "login";
    }

    @GetMapping("/member/detail")
    private String detailMember(Model model, @LoginMember SessionMember loginMember) {
        model.addAttribute("loginMember", loginMember);
        return "member/detail";
    }

    @GetMapping("/save/item")
    private String saveItem() {
        return "item/save";
    }

    @GetMapping("/item/{id}")
    private String detailItem(@PathVariable Long id, Model model, @LoginMember SessionMember loginMember) {
        ItemResponseDto itemResponseDto = itemService.findById(id);
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("item", itemResponseDto);
        return "item/detail";
    }

    @GetMapping("/updateForm/item/{id}")
    private String updateFormItem(@PathVariable Long id, Model model, @LoginMember SessionMember loginMember) {
        ItemResponseDto itemResponseDto = itemService.findById(id);
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("item", itemResponseDto);
        return "item/updateForm";
    }

    @GetMapping("/orderForm/{id}")
    private String orderForm(@PathVariable Long id, Model model, @LoginMember SessionMember loginMember) {
        ItemResponseDto itemResponseDto = itemService.findById(id);
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("item", itemResponseDto);
        return "order/orderForm";
    }

    @GetMapping("/orderList")
    private String orderList(Model model, @LoginMember SessionMember loginMember) {
        Member member = memberRepository.findByEmail(loginMember.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 이메일은 존재하지 않습니다!"));
        List<OrderDetailResponseDto> orderListResponseDtoList = orderService.orderList(member);
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("orderList", orderListResponseDtoList);
        return "order/orderList";
    }

    @GetMapping("/order/{id}")
    private String orderDetail(Model model, @PathVariable Long id) {
        OrderDetailResponseDto orderDetailResponseDto = orderService.orderDetail(id);
        model.addAttribute("order", orderDetailResponseDto);
        return "order/orderDetail";
    }

    @GetMapping("/cartDetail")
    private String cartDetail(Model model, @LoginMember SessionMember sessionMember) {
        List<CartItemResponseDto> cartItemResponseDtoList = cartItemService.findByMember(sessionMember);
        CartResponseDto cartResponseDto = cartService.findByMember(sessionMember);
        model.addAttribute("cartItemList", cartItemResponseDtoList);
        model.addAttribute("cart", cartResponseDto);
        return "cart/cartDetail";
    }
}
