let item = {
    init: function () {
        let _this = this;
        $('#save-item-btn').click(function () {
            alert("제품을 등록하였습니다!");
            $("#save-form").submit();
        });
        $("#delete-item-btn").click(function (){
            _this.delete();
        });
        $("#update-item-btn").click(function (){
            alert("제품을 수정하였습니다!!!");
            $("#update-item-form").submit();
        });
    },

    delete: function () {
        if (confirm("제품을 삭제하시겠습니까?")) {
            let id = $("#id").val();
            $.ajax({
                url: '/api/item/' + id,
                method: 'DELETE',
                //   dataType: 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                alert("제품이 삭제되었습니다!");
                location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        } else {
            alert("취소하였습니다!");
        }

    }
}
item.init();