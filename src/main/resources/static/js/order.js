let order = {
    init: function () {
        let _this = this;
        $('#order-count').on('change',function () {
            let price = $("#price").val();
            let count = $("#order-count").val();
            $("#order-price").text("가격 : " + price * count);
        });
        $("#order-save-btn").on('click',function (){
            let count = $("#order-count").val();
            if(count>0){
                _this.save();
            }else{
                alert("구매하실 개수를 1개이상 입력해주세요");
            }

        });
        $("[name=orderDetail-btn]").on('click',function (){
            location.href="/order/" + this.getAttribute("id");
        });
        $("#delete-order-btn").on('click',function (){
            if (confirm("주문을 취소하시겠습니까?")) {
                let orderId = this.getAttribute("orderId");
                _this.delete(orderId);
            }
        });
    },
    save: function () {
        let data={
            itemResponseDto:itemForRequest,
            count: $("#order-count").val()
        }
        $.ajax({
            url: '/api/order/' ,
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("주문이 완료되었습니다");
            location.href = '/orderList';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function (orderId) {
        $.ajax({
            url: '/api/order/'+orderId,
            method: 'DELETE',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert("주문이 취소되었습니다");
            location.href = '/orderList';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

}
order.init();