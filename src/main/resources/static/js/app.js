$(() => {

    $('ul').on("click", "li span.showhide", function (e) {

        $(e.target).prev().toggle()

        if (e.target.innerText==="SHOW"){
            e.target.innerText = "HIDE";
        } else {
            e.target.innerText="SHOW"
        }
    })


});