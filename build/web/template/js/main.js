$(document).ready(function () {

    /**
     * Функция изменения css на лайках при нажатии статья
     * @return {undefined}
     */
    function UpdateLikesArt() {
        $('.like-button-art').each(
                function () {
                    var postavlen = $(this).attr('postavlen-art');
                    var count = $(this).attr('count');
                    $(this).text(" " + count);

                    if (postavlen == "1") {
                        $(this).removeClass('fa-heart-o');
                        $(this).addClass('fa-heart');
                    } else if (postavlen == "0") {
                        $(this).removeClass('fa-heart');
                        $(this).addClass('fa-heart-o');
                    } else {
                        $(this).attr('title', 'Зарегестрируйтесь');
                        $(this).tooltip();
                    }
                });

    }
    
    /**
     * Функция прибавлени, отбавления лайка в бд статья
     */
    $('.like-button-art').click(function (e) {
        e.preventDefault();

        var like = $(this);
        var id = like.attr('article');
        var postavlen = like.attr('postavlen-art');
        var choSdelat = 0;

        if (postavlen == 1) {
            choSdelat = 0;
        } else if(postavlen == 0){
            choSdelat = 1;
        }

        $.ajax({
            method: "GET",
            url: "./like-art",
            data: {id: id, choSdelat: choSdelat},
            success: function (response) {
                var split = response.split(",");

                like.text(" " + split[1]);
                like.attr("postavlen-art", split[0]);
                like.attr("count", split[1]);

                UpdateLikesArt();
            }
        });
    });
    
    /**
     * Функция изменения css на лайках при нажатии комментарий
     * @return {undefined}
     */
    function UpdateLikesCom() {

        $('.like-button-com').each(
                function () {
                    var postavlen = $(this).attr('postavlen-com');
                    var count = $(this).attr('count');
                    $(this).text(" " + count);

                    if (postavlen == "1") {
                        $(this).removeClass('fa-heart-o');
                        $(this).addClass('fa-heart');
                    } else if (postavlen == "0") {
                        $(this).removeClass('fa-heart');
                        $(this).addClass('fa-heart-o');
                    } else {
                        $(this).attr('title', 'Зарегестрируйтесь');
                        $(this).tooltip();
                    }
                });
    }

    /**
     * Функция прибавлени, отбавления лайка в бд комментарий
     */
    $('.like-button-com').click(function (e) {
        e.preventDefault();

        var like = $(this);
        var id = like.attr('article');
        var postavlen = like.attr('postavlen-com');
        var choSdelat = 0;

        if (postavlen == 1) {
            choSdelat = 0;
        } else if(postavlen == 0){
            choSdelat = 1;
        }

        $.ajax({
            method: "GET",
            url: "./like-com",
            data: {id: id, choSdelat: choSdelat},
            success: function (response) {
                var split = response.split(",");

                like.text(" " + split[1]);
                like.attr("postavlen-com", split[0]);
                like.attr("count", split[1]);

                UpdateLikesCom();
            }
        });
    });

    UpdateLikesArt();
    UpdateLikesCom();
});
