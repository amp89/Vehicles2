$(document).ready(function() {
	console.log("control.js loaded");

	var $mainDiv = $("#main");

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
		var $resultBody = $("#resultBody");
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
			
			var singleResultBody = "<div vid='"+resultId+"' id='singleResultBody_"+resultId+"'>"+year + "  " + make  + "  "  + model +"</div>";
			$resultBody.append(singleResultBody);
			var $singleResultBody = $("#singleResultBody_"+resultId);
//			$singeResultBody.append("<div id="+resultId+ ">"+year + "  " + make  + "  "  + model +"</div>");	
			$singleResultBody.append(mechDataButton);
			var $mechDataButton = $("#ShowMechData_"+resultId); //this is only selecting the last one for some reason
			$mechDataButton.on("click",function(){
				var $parent = $mechDataButton.parent()
				console.log("clickcillkj");
				console.log($parent);
				console.log("id clicked: " + $parent.attr("vid"));
			})
			
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
		$mainDiv.load("htmlParts/addForm.html");
		
		$("#submitNew").on('click', function(event) {
			event.preventDefault();
			console.log("new submitted");
			addNew();
			
		});
		
	});
	
	

});
