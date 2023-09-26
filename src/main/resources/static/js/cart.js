let cart={
    init: function () {
        let _this = this;
        $("#cart-save-btn").on('click',function (){
            let count = $("#order-count").val();
            if(count>0){
                _this.save();
            }else{
                alert("구매하실 개수를 1개이상 입력해주세요");
            }
        });
        $("[name=cartItemDelete-btn]").on('click',function (){
            let id = this.getAttribute("cartItemId");
            if (confirm("해당 상품을 장바구니에서 삭제하시겠습니까?")) {
                _this.deleteCartItem(id);
            }else{
                alert("취소되었습니다!");
            }
        });
        $("#orderFromCart-btn").on('click',function (){
            if (confirm("장바구니에 있는 모든 상품을 주문하시겠습니까?")) {
                _this.orderFromCart();
            }

        });
    },
    save: function () {
        let data={
            itemResponseDto:itemForRequest,
            count: $("#order-count").val()
        }
        $.ajax({
            url: '/api/cart' ,
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("상품이 장바구니로 이동되었습니다");
            location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    deleteCartItem: function (id) {

        $.ajax({
            url: '/api/cartItem/'+id ,
            method: 'DELETE',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert("상품이 장바구니에서 삭제되었습니다!");
            location.href = '/cartDetail';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    orderFromCart: function () {
        let data={
            cartItemResponseDtoList:cartItemList,
            orderPrice: $("#orderPrice").val()
        }
        $.ajax({
            url: '/api/order/cart' ,
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("장바구니에 있는 상품을 주문하였습니다!");
            location.href = '/orderList';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
}
cart.init();