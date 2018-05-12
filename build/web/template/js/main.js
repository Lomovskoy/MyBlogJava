$(document).ready(function () {
    function UpdateLikes() {
        $('.like-button').each(
                function () {
                    var postavlen = $(this).attr('postavlen');
                    var count = $(this).attr('count');

                    $(this).text(" " + count);

                    if (postavlen == "1") {
                        //alert("Like OK");
                        $(this).removeClass('fa-heart-o');
                        $(this).addClass('fa-heart');
                    } else {
                        //alert("Like NOT OK");
                        $(this).removeClass('fa-heart');
                        $(this).addClass('fa-heart-o');
                    }
                });
    }

    $('.like-button').click(function (e) {
        e.preventDefault();

        var like = $(this);

        var id = like.attr('article');
        var postavlen = like.attr('postavlen');

        var choSdelat = 0;

        if (postavlen == 1) {
            choSdelat = 0;
        } else {
            choSdelat = 1;
        }

        $.ajax({
            method: "GET",
            url: "./like",
            data: {id: id, choSdelat: choSdelat},
            success: function (response) {
                var split = response.split(",");

                like.text(" " + split[1]);
                like.attr("postavlen", split[0]);
                like.attr("count", split[1]);

                UpdateLikes();
            }
        });
    });
    
    UpdateLikes();
});
