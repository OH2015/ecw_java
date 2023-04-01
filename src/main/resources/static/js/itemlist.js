$(function(){
	$("#item_category_dropdown").click(function(){
		if($("#item_category_dropdown_list").css("display") == "none"){
			$("#item_category_dropdown").addClass("open");
			$("#item_category_dropdown_list").slideDown({duration: 16});
		}else{
			$("#item_category_dropdown").removeClass("open");
			$("#item_category_dropdown_list").slideUp({duration: 16});
		}
	});
});

$(window).resize(function() {
	$("#item_category_dropdown").removeClass("open");
	$("#item_category_dropdown_list").css("display", "none");
});

// 検索窓から検索実行
function search(){
	$("#execSearch").submit();
}

// カテゴリ選択から検索実行
function searchByCategory(c1, c2){
	// 商品分類1コードをhiddenにセット
	$("#hid_c1").val(c1);
	// 商品分類2コードをhiddenにセット
	$("#hid_c2").val(c2);
	$("#execSearchByCategory").submit();
}

// ページング
function paging(c1, c2, k, min, max, page){
	// 商品分類1コードをhiddenにセット
	$("#hid_p_c1").val(c1);
	// 商品分類2コードをhiddenにセット
	$("#hid_p_c2").val(c2);
	// キーワードをhiddenにセット
	$("#hid_p_k").val(k);
	// 価格帯下限をhiddenにセット
	$("#hid_p_min").val(min);
	// 価格帯上限をhiddenにセット
	$("#hid_p_max").val(max);
	// ページ番号をhiddenにセット
	$("#hid_p_page").val(page);
	$("#execPaging").submit();
}

// 課題#09 税抜下代単価の上限・下限を指定して絞り込み可能にする
//         ここに「条件で絞り込んで検索」ボタンのクリックで呼び出される関数を追加します。
//         hiddenに値をセットしてsubmitする方法は、既にある関数「検索窓から検索実行」「カテゴリ選択から検索実行」「ページング」と同様です。
//
//         価格帯上限／下限で絞り込んだ状態でページング可能にするため、
//         このjsファイルにあるpaging関数の引数に、価格帯上限／下限の引数を追加する考慮も必要です。
//
//         javascriptの document.getElementById("ID名")と、jQueryの $("#ID名") は等価で（※厳密には$("#ID名")[0] と等価）、指定したID名のオブジェクトを取得します。
//         javascriptの document.getElementByIdはブラウザ依存（動かないブラウザがある）ですが、jQueryはクロスブラウザに対応しており、
//         特定のブラウザで動かないといった問題がないため、開発現場ではjQueryの$("#ID名")の方を使います。


$(function(){
	// 下限価格入力時に文字チェック＆hiddenにセット
	$("#min_text").keyup(function(){
		this.value = this.value.replace(/[^0-9]+/i,'');
		$("#hid_min").val($(this).val());
	});
	// 上限価格入力時に文字チェック＆hiddenにセット
	$("#max_text").keyup(function(){
		this.value = this.value.replace(/[^0-9]+/i,'');
		$("#hid_max").val($(this).val());
	});
});

// 価格帯検索実行
function searchByPrice(){
	$("#hid_min").val($("#min_text").val());
	$("#hid_max").val($("#max_text").val());
	$("#execSearchByPrice").submit();
}

// 商品一覧クリック時
function onClickItem(item_cd){
	$("#hid_detail_cd").val(item_cd);
	$("#execDetail").submit();
}

// バナークリック時
function onClickBanner(banner_id){
	if(banner_id == "3"){
		$("#hid_c1").val("10");
		$("#hid_c2").val("30");
		$("#search").submit();
	}else{
		$("#top").submit();
	}
}

// スライドクリック時
function onClickSlide(slide_id){
	if(slide_id == "1"){
		$("#hid_c1").val("30");
		$("#hid_c2").val("20");
		$("#execSearchByCategory").submit();
	}else if(slide_id == "2"){
		$("#hid_slide_cd").val("0000001002");
		$("#execSlideDetail").submit();
	}else if(slide_id == "3"){
		$("#hid_c1").val("20");
		$("#hid_c2").val("10");
		$("#execSearchByCategory").submit();
	}
}


$(document).ready(function(){
  loadSlider();
});

function loadSlider(){
    $('.bxslider').bxSlider({
	auto: true,
    autoControls: true,
    stopAutoOnClick: true,
    pager: true,
	touchEnabled:false,

    slideWidth: 1000
  });
}


