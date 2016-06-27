$(document).ready(function() {
    console.log("control.js loaded");

//    var $navLinks = $(".navtitle");
//    	
//    $navLinks.css("color","lime")
//    $navLinks.on("mouseover",function(){
//    	
//    	css("color","lime");
//    	
//    });
//    	
    	
    
    
    var $mainDiv = $("#main");
    var $resultBody = $("#resultBody");
    // html forms
    var modifyFormLoad = function(vid) {
        $mainDiv.empty();
        $resultBody.empty();
        $mainDiv.load("htmlParts/modifyForm.html", null, function() {

            var idNumber = vid;
            var $updateForm = $("#modifyForm")
            $.getJSON("rest/getOneVehicle/" + idNumber, null, function(data) {
                console.log("wowowowow" + data);
                $updateForm.append("<input type='hidden' name = 'id' value='" + data._id.$oid + "'/><br>");
                $updateForm.append("Year <input type='text' name = 'year' value='" + data.year + "'/><br>");
                $updateForm.append("Model<input type='text' name = 'model' value='" + data.model + "'/><br>");
                $updateForm.append("Make<input type='text' name = 'make' value='" + data.make + "'/><br>");
                $updateForm.append("DriveType <input type='text' name = 'driveType' value='" + data.mechData.driveType + "'/><br>");
                $updateForm.append("Displacement <input type='text' name = 'displacment' value='" + data.mechData.displacment + "'/><br>");
                $updateForm.append("Cylinders <input type='text' name = 'cylinders' value='" + data.mechData.cylinders + "'/><br>");
                $updateForm.append("Trans Type<input type='text' name = 'transmissionType' value='" + data.mechData.transmissionType + "'/><br>");
                $updateForm.append("Fuel Type<input type='text' name = 'fuelType' value='" + data.mechData.fuelType + "'/><br>");
                $updateForm.append("City Mpg<input type='text' name = 'cityMpg' value='" + data.epaData.cityMpg + "'/><br>");
                $updateForm.append("HighwayMpg <input type='text' name = 'highwayMpg' value='" + data.epaData.highwayMpg + "'/><br>");
                $updateForm.append("Gas Tax<input type='text' name = 'hasGasTax' value='" + data.epaData.hasGasTax + "'/><br>");

                $updateForm.append("Emissions <input type='text' name = 'emissions' value='" + data.epaData.emissions + "'/><br>");
                //TODO change this so that it calculates on submission
                $updateForm.append("<input type='text' name = 'averageMpg' value='" + data.epaData.averageMpg + "'/><br>");
                $updateForm.append("<button id=\"submitModifiactions\">Submit Modification</button>");

                $("#submitModifiactions").on('click', function(event) {
                    event.preventDefault();
                    //TODO actual submission here
                    var $formValues = $updateForm.serializeArray();

                    var object = {}
                    $.each($formValues, function() {
                        object[this.name] = this.value;
                    });

                    var updateToSubmit = JSON.stringify(object);


                    $.ajax({
                        type: "put",
                        url: "rest/updateVehicle",
                        contentType: 'application/json; charset=utf-8',
                        data: updateToSubmit,
                        //dataType:"json"
                        dataType: "text"
                    }).done(function(data) {
                        console.log(data);
                        //nothing actually comes here
                    }).fail(function() {
                        console.log("update failed");
                    });
                });

            }, 'json');


        });
    };

    var doSearch = function() {
        // ajax call with a callback to load data

        var $formValues = $("#searchForm").serializeArray();

        for (var i = 0; i < $formValues.length; i++) {
            console.log($formValues[i]);
        }

        var object = {}
        $.each($formValues, function() {
            object[this.name] = this.value;
        });



        var serachData = JSON.stringify(object);

        $.ajax({
            type: "post",
            url: "rest/searchVehicles",
            contentType: 'application/json; charset=utf-8',
            data: serachData,
            //dataType:"json"
            dataType: "json"
        }).done(function(data) {
            console.log(data);
            //nothing actually comes here
            iterateSearchResults(data);
        }).fail(function() {
            console.log("update failed");
        });

    }

    var iterateSearchResults = function(data) {
        var searchResults = data;
        $resultBody.empty();
        for (var i = 0; i < searchResults.length; i++) {
            console.log(i);
            var result = $.parseJSON(searchResults[i]);
            var resultId = result._id.$oid;
            var year = result.year;
            var make = result.make;
            var model = result.model;
            console.log(resultId + " " + year + " " + make + " " + model);
            //			var mechData = "<div id="+resultId+"_MechData></div>";
            var mechDataButton = "<button class='vehicle_button' id='ShowMechData_" + resultId + "' vid='" + resultId + "'>Show Mech Data</button>";
            var epaDataButton = "<button  class='vehicle_button'  id='ShowEpaData_" + resultId + "' vid='" + resultId + "'>Show Epa Data</button>";
            var imageButton = "<button class='vehicle_button'  id='ShowImage_" + resultId + "' vid='" + resultId + "'>Show Image</button>";
            var modifyButton = "<button   class='vehicle_button'  id='Modify_" + resultId + "' vid='" + resultId + "'>Modify</button>";
            var deleteButton = "<button  class='vehicle_button'  id='Delete_" + resultId + "' vid='" + resultId + "'>Delete</button>";
            console.log(mechDataButton);
            var singleResultBody = "<div class='row center-text'>" +
            "<div class='col-xs-12 vehicleResultTitle' vid='" + resultId + "' id='singleResultBody_" + resultId + "'>" + year + "  " + make + "  " + model +"</br></div>" +
            "<div class='col-xs-12'>"+ mechDataButton + epaDataButton + imageButton + modifyButton + deleteButton + "<br></div>" +
            "<div class='col-xs-12 vehicleResultTitle center-text' vid='" + resultId + "' id='singleResultData_" + resultId + "'>"+"</br></div>" +
            						"</div>";
            $resultBody.append(singleResultBody);
            $modifyButton = $("#Modify_" + resultId);
            $modifyButton.on('click', function(event) {
                event.preventDefault();
                console.log("eventarg attribute stuff" + event.target.parentNode);
                var vidNumber = event.target.attributes[2].nodeValue;
                console.log('midify');
                modifyFormLoad(vidNumber);
            });
            $deleteButton = $("#Delete_" + resultId);
            $deleteButton.on('click', function(event) {
                event.preventDefault();
                console.log(event);
                var vidNumber = event.target.attributes[2].nodeValue;
                console.log('delete time');
                var idObject = {
                    id: vidNumber
                }

                var idToDelete = JSON.stringify(idObject);
                var $matchingResultBody = $("#singleResultData_" + vidNumber);
                $.ajax({
                    type: "delete",
                    url: "rest/deleteById/" + vidNumber,
                    contentType: 'application/json; charset=utf-8',
                    data: idToDelete,
                    //dataType:"json"
                    dataType: "json"
                }).done(function(data) {
                    console.log(data);
                    //nothing actually comes here
                    console.log("delteted");
                    console.log(event);
                    console.log(event.target);
                    console.log(event.target.parent());
                    event.target.parentNode.empty();
                    $matchingResultBody.parent().empty();
                }).fail(function(data) {
                    $matchingResultBody.parent().empty();
                    event.target.parentNode.empty();
                    console.log(data);
                    console.log("delete failed");
                });
            });
            var $singleResultBody = $("#singleResultData_" + resultId);
            //			$singeResultBody.append("<div id="+resultId+ ">"+year + "  " + make  + "  "  + model +"</div>");	
            var $mechDataButton = $("#ShowMechData_" + resultId); //this is only selecting the last one for some reason
            $mechDataButton.on("click", function(event) {
                console.log(event);
                var vidNumber = event.target.attributes[2].nodeValue;
                
                
                console.log("VID NUMBER: " + vidNumber);
                $.getJSON("rest/getMechData/" + vidNumber, null, function(data) {
                    console.log(data.mechData);
                    var mechData = data.mechData;
	                    //TODO append mech/epa data
	                    $("#ShowMechData_" + vidNumber).hide();
	                    var mechDiv = "<div class='col-xs-12 col-md-3 divData' id='mechDiv_" + vidNumber + "'></div>";
	                    var $matchingResultBody = $("#singleResultData_" + vidNumber);
	                    $matchingResultBody.append(mechDiv);
	                    var $mechDiv = $("#mechDiv_" + vidNumber);
	                    $mechDiv.append("<ul></ul>");
	                    var $mechDivUL = $mechDiv.children();
	                    $mechDivUL.append("<li>Fuel Type: " + mechData.fuelType + "</li>")
                    $mechDivUL.append("<li>Drive Type: " + mechData.driveType + "</li>")
                    $mechDivUL.append("<li>Transmission Type: " + mechData.transmissionType + "</li>")
                    $mechDivUL.append("<li>Displacement: " + mechData.displacment + "</li>")
                    $mechDivUL.append("<li>Cylinders: " + mechData.cylinders + "</li>")
                }, 'json');

            });
            var $epaDataButton = $("#ShowEpaData_" + resultId); //this is only selecting the last one for some reason
            $epaDataButton.on("click", function(event) {
                console.log(event);
                var vidNumber = event.target.attributes[2].nodeValue;
                console.log("VID NUMBER: " + vidNumber);
                $.getJSON("rest/getEpaData/" + vidNumber, null, function(data) {
                    console.log(data.epaData);
                    var epaData = data.epaData;
                    //TODO append mech/epa data
                    $("#ShowEpaData_" + vidNumber).hide();
                    var epaDiv = "<div class='col-xs-12 col-md-3 divData' id='epaDiv_" + vidNumber + "'></div>";
                    var $matchingResultBody = $("#singleResultData_" + vidNumber);
                    $matchingResultBody.append(epaDiv);
                    var $epaDiv = $("#epaDiv_" + vidNumber);
                    $epaDiv.append("<ul></ul>");
                    var $epaDivUL = $epaDiv.children();
                    $epaDivUL.append("<li>City MPG: " + epaData.cityMpg + "</li>")
                    $epaDivUL.append("<li>HighwayMpg: " + epaData.highwayMpg + "</li>")
                    $epaDivUL.append("<li>Average MPG: " + epaData.averageMpg + "</li>")
                    $epaDivUL.append("<li>Has Gas Tax: " + epaData.hasGasTax + "</li>")
                    $epaDivUL.append("<li>Emissions: " + epaData.emissions + "</li>")

                }, 'json');

            });

            var $imageButton = $("#ShowImage_" + resultId); //this is only selecting the last one for some reason
            $imageButton.on("click", function(event) {
                console.log(event);
                var vidNumber = event.target.attributes[2].nodeValue;
                console.log("VID NUMBER: " + vidNumber);
                $.getJSON("rest/getImage/" + vidNumber, null, function(data) {
                    console.log(data);
                    console.log(data.image);
                    var imageLink = data.imageLink;
                    //TODO append mech/epa data
                    $("#ShowImage_" + vidNumber).hide();
                    var imageDiv = "<div class='col-xs-12 col-md-3 divData' id='imageDiv_" + vidNumber + "'></div>";
                    var $matchingResultBody = $("#singleResultData_" + vidNumber);
                    $matchingResultBody.append(imageDiv);
                    var $imageDiv = $("#imageDiv_" + vidNumber);
                    $imageDiv.append("<img class='fitImage' src='" + imageLink + "' alt='" + vidNumber + "' />");


                }, 'text');

            });

            console.log($resultBody);
            //			$resultBody.load(function(){
            //				$("#ShowMechData_"+resultId).on("click",function(){
            //					//switch to show mech data
            //					console.log('show mech data' + resultId);
            //				});
            //				
            //			})

        }

    }


    $("#searchButton").on('click', function(event) {
        event.preventDefault();
        $mainDiv.empty();

        $resultBody.empty();

        $mainDiv.load("htmlParts/searchForm.html", null, function() {

        	//load in year selection
        	
        	var $yearSelect = $(".yearSelect");
        	
        	$yearSelect.append("<option value=''>Any</option>");
      
        	for(var year = 1980; year < 2020; year++){
        		$yearSelect.append("<option value='"+year+"'>"+year+"</option>");
        	}
        	
            //load in make list
            var $makeSelect = $("#makeSelect");
            
                       
            
            $.getJSON("rest/getMakeList", null, function(data) {
                var makeList = data;
                for (var i = 0; i < makeList.length; i++) {
                    var make = makeList[i]
                    console.log(make);
                    $makeSelect.append("<option value='" + make + "'>" + make + "</option>");

                };
                $makeSelect.on('change', function(event) {
                    event.preventDefault();
                    console.log("add event listener here");
                    var $makeSelect = $("#makeSelect :selected").text();
                    console.log($makeSelect);
                    $.getJSON("rest/getModelList/" + $makeSelect, null, function(data) {
                        var modelList = data;
                        console.log("modelssssssss");
                        console.log(data);
                        var $modelSelect = $("#modelSelect");
                        $modelSelect.empty();
                        $modelSelect.append("<option value='' value='' >Any</option>");
                        for (var i = 0; i < modelList.length; i++) {
                            var model = modelList[i]
                            console.log(model);
                            $modelSelect.append("<option  value='" + model + "'>" + model + "</option>");

                        };

                    }, 'json');
                });

            }, 'json');
            //			$makeSelect
            //load in model list when make is changed

            //load transmission types
            $.getJSON("rest/getTransmissionTypeList", null, function(data) {
                var transmissionList = data;
                console.log("trans");
                console.log(data);
                var $transmissionSelect = $("#transmissionSelect");
                $transmissionSelect.empty();
                $transmissionSelect.append("<option name='' value=''  >Any</option>");
                for (var i = 0; i < transmissionList.length; i++) {
                    var transmission = transmissionList[i]
                    console.log(transmission);
                    $transmissionSelect.append("<option  name='" + transmission + "'>" + transmission + "</option>");

                };
            }); //get trans
            //load drive types
            $.getJSON("rest/getDriveTypeList", null, function(data) {
            	var driveTypeList = data;
            	console.log("drive");
            	console.log(data);
            	var $driveSelect = $("#driveTypeSelect");
            	$driveSelect.empty();
            	$driveSelect.append("<option name='' value='' >Any</option>");
            	for (var i = 0; i < driveTypeList.length; i++) {
            		var drive = driveTypeList[i]
            		console.log(drive);
            		$driveSelect.append("<option  name='" + drive + "'>" + drive + "</option>");
            		
            	};
            }); //get trans

            
            
            //load fuel types
            $.getJSON("rest/getFuelTypeList", null, function(data) {
            	var fuelTypeList = data;
            	console.log("fuel");
            	console.log(data);
            	var $fuelSelect = $("#fuelTypeSelect");
            	$fuelSelect.empty();
            	$fuelSelect.append("<option name='' value='' >Any</option>");
            	for (var i = 0; i < fuelTypeList.length; i++) {
            		var fuel = fuelTypeList[i]
            		console.log(fuel);
            		$fuelSelect.append("<option  name='" + fuel + "'>" + fuel + "</option>");
            		
            	};
            }); //get trans
            
            
            
                $("#submitSearch").on('click', function(event) {
                    event.preventDefault();
                    console.log("search submitted");
                    doSearch();

                });
                $("#resetSearch").on('click', function(event) {
                    event.preventDefault();
                    var $searchForm = $("#searchForm");
                    $searchForm[0].reset();
                    console.log("resetting the search");
                    //				$mainDiv.empty();
                    $resultBody.empty();

                });

            }); //end main div load


        });



        $("#addButton").on('click', function(event) {
            event.preventDefault();
            $mainDiv.empty();
            $resultBody.empty();
            $mainDiv.load("htmlParts/addForm.html", null, function() {
                $("#submitNew").on('click', function(event) {
                    event.preventDefault();
                    console.log("new submitted");
                    addNew();

                });


            });
        }); //end add buton

        var addNew = function() {
                // ajax call w/ callback to load data

                var object = {};
                var $formValues = $("#addForm").serializeArray();
                $.each($formValues, function() {
                    object[this.name] = this.value;
                });

                var objectToPost = JSON.stringify(object);

                $.ajax({
                    type: "post",
                    url: "rest/testVehiclePost",
                    contentType: 'application/json; charset=utf-8',
                    data: objectToPost,
                    //dataType:"json"
                    dataType: "text"
                }).done(function(data) {
                    console.log("data");
                    $mainDiv.empty();
                    $resultBody.empty();
                    //nothing actually comes here
                    //TODO make this go to modify page directly
                }).fail(function() {
                    console.log('oops');
                });
            } //end add new

        //	
        //	$("#addButton").on('click', function(event) {
        //		event.preventDefault();
        //		$mainDiv.empty();
        //		$resultBody.empty();
        //		$mainDiv.load("htmlParts/addForm.html",null,function(){
        //			$("#submitNew").on('click', function(event) {
        //				event.preventDefault();
        //				console.log("new submitted");
        //				addNew();
        //				
        //			});
        //			
        //			
        //		});
        //	});


//    });
});