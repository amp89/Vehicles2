$(document).ready(function() {
	console.log("control.js loaded");

	var $mainDiv = $("#main");
	var $resultBody = $("#resultBody");
	// html forms
	var doSearch = function() {
		// ajax call with a callback to load data

		 var $formValues = $("#searchForm").serializeArray();
		 
		 for(var i = 0; i < $formValues.length; i++){
			 console.log($formValues[i]);
		 }
		 
		 var object = {}
		 $.each($formValues,function(){
			 object[this.name] = this.value;
		 });
		 
		 
		 
		 var serachData = JSON.stringify(object);
		 
		 $.ajax({
			 type:"post",
			 url:"rest/searchVehicles",
			 contentType : 'application/json; charset=utf-8',
			 data:serachData,
			 //dataType:"json"
			 dataType:"json"
		 }).done(function(data){
			 console.log(data);
			 //nothing actually comes here
			 iterateSearchResults(data);
		 }).fail(function(){
			 console.log("update failed");
		 });

	}
	
	var iterateSearchResults = function(data){
		var searchResults = data;
		$resultBody.empty();
		for(var i = 0; i < searchResults.length; i++){
			console.log(i);
			var result = $.parseJSON(searchResults[i]);
			var resultId = result._id.$oid;
			var year = result.year;
			var make = result.make;
			var model = result.model;
			console.log(resultId + " "+year+" "+make+" "+model);
//			var mechData = "<div id="+resultId+"_MechData></div>";
			var mechDataButton = "<button id='ShowMechData_"+resultId+"' vid='"+resultId+"'>Show Mech Data</button>";
			var epaDataButton = "<button id='ShowEpaData_"+resultId+"' vid='"+resultId+"'>Show Epa Data</button>";
			var imageButton = "<button id='ShowImage_"+resultId+"' vid='"+resultId+"'>Show Image</button>";
			console.log(mechDataButton);
			var singleResultBody = "<div vid='"+resultId+"' id='singleResultBody_"+resultId+"'>"+year + "  " + make  + "  "  + model + mechDataButton +epaDataButton + imageButton + "</div>";
			$resultBody.append(singleResultBody);
			var $singleResultBody = $("#singleResultBody_"+resultId);
//			$singeResultBody.append("<div id="+resultId+ ">"+year + "  " + make  + "  "  + model +"</div>");	
			var $mechDataButton = $("#ShowMechData_"+resultId); //this is only selecting the last one for some reason
			$mechDataButton.on("click",function(event){
				console.log(event);
				var vidNumber = event.target.attributes[1].nodeValue;
				console.log("VID NUMBER: " + vidNumber);
				$.getJSON("rest/getMechData/" + vidNumber ,null,function(data){
					 console.log(data.mechData);
					 var mechData = data.mechData;
					 //TODO append mech/epa data
					 $("#ShowMechData_"+vidNumber).hide();
					 var mechDiv = "<div id='mechDiv_"+vidNumber+"'></div>";
					 var $matchingResultBody =  $("#singleResultBody_"+vidNumber);
					 $matchingResultBody.append(mechDiv);
					 var $mechDiv = $("#mechDiv_"+vidNumber);
					 $mechDiv.append("<ul></ul>");
					 var $mechDivUL = $mechDiv.children();
					 $mechDivUL.append("<li>Fuel Type: "+mechData.fuelType+"</li>")
					 $mechDivUL.append("<li>Drive Type: "+mechData.driveType+"</li>")
					 $mechDivUL.append("<li>Transmission Type: "+mechData.transmissionType+"</li>")
					 $mechDivUL.append("<li>Displacement: "+mechData.displacement+"</li>")
					 $mechDivUL.append("<li>Cylinders: "+mechData.cylinders+"</li>")
				 },'json');
				
			});
			var $epaDataButton = $("#ShowEpaData_"+resultId); //this is only selecting the last one for some reason
			$epaDataButton.on("click",function(event){
				console.log(event);
				var vidNumber = event.target.attributes[1].nodeValue;
				console.log("VID NUMBER: " + vidNumber);
				$.getJSON("rest/getEpaData/" + vidNumber ,null,function(data){
					 console.log(data.epaData);
					 var epaData = data.epaData;
					 //TODO append mech/epa data
					 $("#ShowEpaData_"+vidNumber).hide();
					 var epaDiv = "<div id='epaDiv_"+vidNumber+"'></div>";
					 var $matchingResultBody =  $("#singleResultBody_"+vidNumber);
					 $matchingResultBody.append(epaDiv);
					 var $epaDiv = $("#epaDiv_"+vidNumber);
					 $epaDiv.append("<ul></ul>");
					 var $epaDivUL = $epaDiv.children();
					 $epaDivUL.append("<li>City MPG: "+epaData.cityMpg+"</li>")
					 $epaDivUL.append("<li>HighwayMpg: "+epaData.highwayMpg+"</li>")
					 $epaDivUL.append("<li>Average MPG: "+epaData.averageMpg+"</li>")
					 $epaDivUL.append("<li>Has Gas Tax: "+epaData.hasGasTax+"</li>")
					 $epaDivUL.append("<li>Emissions: "+epaData.emissions+"</li>")
	
				},'json');
				
			});
			
			var $imageButton = $("#ShowImage_"+resultId); //this is only selecting the last one for some reason
			$imageButton.on("click",function(event){
				console.log(event);
				var vidNumber = event.target.attributes[1].nodeValue;
				console.log("VID NUMBER: " + vidNumber);
				$.getJSON("rest/getImage/" + vidNumber ,null,function(data){
					console.log(data);
					 console.log(data.image);
					 var imageLink = data.imageLink;
					 //TODO append mech/epa data
					 $("#ShowImage_"+vidNumber).hide();
					 var imageDiv = "<div id='imageDiv_"+vidNumber+"'></div>";
					 var $matchingResultBody =  $("#singleResultBody_"+vidNumber);
					 $matchingResultBody.append(imageDiv);
					 var $imageDiv = $("#imageDiv_"+vidNumber);
					 $imageDiv.append("<img src='"+imageLink+"' alt='"+vidNumber+"' />");
					 
	
				},'text');
				
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

	var addNew = function() {
		// ajax call w/ callback to load data
	}

	$("#searchButton").on('click', function(event) {
		event.preventDefault();
		$mainDiv.empty();
		
		$resultBody.empty();
		
		$mainDiv.load("htmlParts/searchForm.html",null,function(){
			
			
			$("#submitSearch").on('click', function(event) {
				event.preventDefault();
				console.log("search submitted");
				doSearch();
				
			});
			
		});
		

	});
	
	
	$("#addButton").on('click', function(event) {
		event.preventDefault();
		$mainDiv.empty();
		$resultBody.empty();
		$mainDiv.load("htmlParts/addForm.html");
		
		$("#submitNew").on('click', function(event) {
			event.preventDefault();
			console.log("new submitted");
			addNew();
			
		});
		
	});
	
	

});
