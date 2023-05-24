$('#category_all_btn').click(function(){
    $('#nav').slideToggle();
    console.log('toggle clicked');
})

$(document).ready(function(){
    $('.banner_wrap').bxSlider({
        auto: true,
        autoControls: false,
        slideWidth: 1100,
        hideControlOnEnd: true,
        pager: false
    });
})

let mark = true;

document.addEventListener("scroll", e => {

    if (location.pathname === '/products/list') {
        var body = $('body')
        var footer = $('#footer_left')
        var height = body.innerHeight() - footer.innerHeight()

        var scroll = $(this).scrollTop() + $(this).innerHeight();
        //var height=$(this)[0].scrollHeight;
        // console.log(height, scroll)

        if (height <= scroll) {
            let changeScrollBtn = document.getElementById("changeScrollBtn")
            let end = document.getElementById("end")

            if (changeScrollBtn.style.display == 'none' && changeScrollBtn.style.display == 'none' && mark) {
                infinityScroll()
            }

        }
    }


}, 200)

document.addEventListener("DOMContentLoaded", function () {
    const urlParams = new URL(location.href).searchParams;
    const el = document.getElementById('msType');
    const len = el.options.length;

    // 목록 페이지가 검색 목록을 보여주는 페이지라면 (검색결과 화면이라면)
    if (urlParams.get('types') !== null) {
        const str = urlParams.get('types');
        console.log(str)
        for (let i = 0; i < len; i++) {
            //select box의 option value가 입력 받은 value의 값과 일치할 경우 selected
            if (el.options[i].value == str) {
                el.options[i].selected = true;
            }
        }
    } else {
        el.options[0].selected = true;
    }

});

const pagination = document.querySelector(".pagination")
if (pagination) {
    pagination.addEventListener("click", function (e) {
        e.preventDefault();
        e.stopPropagation();

        const target = e.target;

        if (target.tagName !== 'A') {
            return;
        }

        const num = target.getAttribute("data-num");
        const formObj = document.querySelector("form");
        console.log(formObj);

        formObj.innerHTML += `<input type='hidden' name='page' value='${num}'>`;
        formObj.submit();

    }, false)

}

document.querySelector(".clearBtn").addEventListener("click", function (e) {
    e.preventDefault();
    e.stopPropagation();

    self.location = '/products/list';
}, false)


function goCategory(e) {
    let uri = '/products/list';
    const cid = e.target.className
    const url = new URL(window.location.href)
    const urlParams = url.searchParams;

    uri += '?cid=' + cid;

    if (urlParams.has('types')) {
        let keyword = urlParams.get('keyword')
        let types = urlParams.get('types')
        uri += '&types=' + types
        uri += '&keyword=' + keyword
    }

    location.href = uri
}

function changeScrollV(e) {
    e.preventDefault();
    e.stopPropagation();
    let changeScrollBtn = document.getElementById("changeScrollBtn")
    let pagenav = document.getElementById("page_wrap")
    pagenav.style.display = 'none'
    changeScrollBtn.style.display = 'none'

}

function goRequestBtn() {
    location.href = '/board/request_book_list';
}