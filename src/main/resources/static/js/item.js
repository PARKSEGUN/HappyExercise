let item = {
    init: function () {
        let _this = this;
        $('#save-btn').click(function () {
            _this.save();

        });
    },
    save: function () {
        alert("제품을 등록하였습니다!");
        $("#save-form").submit();
        location.href = '/';
    }
}

item.init();