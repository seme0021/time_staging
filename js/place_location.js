        function lookup(inputString) {
                if(inputString.length == 0) {
                        // Hide the suggestion box.
                        $('#suggestions').hide();
                } else {
                        $.post("/api/location.php", {queryString: ""+inputString+""}, function(data){
                                if(data.length >0) {
                                        $('#suggestions').show();
                                        $('#autoSuggestionsList').html(data);
                                }
                        });
                }
        } // lookup
        
        function fill(thisValue) {
                $('#inputString').val(thisValue);
                setTimeout("$('#suggestions').hide();", 200);
        }

       function getPlaces(input) {
             var e = document.getElementById("location").value;
             console.log(e);
            if(input.length == 0) {
            //Hide the suggestion box
            } else {
                $.post("/api/places.php", {queryString: ""+input+"|"+e+""}, function(data){
                      if(data.length>0){
                           $('#placesuggestions').show();
                           $('#autoPlacesList').html(data);
                        }
                     });
            }
          }

      function fillP(thisValue){
             $('#places').val(thisValue);
             setTimeout("$('#placesuggestions').hide();", 200);
          }

