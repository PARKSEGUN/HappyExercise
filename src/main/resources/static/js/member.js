let member = {
    init: function () {
        let _this = this;
        // 버튼을 클릭하는순간 this는 버튼 그 자체로 변하기때문에 변하기전에 this를 저장시켜놓음으로써 delete()함수를 사용할 수 있게 한다
        $('#delete-btn').on('click', function () {
            _this.delete();
        });
    },
    delete: function () {
        let id = $('#loginMemberId').val();
        if (confirm("회원을 삭제하시겠습니까?")) {
            $.ajax({
                url: '/api/member/' + id,
                method: 'DELETE',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                alert("회원이 삭제되었습니다!");
                location.href = '/logout';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });

        } else {
            alert("취소하였습니다!");
        }
    }
}
member.init();