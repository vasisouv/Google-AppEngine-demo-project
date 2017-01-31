$( "#encryptForm" ).submit(function( event ) {

  var data={"message":$("#message").val(),"encryption_key":$("#encryption_key").val()};
  $(".message-showbox").css("display", "inline-block");
  $(".btn").addClass("disabled");

  $.ajax({
      url : 'http://aem17-156819.appspot.com/encrypt',
      type : 'POST',
      data: JSON.stringify(data)
    }).done(function(data) {
        clean();
        $(".message-showbox").hide();
        $("#message-response").html(data);
        $("#message-response").addClass('blink-me');
        $(".btn").removeClass("disabled");
    });
  event.preventDefault();

});
$("#message").on('input',function(){
    $("#message-preview").html($("#message").val());
});
$("#encryption_key").on('input',function(){
    $("#encryption-preview").html($("#encryption_key").val());
});

$( "#searchForm" ).submit(function( event ) {

  var data={"search":$("#search-text").val(),"decryption_key":$("#decryption_key").val()};
  $(".search-showbox").css("display", "inline-block");
  $(".btn").addClass("disabled");

  $.ajax({
      url : 'http://aem17-156819.appspot.com/decrypt',
      type : 'POST',
      data: JSON.stringify(data)
    }).done(function(data) {
        $("input").val("");
        clean();
        $(".search-showbox").hide();
        $("#search-response").html(data);
        $("#search-response").addClass('blink-me');
         $(".btn").removeClass("disabled");
    });
  event.preventDefault();

});
$("#search-text").on('input',function(){
    $("#search-text-preview").html($("#search-text").val());
});
$("#decryption_key").on('input',function(){
    $("#decryption-preview").html($("#decryption_key").val());
});

$( "#anagramForm" ).submit(function( event ) {

  var data={"find":$("#anagram-text").val(),"decryption_key":$("#anagram-decryption_key").val()};
  $(".anagram-showbox").css("display", "inline-block");
  $(".btn").addClass("disabled");

  $.ajax({
      url : 'http://aem17-156819.appspot.com/findanagram',
      type : 'POST',
      data: JSON.stringify(data)
    }).done(function(data) {
        $("input").val("");
        clean();
        $(".anagram-showbox").hide();
        $("#anagram-response").html(data);
        $("#anagram-response").addClass('blink-me');
         $(".btn").removeClass("disabled");
    });
  event.preventDefault();

});
$("#anagram-text").on('input',function(){
    $("#anagram-text-preview").html($("#anagram-text").val());
});
$("#anagram-decryption_key").on('input',function(){
    $("#anagram-decryption-preview").html($("#anagram-decryption_key").val());
});

function clean(){
    $("i").removeClass("active");
    $("label").removeClass("active");
    $("input").val("");
}

